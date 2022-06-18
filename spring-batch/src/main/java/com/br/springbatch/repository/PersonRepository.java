package com.br.springbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.springbatch.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

}
