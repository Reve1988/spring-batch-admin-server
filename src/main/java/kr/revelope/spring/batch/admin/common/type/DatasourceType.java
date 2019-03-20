package kr.revelope.spring.batch.admin.common.type;

import kr.revelope.spring.batch.admin.common.exception.UnsupportedDatasourceException;

public enum DatasourceType {
	ORACLE("oracle.jdbc.driver.OracleDriver"),
	MY_SQL("com.mysql.jdbc.Driver");

	private final String driverClassName;

	DatasourceType(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public static DatasourceType get(String driverClassName) {
		for (DatasourceType type : values()) {
			if (type.getDriverClassName().equals(driverClassName)) {
				return type;
			}
		}

		throw new UnsupportedDatasourceException(String.format("%s is not support.", driverClassName));
	}
}
