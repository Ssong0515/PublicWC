package bitc.fullstack405.publicwc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;

//@Configuration
//public class DbConfig {
//
//    private final Environment env;
//
//    public DbConfig(Environment env) {
//        this.env = env;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setDriverClassName(env.getProperty("spring.datasource.hikari.driver-class-name"));
//        hikariConfig.setJdbcUrl(env.getProperty("spring.datasource.hikari.jdbc-url"));
//        hikariConfig.setUsername(env.getProperty("spring.datasource.hikari.username"));
//        hikariConfig.setPassword(env.getProperty("spring.datasource.hikari.password"));
//        hikariConfig.setConnectionTestQuery(env.getProperty("spring.datasource.hikari.connection-test-query"));
//
//        return new HikariDataSource(hikariConfig);
//    }
//
//    // Hibernate 설정을 위한 Properties Bean
//    @Bean
//    public Properties hibernateConfig() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//        properties.setProperty("hibernate.hbm2ddl.auto", "create");
//        properties.setProperty("hibernate.show_sql", "true");
//        properties.setProperty("hibernate.format_sql", "true");
//        return properties;
//    }
//}
