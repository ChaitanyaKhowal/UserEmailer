package com.khowal.runner;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner  implements CommandLineRunner{

	@Value("${app.name}")
	private String name;
	@Override
	public void run(String... args) throws Exception {
		System.out.println(name);
		
	}

}
