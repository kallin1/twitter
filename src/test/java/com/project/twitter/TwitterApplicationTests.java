package com.project.twitter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tweets.TweetsApplication;

@SpringBootTest(classes = TweetsApplication.class) // TwitterApplication 클래스를 명시적으로 지정
class TwitterApplicationTests {

	@Test
	void contextLoads() {
		// Spring Application Context가 잘 로드되는지 확인
	}

}
