package com.findwise.homework;

import com.findwise.homework.controller.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class HomeworkApplicationTests {

	@Autowired
	private HomeController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
