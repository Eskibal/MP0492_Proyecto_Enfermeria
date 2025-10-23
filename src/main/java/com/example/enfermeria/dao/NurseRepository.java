package com.example.enfermeria.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.enfermeria.entity.Nurse;

public interface NurseRepository extends CrudRepository<Nurse, Integer> {

}
