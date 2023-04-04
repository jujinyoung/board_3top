package education.top;

import education.top.config.MySQLConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(MySQLConfig.class)
@SpringBootApplication(scanBasePackages = "education.top.web")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
