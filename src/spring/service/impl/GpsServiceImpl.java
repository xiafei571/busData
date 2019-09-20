package spring.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import domain.ServiceVO;
import spring.model.mapper.ServiceMapper;
import spring.service.GpsService;
import util.GPSUtil;

@Component("gpsService")
public class GpsServiceImpl implements GpsService {

	@Autowired
	private ServiceMapper serviceMapper;

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

	private Calendar getCalendarByTime(Integer timeIndex) {
		Calendar c1 = Calendar.getInstance();
		c1.set(timeIndex / 1000000, (timeIndex / 10000) % 100 - 1, (timeIndex / 100) % 100);
		c1.set(Calendar.HOUR_OF_DAY, timeIndex % 100);
		return c1;
	}
	
	@Override
	public String getServiceGeoJsonByHourRefactor(Integer timeIndex, Integer timeSize) {
		List<ServiceVO> services = getServiceByHour(timeIndex, timeSize);
		//TODO http://localhost:8080/busData/service/map
		if (services.isEmpty())
			return "";
		
		return "";
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
				if (services.get(i).getSpeed() / 10 == speed) {// 同一速度范围
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
					initFeatureList(services, featureList, services.get(i).getSpeed() / 10, carNum, i);
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

}
