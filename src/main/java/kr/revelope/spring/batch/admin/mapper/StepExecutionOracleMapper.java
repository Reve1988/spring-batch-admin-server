package kr.revelope.spring.batch.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.revelope.spring.batch.admin.model.StepExecution;

@Mapper
public interface StepExecutionOracleMapper {
	StepExecution selectStepExecution(@Param("stepExecutionId") long stepExecutionId, @Param("tablePrefix") String tablePrefix);

	List<StepExecution> selectStepExecutionList(@Param("jobExecutionId") long jobExecutionId, @Param("tablePrefix") String tablePrefix);

	long selectMaxStepExecutionId(@Param("jobExecutionId") long jobExecutionId, @Param("tablePrefix") String tablePrefix);

	void deleteStepExecutionContextLessThanOrEqualTo(@Param("stepExecutionId") long stepExecutionId, @Param("tablePrefix") String tablePrefix);

	void deleteStepExecutionLessThanOrEqualTo(@Param("stepExecutionId") long stepExecutionId, @Param("tablePrefix") String tablePrefix);
}
