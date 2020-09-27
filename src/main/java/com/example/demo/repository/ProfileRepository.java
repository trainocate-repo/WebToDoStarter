package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>{

	Optional<Profile> findById(Integer id);

}
