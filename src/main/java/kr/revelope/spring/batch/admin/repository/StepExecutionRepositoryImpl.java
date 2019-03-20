package kr.revelope.spring.batch.admin.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import kr.revelope.spring.batch.admin.common.type.DatasourceType;
import kr.revelope.spring.batch.admin.mapper.StepExecutionMysqlMapper;
import kr.revelope.spring.batch.admin.mapper.StepExecutionOracleMapper;
import kr.revelope.spring.batch.admin.model.StepExecution;

@Repository
public class StepExecutionRepositoryImpl implements StepExecutionRepository {
	private final String tablePrefix;
	private final DatasourceType datasourceType;

	private final StepExecutionOracleMapper stepExecutionOracleMapper;
	private final StepExecutionMysqlMapper stepExecutionMysqlMapper;

	public StepExecutionRepositoryImpl(
			@Value("${spring.batch.table-prefix}") String tablePrefix,
			@Value("${spring.datasource.hikari.driver-class-name}") String driverClassName,
			StepExecutionOracleMapper stepExecutionOracleMapper,
			StepExecutionMysqlMapper stepExecutionMysqlMapper) {
		this.tablePrefix = tablePrefix;
		this.datasourceType = DatasourceType.get(driverClassName);
		this.stepExecutionOracleMapper = stepExecutionOracleMapper;
		this.stepExecutionMysqlMapper = stepExecutionMysqlMapper;
	}

	@Override
	public StepExecution selectStepExecution(long stepExecutionId) {
		if (DatasourceType.ORACLE == datasourceType) {
			return stepExecutionOracleMapper.selectStepExecution(stepExecutionId, tablePrefix);
		} else {
			return stepExecutionMysqlMapper.selectStepExecution(stepExecutionId, tablePrefix);
		}
	}

	@Override
	public List<StepExecution> selectStepExecutionList(long jobExecutionId) {
		if (DatasourceType.ORACLE == datasourceType) {
			return stepExecutionOracleMapper.selectStepExecutionList(jobExecutionId, tablePrefix);
		} else {
			return stepExecutionMysqlMapper.selectStepExecutionList(jobExecutionId, tablePrefix);
		}
	}

	@Override
	public long selectMaxStepExecutionId(long jobExecutionId) {
		if (DatasourceType.ORACLE == datasourceType) {
			return stepExecutionOracleMapper.selectMaxStepExecutionId(jobExecutionId, tablePrefix);
		} else {
			return stepExecutionMysqlMapper.selectMaxStepExecutionId(jobExecutionId, tablePrefix);
		}
	}

	@Override
	public void deleteStepExecutionContextLessThan(long stepExecutionId) {
		if (DatasourceType.ORACLE == datasourceType) {
			stepExecutionOracleMapper.deleteStepExecutionContextLessThan(stepExecutionId, tablePrefix);
		} else {
			stepExecutionMysqlMapper.deleteStepExecutionContextLessThan(stepExecutionId, tablePrefix);
		}
	}

	@Override
	public void deleteStepExecutionLessThan(long stepExecutionId) {
		if (DatasourceType.ORACLE == datasourceType) {
			stepExecutionOracleMapper.deleteStepExecutionLessThan(stepExecutionId, tablePrefix);
		} else {
			stepExecutionMysqlMapper.deleteStepExecutionLessThan(stepExecutionId, tablePrefix);
		}
	}
}
