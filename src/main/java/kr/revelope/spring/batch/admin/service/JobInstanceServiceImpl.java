package kr.revelope.spring.batch.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.revelope.spring.batch.admin.model.JobInstance;
import kr.revelope.spring.batch.admin.repository.JobInstanceRepository;

@Service
public class JobInstanceServiceImpl implements JobInstanceService {
	private static final Logger LOGGER = LoggerFactory.getLogger(JobInstanceServiceImpl.class);

	private final JobInstanceRepository jobInstanceRepository;
	private final JobExecutionService jobExecutionService;

	@Autowired
	public JobInstanceServiceImpl(
			JobInstanceRepository jobInstanceRepository,
			JobExecutionService jobExecutionService) {
		this.jobInstanceRepository = jobInstanceRepository;
		this.jobExecutionService = jobExecutionService;
	}

	@Override
	public JobInstance getJobInstance(long jobInstanceId) {
		return jobInstanceRepository.selectJobInstance(jobInstanceId);
	}

	@Override
	public void deleteLessThanOrEqualTo(long jobInstanceId) {
		jobInstanceRepository.deleteJobInstanceLessThanOrEqualTo(jobInstanceId);
	}
}
