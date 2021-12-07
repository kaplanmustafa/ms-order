package com.example.msorder.rest;

import com.example.msorder.models.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/play")
public class MyRestPlayground {

    // En fazla 1 veya 2 parametre al, daha fazla olursa request body kullan
    @GetMapping("/hello")
    public String hello(@RequestParam("name") final String name, @RequestParam("surname") final String surname) {
        return "Hello GET " + name + " " + surname;
    }

    // En fazla 1 veya 2 path parametresi al, daha fazla olursa request body kullan
    @GetMapping("/hello2/{name}/{surname}")
    public String hello2(@PathVariable("name") final String name, @PathVariable("surname") final String surname) {
        return "Hello GET " + name + " " + surname;
    }

    // API dizaynında header kullanılmaz! --> Bu şekilde kullanma!
    @GetMapping("/hello3")
    public String hello3(@RequestHeader("name") final String name, @RequestHeader("surname") final String surname) {
        return "Hello GET " + name + " " + surname;
    }

    @PostMapping("/hello4")
    public String hello4(@RequestBody Person person) {
        return "Hello GET " + person;
    }

    @PostMapping(value = "/hello5", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
            })
    public Person hello5(@RequestBody final Person person) {
        person.setName("ali");
        return person;
    }

    @PostMapping(value = "/hello6")
    public ResponseEntity<Person> hello6(@RequestBody final Person person) {
        person.setName("ali");
        //        List<String> asListLoc = Arrays.asList("osman",
        //                                               "ali");
        //        asListLoc.stream()
        //                 .sorted()
        //                 .filter(s -> s.length() > 3)
        //                 .forEach(System.out::println);
        System.out.println("c1");
        return ResponseEntity.status(254)
                .header("X-text",
                        "test edildi")
                .header("Content-Type",
                        "application/json")
                .body(person);
    }


    //Yapma
    @PostMapping(value = "/hello7/{operasyon}")
    public ResponseEntity<?> hello7(@PathVariable("operasyon") final String op, final HttpServletRequest hsr) throws Exception {
        switch (op) {
            case "add":
                ObjectMapper om = new ObjectMapper();
                Person person = om.readValue(hsr.getInputStream(),
                        Person.class);
                person.setName("ali");
                return ResponseEntity.status(205)
                        .header("X-text",
                                "test edildi")
                        .body(person);
            case "delete":
                return ResponseEntity.status(205)
                        .header("X-text",
                                "test edildi")
                        .body("Deleted");
            default:
                return ResponseEntity.status(205)
                        .header("X-text",
                                "ok")
                        .body("Deleted");
        }
    }
}
