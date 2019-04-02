package kr.revelope.spring.batch.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.revelope.spring.batch.admin.model.StepExecution;
import kr.revelope.spring.batch.admin.repository.StepExecutionRepository;

@Service
public class StepExecutionServiceImpl implements StepExecutionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(StepExecutionServiceImpl.class);

	private final StepExecutionRepository stepExecutionRepository;

	@Autowired
	public StepExecutionServiceImpl(
			StepExecutionRepository stepExecutionRepository) {
		this.stepExecutionRepository = stepExecutionRepository;
	}

	@Override
	public StepExecution getStepExecution(long stepExecutionId) {
		return stepExecutionRepository.selectStepExecution(stepExecutionId);
	}

	@Override
	public List<StepExecution> getStepExecutionList(long jobExecutionId) {
		return stepExecutionRepository.selectStepExecutionList(jobExecutionId);
	}

	@Override
	public void deleteLessThanOrEqualTo(long jobExecutionId) {
		long maxStepExecutionId = stepExecutionRepository.selectMaxStepExecutionId(jobExecutionId);

		stepExecutionRepository.deleteStepExecutionContextLessThanOrEqualTo(maxStepExecutionId);
		stepExecutionRepository.deleteStepExecutionLessThanOrEqualTo(maxStepExecutionId);
	}
}
