package kr.revelope.spring.batch.admin.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import kr.revelope.spring.batch.admin.mapper.StepExecutionMapper;
import kr.revelope.spring.batch.admin.model.StepExecution;

@Repository
public class StepExecutionRepositoryImpl implements  StepExecutionRepository{
	private final String tablePrefix;

	private final StepExecutionMapper stepExecutionMapper;

	public StepExecutionRepositoryImpl(
			@Value("${spring.batch.table-prefix}") String tablePrefix,
			StepExecutionMapper stepExecutionMapper) {
		this.tablePrefix = tablePrefix;
		this.stepExecutionMapper = stepExecutionMapper;
	}

	@Override
	public StepExecution selectStepExecution(long stepExecutionId) {
		return stepExecutionMapper.selectStepExecution(stepExecutionId, tablePrefix);
	}

	@Override
	public List<StepExecution> selectStepExecutionList(long jobExecutionId) {
		return stepExecutionMapper.selectStepExecutionList(jobExecutionId, tablePrefix);
	}

	@Override
	public long selectMaxStepExecutionId(long jobExecutionId) {
		return stepExecutionMapper.selectMaxStepExecutionId(jobExecutionId, tablePrefix);
	}

	@Override
	public void deleteStepExecutionContextLessThan(long stepExecutionId) {
		stepExecutionMapper.deleteStepExecutionContextLessThan(stepExecutionId, tablePrefix);
	}

	@Override
	public void deleteStepExecutionLessThan(long stepExecutionId) {
		stepExecutionMapper.deleteStepExecutionLessThan(stepExecutionId, tablePrefix);
	}
}
