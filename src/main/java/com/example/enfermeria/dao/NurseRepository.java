package com.example.enfermeria.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.enfermeria.entity.Nurse;

public interface NurseRepository extends CrudRepository<Nurse, Integer> {

	Optional<Nurse> findByNameIgnoreCase(String name);

	Optional<Nurse> findByUser(String user);
}