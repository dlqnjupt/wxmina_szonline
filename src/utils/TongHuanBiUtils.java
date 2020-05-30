package utils;


import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TongHuanBiUtils {
	 Calendar calendar = Calendar.getInstance();
	 SimpleDateFormat sfDate = new SimpleDateFormat("YYYY-MM-dd");
	 SimpleDateFormat sfTime = new SimpleDateFormat("HH-mm-ss");
	
	
	public String getLastYear(java.sql.Date date_S){

		calendar.setTime(date_S);
		calendar.add(calendar.YEAR, -1);
		calendar.add(calendar.DATE, -1);
		Date date = calendar.getTime();
		String lastYear = sfDate.format(date);
		return lastYear;
	}

	public String getOneHourBefore(java.sql.Time time_S){
		calendar.setTime(time_S);
		calendar.add(calendar.HOUR, -1);
		Date time = calendar.getTime();
		String oneHourBefore = sfTime.format(time);
		return oneHourBefore;
	}
	
	
	public String getLastMonth(java.sql.Date date_S){
		calendar.setTime(date_S);
		calendar.add(calendar.MONTH, -1);
		calendar.add(calendar.DATE, -1);
		Date date = calendar.getTime();
		String LastMonth = sfDate.format(date);
		return LastMonth;
	}

}
