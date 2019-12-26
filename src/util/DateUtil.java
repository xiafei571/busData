package util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		// 月份 0开始
		calendar.set(2019, 11, 2);
		Date date = calendar.getTime();
		System.out.println(getFirstDayOfWeek(date));
		System.out.println(getLastDayOfWeek(date));
	}

	public static int getDayOfWeek(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		// 0:Sunday ~ 6:Saturday
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week < 0)
			week = 0;
		return week;
	}

	public static Date getFirstDayOfWeek(Date dt) {
		int week = getDayOfWeek(dt);
		if (week == 0) {
			return dt;
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			cal.add(Calendar.DAY_OF_MONTH, -week);
			return cal.getTime();
		}
	}

	public static Date getLastDayOfWeek(Date dt) {
		int week = getDayOfWeek(dt);
		if (week == 6) {
			return dt;
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			cal.add(Calendar.DAY_OF_MONTH, 6 - week);
			return cal.getTime();
		}
	}

}
