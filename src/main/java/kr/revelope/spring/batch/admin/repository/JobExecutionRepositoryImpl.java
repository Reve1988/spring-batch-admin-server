package kr.revelope.spring.batch.admin.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import kr.revelope.spring.batch.admin.mapper.JobExecutionMapper;
import kr.revelope.spring.batch.admin.model.JobExecution;
import kr.revelope.spring.batch.admin.model.JobExecutionParam;

@Repository
public class JobExecutionRepositoryImpl implements JobExecutionRepository {
	private final String tablePrefix;

	private final JobExecutionMapper jobExecutionMapper;

	@Autowired
	public JobExecutionRepositoryImpl(
			@Value("${spring.batch.table-prefix}") String tablePrefix,
			JobExecutionMapper jobExecutionMapper) {
		this.tablePrefix = tablePrefix;
		this.jobExecutionMapper = jobExecutionMapper;
	}

	@Override
	public JobExecution selectJobExecution(long jobExecutionId) {
		return jobExecutionMapper.selectJobExecution(jobExecutionId, tablePrefix);
	}

	@Override
	public int selectJobExecutionTotalCount(List<String> jobNameList) {
		return jobExecutionMapper.selectJobExecutionTotalCount(jobNameList, tablePrefix);
	}

	@Override
	public List<JobExecution> selectJobExecutionList(List<String> jobNameList, long start, long length) {
		return jobExecutionMapper.selectJobExecutionList(jobNameList, start, length, tablePrefix);
	}

	@Override
	public long selectMaxJobExecutionIdLessThan(String createDate) {
		return jobExecutionMapper.selectMaxJobExecutionIdLessThan(createDate, tablePrefix);
	}

	@Override
	public List<JobExecutionParam> selectJobExecutionParamList(long jobExecutionId) {
		return jobExecutionMapper.selectJobExecutionParamList(jobExecutionId, tablePrefix);
	}

	@Override
	public void deleteJobExecutionParamLessThan(long jobExecutionId) {
		jobExecutionMapper.deleteJobExecutionParamLessThan(jobExecutionId, tablePrefix);
	}

	@Override
	public void deleteJobExecutionContextLessThan(long jobExecutionId) {
		jobExecutionMapper.deleteJobExecutionContextLessThan(jobExecutionId, tablePrefix);
	}

	@Override
	public void deleteJobExecutionLessThan(long jobExecutionId) {
		jobExecutionMapper.deleteJobExecutionLessThan(jobExecutionId, tablePrefix);
	}
}
