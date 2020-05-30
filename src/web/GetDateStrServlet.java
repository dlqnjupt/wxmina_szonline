package web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import utils.GetDateStrUtils;

public class GetDateStrServlet extends HttpServlet {
	
	int visits = 0;

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd");
		SimpleDateFormat sf2 = new SimpleDateFormat("HH:mm:ss");
		String datString = sf.format(date)+" "+sf2.format(date);
		
		
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int i = this.visits++;
		
		
		GetDateStrUtils utils = new GetDateStrUtils();
		HashMap<String, String> allBywDateStr = utils.getAlllBywDateStr();
		HashMap<String, String> allCorpoDateStr = utils.getAllCorpoDateStr();
		
		ArrayList list = new ArrayList();
		
		list.add(allCorpoDateStr);
		list.add(allBywDateStr);
		
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.getWriter().write(json);
		System.out.println("这是第："+i+"次访问！"+datString);
		System.out.println(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

