package kr.revelope.spring.batch.admin.repository;

import java.util.List;

import kr.revelope.spring.batch.admin.model.JobExecution;
import kr.revelope.spring.batch.admin.model.JobExecutionParam;

public interface JobExecutionRepository {
    JobExecution selectJobExecution(long jobExecutionId);

    int selectJobExecutionTotalCount(List<String> jobNameList);

    List<JobExecution> selectJobExecutionList(List<String> jobNameList, long start, long length);

    long selectMaxJobExecutionIdLessThan(String createDate);

    long selectMaxJobInstanceIdLessThan(String createDate);

    List<JobExecutionParam> selectJobExecutionParamList(long jobExecutionId);

    void deleteJobExecutionParamLessThan(long jobExecutionId);

    void deleteJobExecutionContextLessThan(long jobExecutionId);

    void deleteJobExecutionLessThan(long jobExecutionId);
}
