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
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebapiService extends Application<WebapiConfiguration> {
    private static final Logger LOG = LoggerFactory.getLogger(WebapiService.class);

    public static void main(String[] args) throws Exception {
        new WebapiService().run(args);
    }

    @Override
    public String getName() {
        return "webapi";
    }

    @Override
    public void initialize(Bootstrap<WebapiConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/app/", "/", "index.html"));
        ObjectMapper mapper = bootstrap.getObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void run(WebapiConfiguration configuration,
                    Environment environment) throws Exception {



        AddCorsSupport(environment);

        environment.jersey().setUrlPattern("/webapi/*");


        environment.jersey().register(new RobotResource());
    }

    private void AddCorsSupport(Environment environment) {
        final Map<String, String> params = new HashMap<>();
        params.put("Access-Control-Allow-Origin", "/*");
        params.put("Access-Control-Allow-Credentials", "true");
        params.put("Access-Control-Expose-Headers", "true");
        params.put("Access-Control-Allow-Headers", "Content-Type, X-Requested-With");
        params.put("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

        FilterRegistration.Dynamic filter = environment.servlets()
            .addFilter("cors", CrossOriginFilter.class);

        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*", "/**");
        filter.setInitParameters(params);
    }
}
