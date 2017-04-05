package pl.sda;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by pzawa on 05.04.2017.
 */

@EnableJpaRepositories(basePackages = {"pl.sda.dao"})
@SpringBootApplication(scanBasePackages = {"pl.sda.dao", "pl.sda.domain", "pl.sda.rest"})
@EnableTransactionManagement
@EnableSwagger2
@EntityScan(basePackages = {"pl.sda.domain"})
public class Application {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    @Bean
    ObjectMapper customizeJacksonConfiguration() {
        ObjectMapper mapper = new ObjectMapper();
        Hibernate5Module hibernate5Module = new Hibernate5Module();
        hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
        mapper.registerModule(hibernate5Module);
        return mapper;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(DataSource ds, EntityManagerFactory emf) {

        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(ds);
        jpaTransactionManager.setEntityManagerFactory(emf);
        return jpaTransactionManager;
    }


    @Bean
    public Docket api(@Value("${info.name}") String projectName, @Value("${info.version}") String projectVersion) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("springboot")
                .apiInfo(apiInfo(projectName, projectVersion))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(String  projectName, String projectVersion) {
        return new ApiInfoBuilder()
                .title(projectName + ": SpringBoot REST API with Swagger")
                .version(projectVersion)
                .build();
    }

}
