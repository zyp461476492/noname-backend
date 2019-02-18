package github.beginner.noname;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 * ServletComponentScan:
 *  servlet, Filter, Listener可以直接通过@WebServlet @WebFilter @WebListener来进行自动注册
 * @author springboot.io
 */
@SpringBootApplication
@ServletComponentScan
public class NonameApplication {
	public static void main(String[] args) {
		SpringApplication.run(NonameApplication.class, args);
	}
}
