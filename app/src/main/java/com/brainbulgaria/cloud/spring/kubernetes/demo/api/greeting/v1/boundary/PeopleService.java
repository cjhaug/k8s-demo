package com.brainbulgaria.cloud.spring.kubernetes.demo.api.greeting.v1.boundary;

import com.brainbulgaria.cloud.spring.kubernetes.demo.api.greeting.v1.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PeopleService implements IPeopleService {
    @Autowired
    public PeopleRepository peopleRepository;



    @Override
    public List<Person> findByName(String name) {
        return peopleRepository.findAll().stream().filter(p->p.getName().equals(name)).toList();
    }

    @Override
    public Optional<Person> findById(Long id) {
        return peopleRepository.findById(id);
    }

    @Override
    public void addPerson(Person person) {
        peopleRepository.save(person);
    }
}
