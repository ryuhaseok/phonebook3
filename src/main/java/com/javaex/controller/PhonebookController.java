package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.PersonVo;


@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {
	//필드
	private static final long serialVersionUID = 1L;
       
	//생성자 - 디폴트생성자 사용
	
	//메소드 -gs
	
	//메소드 일반
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("PhonebookController.goget()");
		
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("wform".equals(action)) {
			System.out.println("wform:등록폼");

			/*
			//jsp에게 html 그리기 응답하라
			RequestDispatcher rd = request.getRequestDispatcher("/writeForm.jsp");
			rd.forward(request, response);
			*/
			
			WebUtil.forward(request, response, "/writeForm.jsp");
			
			/*
			foward("/writeForm.jsp", request, response);
			redirect("/phonebook3/pbc?action=list", request, response);
			*/
			
		} else if("insert".equals(action)) {
			System.out.println("insert:등록");
			
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			// vo로 묶기
			PersonVo personvo = new PersonVo(name, hp, company);
			System.out.println(personvo.toString());
			
			PhoneDao phonedao = new PhoneDao();
			
			//db에 저장
			phonedao.personInsert(personvo);
			
			/*
			http://localhost:8080/phonebook3/pbc?action=list 엔터 효과를 낸다
			리다이렉트
			*/
			
			//response.sendRedirect("http://localhost:8080/phonebook3/pbc?action=list");
			WebUtil.redirect(request, response, "/phonebook3/pbc?action=list");
			/*
			// db에서 전체 데이터 가져오기
			List<PersonVo> personList = phonedao.personSelect();
			System.out.println(personList);
			
			request.setAttribute("personList", personList);
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
			*/
		
		} else if("delete".equals(action)) {
			System.out.println("delete:삭제");
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println(no);
			
			//db 사용
			PhoneDao phoneDao = new PhoneDao();
			
			//삭제
			phoneDao.personDelete(no);
			
			//리다이렉트
			//response.sendRedirect("/phonebook3/pbc?action=list");
			
			//WebUtil webUtil = new WebUtil();
			WebUtil.redirect(request, response, "/phonebook3/pbc?action=list");
			
		} else if("updateForm".equals(action)) {
			System.out.println("update:수정폼");
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println(no);
			request.setAttribute("no", no);
			
			PersonVo personVo = new PersonVo(no);
			PhoneDao phoneDao = new PhoneDao();
			PersonVo personVoAll = phoneDao.personSelectAll(personVo);
			System.out.println(personVoAll);
			
			
			request.setAttribute("personVoAll",personVoAll);
			
			/*
			//jsp에게 html 그리기 응답하라
			RequestDispatcher rd = request.getRequestDispatcher("/updateForm.jsp");
			rd.forward(request, response);
			*/
			
			WebUtil.forward(request, response, "/updateForm.jsp");

		} else if("update".equals(action)) {
			System.out.println("update:수정");
			
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			int no = Integer.parseInt(request.getParameter("no"));
			
			// vo로 묶기
			PersonVo personvo = new PersonVo(no, name, hp, company);
			System.out.println(personvo.toString());
			
			//db 사용
			PhoneDao phonedao = new PhoneDao();
			
			//수정
			phonedao.personUpdate(personvo);
			
			//리다이렉트
			//response.sendRedirect("http://localhost:8080/phonebook3/pbc?action=list");
			
			WebUtil.redirect(request, response, "/phonebook3/pbc?action=list");
			
		} else {
			System.out.println("list:리스트");
			
			//db사용
			PhoneDao phoneDao = new PhoneDao();
			
			//리스트 가져오기
			List<PersonVo> personList = phoneDao.personSelect();
			System.out.println(personList);
			
			//데이터 담기 포워드
			request.setAttribute("personList", personList);
			
			/*
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
			*/
			
			//WebUtil webUtil = new WebUtil();
			WebUtil.forward(request, response, "/list.jsp");
		}
		
	}//


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
