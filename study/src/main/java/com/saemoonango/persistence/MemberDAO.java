package com.saemoonango.persistence;

import java.util.List;

import com.saemoonango.domain.MemberVO;

public interface MemberDAO {
	//public void insert(MemberDetailVO vo) throws Exception;
	
	//public List<MenuVO> select(Integer mno)throws Exception;
	
	//public void update(LocationVO vo)throws Exception;
	
	public void myLocation(MemberVO vo)throws Exception;

	
	//public void delete(LocationVO vo) throws Exception;
	
	public List<MemberVO> read(String id) throws Exception;
	
	//public boolean certificate(MemberDetailVO vo) throws Exception;
	
	public void totalPoint() throws Exception;
	
	public List<MemberVO> memberList() throws Exception;
	
}
