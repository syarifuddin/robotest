package com.sen.toy;

import com.sen.toy.config.*;
import com.sen.toy.models.*;
import com.sen.toy.resources.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Optional;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebapiService extends Application<WebapiConfiguration> {
    private static final Logger LOG = LoggerFactory.getLogger(WebapiService.class);

    public static void main(String[] args) throws Exception {
        new WebapiService().run(args);
    }

    private final HibernateBundle<WebapiConfiguration> hibernateBundle = new HibernateBundle<WebapiConfiguration>(

            Robot.class,
            Void.class
        ) {
        @Override
        public DataSourceFactory getDataSourceFactory(WebapiConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "webapi";
    }

    @Override
    public void initialize(Bootstrap<WebapiConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/app/", "/", "index.html"));
        bootstrap.addBundle(hibernateBundle);
        ObjectMapper mapper = bootstrap.getObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void run(WebapiConfiguration configuration,
                    Environment environment) throws Exception {
        environment.jersey().setUrlPattern("/webapi/*");


        environment.jersey().register(new RobotResource());
    }
}
