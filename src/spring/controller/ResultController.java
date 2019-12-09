package spring.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Result;
import common.page.PaginationResult;
import domain.ResultVO;
import spring.service.ResultService;

@Controller
@RequestMapping("/result")
public class ResultController {

	@Autowired
	private ResultService resultService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		return "index";
	}

	@RequestMapping(value = "/avgOfDepByHour", method = RequestMethod.GET)
	@ResponseBody
	public Result<Map<String, List<Object>>> getAvgOfDepByHour() {
		Result<Map<String, List<Object>>> result = new Result<Map<String, List<Object>>>(
				resultService.getAvgOfDepByHour());
		return result;
	}

	@RequestMapping(value = "/allOfDep", method = RequestMethod.GET)
	@ResponseBody
	public Result<Map<String, List<Object>>> getAllOfDep() {
		Result<Map<String, List<Object>>> result = new Result<Map<String, List<Object>>>(resultService.getAllOfDep());
		return result;
	}

	@RequestMapping(value = "/allOfArr", method = RequestMethod.GET)
	@ResponseBody
	public Result<Map<String, List<Object>>> getAllOfArr() {
		Result<Map<String, List<Object>>> result = new Result<Map<String, List<Object>>>(resultService.getAllOfArr());
		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getBusList(Integer pageIndex, Integer pageSize, ModelMap modelMap) {
		PaginationResult<List<ResultVO>> result = resultService.getBusList(pageIndex, pageSize);
		modelMap.addAttribute("result", result);
		return "busList";
	}

	@RequestMapping(value = "/list/{carNum}", method = RequestMethod.GET)
	public String getBusSchedule(@PathVariable Integer carNum, Integer pageIndex, Integer pageSize, ModelMap modelMap) {
		PaginationResult<List<ResultVO>> result = resultService.getBusList(carNum, pageIndex, pageSize);
		modelMap.addAttribute("first_resultId", result.getPageData().get(0).getResultId());
		modelMap.addAttribute("result", result);
		modelMap.addAttribute("carNum", carNum);
		return "scheduleList";
	}
}
