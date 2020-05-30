package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.Bywscsj;
import service.BywsltjService;

public class BywscsjServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		BywsltjService service = new BywsltjService();
		
		//白洋湾水厂生产数据
		Bywscsj bywscsjData = null;
		
		try {
			bywscsjData = service.getBywscsjData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(bywscsjData);
		
		response.getWriter().write(json);
		
		System.out.println("访问成功");
		System.out.println(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

