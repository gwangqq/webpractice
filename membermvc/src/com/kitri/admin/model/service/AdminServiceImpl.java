package com.kitri.admin.model.service;

import java.util.*;

import com.kitri.admin.model.dao.AdminDaoImpl;
import com.kitri.member.model.MemberDetailDto;

public class AdminServiceImpl implements AdminService {

	private static AdminService adminService;

	static {
		adminService = new AdminServiceImpl();
	}

	private AdminServiceImpl() {
	}

	public static AdminService getAdminService() {
		return adminService;
	}

	@Override
	public String getMemList(String key, String word) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("word", word);

		List<MemberDetailDto> list = AdminDaoImpl.getAdminDao().getmemberList(map);
		
		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
		
		result += "<memberlist>\n";
		for(MemberDetailDto memberDetailDto : list) {
		result += "	<member>";
		result += "		<id>"+memberDetailDto.getId()+"</id>";
		result += "		<name>"+memberDetailDto.getName()+"</name>";
		result += "		<email>"+memberDetailDto.getEmailid()+"@"+memberDetailDto.getEmaildomain()+"</email>";
		result += "		<tel>"+memberDetailDto.getTel1()+"-"+ memberDetailDto.getTel2()+"-"+memberDetailDto.getTel3()+"</tel>";
		result += "		<address><![CDATA["+memberDetailDto.getZipcode()+ " "+ memberDetailDto.getAddress()+" " + memberDetailDto.getAddressdetail()+"]]></address>";
		result += "		<joindate>"+memberDetailDto+"</joindate>";
		result += "	</member>";
		
		list.add(memberDetailDto);
		}
		result += "</memberlist>";
		System.out.println(result);
		return result;
	}

}