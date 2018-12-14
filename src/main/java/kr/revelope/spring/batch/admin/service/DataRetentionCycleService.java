package kr.revelope.spring.batch.admin.service;

import java.time.LocalDate;

public interface DataRetentionCycleService {
	void clearBefore(LocalDate date);
}
