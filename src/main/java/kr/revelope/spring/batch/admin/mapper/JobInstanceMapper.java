package kr.revelope.spring.batch.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.revelope.spring.batch.admin.model.JobInstance;

@Mapper
public interface JobInstanceMapper {
	JobInstance selectJobInstance(@Param("jobInstanceId") long jobInstanceId);

	void deleteJobInstanceLessThan(@Param("jobInstanceId") long jobInstanceId);
}
