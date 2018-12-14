package kr.revelope.spring.batch.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.revelope.spring.batch.admin.model.JobExecution;
import kr.revelope.spring.batch.admin.model.JobExecutionParam;

@Mapper
public interface JobExecutionMapper {
	JobExecution selectJobExecution(@Param("jobExecutionId") long jobExecutionId);

	int selectJobExecutionTotalCount(@Param("jobNameList") List<String> jobNameList);

	List<JobExecution> selectJobExecutionList(@Param("jobNameList") List<String> jobNameList, @Param("start") long start, @Param("length") long length);

	long selectMaxJobExecutionIdLessThan(@Param("createDate") String createDate);

	List<JobExecutionParam> selectJobExecutionParamList(@Param("jobExecutionId") long jobExecutionId);

	void deleteJobExecutionParamLessThan(@Param("jobExecutionId") long jobExecutionId);

	void deleteJobExecutionContextLessThan(@Param("jobExecutionId") long jobExecutionId);

	void deleteJobExecutionLessThan(@Param("jobExecutionId") long jobExecutionId);
}
