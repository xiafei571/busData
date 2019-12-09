package spring.service;

import java.util.List;

import domain.ServiceVO;

public interface GpsService {

	List<ServiceVO> getServiceByHour(Integer timeIndex, Integer timeSize);

	String getServiceGeoJsonByHour(Integer timeIndex, Integer timeSize);

	String getServiceGeoJsonByHourRefactor(Integer timeIndex, Integer timeSize);

	String getServiceGeoJsonByResultId(Integer resultId);
}
