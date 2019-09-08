package spring.service;

import java.util.List;
import java.util.Map;

public interface ResultService {

	Map<String, List<Object>> getAvgOfDepByHour();

	Map<String, List<Object>> getAllOfDep();
	
	Map<String, List<Object>> getAllOfArr();
}
