package kr.revelope.spring.batch.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.revelope.spring.batch.admin.common.exception.InvalidParameterException;
import kr.revelope.spring.batch.admin.model.JobInstance;
import kr.revelope.spring.batch.admin.service.JobInstanceService;

@RestController
@RequestMapping("/spring/batch/api")
public class JobInstanceRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(JobInstanceRestController.class);

	private final JobInstanceService jobInstanceService;

	@Autowired
	public JobInstanceRestController(
			JobInstanceService jobInstanceService) {
		this.jobInstanceService = jobInstanceService;
	}

	@GetMapping("/jobInstance/{jobInstanceId}")
	public JobInstance getJobInstance(@PathVariable("jobInstanceId") long jobInstanceId) {
		if (jobInstanceId < 0) {
			throw new InvalidParameterException("JobInstanceId can not be negative number.");
		}

		return jobInstanceService.getJobInstance(jobInstanceId);
	}
}
