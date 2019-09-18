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
		c1.set(timeIndex / 1000000, (timeIndex / 10000) % 100, (timeIndex / 100) % 100);
		c1.set(Calendar.HOUR_OF_DAY, timeIndex % 100);
		return c1;
	}

	@Override
	public String getServiceGeoJsonByHour(Integer timeIndex, Integer timeSize) {
		List<ServiceVO> services = getServiceByHour(timeIndex, timeSize);

		if (services.isEmpty())
			return "";

		JSONObject featureCollection = new JSONObject();

		try {
			featureCollection.put("type", "featureCollection");
			JSONArray featureList = new JSONArray();
			JSONObject line = new JSONObject();
			line.put("type", "Feature");

			int speed = 0;
			Integer carNum = services.get(0).getCarNum();

			JSONObject properties = new JSONObject();
			properties.put("carNum", carNum);
			properties.put("speed", speed);
			line.put("properties", properties);

			for (ServiceVO serviceVO : services) {
				if (carNum.equals(serviceVO.getCarNum())) {
					// 同车
					if (serviceVO.getSpeed() >= speed && serviceVO.getSpeed() < speed + 10) {
						// 同一速度范围

					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

}
