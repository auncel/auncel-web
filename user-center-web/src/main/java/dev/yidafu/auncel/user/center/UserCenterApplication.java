package dev.yidafu.auncel.user.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableAutoConfiguration
public class UserCenterApplication {

	public static void main(String[] args) throws InterruptedException {

//			new SpringApplicationBuilder(UserCenterApplication.class)
//					.properties("spring.profiles.active=nacos").run(args);

		ConfigurableApplicationContext applicationContext = SpringApplication.run(UserCenterApplication.class, args);
//		while(true) {
//			//当动态配置刷新时，会更新到 Enviroment中，因此这里每隔一秒中从Enviroment中获取配置
//			String userName = applicationContext.getEnvironment().getProperty("user.name");
//			String userAge = applicationContext.getEnvironment().getProperty("user.age");
//			System.err.println("user name :" + userName + "; age: " + userAge);
//			TimeUnit.SECONDS.sleep(1);
//		}
	}

}


//org.apache.dubbo.remoting.RemotingException:
// client(url: dubbo://192.168.1.3:20881/com.alibaba.cloud.dubbo.service.DubboMetadataService?anyhost=true&application=user-center&bind.ip=192.168.1.3&bind.port=20881&check=false&codec=dubbo&deprecated=false&dubbo=2.0.2&dynamic=true&generic=true&group=problem-warehouse&heartbeat=60000&interface=com.alibaba.cloud.dubbo.service.DubboMetadataService&lazy=false&methods=getAllServiceKeys,getServiceRestMetadata,getExportedURLs,getAllExportedURLs&pid=2884&qos.enable=false&register.ip=192.168.1.3&release=2.7.4.1&remote.application=problem-warehouse&revision=2.2.0.RELEASE&side=consumer&sticky=false&timestamp=1585311816604&version=1.0.0)
// failed to connect to server /192.168.1.3:20881, error message is:Connection refused: no further information: /192.168.1.3:20881



//org.apache.dubbo.remoting.RemotingException: client(
// url: dubbo://192.168.1.3:20881/com.alibaba.cloud.dubbo.service.DubboMetadataService?anyhost=true&application=user-center&bind.ip=192.168.1.3&bind.port=20881&check=false&codec=dubbo&deprecated=false&dubbo=2.0.2&dynamic=true&generic=true&group=problem-warehouse&heartbeat=60000&interface=com.alibaba.cloud.dubbo.service.DubboMetadataService&lazy=false&methods=getAllServiceKeys,getServiceRestMetadata,getExportedURLs,getAllExportedURLs&pid=2884&qos.enable=false&register.ip=192.168.1.3&release=2.7.4.1&remote.application=problem-warehouse&revision=2.2.0.RELEASE&side=consumer&sticky=false&timestamp=1585311816604&version=1.0.0) failed to connect to server /192.168.1.3:20881, error message is:Connection refused: no further information: /192.168.1.3:20881
