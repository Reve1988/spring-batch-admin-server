package kr.revelope.spring.batch.admin.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import kr.revelope.spring.batch.admin.common.type.DatasourceType;
import kr.revelope.spring.batch.admin.mapper.JobInstanceMysqlMapper;
import kr.revelope.spring.batch.admin.mapper.JobInstanceOracleMapper;
import kr.revelope.spring.batch.admin.model.JobInstance;

@Repository
public class JobInstanceRepositoryImpl implements JobInstanceRepository {
	private final String tablePrefix;
	private final DatasourceType datasourceType;

	private final JobInstanceOracleMapper jobInstanceOracleMapper;
	private final JobInstanceMysqlMapper jobInstanceMysqlMapper;

	public JobInstanceRepositoryImpl(
			@Value("${spring.batch.table-prefix}") String tablePrefix,
			@Value("${spring.datasource.hikari.driver-class-name}") String driverClassName,
			JobInstanceOracleMapper jobInstanceOracleMapper,
			JobInstanceMysqlMapper jobInstanceMysqlMapper) {
		this.tablePrefix = tablePrefix;
		this.datasourceType = DatasourceType.get(driverClassName);
		this.jobInstanceOracleMapper = jobInstanceOracleMapper;
		this.jobInstanceMysqlMapper = jobInstanceMysqlMapper;
	}

	@Override
	public JobInstance selectJobInstance(long jobInstanceId) {
		if (DatasourceType.ORACLE == datasourceType) {
			return jobInstanceOracleMapper.selectJobInstance(jobInstanceId, tablePrefix);
		} else {
			return jobInstanceMysqlMapper.selectJobInstance(jobInstanceId, tablePrefix);
		}
	}

	@Override
	public void deleteJobInstanceLessThan(long jobInstanceId) {
		if (DatasourceType.ORACLE == datasourceType) {
			jobInstanceOracleMapper.deleteJobInstanceLessThan(jobInstanceId, tablePrefix);
		} else {
			jobInstanceMysqlMapper.deleteJobInstanceLessThan(jobInstanceId, tablePrefix);
		}
	}
}
