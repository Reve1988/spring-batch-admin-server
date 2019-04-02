package kr.revelope.spring.batch.admin.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import kr.revelope.spring.batch.admin.model.JobExecution;
import kr.revelope.spring.batch.admin.model.JobExecutionParam;
import kr.revelope.spring.batch.admin.repository.JobExecutionRepository;

@Service
public class JobExecutionServiceImpl implements JobExecutionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(JobExecutionServiceImpl.class);
	private static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

	private final JobExecutionRepository jobExecutionRepository;

	@Autowired
	public JobExecutionServiceImpl(
			JobExecutionRepository jobExecutionRepository) {
		this.jobExecutionRepository = jobExecutionRepository;
	}

	@Override
	public JobExecution getJobExecution(long jobExecutionId) {
		return jobExecutionRepository.selectJobExecution(jobExecutionId);
	}

	@Override
	public int getTotalCount(List<String> jobNameList) {
		if (CollectionUtils.isEmpty(jobNameList)) {
			return 0;
		}

		return jobExecutionRepository.selectJobExecutionTotalCount(jobNameList);
	}

	@Override
	public List<JobExecution> getJobExecutionList(List<String> jobNameList, long start, long length) {
		if (CollectionUtils.isEmpty(jobNameList)) {
			return new ArrayList<>();
		}

		return jobExecutionRepository.selectJobExecutionList(jobNameList, start, length);
	}

	@Override
	public long getMaxJobExecutionIdBefore(LocalDate date) {
		return jobExecutionRepository.selectMaxJobExecutionIdLessThan(date.format(DEFAULT_DATE_FORMATTER));
	}

	@Override
	public long getMaxJobInstanceIdBefore(LocalDate date) {
		return jobExecutionRepository.selectMaxJobInstanceIdLessThan(date.format(DEFAULT_DATE_FORMATTER));
	}

	@Override
	public List<JobExecutionParam> getJobExecutionParamList(long jobExecutionId) {
		return jobExecutionRepository.selectJobExecutionParamList(jobExecutionId);
	}

	@Override
	public void deleteLessThanOrEqualTo(long jobExecutionId) {
		jobExecutionRepository.deleteJobExecutionParamLessThanOrEqualTo(jobExecutionId);
		jobExecutionRepository.deleteJobExecutionContextLessThanOrEqualTo(jobExecutionId);
		jobExecutionRepository.deleteJobExecutionLessThanOrEqualTo(jobExecutionId);
	}
}
