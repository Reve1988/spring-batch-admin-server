package kr.revelope.spring.batch.admin.service;

import kr.revelope.spring.batch.admin.model.JobInstance;

public interface JobInstanceService {
	JobInstance getJobInstance(long jobInstanceId);

	void deleteLessThanOrEqualTo(long jobInstanceId);
}
