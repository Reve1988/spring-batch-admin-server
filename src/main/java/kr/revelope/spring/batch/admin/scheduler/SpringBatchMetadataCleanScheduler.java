package kr.revelope.spring.batch.admin.scheduler;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.revelope.spring.batch.admin.service.DataRetentionCycleService;

@Component
public class SpringBatchMetadataCleanScheduler {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBatchMetadataCleanScheduler.class);

	private final long dataPeriodDays;

	private final DataRetentionCycleService dataRetentionCycleService;

	@Autowired
	public SpringBatchMetadataCleanScheduler(
			@Value("${spring.batch.meta.period-days}") long dataPeriodDays,
			DataRetentionCycleService dataRetentionCycleService) {
		this.dataPeriodDays = dataPeriodDays;
		this.dataRetentionCycleService = dataRetentionCycleService;
	}

	@Scheduled(cron = "${spring.batch.cleaner.cron}")
	public void clean() {
		LOGGER.info("Start.");

		try {
			LocalDate standardDate = LocalDate.now().minusDays(dataPeriodDays);
			dataRetentionCycleService.clearBefore(standardDate);
		} catch (Exception e) {
			LOGGER.error("Exception occured.", e);
		}

		LOGGER.info("End.");
	}
}
