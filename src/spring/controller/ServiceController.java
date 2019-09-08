package spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/service")
public class ServiceController {

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		return "map";
	}
}
