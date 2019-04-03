package kr.revelope.spring.batch.admin.repository;

import java.util.List;

import kr.revelope.spring.batch.admin.model.StepExecution;

public interface StepExecutionRepository {
	StepExecution selectStepExecution(long stepExecutionId);

	List<StepExecution> selectStepExecutionList(long jobExecutionId);

	long selectMaxStepExecutionId(long jobExecutionId);

	void deleteStepExecutionContextLessThanOrEqualTo(long stepExecutionId);

	void deleteStepExecutionLessThanOrEqualTo(long stepExecutionId);
}
