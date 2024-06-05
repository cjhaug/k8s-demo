package com.brainbulgaria.cloud.spring.kubernetes.demo.api.greeting.v1.boundary;

import com.brainbulgaria.cloud.spring.kubernetes.demo.api.greeting.v1.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {
}
