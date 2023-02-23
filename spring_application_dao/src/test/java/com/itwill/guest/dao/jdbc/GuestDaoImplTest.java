package com.itwill.guest.dao.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.guest.dao.jdbc.Guest;
import com.itwill.guest.dao.jdbc.GuestDao;

@SpringBootTest
class GuestDaoImplTest {

	@Autowired
	GuestDao guestDao;
	
	@Disabled
	@Test
	void testSelectAll() throws Exception {
		assertNotEquals(guestDao.selectAll(), null);	
		assertNotEquals(guestDao.selectAll().size(), 0);
		
		assertNotNull(guestDao.selectByNo(196));
		assertEquals(guestDao.selectByNo(196).getGuest_no(),196);
		
		System.out.println(guestDao.selectAll().size());
		System.out.println(guestDao.selectAll());
	}
	
	@Test
	void testSelectByNo() throws Exception {
		assertNull(guestDao.selectByNo(19612484));	
		assertNotNull(guestDao.selectByNo(196));
		assertEquals(guestDao.selectByNo(196).getGuest_name(),"요셉짱");
		System.out.println(guestDao.selectByNo(196));
	}
	
	@Test
	void testInsertGuest() throws Exception {
		Guest inserGuest = 
				new Guest(0, "테스트", null, "test1@naver.com", "test1.com", "타타이틀", "컨컨텐트");
		assertEquals(guestDao.insertGuest(inserGuest), 1);
	}
	
	@Test
	void testUpdateGuest() throws Exception {
		Guest updateGuest = 
				new Guest(66, "테스트수정", null, "testchange1@naver.com", "testchange1.com", "타타이틀수정", "컨컨텐트수정");
		int updateRowCount = guestDao.updateGuest(updateGuest);
		if(updateRowCount!=1) {
			fail("update 실패");
		}
	}

}
