package com.kitri.admin.model.dao;

import java.util.List;
import java.util.Map;

import com.kitri.member.model.MemberDetailDto;

public interface AdminDao {
   
   public List<MemberDetailDto> getmemberList(Map<String, String> map);

}