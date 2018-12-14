package kr.revelope.spring.batch.admin.controller;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.revelope.spring.batch.admin.common.exception.InvalidParameterException;
import kr.revelope.spring.batch.admin.service.DataRetentionCycleService;

@RestController
@RequestMapping("/spring/batch/api")
public class DataRetentionCycleRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataRetentionCycleRestController.class);

	private final DataRetentionCycleService dataRetentionCycleService;

	@Autowired
	public DataRetentionCycleRestController(
			DataRetentionCycleService dataRetentionCycleService) {
		this.dataRetentionCycleService = dataRetentionCycleService;
	}

	/**
	 * clear spring batch meta data started before {year}-{month}-{day}
	 *
	 * @param year year
	 * @param month month
	 * @param day day
	 * @return clear result
	 */
	@GetMapping("/{year}/{month}/{day}")
	public boolean clearBefore(
			@PathVariable("year") int year,
			@PathVariable("month") int month,
			@PathVariable("day") int day) {
		try {
			LocalDate date = LocalDate.of(year, month, day);
			dataRetentionCycleService.clearBefore(date);
		} catch (DateTimeException e) {
			throw new InvalidParameterException("Invalid date format exception : " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Data retention cycle management failed. (before : {}-{}-{})", year, month, day, e);
			return false;
		}

		return true;
	}
}
