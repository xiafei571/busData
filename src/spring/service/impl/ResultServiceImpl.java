package spring.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import common.page.Pagination;
import common.page.PaginationResult;
import domain.ChartData;
import domain.ResultVO;
import spring.model.mapper.ResultMapper;
import spring.service.ResultService;

@Component("resultService")
public class ResultServiceImpl implements ResultService {

	@Autowired
	private ResultMapper resultMapper;

	@Override
	public Map<String, List<Object>> getAvgOfDepByHour() {

		List<ChartData> depList = resultMapper.getAvgOfDepByHour();
		List<Object> dataList1 = new ArrayList<Object>();
		List<Object> valueList1 = new ArrayList<Object>();

		for (ChartData data : depList) {
			dataList1.add(data.getData());
			valueList1.add(data.getValue());
		}

		List<ChartData> arrList = resultMapper.getAvgOfArrByHour();
		List<Object> dataList2 = new ArrayList<Object>();
		List<Object> valueList2 = new ArrayList<Object>();

		for (ChartData data : arrList) {
			dataList2.add(data.getData());
			valueList2.add(data.getValue());
		}

		Map<String, List<Object>> result = new HashMap<String, List<Object>>();
		result.put("dataList1", dataList1);
		result.put("valueList1", valueList1);

		result.put("dataList2", dataList2);
		result.put("valueList2", valueList2);

		return result;
	}

	@Override
	public Map<String, List<Object>> getAllOfDep() {
		List<ChartData> depList = resultMapper.getAllOfDep();
		List<Object> dataList = new ArrayList<Object>();
		List<Object> valueList = new ArrayList<Object>();
		for (ChartData data : depList) {
			dataList.add(data.getData());
			valueList.add(data.getValue());
		}
		Map<String, List<Object>> result = new HashMap<String, List<Object>>();
		result.put("dataList", dataList);
		result.put("valueList", valueList);
		return result;
	}

	@Override
	public Map<String, List<Object>> getAllOfArr() {
		List<ChartData> arrList = resultMapper.getAllOfArr();
		List<Object> dataList = new ArrayList<Object>();
		List<Object> valueList = new ArrayList<Object>();
		for (ChartData data : arrList) {
			dataList.add(data.getData());
			valueList.add(data.getValue());
		}
		Map<String, List<Object>> result = new HashMap<String, List<Object>>();
		result.put("dataList", dataList);
		result.put("valueList", valueList);
		return result;
	}

	@Override
	public PaginationResult<List<ResultVO>> getBusList(Integer pageIndex, Integer pageSize) {
		Pagination pagination = new Pagination(pageIndex, pageSize);
		Integer count = resultMapper.getResultCount();
		pagination.setTotalCount(count);

		List<ResultVO> list = resultMapper.getBusList(pagination.getCursor(), pagination.getOffset());
		PaginationResult<List<ResultVO>> result = new PaginationResult<List<ResultVO>>(pagination, list);

		return result;
	}

	@Override
	public PaginationResult<List<ResultVO>> getBusList(Integer carNum, Integer pageIndex, Integer pageSize) {
		Pagination pagination = new Pagination(pageIndex, pageSize);
		Integer count = resultMapper.getResultCountByCarNum(carNum);
		pagination.setTotalCount(count);

		List<ResultVO> list = resultMapper.getSchduleList(carNum, pagination.getCursor(), pagination.getOffset());
		PaginationResult<List<ResultVO>> result = new PaginationResult<List<ResultVO>>(pagination, list);
		return result;
	}

	@Override
	public ResultVO getResultInfo(Integer resultId) {
		ResultVO result = resultMapper.getResultInfo(resultId);
		return result;
	}

}
