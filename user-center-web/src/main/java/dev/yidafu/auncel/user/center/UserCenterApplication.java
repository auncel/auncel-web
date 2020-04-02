package dev.yidafu.auncel.user.center;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserCenterApplication {

	public static void main(String[] args) throws InterruptedException {

//			new SpringApplicationBuilder(UserCenterApplication.class)
//					.properties("spring.profiles.active=nacos").run(args);

		SpringApplication.run(UserCenterApplication.class, args);

//		while(true) {
//			//当动态配置刷新时，会更新到 Enviroment中，因此这里每隔一秒中从Enviroment中获取配置
//			String userName = applicationContext.getEnvironment().getProperty("user.name");
//			String userAge = applicationContext.getEnvironment().getProperty("user.age");
//			System.err.println("user name :" + userName + "; age: " + userAge);
//			TimeUnit.SECONDS.sleep(1);
//		}
	}
}
