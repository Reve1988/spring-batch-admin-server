package kr.revelope.spring.batch.admin.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataRetentionCycleServiceImpl implements DataRetentionCycleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataRetentionCycleServiceImpl.class);

	private final JobInstanceService jobInstanceService;
	private final JobExecutionService jobExecutionService;
	private final StepExecutionService stepExecutionService;

	@Autowired
	public DataRetentionCycleServiceImpl(
			JobInstanceService jobInstanceService,
			JobExecutionService jobExecutionService,
			StepExecutionService stepExecutionService) {
		this.jobInstanceService = jobInstanceService;
		this.jobExecutionService = jobExecutionService;
		this.stepExecutionService = stepExecutionService;
	}

	@Override
	@Transactional
	public void clearBefore(LocalDate date) {
		long maxJobExecutionId = jobExecutionService.getMaxJobExecutionIdBefore(date);

		stepExecutionService.deleteLessThan(maxJobExecutionId);
		jobInstanceService.deleteLessThan(maxJobExecutionId);
		jobExecutionService.deleteLessThan(maxJobExecutionId);
	}
}
