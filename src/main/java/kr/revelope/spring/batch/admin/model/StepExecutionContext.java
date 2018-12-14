package kr.revelope.spring.batch.admin.model;

public class StepExecutionContext {
	private long stepExecutionId;
	private String shortContext;

	public long getStepExecutionId() {
		return stepExecutionId;
	}

	public void setStepExecutionId(long stepExecutionId) {
		this.stepExecutionId = stepExecutionId;
	}

	public String getShortContext() {
		return shortContext;
	}

	public void setShortContext(String shortContext) {
		this.shortContext = shortContext;
	}
}
