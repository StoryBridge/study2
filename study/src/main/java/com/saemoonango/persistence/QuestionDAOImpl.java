package com.saemoonango.persistence;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.saemoonango.domain.LocationVO;
import com.saemoonango.domain.QuestionVO;

@Repository
public class QuestionDAOImpl implements QuestionDAO {

	@Inject
	private SqlSessionTemplate sqlsession;

	@Override
	public void insert(QuestionVO vo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(QuestionVO vo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(QuestionVO vo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<QuestionVO> read(int lno) throws Exception {
		return sqlsession.selectList("com.zzennam.persistence.QuestionMapper.read", lno);

	}

	@Override
	public void getPoint(int point) throws Exception {
		//sqlsession.insert("org.food.persistence.MemberMapper.insert", point);
		
	}

}
