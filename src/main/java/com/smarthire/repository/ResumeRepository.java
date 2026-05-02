package com.smarthire.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smarthire.entity.Resume;
import com.smarthire.entity.User;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {

	Optional<Resume> findByUser(User user);
}