package com.example.msorder.order.integrations;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Configuration
public class ClientConfig {

    private static final Logger logger = LoggerFactory.getLogger(ClientConfig.class);

    public static class MyRestClientErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(final ClientHttpResponse responseParam) throws IOException {
            return responseParam.getStatusCode().is4xxClientError();
        }

        @Override
        public void handleError(final ClientHttpResponse responseParam) throws IOException {
            if (ClientConfig.logger.isInfoEnabled()) {
                ClientConfig.logger.info("[ClientConfig.MyRestClientErrorHandler][handleError]-> Error Status code : " + responseParam.getStatusCode());
            }
        }
    }

    public static class MyRestClientInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(final HttpRequest requestParam, final byte[] bodyParam, final ClientHttpRequestExecution executionParam) throws IOException {
            long delta = System.nanoTime();
            HttpHeaders headersLoc = requestParam.getHeaders();
            headersLoc.add("X-Origin", "restaurant.subdomain.order.order");
            ClientHttpResponse executeLoc = executionParam.execute(requestParam, bodyParam);

            if (ClientConfig.logger.isInfoEnabled()) {
                ClientConfig.logger.info("[ClientConfig.MyRestClientInterceptor][intercept]-> Call Delta : " + (System.nanoTime() - delta));
            }

            return executeLoc;
        }
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplateBuilder builderLoc = this.restTemplateBuilder();
        RestTemplate rt = builderLoc.build();
        List<ClientHttpRequestInterceptor> interceptorsLoc = rt.getInterceptors();

        if (CollectionUtils.isEmpty(interceptorsLoc)) {
            interceptorsLoc = new ArrayList<>();
        }

        interceptorsLoc.add(new MyRestClientInterceptor());
        rt.setInterceptors(interceptorsLoc);

        return rt;
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        RestTemplateBuilder builderLoc = new RestTemplateBuilder();
        builderLoc.requestFactory(this::clientHttpRequestFactory);
        builderLoc.errorHandler(new MyRestClientErrorHandler());
        builderLoc.interceptors(new MyRestClientInterceptor());
        return builderLoc;
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(this.closeableHttpClient());
        return factory;
    }

    public CloseableHttpClient closeableHttpClient() {
        RequestConfig configLoc = RequestConfig.custom()
                .setConnectTimeout(1000)
                .setSocketTimeout(5000)
                .setConnectionRequestTimeout(2000)
                .build();

        return HttpClients.custom()
                .setDefaultRequestConfig(configLoc)
                .setKeepAliveStrategy(this.connectionKeepAliveStrategy())
                .setConnectionManager(this.poolingHttpClientConnectionManager())
                .build();
    }

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager pccm = new PoolingHttpClientConnectionManager();
        pccm.setMaxTotal(200);
        pccm.setDefaultMaxPerRoute(50);
        HttpRoute httpRouteLoc = new HttpRoute(HttpHost.create("http://127.0.0.1:8080"));
        pccm.setMaxPerRoute(httpRouteLoc, 100);
        return pccm;
    }

    @Scheduled(fixedDelay = 60_000)
    public void closeIdleConnecrtions() {
        PoolingHttpClientConnectionManager pool = this.poolingHttpClientConnectionManager();
        pool.closeExpiredConnections();
        pool.closeIdleConnections(60, TimeUnit.SECONDS);
    }

    @Bean
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return ClientConfig::getKeepAliveDurationProxy;
    }

    public static long getKeepAliveDurationProxy(final HttpResponse r,
                                                 final HttpContext c) {
        try {
            Header[] allHeadersLoc = r.getAllHeaders();
            for (Header headerLoc : allHeadersLoc) {
                String nameLoc = headerLoc.getName();

                if ("Keep-Alive".equals(nameLoc)) {
                    String[] splitLoc = headerLoc.getValue().split("=");
                    if (splitLoc[0].equals("timeout")) {
                        return Long.parseLong(splitLoc[1]);
                    }
                }
            }
        } catch (Exception eLoc) {
            ClientConfig.logger.error("[ClientConfig][connectionKeepAliveStrategy]-> *Error* : "
                    + eLoc.getMessage(), eLoc);
        }
        return 60_000;

    }
}
