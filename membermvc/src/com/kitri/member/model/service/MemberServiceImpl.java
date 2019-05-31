package com.kitri.member.model.service;

import java.util.*;

import com.kitri.member.model.*;
import com.kitri.member.model.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService {

	private static MemberService memberSerivce;
	
	static {
		memberSerivce = new MemberServiceImpl();
	}
	//객체를 딱 한번만 만들어내는 개발 방법. single-tone-pattern
	private MemberServiceImpl() {}
	
	public static MemberService getMemberSerivce() {
		return memberSerivce;
	}


	@Override
	public String idCheck(String id) {
		int cnt = MemberDaoImpl.getMemberDaoImpl().idCheck(id);
		System.out.println("cnt : " + cnt);
		String result = "";
		result += "<idcount>\n";
		result += "	<cnt>"+ cnt +"</cnt>\n";
		result += "</idcount>";
//		System.out.println(result);
		return result;
	}

	@Override
	public String zipSearch(String doro) {
		String result = "";
		List<ZipcodeDto> list = MemberDaoImpl.getMemberDaoImpl().zipSearch(doro);
		result += "<ziplist>\n";
		for(ZipcodeDto zipDto : list) {
			
		result += "	<zip>\n";
		result += "		<zipcode>"+zipDto.getZipcode()+"</zipcode>\n";
		result += "		<address><![CDATA["+zipDto.getSido() +" "+ zipDto.getGugun()+ " "+zipDto.getUpmyon()+" "+ zipDto.getDoro()+" "+zipDto.getSigugunBuildingName()+" "+zipDto.getBuildingNumber()+"]]> </address>\n";
		result += "	</zip>\n";
		}
		System.out.println(result);
		result += "</ziplist>";
		return result;
	}

	@Override
	public int registerMember(MemberDetailDto memberdetailDto) {
		return MemberDaoImpl.getMemberDaoImpl().registerMember(memberdetailDto);
	}


	@Override
	public MemberDetailDto getMember(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyMember(MemberDetailDto memberdetailDto) {
		return 0;
	}

	@Override
	public int deleteMember(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberDto loginMember(String id, String pass) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		map.put("userpwd", pass);
		return MemberDaoImpl.getMemberDaoImpl().loginMember(map);
	}
}
