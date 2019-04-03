package kr.revelope.spring.batch.admin.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import kr.revelope.spring.batch.admin.common.type.DatasourceType;
import kr.revelope.spring.batch.admin.mapper.JobExecutionMysqlMapper;
import kr.revelope.spring.batch.admin.mapper.JobExecutionOracleMapper;
import kr.revelope.spring.batch.admin.model.JobExecution;
import kr.revelope.spring.batch.admin.model.JobExecutionParam;

@Repository
public class JobExecutionRepositoryImpl implements JobExecutionRepository {
	private final String tablePrefix;
	private final DatasourceType datasourceType;

	private final JobExecutionOracleMapper jobExecutionOracleMapper;
	private final JobExecutionMysqlMapper jobExecutionMysqlMapper;

	@Autowired
	public JobExecutionRepositoryImpl(
			@Value("${spring.batch.table-prefix}") String tablePrefix,
			@Value("${spring.datasource.hikari.driver-class-name}") String driverClassName,
			JobExecutionOracleMapper jobExecutionOracleMapper,
			JobExecutionMysqlMapper jobExecutionMysqlMapper) {
		this.tablePrefix = tablePrefix;
		this.datasourceType = DatasourceType.get(driverClassName);
		this.jobExecutionOracleMapper = jobExecutionOracleMapper;
		this.jobExecutionMysqlMapper = jobExecutionMysqlMapper;
	}

	@Override
	public JobExecution selectJobExecution(long jobExecutionId) {
		if (DatasourceType.ORACLE == datasourceType) {
			return jobExecutionOracleMapper.selectJobExecution(jobExecutionId, tablePrefix);
		} else {
			return jobExecutionMysqlMapper.selectJobExecution(jobExecutionId, tablePrefix);
		}
	}

	@Override
	public int selectJobExecutionTotalCount(List<String> jobNameList) {
		if (DatasourceType.ORACLE == datasourceType) {
			return jobExecutionOracleMapper.selectJobExecutionTotalCount(jobNameList, tablePrefix);
		} else {
			return jobExecutionMysqlMapper.selectJobExecutionTotalCount(jobNameList, tablePrefix);
		}
	}

	@Override
	public List<JobExecution> selectJobExecutionList(List<String> jobNameList, long start, long length) {
		if (DatasourceType.ORACLE == datasourceType) {
			return jobExecutionOracleMapper.selectJobExecutionList(jobNameList, start, length, tablePrefix);
		} else {
			return jobExecutionMysqlMapper.selectJobExecutionList(jobNameList, start, length, tablePrefix);
		}
	}

	@Override
	public long selectMaxJobExecutionIdLessThan(String createDate) {
		if (DatasourceType.ORACLE == datasourceType) {
			return jobExecutionOracleMapper.selectMaxJobExecutionIdLessThan(createDate, tablePrefix);
		} else {
			return jobExecutionMysqlMapper.selectMaxJobExecutionIdLessThan(createDate, tablePrefix);
		}
	}

	@Override
	public long selectMaxJobInstanceIdLessThan(String createDate) {
		if (DatasourceType.ORACLE == datasourceType) {
			return jobExecutionOracleMapper.selectMaxJobInstanceIdLessThan(createDate, tablePrefix);
		} else {
			return jobExecutionMysqlMapper.selectMaxJobInstanceIdLessThan(createDate, tablePrefix);
		}
	}

	@Override
	public List<JobExecutionParam> selectJobExecutionParamList(long jobExecutionId) {
		if (DatasourceType.ORACLE == datasourceType) {
			return jobExecutionOracleMapper.selectJobExecutionParamList(jobExecutionId, tablePrefix);
		} else {
			return jobExecutionMysqlMapper.selectJobExecutionParamList(jobExecutionId, tablePrefix);
		}
	}

	@Override
	public void deleteJobExecutionParamLessThanOrEqualTo(long jobExecutionId) {
		if (DatasourceType.ORACLE == datasourceType) {
			jobExecutionOracleMapper.deleteJobExecutionParamLessThanOrEqualTo(jobExecutionId, tablePrefix);
		} else {
			jobExecutionMysqlMapper.deleteJobExecutionParamLessThanOrEqualTo(jobExecutionId, tablePrefix);
		}
	}

	@Override
	public void deleteJobExecutionContextLessThanOrEqualTo(long jobExecutionId) {
		if (DatasourceType.ORACLE == datasourceType) {
			jobExecutionOracleMapper.deleteJobExecutionContextLessThanOrEqualTo(jobExecutionId, tablePrefix);
		} else {
			jobExecutionMysqlMapper.deleteJobExecutionContextLessThanOrEqualTo(jobExecutionId, tablePrefix);
		}
	}

	@Override
	public void deleteJobExecutionLessThanOrEqualTo(long jobExecutionId) {
		if (DatasourceType.ORACLE == datasourceType) {
			jobExecutionOracleMapper.deleteJobExecutionLessThanOrEqualTo(jobExecutionId, tablePrefix);
		} else {
			jobExecutionMysqlMapper.deleteJobExecutionLessThanOrEqualTo(jobExecutionId, tablePrefix);
		}
	}
}
