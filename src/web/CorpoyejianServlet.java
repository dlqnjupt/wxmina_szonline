package web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.BywConsume;
import domain.Corpoyejian;
import service.CorpoService;

public class CorpoyejianServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		CorpoService service = new CorpoService();
		
		//前日夜间小流量
		Corpoyejian corpoYejian = null;
		//前十日夜间小流量
		List<Corpoyejian> lastTenYejianData =null;

		//页面显示数据
		ArrayList list = new ArrayList();
		
		
		try {
			corpoYejian = service.getCorpoYejian();
			lastTenYejianData = service.getLastTenData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(!lastTenYejianData.isEmpty()){
			ArrayList<String> days2 = getDays(lastTenYejianData);
		}
		
		list.add(corpoYejian);
		list.add(lastTenYejianData);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.getWriter().write(json);
		System.out.println(json);
		
	
	}
	
	public ArrayList<String> getDays(List<Corpoyejian> lastTenYejianData){
		ArrayList<String> a = new ArrayList<String>();
		for (Corpoyejian corpoyejian : lastTenYejianData) {
			String date_st = corpoyejian.getDate_st();
			String days = subString(date_st);
			a.add(days);
		}
		return a;
	}
	
	//获取Date_st的后两位，年月日中的日。
	public String subString(String st){
		return st.substring(8);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

