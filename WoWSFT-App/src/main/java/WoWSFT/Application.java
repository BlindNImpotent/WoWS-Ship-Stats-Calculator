package WoWSFT;

import WoWSFT.parser.JsonParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

//@EnableScheduling
//@EnableCaching
@EnableAsync
@SpringBootApplication
public class Application extends SpringBootServletInitializer
{
    @PostConstruct
    void started()
    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Bean
    public ThreadPoolTaskExecutor executor()
    {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setThreadNamePrefix("Thread-");
        executor.initialize();

        return executor;
    }

    @Bean(value = "jsonParser")
    public JsonParser jsonParser()
    {
        return new JsonParser();
    }

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}