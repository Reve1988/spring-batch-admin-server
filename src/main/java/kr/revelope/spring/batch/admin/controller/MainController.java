package kr.revelope.spring.batch.admin.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	@GetMapping("/")
	public String main(@RequestParam(value = "jobNames", required = false) String jobNames) {
		if (jobNames == null) {
			jobNames = StringUtils.EMPTY;
		}

		return "index.html?jobName=" + jobNames;
	}
}
