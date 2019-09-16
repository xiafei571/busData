package util;

public class GPSUtil {

	public static void main(String[] args) {
		jp256ToWorld(32764902, 128800921);
	}

	/**
	 * 日本の測地系 1/256sec to 日本測地系10進
	 * 
	 * @return
	 */
	public static double jp256ToJp10(double num) {
		// 日本測地系1sec
		double td = num / 256;
		// 日本測地系deg
		double deg = td / 3600;
		// 日本測地系min
		double min = (td - deg * 3600) / 60;
		// 日本測地系sec
		double sec = td - deg * 3600 - min * 60;
		// 日本測地系10進
		double jp10 = deg + (min / 60) + (sec / 3600);
		System.out.println("日本測地系10進:" + jp10);
		return jp10;
	}

	public static double[] jp256ToJp10(double jp_lat, double jp_lon) {
		return new double[] { jp256ToJp10(jp_lat), jp256ToJp10(jp_lon) };
	}

	public static double[] jp256ToWorld(double jp_lat, double jp_lon) {
		System.out.println("日本測地系 1/256秒(jp_lat): " + jp_lat);
		System.out.println("日本測地系 1/256秒(jp_lon): " + jp_lon);
		return jpToWorld(jp256ToJp10(jp_lat), jp256ToJp10(jp_lon));
	}

	public static double[] jpToWorld(double jp_lat, double jp_lon) {
		double lat = jp_lat - jp_lat * 0.00010695 + jp_lon * 0.000017464 + 0.0046017;
		double lon = jp_lon - jp_lat * 0.000046038 - jp_lon * 0.000083043 + 0.010040;
		System.out.println("世界測地系（lat）:" + lat);
		System.out.println("世界測地系（lon）:" + lon);
		double result[] = { lat, lon };
		return result;
	}
}
