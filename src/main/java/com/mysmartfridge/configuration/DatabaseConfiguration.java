package com.mysmartfridge.configuration;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mysmartfridge.MySmartFridge;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
@EnableJpaRepositories("com.mysmartfridge.domain")
@EnableTransactionManagement
public class DatabaseConfiguration {

    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    /**
     * Open the TCP port for the H2 database, so it is available remotely.
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    @Profile(MySmartFridge.PROFILE_DEV)
    public Server h2TCPServer() throws SQLException {
        return Server.createTcpServer("-tcp","-tcpAllowOthers");
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:liquibase/master.xml");
        log.debug("Configuring Liquibase");
        return liquibase;
    }
}