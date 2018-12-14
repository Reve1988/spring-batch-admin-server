package kr.revelope.spring.batch.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.revelope.spring.batch.admin.model.StepExecution;

@Mapper
public interface StepExecutionMapper {
	StepExecution selectStepExecution(@Param("stepExecutionId") long stepExecutionId);

	List<StepExecution> selectStepExecutionList(@Param("jobExecutionId") long jobExecutionId);

	long selectMaxStepExecutionId(@Param("jobExecutionId") long jobExecutionId);

	void deleteStepExecutionContextLessThan(@Param("stepExecutionId") long stepExecutionId);

	void deleteStepExecutionLessThan(@Param("stepExecutionId") long stepExecutionId);
}
