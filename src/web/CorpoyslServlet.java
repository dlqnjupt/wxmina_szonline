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

import domain.Corpogsl;
import domain.Corpoysl;
import service.CorpoService;

public class CorpoyslServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws 
	ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String yearMonth = request.getParameter("yearMonth");
		
		CorpoService service = new CorpoService();
		//前日区域用水量
		Corpoysl qyysl = null;
		//当月每日用水量
		List<Corpoysl> monthYsl = null;
		//当月累计用水量
		List<String> sumList = null;
		
		
		
		//页面需要的数据
		ArrayList list = new ArrayList();
		
		
		try {
			qyysl = service.getQyysl();
			monthYsl = service.getMonthQyysl(yearMonth);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(!monthYsl.isEmpty()){
			sumList = sumFun(monthYsl);
		}
		
		list.add(qyysl);//0:前日区域用水量
		list.add(sumList);//1：当月累计用水量
		
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		System.out.println(json);
		response.getWriter().write(json);
		
	}
	
	private List<String> sumFun(List<Corpoysl> monthYsl){
		
		final DecimalFormat df = new DecimalFormat("0.00");
		double sum = 0;
		
		List<String> sumList = new ArrayList<>();
		
		sum = monthYsl.stream().mapToDouble(Corpoysl::getXcq).sum();
		sumList.add(df.format(sum));
		sum = monthYsl.stream().mapToDouble(Corpoysl::getSq).sum();
		sumList.add(df.format(sum));
		sum = monthYsl.stream().mapToDouble(Corpoysl::getMd).sum();
		sumList.add(df.format(sum));
		sum = monthYsl.stream().mapToDouble(Corpoysl::getXk).sum();
		sumList.add(df.format(sum));
		sum = monthYsl.stream().mapToDouble(Corpoysl::getThdjq).sum();
		sumList.add(df.format(sum));

		return sumList;
	}
	
	
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

