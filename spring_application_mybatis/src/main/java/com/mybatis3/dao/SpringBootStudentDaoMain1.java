package com.mybatis3.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.mybatis3.basic.SpringBootMyBatisFlowMain;

@SpringBootApplication
public class SpringBootStudentDaoMain1 {
	public static void main(String[] args) {
		ApplicationContext applicationContext=
				SpringApplication.run(SpringBootStudentDaoMain1.class, args);
		StudentDao studentDao = (StudentDao)applicationContext.getBean(StudentDao.class);
		
		System.out.println("---------findStudentById-----------------------------");
		System.out.println(studentDao.findStudentById(1));
		System.out.println("---------findAllStudents-----------------------------");
		System.out.println(studentDao.findAllStudents());
		System.out.println("---------findStudentNameById-------------------------");
		System.out.println("---------findStudentNameList-------------------------");
		System.out.println("---------findStudentByIdResultMap--------------------");
		System.out.println("---------findAllStudentsResultMap--------------------");
		
		
	}
}