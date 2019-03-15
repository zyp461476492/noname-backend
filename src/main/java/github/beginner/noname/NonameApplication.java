package github.beginner.noname;

import github.beginner.noname.util.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;


/**
 * ServletComponentScan:
 *  servlet, Filter, Listener可以直接通过@WebServlet @WebFilter @WebListener来进行自动注册
 * @author springboot.io
 */
@SpringBootApplication
@ServletComponentScan
public class NonameApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(NonameApplication.class, args);
		SpringContextUtil.setApplicationContext(applicationContext);
	}
}
