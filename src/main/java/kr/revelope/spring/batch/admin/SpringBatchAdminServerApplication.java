package kr.revelope.spring.batch.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "kr.revelope.spring.batch.admin.mapper")
public class SpringBatchAdminServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBatchAdminServerApplication.class, args);
	}
}
