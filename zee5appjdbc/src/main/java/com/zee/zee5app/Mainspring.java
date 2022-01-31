package com.zee.zee5app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.repository.UserRepository;

public class Mainspring {

	//private static final DefaultListableBeanFactory Config = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		UserRepository userRepository =applicationContext.getBean(UserRepository.class);
		
		System.out.println(userRepository);
		UserRepository userRepository2 =applicationContext.getBean(UserRepository.class);
		System.out.println(userRepository2);
		
		System.out.println(userRepository.hashCode());
		System.out.println(userRepository.hashCode());
		System.out.println(userRepository.equals(userRepository2));
		
		
		applicationContext.close();
	}

}
