package kr.revelope.spring.batch.admin.service;

import java.time.LocalDate;
import java.util.List;

import kr.revelope.spring.batch.admin.model.JobExecution;
import kr.revelope.spring.batch.admin.model.JobExecutionParam;

public interface JobExecutionService {
	JobExecution getJobExecution(long jobExecutionId);

	int getTotalCount(List<String> jobNameList);

	List<JobExecution> getJobExecutionList(List<String> jobNameList, long start, long length);

	long getMaxJobExecutionIdBefore(LocalDate date);

	long getMaxJobInstanceIdBefore(LocalDate date);

	List<JobExecutionParam> getJobExecutionParamList(long jobExecutionId);

	void deleteLessThanOrEqualTo(long jobExecutionId);
}
