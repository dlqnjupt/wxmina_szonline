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
import domain.Corpogsl;
import service.CorpoService;

public class CorpogslServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String yearMonth = request.getParameter("yearMonth");
		
		CorpoService service = new CorpoService();
		//前日供水量
		Corpogsl LastdayGsl = null;
		//该月每日供水量
		List<Corpogsl> MonthGsl = null;
		//该月个水厂供水量求和
		List<String> sumData = null;
		//页面显示的数据封装成一个List
		ArrayList scgsl = new ArrayList();
		
		try {
			LastdayGsl = service.getCorpogsl();
			MonthGsl = service.getMonthgsl(yearMonth);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(!MonthGsl.isEmpty()){
			sumData = sumFun(MonthGsl);
		}
		
		scgsl.add(LastdayGsl);//0:前日
		scgsl.add(MonthGsl);//1:当月所有
		scgsl.add(sumData);//2:sum
		
		Gson gson = new Gson();
		String json = gson.toJson(scgsl);
		
		response.getWriter().write(json);
		System.out.println(json);
		
		
		
		
	}
	
	private List<String> sumFun(List<Corpogsl> MonthGsl){
		
		final DecimalFormat df = new DecimalFormat("0.00");
		double sum = 0;
		
		List<String> sumList = new ArrayList<>();
		
		sum = MonthGsl.stream().mapToDouble(Corpogsl::getByw).sum();
		sumList.add(df.format(sum));
		sum = MonthGsl.stream().mapToDouble(Corpogsl::getXc).sum();
		sumList.add(df.format(sum));
		sum = MonthGsl.stream().mapToDouble(Corpogsl::getXj).sum();
		sumList.add(df.format(sum));
		sum = MonthGsl.stream().mapToDouble(Corpogsl::getSum).sum();
		sumList.add(df.format(sum));


		return sumList;
	}
	
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
