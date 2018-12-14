package kr.revelope.spring.batch.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.revelope.spring.batch.admin.mapper.JobInstanceMapper;
import kr.revelope.spring.batch.admin.model.JobExecution;
import kr.revelope.spring.batch.admin.model.JobInstance;

@Service
public class JobInstanceServiceImpl implements JobInstanceService {
	private static final Logger LOGGER = LoggerFactory.getLogger(JobInstanceServiceImpl.class);

	private final JobInstanceMapper jobInstanceMapper;
	private final JobExecutionService jobExecutionService;

	@Autowired
	public JobInstanceServiceImpl(
			JobInstanceMapper jobInstanceMapper,
			JobExecutionService jobExecutionService) {
		this.jobInstanceMapper = jobInstanceMapper;
		this.jobExecutionService = jobExecutionService;
	}

	@Override
	public JobInstance getJobInstance(long jobInstanceId) {
		return jobInstanceMapper.selectJobInstance(jobInstanceId);
	}

	@Override
	public void deleteLessThan(long jobExecutionId) {
		JobExecution jobExecution = jobExecutionService.getJobExecution(jobExecutionId);
		if (jobExecution == null) {
			return;
		}

		jobInstanceMapper.deleteJobInstanceLessThan(jobExecution.getJobInstanceId());
	}
}
