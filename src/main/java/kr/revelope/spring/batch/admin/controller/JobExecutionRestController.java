package kr.revelope.spring.batch.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.revelope.spring.batch.admin.common.exception.InvalidParameterException;
import kr.revelope.spring.batch.admin.model.JobExecution;
import kr.revelope.spring.batch.admin.model.JobExecutionParam;
import kr.revelope.spring.batch.admin.model.PagedJobExecutions;
import kr.revelope.spring.batch.admin.service.JobExecutionService;

@RestController
@RequestMapping("/spring/batch/api")
public class JobExecutionRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(JobExecutionRestController.class);

	private final JobExecutionService jobExecutionService;

	@Autowired
	public JobExecutionRestController(
			JobExecutionService jobExecutionService) {
		this.jobExecutionService = jobExecutionService;
	}

	@GetMapping("/jobExecution/{jobExecutionId}")
	public JobExecution getJobExecution(@PathVariable("jobExecutionId") long jobExecutionId) {
		if (jobExecutionId < 0) {
			throw new InvalidParameterException("JobExecutionId can not be negative number.");
		}

		return jobExecutionService.getJobExecution(jobExecutionId);
	}

	@GetMapping("/jobExecutions/{jobNames}")
	public PagedJobExecutions getPagedJobExecutionList(
			@PathVariable("jobNames") List<String> jobNames,
			@RequestParam("start") long start,
			@RequestParam("length") long length) {
		if (start < 0 || length < 0) {
			throw new InvalidParameterException(String.format("Paging data is invalid. (start %s, length %s)", start, length));
		}

		if (length == 0) {
			return new PagedJobExecutions();
		}

		if (CollectionUtils.isEmpty(jobNames)) {
			return new PagedJobExecutions();
		}

		PagedJobExecutions pagedJobExecutions = new PagedJobExecutions();
		pagedJobExecutions.setTotal(jobExecutionService.getTotalCount(jobNames));
		pagedJobExecutions.setJobExecutionList(jobExecutionService.getJobExecutionList(jobNames, start, length));

		return pagedJobExecutions;
	}

	@GetMapping("/jobExecutionParams/{jobExecutionId}")
	public List<JobExecutionParam> getJobExecutionParamList(
			@PathVariable("jobExecutionId") long jobExecutionId) {
		if (jobExecutionId < 0) {
			throw new InvalidParameterException("JobExecutionId can not be negative number.");
		}

		return jobExecutionService.getJobExecutionParamList(jobExecutionId);
	}
}
