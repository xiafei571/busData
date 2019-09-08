package spring.model.mapper;

import java.util.List;

import domain.ChartData;

public interface ResultMapper {
	static String column_all = " * ";

	List<ChartData> getAvgOfDepByHour();

	List<ChartData> getAvgOfArrByHour();

	List<ChartData> getAllOfDep();

	List<ChartData> getAllOfArr();
}
