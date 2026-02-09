package cn.metado.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootApplication(scanBasePackages = "cn.metado")
public class MetadoAdminApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MetadoAdminApplication.class, args);

        int definitionCount = run.getBeanDefinitionCount();
        log.info("项目beans count：{}",definitionCount);

        List<String> beanNames = Arrays.stream(run.getBeanDefinitionNames()).sorted().toList();
        for (String definitionName : beanNames) {
            log.info("项目bean：{}", definitionName);
        }
    }

}
