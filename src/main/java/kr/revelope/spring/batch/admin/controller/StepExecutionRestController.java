package kr.revelope.spring.batch.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.revelope.spring.batch.admin.common.exception.InvalidParameterException;
import kr.revelope.spring.batch.admin.model.StepExecution;
import kr.revelope.spring.batch.admin.service.StepExecutionService;

@RestController
@RequestMapping("/spring/batch/api")
public class StepExecutionRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(StepExecutionRestController.class);

	private final StepExecutionService stepExecutionService;

	@Autowired
	public StepExecutionRestController(
			StepExecutionService stepExecutionService) {
		this.stepExecutionService = stepExecutionService;
	}

	@GetMapping("/stepExecution/{stepExecutionId}")
	public StepExecution getStepExecution(
			@PathVariable("stepExecutionId") long stepExecutionId) {
		if (stepExecutionId < 0) {
			throw new InvalidParameterException("StepExecutionId can not be negative number.");
		}

		return stepExecutionService.getStepExecution(stepExecutionId);
	}

	@GetMapping("/stepExecutions/{jobExecutionId}")
	public List<StepExecution> getStepExecutionList(
			@PathVariable("jobExecutionId") long jobExecutionId) {
		if (jobExecutionId < 0) {
			throw new InvalidParameterException("JobExecutionId can not be negative number.");
		}

		return stepExecutionService.getStepExecutionList(jobExecutionId);
	}
}
