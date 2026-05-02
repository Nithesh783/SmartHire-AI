package com.smarthire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smarthire.entity.Job;
import com.smarthire.entity.User;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

	List<Job> findByRecruiter(User recruiter);
}