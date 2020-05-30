package domain;

public class Corpogsl {
	
	private int id;
	private String Date_st;
	private java.sql.Date Date_s;
	private java.sql.Time Time_s;
	
	private float byw;
	private float xc;
	private float xj;
	private float sum;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	


	public String getDate_st() {
		return Date_st;
	}
	public void setDate_st(String date_st) {
		Date_st = date_st;
	}
	public java.sql.Date getDate_s() {
		return Date_s;
	}
	public void setDate_s(java.sql.Date date_s) {
		Date_s = date_s;
	}
	public java.sql.Time getTime_s() {
		return Time_s;
	}
	public void setTime_s(java.sql.Time time_s) {
		Time_s = time_s;
	}
	public float getByw() {
		return byw;
	}
	public void setByw(float byw) {
		this.byw = byw;
	}
	public float getXc() {
		return xc;
	}
	public void setXc(float xc) {
		this.xc = xc;
	}
	public float getXj() {
		return xj;
	}
	public void setXj(float xj) {
		this.xj = xj;
	}
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
	
	

}
