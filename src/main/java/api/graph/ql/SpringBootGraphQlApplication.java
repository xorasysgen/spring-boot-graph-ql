package api.graph.ql;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class SpringBootGraphQlApplication {

	@PostConstruct
	public void postConstructTest() {
		System.out.println("PostConstruct system starting..");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootGraphQlApplication.class, args);
	}
	
	
	@PreDestroy
	public void preDestroyTest() {
		System.out.println("Pre destroy called to clean up system resources...");
	}

}
