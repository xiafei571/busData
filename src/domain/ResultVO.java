package domain;

import java.util.Date;

public class ResultVO {

	private Integer resultId;

	private String dianame;

	private Integer carNum;
	// 出発予定日時
	private Date departure;
	// 出発実績日時
	private Date departured;
	// 到着予定日時
	private Date arrival;
	// 到着実績日時
	private Date arrived;

	public Integer getResultId() {
		return resultId;
	}

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}

	public String getDianame() {
		return dianame;
	}

	public void setDianame(String dianame) {
		this.dianame = dianame;
	}

	public Integer getCarNum() {
		return carNum;
	}

	public void setCarNum(Integer carNum) {
		this.carNum = carNum;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public Date getDepartured() {
		return departured;
	}

	public void setDepartured(Date departured) {
		this.departured = departured;
	}

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public Date getArrived() {
		return arrived;
	}

	public void setArrived(Date arrived) {
		this.arrived = arrived;
	}

	@Override
	public String toString() {
		return "ResultVO [resultId=" + resultId + ", dianame=" + dianame + ", carNum=" + carNum + ", departure="
				+ departure + ", departured=" + departured + ", arrival=" + arrival + ", arrived=" + arrived + "]";
	}

}
