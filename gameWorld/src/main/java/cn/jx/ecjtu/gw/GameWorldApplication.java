package cn.jx.ecjtu.gw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class GameWorldApplication {

	public static void main(String[] args) {


	    ApplicationContext ac =SpringApplication.run(GameWorldApplication.class, args);
//        Arrays.stream(ac.getBeanDefinitionNames()).forEach(System.out::println);

	}

}
