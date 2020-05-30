package web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import domain.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.BywszjcService;

public class BywszjcServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String date = request.getParameter("date");
		
		System.out.println(date);
		//原水水质监测
		Bywszjc yuanshui = null;
		//出厂水质监测
		Bywszjc chuchang = null;
		//页面显示数据
		ArrayList<Bywszjc> diaplayData = new ArrayList<Bywszjc>();
		
		BywszjcService service = new BywszjcService();
		try {
			yuanshui = service.getYuanshui(date);
			chuchang =service.getChuchang(date);
		} catch (ParseException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		diaplayData.add(yuanshui);
		
		Gson gson = new Gson();
		String json = gson.toJson(diaplayData);
		response.getWriter().write(json);
		
		System.out.println(json);
		//System.out.println(diaplayData.isEmpty());
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

