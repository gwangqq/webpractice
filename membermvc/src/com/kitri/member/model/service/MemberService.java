package com.kitri.member.model.service;

import java.util.List;

import com.kitri.member.model.*;

public interface MemberService {
	
	
//	id 중복 검사.....
	String idCheck(String id);		
//	시, 군 , 구, zipcodeDto가 들어간 List
	String zipSearch(String doro);
//	
	int registerMember(MemberDetailDto memberdetailDto);
//	memberDto
	MemberDto loginMember(String id, String pass);
	
	
//	회원 수정 눌렀을 때 정보 싹 나오는 기능
	MemberDetailDto getMember(String id);
//	회원 수정 하고나서 완료
	int modifyMember(MemberDetailDto memberdetailDto);//0: 참(사용가능함) | 1: 거짓(사용 불가)
//	회원 id 가져와서 int로 반환해야함
	int deleteMember(String id);//0: 참 | 1: 거짓
	
	
}
