package kr.revelope.spring.batch.admin.service;

import java.util.List;

import kr.revelope.spring.batch.admin.model.StepExecution;

public interface StepExecutionService {
	StepExecution getStepExecution(long stepExecutionId);

	List<StepExecution> getStepExecutionList(long jobExecutionId);

	void deleteLessThanOrEqualTo(long jobExecutionId);
}
