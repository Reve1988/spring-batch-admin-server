package kr.revelope.spring.batch.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.revelope.spring.batch.admin.mapper.StepExecutionMapper;
import kr.revelope.spring.batch.admin.model.StepExecution;

@Service
public class StepExecutionServiceImpl implements StepExecutionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(StepExecutionServiceImpl.class);

	private final StepExecutionMapper stepExecutionMapper;

	@Autowired
	public StepExecutionServiceImpl(
			StepExecutionMapper stepExecutionMapper) {
		this.stepExecutionMapper = stepExecutionMapper;
	}

	@Override
	public StepExecution getStepExecution(long stepExecutionId) {
		return stepExecutionMapper.selectStepExecution(stepExecutionId);
	}

	@Override
	public List<StepExecution> getStepExecutionList(long jobExecutionId) {
		return stepExecutionMapper.selectStepExecutionList(jobExecutionId);
	}

	@Override
	public void deleteLessThan(long jobExecutionId) {
		long maxStepExecutionId = stepExecutionMapper.selectMaxStepExecutionId(jobExecutionId);

		stepExecutionMapper.deleteStepExecutionContextLessThan(maxStepExecutionId);
		stepExecutionMapper.deleteStepExecutionLessThan(maxStepExecutionId);
	}
}
