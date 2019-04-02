package kr.revelope.spring.batch.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.revelope.spring.batch.admin.model.JobExecution;
import kr.revelope.spring.batch.admin.model.JobExecutionParam;

@Mapper
public interface JobExecutionMysqlMapper {
	JobExecution selectJobExecution(@Param("jobExecutionId") long jobExecutionId, @Param("tablePrefix") String tablePrefix);

	int selectJobExecutionTotalCount(@Param("jobNameList") List<String> jobNameList, @Param("tablePrefix") String tablePrefix);

	List<JobExecution> selectJobExecutionList(@Param("jobNameList") List<String> jobNameList, @Param("start") long start, @Param("length") long length, @Param("tablePrefix") String tablePrefix);

	long selectMaxJobExecutionIdLessThan(@Param("createDate") String createDate, @Param("tablePrefix") String tablePrefix);

	long selectMaxJobInstanceIdLessThan(@Param("createDate") String createDate, @Param("tablePrefix") String tablePrefix);

	List<JobExecutionParam> selectJobExecutionParamList(@Param("jobExecutionId") long jobExecutionId, @Param("tablePrefix") String tablePrefix);

	void deleteJobExecutionParamLessThan(@Param("jobExecutionId") long jobExecutionId, @Param("tablePrefix") String tablePrefix);

	void deleteJobExecutionContextLessThan(@Param("jobExecutionId") long jobExecutionId, @Param("tablePrefix") String tablePrefix);

	void deleteJobExecutionLessThan(@Param("jobExecutionId") long jobExecutionId, @Param("tablePrefix") String tablePrefix);
}
