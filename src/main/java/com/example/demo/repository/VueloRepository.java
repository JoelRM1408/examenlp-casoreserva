package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Vuelo;
@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Long>{
	
}
