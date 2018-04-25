package com.ceiec.webguide.formal;

import com.ceiec.webguide.formal.utils.RedisUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.ceiec.webguide.formal.mapper","com.ceiec.webguide.formal.dao"})
public class FormalApplication {

	public static void main(String[] args) {

        RedisUtil.init();

		SpringApplication.run(FormalApplication.class, args);
	}
}
