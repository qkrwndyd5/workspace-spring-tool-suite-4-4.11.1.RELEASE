package com.mybatis3.dao.mapper;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.mybatis3.domain.Tutor;
@Mapper
public interface TutorMapper {
	/*
	 <select id="findTutorByIdWithCourses"
			parameterType="int"
			resultMap="tutorWithCoursesResultMap">
			 select t.tutor_id,t.name as tutor_name,email,
			 		course_id,c.name as course_name,description,
			 		start_date,end_date
			 from tutors t  
			 join courses c 
			 on t.tutor_id = c.tutor_id 
			 where t.tutor_id=#{tutorId}
	 </select> 
	 */
	@Results(id = "findTutorByIdWithCourses")
	@Select("select t.tutor_id,t.name as tutor_name,email,"
			+ "			 		course_id,c.name as course_name,description,"
			+ "			 		start_date,end_date"
			+ "			from tutors t  "
			+ "			join courses c "
			+ "			on t.tutor_id = c.tutor_id "
			+ "			where t.tutor_id=#{tutorId}")
	public Tutor findTutorByIdWithCourses(Integer tutorId);
}
