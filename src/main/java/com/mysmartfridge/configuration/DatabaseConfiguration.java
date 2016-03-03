package com.mysmartfridge.configuration;

import org.mongeez.Mongeez;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mapping.model.CamelCaseSplittingFieldNamingStrategy;
import org.springframework.data.mapping.model.FieldNamingStrategy;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;

@Configuration
@Import(value = MongoAutoConfiguration.class)
@EnableMongoRepositories("com.mysmartfridge.domain")
public class DatabaseConfiguration extends AbstractMongoConfiguration {

    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);
    
    @Override
    protected FieldNamingStrategy fieldNamingStrategy() {
    	return new CamelCaseSplittingFieldNamingStrategy("_");
    }
    
    @Autowired
    private Mongo mongo;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;
    
	@Override
	protected String getDatabaseName() {
		return databaseName;
	}

	@Override
	public Mongo mongo() throws Exception {
		return mongo;
	}

    @Bean
    public Mongeez mongeez() {
        log.debug("Configuring Mongeez");
        Mongeez mongeez = new Mongeez();
        mongeez.setFile(new ClassPathResource("/mongeez/master.xml"));
        mongeez.setMongo(mongo);
        mongeez.setDbName(databaseName);
        mongeez.process();
        return mongeez;
    }
    
}