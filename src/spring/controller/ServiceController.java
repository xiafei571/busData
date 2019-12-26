package spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Result;
import domain.HeatData;
import domain.ServiceVO;
import spring.service.GpsService;

@Controller
@RequestMapping("/service")
public class ServiceController {

	@Autowired
	private GpsService gpsService;

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		return "map";
	}
	
	@RequestMapping(value = "/heatMap", method = RequestMethod.GET)
	public String heatMap(HttpServletRequest request, ModelMap model) {
		return "heat_map";
	}

	@RequestMapping(value = "/map/data", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<ServiceVO>> getData(HttpServletRequest request, ModelMap model, Integer timeIndex,
			Integer timeSize) {
		Result<List<ServiceVO>> result = new Result<List<ServiceVO>>(gpsService.getServiceByHour(timeIndex, timeSize));
		return result;
	}

	@RequestMapping(value = "/map/json", method = RequestMethod.GET)
	@ResponseBody
	public Result<String> getJson(HttpServletRequest request, ModelMap model, Integer timeIndex, Integer timeSize) {
		Result<String> result = new Result<String>(gpsService.getServiceGeoJsonByHourRefactor(timeIndex, timeSize));
		return result;
	}

	@RequestMapping(value = "/map/{resultId}", method = RequestMethod.GET)
	@ResponseBody
	public Result<String> getBusJson(@PathVariable Integer resultId, HttpServletRequest request, ModelMap model,
			Integer timeIndex, Integer timeSize) {
		Result<String> result = new Result<String>(gpsService.getServiceGeoJsonByResultId(resultId));
		return result;
	}
	
	@RequestMapping(value = "/heatData", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<HeatData>> getHeatData(HttpServletRequest request, ModelMap model, Integer timeIndex,
			Integer timeSize){
		Result<List<HeatData>> result = new Result<List<HeatData>>(gpsService.getHeatData(timeIndex, timeSize));
		return result;
	}
	
	@RequestMapping(value = "/mapByWeek/{resultId}", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<String>> getBusWeekJson(@PathVariable Integer resultId, HttpServletRequest request, ModelMap model,
			Integer timeIndex, Integer timeSize) {
		Result<List<String>> result = new Result<List<String>>(gpsService.getServiceGeoJsonByWeek(resultId));
		return result;
	}
}
