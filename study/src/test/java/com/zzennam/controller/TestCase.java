package com.zzennam.controller;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.saemoonango.domain.MemberDetailVO;
import com.saemoonango.persistence.LocationDAO;
import com.saemoonango.persistence.MemberDetailDAOImpl;
import com.saemoonango.persistence.QuestionDAO;
import com.saemoonango.service.MemberDetailService;
import com.saemoonango.service.MemberServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class TestCase {

	@Inject
	private QuestionDAO dao;

	@Autowired
	private LocationDAO ldao;

	@Inject
	private MemberDetailService mdDao;
	@Inject
	private MemberDetailDAOImpl mmdao;

	@Inject
	private MemberServiceImpl mService;

	@Test
	public void memberList() throws Exception {
		String id = "4";
		System.out.println(mService.read(id));
	}

	@Test
	public void read() throws Exception {
		System.out.println(dao.read(1));
		System.out.println("Success");
	}

	@Test
	public void read2() throws Exception {
		System.out.println(ldao.read());
		System.out.println("Success");
		fail("Not yet implemented");
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void memberDetailInsertTest() throws Exception {
		MemberDetailVO vo = new MemberDetailVO();
		vo.setGetPoint(99);
		vo.setId("id5");
		vo.setQno(4);
		System.out.println(vo.toString());
		mdDao.insert(vo);

	}

	@Test
	public void memberDetailCertificateTest() throws Exception {
		MemberDetailVO vo = new MemberDetailVO();
		vo.setId("id1");
		vo.setQno(5);
		// 있으면 true 없으면 false
		System.out.println(mdDao.certificate(vo));
	}

	@Inject
	private MemberServiceImpl msi;

	@Test
	public void memberTotalPoint() {
		try {
			msi.totalPoint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fail...");
		}
	}
}
