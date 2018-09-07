package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.Stu;

public interface StuDao extends JpaRepository<Stu, Long> {

	Stu findAllById(Long id);

	List<Stu> findByNameAndPwd(String name, String pwd);
	
	//分页
	@Query(value="select * from student",
			countQuery="select count(*) from student",
			nativeQuery=true)
	Page<Stu> fylist(Pageable pageable);

}
