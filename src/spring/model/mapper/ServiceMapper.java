package spring.model.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import domain.ServiceVO;

public interface ServiceMapper {
	static String column_all = " * ";

	List<ServiceVO> getServiceByHour(@Param("start") Date start, @Param("end") Date end);

	List<ServiceVO> getServiceByTime(@Param("carNum") Integer carNum, @Param("start") Date start,
			@Param("end") Date end);
}
