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

import domain.Corpoyejian;
import service.CorpoService;

public class Echars_YejianServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		CorpoService service = new CorpoService();

		List<Corpoyejian> lastTenYejianData = null;
		// Echarts数据
		ArrayList<String> days = null;
		ArrayList allData =null;

		// 页面显示数据
		ArrayList list = new ArrayList();

		try {
			lastTenYejianData = service.getLastTenDataOfEcharts();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (!lastTenYejianData.isEmpty()) {
			days = getDays(lastTenYejianData);
			allData = getDatas(lastTenYejianData);
		}

		list.add(days);
		list.add(allData);

		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.getWriter().write(json);
		System.out.println(json);

	}
	
	//获取Day
	public ArrayList<String> getDays(List<Corpoyejian> lastTenYejianData) {
		ArrayList<String> a = new ArrayList<String>();
		for (Corpoyejian corpoyejian : lastTenYejianData) {
			String date_st = corpoyejian.getDate_st();
			String days = subString(date_st);
			a.add(days);
		}
		Collections.reverse(a); //因为是逆序从数据库取得数据，所以需要逆逆得正
		return a;
	}
	
	//获取各水厂对应数据
	public ArrayList getDatas(List<Corpoyejian> lastTenYejianData){
		ArrayList allData = new ArrayList();
		
		ArrayList<Float> xcq = new ArrayList<Float>();
		ArrayList<Float> gsq = new ArrayList<Float>();
		ArrayList<Float> mddq = new ArrayList<Float>();
		ArrayList<Float> xkdq = new ArrayList<Float>();
		ArrayList<Float> thdjq = new ArrayList<Float>();

		for (Corpoyejian corpoyejian : lastTenYejianData) {
			float xcq2 = corpoyejian.getXcq();
			float gsq2 = corpoyejian.getGsq();
			float mddq2 = corpoyejian.getMddq();
			float xkdq2 = corpoyejian.getXkdq();
			float thdjq2 = corpoyejian.getThdjq();
			xcq.add(xcq2);
			gsq.add(gsq2);
			mddq.add(mddq2);
			xkdq.add(xkdq2);
			thdjq.add(thdjq2);
		}
		Collections.reverse(xcq);
		Collections.reverse(gsq);
		Collections.reverse(mddq);
		Collections.reverse(xkdq);
		Collections.reverse(thdjq);

		allData.add(xcq);//0：相城区
		allData.add(gsq);//1 ：姑苏区
		allData.add(mddq);//2 ：木渎地区
		allData.add(xkdq);//3 ：胥口地区
		allData.add(thdjq);//4 ：太湖度假区
		
		return allData;
		
	}
	
	

	// 获取Date_st的后两位，年月日中的日。
	public String subString(String st) {
		return st.substring(8);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
