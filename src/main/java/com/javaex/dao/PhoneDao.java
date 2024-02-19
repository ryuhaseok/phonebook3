package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PersonVo;

public class PhoneDao {
	
	//필드
	
	//생성자
	
	//메소드 g/s
	
	//메소드 일반
	
	//전체 가져오기
	public List<PersonVo> personSelect() {
		
		List<PersonVo> personList = new ArrayList<PersonVo>();
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/phone_db";
			conn = DriverManager.getConnection(url, "phone", "phone");
		// 3. SQL문 준비 / 바인딩 / 실행
			String query = " select  person_id, "
							+ "		name, "
							+ "     hp, "
							+ "     company "
							+ " from person ";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			
			while(rs.next()) {
				int personID = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PersonVo personvo = new PersonVo(personID, name, hp, company);
				//리스트에 주소 추가
				personList.add(personvo);
			}
			
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
		try {
		if (rs != null) {
		rs.close();
		}
		if (pstmt != null) {
		pstmt.close();
		}
		if (conn != null) {
		conn.close();
		}
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		}
		return personList;
	}// 전체가져오기
	
	//등록
	public int personInsert(PersonVo personVo) {
		
		int count = -1;
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/phone_db";
			conn = DriverManager.getConnection(url, "phone", "phone");
		// 3. SQL문 준비 / 바인딩 / 실행
			String query = " insert into person "
					+ " values(null, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			
			count = pstmt.executeUpdate();
			
		// 4.결과처리
			System.out.println(count + "건 등록되었습니다.");
			
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
		try {
		if (rs != null) {
		rs.close();
		}
		if (pstmt != null) {
		pstmt.close();
		}
		if (conn != null) {
		conn.close();
		}
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		}
		return count;
	}// 등록
	
	//삭제
	
	//수정

}
