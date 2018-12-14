package kr.revelope.spring.batch.admin.model;

import java.util.ArrayList;
import java.util.List;

public class PagedJobExecutions {
	private int total = 0;
	private List<JobExecution> jobExecutionList = new ArrayList<>();

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<JobExecution> getJobExecutionList() {
		return jobExecutionList;
	}

	public void setJobExecutionList(List<JobExecution> jobExecutionList) {
		this.jobExecutionList = jobExecutionList;
	}
}
