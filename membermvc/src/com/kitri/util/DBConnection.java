package com.kitri.util;

import java.sql.*;

public class DBConnection {
	
	static {
		try {
			Class.forName(SiteConstance.DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection makeConnection() throws SQLException {
		return DriverManager.getConnection(SiteConstance.DB_URL, SiteConstance.DB_USERNAME, SiteConstance.DB_PASSWORD);
	}
	
}
//생성자는 new 호출 할 때 memory에 올라간다
//A a = new A(); --> static은 A a1; A라고 선언할 때 바로 호출 된다 static블록 초기화를 사용하는 이유는 최초에 한번만 호출하면 된다.

