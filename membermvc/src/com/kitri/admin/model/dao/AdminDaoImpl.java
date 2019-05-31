package com.kitri.admin.model.dao;

import java.sql.*;
import java.util.*;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.util.DBClose;
import com.kitri.util.DBConnection;

public class AdminDaoImpl implements AdminDao{

   private static AdminDao adminDao;
   
   static {
      adminDao = new AdminDaoImpl();
   }
   
   private AdminDaoImpl(){}
   
   public static AdminDao getAdminDao() {
      return adminDao;
   }
   
   @Override
   public List<MemberDetailDto> getmemberList(Map<String, String> map) {
      
      List<MemberDetailDto> list = new ArrayList<MemberDetailDto>();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      try {
         conn = DBConnection.makeConnection();
         StringBuffer sql = new StringBuffer();
         sql.append("select m.name, m.id, emailid, m.emaildomain, m.joindate, \n");
         sql.append(" d.tel1, d.tel2, d.tel3, d.zipcode, d.address, d.address_detail \n");
         sql.append("from member m, member_detail d \n");
         sql.append("where m.id = d.id\n");
         String key = map.get("key");
         String word = map.get("word");
         if (word != null && !word.isEmpty()) {
            if ("id".equals(key)) {
               sql.append("and m.id = ? \n"); // ?는 값만 넣을수 있음 컬럼 안뎀.
            } else {
               sql.append("add m." + key + "like '%'||?||'%'\n");
            }
         }
         
         
         pstmt = conn.prepareStatement(sql.toString());
         if (word != null && !word.isEmpty()) {
            pstmt.setString(1, word);
         }
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            MemberDetailDto memberdetailDto = new MemberDetailDto(); // 안으로 들어왔다는것은 일치한다는것음
            memberdetailDto.setName(rs.getString("name"));
            // 누구것이냐가 중요한게 아니고 컬럼이름으로 가져감.
            memberdetailDto.setId(rs.getString("id"));
            memberdetailDto.setEmailid(rs.getString("emailid"));
            memberdetailDto.setEmaildomain(rs.getString("emaildomain"));
            memberdetailDto.setJoindate(rs.getString("joindate"));
            memberdetailDto.setTel1(rs.getString("tel1"));
            memberdetailDto.setTel2(rs.getString("tel2"));
            memberdetailDto.setTel3(rs.getString("tel3"));
            memberdetailDto.setZipcode(rs.getString("zipcode"));
            memberdetailDto.setAddress(rs.getString("address"));
            memberdetailDto.setAddressdetail(rs.getString("address_detail"));
            list.add(memberdetailDto);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DBClose.close(conn, pstmt, rs);
      }
      
      
      return list;
   }

}