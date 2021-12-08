package com.example.msorder.data;

import com.example.msorder.models.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonDAO {

    private final Map<String, Person> personMap = new ConcurrentHashMap<>();
    private final List<Person> list = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong counter = new AtomicLong();

    public void add(final Person personParam) {
        this.counter.incrementAndGet();
        this.personMap.put(personParam.getNumber(),
                personParam);
    }

    public Person getPerson(final String numberParam) {
        return this.personMap.get(numberParam);
    }

    public List<Person> getAll() {
        return new ArrayList<>(this.personMap.values());
    }
}
