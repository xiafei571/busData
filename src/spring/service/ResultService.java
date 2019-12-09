package spring.service;

import java.util.List;
import java.util.Map;

import common.page.PaginationResult;
import domain.ResultVO;

public interface ResultService {

	Map<String, List<Object>> getAvgOfDepByHour();

	Map<String, List<Object>> getAllOfDep();

	Map<String, List<Object>> getAllOfArr();

	PaginationResult<List<ResultVO>> getBusList(Integer pageIndex, Integer pageSize);

	PaginationResult<List<ResultVO>> getBusList(Integer carNum, Integer pageIndex, Integer pageSize);
	
	ResultVO getResultInfo(Integer resultId);
}
