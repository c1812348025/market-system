package com.example.marketsystem;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MarketsystemApplicationTests {

	@Test
	void contextLoads() {
		String hashAlgorithmName = "MD5";//加密方式
		Object crdentials = "123";//密码原值
		Object salt = "root9863";//盐值
		int hashIterations = 2;//加密1024次
		Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
		System.out.println(result);
	}

}
