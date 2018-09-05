package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Stu;

public interface StuDao extends JpaRepository<Stu, Long> {

	Stu findAllById(Long id);

	List<Stu> findByNameAndPwd(String name, String pwd);

}
