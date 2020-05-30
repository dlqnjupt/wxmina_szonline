package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.Corpogsl;
import domain.Corpoyejian;
import service.CorpoService;

public class Echarts_GongshuiServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String yearMonth = request.getParameter("yearMonth");
		
		CorpoService service = new CorpoService();
		//该月每日供水量
		List<Corpogsl> MonthGsl = null;
		
		// Echarts数据
		ArrayList<String> days = null;
		ArrayList allData =null;
		// 页面显示数据
		ArrayList list = new ArrayList();
		
		
		try {
			MonthGsl = service.getMonthgsl(yearMonth);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (!MonthGsl.isEmpty()) {
			days = getDays(MonthGsl);
			allData = getDatas(MonthGsl);
		}
		list.add(days);
		list.add(allData);

		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.getWriter().write(json);
		System.out.println(json);
		
	}
	
	
	//获取Day
	public ArrayList<String> getDays(List<Corpogsl> lastTenYejianData) {
		ArrayList<String> a = new ArrayList<String>();
		for (Corpogsl corpoyejian : lastTenYejianData) {
			String date_st = corpoyejian.getDate_st();
			String days = subString(date_st);
			a.add(days);
		}
		return a;
	}
	
	// 获取Date_st的后两位，年月日中的日。
	public String subString(String st) {
		return st.substring(8);
	}
	
	//获取所有数据
	public ArrayList getDatas(List<Corpogsl> lastTenYejianData){
		ArrayList allData = new ArrayList();
		
		ArrayList<Float> byw = new ArrayList<Float>();
		ArrayList<Float> xc = new ArrayList<Float>();
		ArrayList<Float> xj = new ArrayList<Float>();
		ArrayList<Float> sum = new ArrayList<Float>();


		for (Corpogsl corpoyejian : lastTenYejianData) {
			float byw2 = corpoyejian.getByw();
			float xc2 = corpoyejian.getXc();
			float xj2 = corpoyejian.getXj();
			float sum2 = corpoyejian.getSum();
			byw.add(byw2);
			xc.add(xc2);
			xj.add(xj2);
			sum.add(sum2);
			

		}


		allData.add(byw);//0:白洋湾
		allData.add(xc);//1:相城
		allData.add(xj);//2:胥江
		allData.add(sum);//3:累计

		
		return allData;
		
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

