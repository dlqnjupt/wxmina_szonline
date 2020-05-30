package web;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.BywConsume;
import domain.BywData;
import domain.DataEntity;
import domain.PlanData;
import service.RequestService;

public class BywConsumeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String yearMonth = request.getParameter("yearMonth");

		RequestService service = new RequestService();
		// 获得最新的一条数据，ID最大；
		DataEntity bywRealTimeData = null;
		// 获得计算水量，电耗，矾耗，氯耗，氧耗，成本同环比的数据
		float[] duibiData = null;
		// 获得十一月份所有的数据；
		List<BywConsume> bywMonthConsumeData = null;
		// 定义一个集合存放每月的总消耗；
		List<String> bywSumData = null;
		// 计划量
		PlanData planData = null;
		// 同环比
		String[] tongHuanBi = new String[12];

		// 返回前端的所有数据(消耗指标、同环比、计划)
		List allData = new ArrayList<>();

		try {
			bywRealTimeData = service.getRealTimeData();
			duibiData = service.getDuibiData(bywRealTimeData);
			bywMonthConsumeData = service.getBywConsumeData(yearMonth);
			planData = service.getPlanData();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (bywMonthConsumeData.size() != 0 & bywMonthConsumeData != null) {
			bywSumData = sumFun(bywMonthConsumeData);
		}
		if (duibiData.length != 0 && duibiData != null) {
			tongHuanBi = calResult(duibiData);
		}
		
		HashMap<String, String> realDateStr = getRealDateStr(bywRealTimeData);

		allData.add(bywRealTimeData);// 0
		allData.add(tongHuanBi);// 1
		allData.add(bywSumData);// 2
		allData.add(planData);// 3
		allData.add(realDateStr);//4

		Gson gson = new Gson();
		String json = gson.toJson(allData);

		response.getWriter().write(json);
		System.out.println("//////////////////////////////");

		System.out.println(json);
		// System.out.println(bywRealTimeData);

		/*
		 * for(int i=0;i<tongHuanBi.length;i++){
		 * System.out.print(tongHuanBi[i]+","); }
		 */

		// System.out.println(bywSumData);

	}

	private List<String> sumFun(List<BywConsume> bywMonthConsumeData) {

		final DecimalFormat df = new DecimalFormat("0.00");
		double sum = 0;

		List<String> sumList = new ArrayList<>();

		sum = bywMonthConsumeData.stream().mapToDouble(BywConsume::getSl).sum();
		sumList.add(df.format(sum));
		sum = bywMonthConsumeData.stream().mapToDouble(BywConsume::getDh).sum();
		sumList.add(df.format(sum));
		sum = bywMonthConsumeData.stream().mapToDouble(BywConsume::getFh).sum();
		sumList.add(df.format(sum));
		sum = bywMonthConsumeData.stream().mapToDouble(BywConsume::getLh).sum();
		sumList.add(df.format(sum));
		sum = bywMonthConsumeData.stream().mapToDouble(BywConsume::getYh).sum();
		sumList.add(df.format(sum));
		sum = bywMonthConsumeData.stream().mapToDouble(BywConsume::getCb).sum();
		sumList.add(df.format(sum));
		return sumList;
	}

	public float calTonghuanbi(float real, float last) {
		if (last != 0) {
			return real / last;
		} else {
			return -1;
		}
	}

	public String[] calResult(float[] duibiData) {
		float[] result = new float[12];
		String[] resultStr = new String[12];

		DecimalFormat df1 = new DecimalFormat("0.00");
		DecimalFormat df2 = new DecimalFormat("0");


		result[0] = calTonghuanbi(duibiData[12], duibiData[0]);
		result[1] = calTonghuanbi(duibiData[12], duibiData[1]);
		result[2] = calTonghuanbi(duibiData[13], duibiData[2]);
		result[3] = calTonghuanbi(duibiData[13], duibiData[3]);
		result[4] = calTonghuanbi(duibiData[14], duibiData[4]);
		result[5] = calTonghuanbi(duibiData[14], duibiData[5]);
		result[6] = calTonghuanbi(duibiData[15], duibiData[6]);
		result[7] = calTonghuanbi(duibiData[15], duibiData[7]);
		result[8] = calTonghuanbi(duibiData[16], duibiData[8]);
		result[9] = calTonghuanbi(duibiData[16], duibiData[9]);
		result[10] = calTonghuanbi(duibiData[17], duibiData[10]);
		result[11] = calTonghuanbi(duibiData[17], duibiData[10]);

		for (int i = 0; i < result.length; i++) {
			if (result[i] != -1) {
				resultStr[i] = df1.format(result[i]);
			}else{
				resultStr[i]= df2.format(result[i]);
			}
		}

		return resultStr;

	}
	
	public HashMap<String,String> getRealDateStr(DataEntity bywRealTimeData){
		HashMap<String,String> map = new HashMap<String,String>();
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat sf2 = new SimpleDateFormat("HH:mm:ss");
		Date date_S = bywRealTimeData.getDate_s();
		Time time_S = bywRealTimeData.getTime_s();
		String formatDate = sf1.format(date_S);
		String formatTime = sf2.format(time_S);
		String timeStr = formatDate+formatTime;
		map.put("realDateStr",timeStr);
		return map;
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
