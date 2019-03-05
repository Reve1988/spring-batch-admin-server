package kr.revelope.spring.batch.admin.repository;

import kr.revelope.spring.batch.admin.model.JobInstance;

public interface JobInstanceRepository {
	JobInstance selectJobInstance(long jobInstanceId);

	void deleteJobInstanceLessThan(long jobInstanceId);
}
