package kr.revelope.spring.batch.admin.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import kr.revelope.spring.batch.admin.mapper.JobInstanceMapper;
import kr.revelope.spring.batch.admin.model.JobInstance;

@Repository
public class JobInstanceRepositoryImpl implements JobInstanceRepository {
	private final String tablePrefix;

	private final JobInstanceMapper jobInstanceMapper;

	public JobInstanceRepositoryImpl(
			@Value("${spring.batch.table-prefix}") String tablePrefix,
			JobInstanceMapper jobInstanceMapper) {
		this.tablePrefix = tablePrefix;
		this.jobInstanceMapper = jobInstanceMapper;
	}

	@Override
	public JobInstance selectJobInstance(long jobInstanceId) {
		return jobInstanceMapper.selectJobInstance(jobInstanceId, tablePrefix);
	}

	@Override
	public void deleteJobInstanceLessThan(long jobInstanceId) {
		jobInstanceMapper.deleteJobInstanceLessThan(jobInstanceId, tablePrefix);
	}
}
