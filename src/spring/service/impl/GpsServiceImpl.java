package spring.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import domain.HeatData;
import domain.ResultVO;
import domain.ServiceVO;
import spring.model.mapper.ResultMapper;
import spring.model.mapper.ServiceMapper;
import spring.service.GpsService;
import util.DateUtil;
import util.GPSUtil;

@Component("gpsService")
public class GpsServiceImpl implements GpsService {

	private static final int SPEED_INTERVAL = 10;
	@Autowired
	private ServiceMapper serviceMapper;

	@Autowired
	private ResultMapper resultMapper;

	@Override
	public List<ServiceVO> getServiceByHour(Integer timeIndex, Integer timeSize) {
		// 2019070109
		Calendar c1 = getCalendarByTime(timeIndex);
		Date start = c1.getTime();
		c1.add(Calendar.HOUR_OF_DAY, timeSize);
		Date end = c1.getTime();
		List<ServiceVO> serviceList = serviceMapper.getServiceByHour(start, end);
		return serviceList;
	}

	public List<ServiceVO> getServiceByResultId(Integer resultId) {
		ResultVO result = resultMapper.getResultInfo(resultId);
		if (null != result) {
			List<ServiceVO> serviceList = serviceMapper.getServiceByTime(result.getCarNum(), result.getDepartured(),
					result.getArrived());
			return serviceList;
		}
		return null;
	}

	private Calendar getCalendarByTime(Integer timeIndex) {
		Calendar c1 = Calendar.getInstance();
		c1.set(timeIndex / 1000000, (timeIndex / 10000) % 100 - 1, (timeIndex / 100) % 100);
		c1.set(Calendar.HOUR_OF_DAY, timeIndex % 100);
		return c1;
	}

	@Override
	public String getServiceGeoJsonByHourRefactor(Integer timeIndex, Integer timeSize) {
		if (timeIndex == null || timeSize == null) {
			timeIndex = 2019070108;
			timeSize = 1;
		}

		List<ServiceVO> services = getServiceByHour(timeIndex, timeSize);
		// TODO http://localhost:8080/busData/service/map
		if (services.isEmpty())
			return "";

		String featureList = generateFeatureJsonArray(services);
		return featureList;
	}

	public String getServiceGeoJsonByResultId(Integer resultId) {
		List<ServiceVO> services = getServiceByResultId(resultId);
		if (services.isEmpty())
			return "";

		String featureList = generateFeatureJsonArray(services);
		return featureList;
	}

	private String generateFeatureJsonArray(List<ServiceVO> services) {
		JSONArray featureList = new JSONArray();
		Integer speed = services.get(0).getSpeed() / SPEED_INTERVAL;
		Integer carNum = services.get(0).getCarNum();
		// 先初始化一个
		JSONObject line = initLineObject(speed, carNum);
		JSONArray coordinates = new JSONArray();
		JSONObject geometry = new JSONObject();
		geometry.put("type", "LineString");

		for (int i = 1; i < services.size(); i++) {

			if (carNum.equals(services.get(i).getCarNum())) {// 同车

				if (speed.equals(services.get(i).getSpeed() / SPEED_INTERVAL)) {// 同速区间

					coordinates.add(GPSUtil.jp256ToWorldJsonChange(services.get(i).getLatitude(),
							services.get(i).getLongitude()));
				} else {// 不同速
					geometry.put("coordinates", coordinates);
					line.put("geometry", geometry);
					featureList.add(line);
					// 创建新的json
					speed = services.get(i).getSpeed() / SPEED_INTERVAL;
					line = initLineObject(speed, carNum);
					coordinates = new JSONArray();
					geometry = new JSONObject();
					geometry.put("type", "LineString");
					if (i > 0) {// 把前一个点也加进去
						coordinates.add(GPSUtil.jp256ToWorldJsonChange(services.get(i - 1).getLatitude(),
								services.get(i - 1).getLongitude()));
					}
					coordinates.add(GPSUtil.jp256ToWorldJsonChange(services.get(i).getLatitude(),
							services.get(i).getLongitude()));
				}

			} else {// 不同车
					// 1.创建新的json
				geometry.put("coordinates", coordinates);
				line.put("geometry", geometry);
				featureList.add(line);
				// 创建新的json
				carNum = services.get(i).getCarNum();
				line = initLineObject(speed, carNum);
				coordinates = new JSONArray();
				geometry = new JSONObject();
				geometry.put("type", "LineString");
				coordinates.add(
						GPSUtil.jp256ToWorldJsonChange(services.get(i).getLatitude(), services.get(i).getLongitude()));
			}

			if (i == services.size() - 1) {// 最后一个了
				geometry.put("coordinates", coordinates);
				line.put("geometry", geometry);
				featureList.add(line);
			}
		}
		return featureList.toJSONString();
	}

	@Override
	public String getServiceGeoJsonByHour(Integer timeIndex, Integer timeSize) {
		List<ServiceVO> services = getServiceByHour(timeIndex, timeSize);

		if (services.isEmpty())
			return "";

		int speed = 0;
		Integer carNum = services.get(0).getCarNum();
		int index = 0;

		JSONArray featureList = new JSONArray();
		initFeatureList(services, featureList, speed, carNum, index);
		return featureList.toJSONString();
	}

	private void initFeatureList(List<ServiceVO> services, JSONArray featureList, int speed, Integer carNum,
			int index) {
		JSONObject line = initLineObject(speed, carNum);
		JSONArray coordinates = new JSONArray();
		JSONObject geometry = new JSONObject();
		geometry.put("type", "LineString");

		for (int i = index; i < services.size(); i++) {
			if (carNum.equals(services.get(i).getCarNum())) {// 同车
				if (services.get(i).getSpeed() / SPEED_INTERVAL == speed) {// 同一速度范围
					coordinates.add(GPSUtil.jp256ToWorldJsonChange(services.get(i).getLatitude(),
							services.get(i).getLongitude()));

				} else {// 不同速度范围
						// 先把上一量车的数组添进去
					if (coordinates.size() > 0) {
						geometry.put("coordinates", coordinates);
						line.put("geometry", geometry);
						featureList.add(line);
					}
					// TODO 先把上一个点加进去
					// 递归
					initFeatureList(services, featureList, services.get(i).getSpeed() / SPEED_INTERVAL, carNum, i);
					break;
				}
			} else {// 另一辆车
				// 先把上一量车的数组添进去
				if (coordinates.size() > 0) {
					geometry.put("coordinates", coordinates);
					line.put("geometry", geometry);
					featureList.add(line);
				}
				// 递归
				initFeatureList(services, featureList, speed, services.get(i).getCarNum(), i);
				break;
			}
		}

		geometry.put("coordinates", coordinates);
		line.put("geometry", geometry);
		featureList.add(line);
	}

	private JSONObject initLineObject(int speed, Integer carNum) {
		JSONObject line = new JSONObject();
		line.put("type", "Feature");
		JSONObject properties = new JSONObject();
		properties.put("carNum", carNum);
		properties.put("speed", speed);
		line.put("properties", properties);
		return line;
	}

	@Override
	public List<HeatData> getHeatData(Integer timeIndex, Integer timeSize) {
		Calendar c1 = getCalendarByTime(timeIndex);
		Date start = c1.getTime();
		c1.add(Calendar.HOUR_OF_DAY, timeSize);
		Date end = c1.getTime();
		List<HeatData> heatDataList = serviceMapper.getHeatDataByHour(start, end);
		double[] array = new double[2];
		for (HeatData data : heatDataList) {
			array = GPSUtil.jp256ToWorld(data.getLatitude(), data.getLongitude());
			data.setLat(array[0]);
			data.setLng(array[1]);
			data.setValue(6 - (data.getSpeed() / SPEED_INTERVAL) > 0 ? 6 - (data.getSpeed() / SPEED_INTERVAL) : 0);
		}

		return heatDataList;
	}

	@Override
	public List<String> getServiceGeoJsonByWeek(Integer resultId) {
		// TODO Auto-generated method stub
		ResultVO result = resultMapper.getResultInfo(resultId);

		DateUtil.getDayOfWeek(result.getDeparture());

		return null;
	}

}
