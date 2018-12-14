package kr.revelope.spring.batch.admin.model;

public class JobExecutionContext {
	private long jobExecutionId;
	private String shortContext;

	public long getJobExecutionId() {
		return jobExecutionId;
	}

	public void setJobExecutionId(long jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}

	public String getShortContext() {
		return shortContext;
	}

	public void setShortContext(String shortContext) {
		this.shortContext = shortContext;
	}
}
