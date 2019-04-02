package kr.revelope.spring.batch.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.revelope.spring.batch.admin.model.JobInstance;

@Mapper
public interface JobInstanceMysqlMapper {
	JobInstance selectJobInstance(@Param("jobInstanceId") long jobInstanceId, @Param("tablePrefix") String tablePrefix);

	void deleteJobInstanceLessThanOrEqualTo(@Param("jobInstanceId") long jobInstanceId, @Param("tablePrefix") String tablePrefix);
}
