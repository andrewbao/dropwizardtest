package com.akqa.andrew.dropwizardtest;

import com.akqa.andrew.dropwizardtest.configs.DropwizardTestConfiguration;
import com.akqa.andrew.dropwizardtest.core.Store;
import com.akqa.andrew.dropwizardtest.dao.CityDAO;
import com.akqa.andrew.dropwizardtest.dao.StoreDAO;
import com.akqa.andrew.dropwizardtest.health.DAOHealthCheck;
import com.akqa.andrew.dropwizardtest.resources.StoreAPIResource;
import com.akqa.andrew.dropwizardtest.resources.StoreListResource;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.jdbi.DBIFactory;
import com.yammer.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import com.yammer.dropwizard.views.ViewBundle;
import org.skife.jdbi.v2.DBI;

/**
 * Created by andrew.bao on 2/19/14.
 */
public class DropwizardTestService extends Service<DropwizardTestConfiguration> {

    private final HibernateBundle<DropwizardTestConfiguration> hibernate = new HibernateBundle<DropwizardTestConfiguration>(Store.class) {
        @Override
        public DatabaseConfiguration getDatabaseConfiguration(DropwizardTestConfiguration configuration) {
            return configuration.getDatabaseConfiguration();
        }
    };

    public static void main(String[] args) throws Exception {
        new DropwizardTestService().run(args);
    }

    @Override
    public void initialize(Bootstrap<DropwizardTestConfiguration> bootstrap) {
        bootstrap.setName("Dropwizard Test");

        bootstrap.addBundle(new ViewBundle());

        bootstrap.addBundle(new AssetsBundle("/assets/style", "/style"));
        bootstrap.addBundle(new AssetsBundle("/assets/script", "/script"));

        bootstrap.addBundle(new DBIExceptionsBundle());

        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(DropwizardTestConfiguration config, Environment environment) throws ClassNotFoundException {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, config.getDatabaseConfiguration(), "mysql");
        environment.addHealthCheck(new DAOHealthCheck("DAO check"));

        // Guice injection
        // Injector injector = createInjectorForFakeData(config);
        Injector injector = createInjectorForMySql(jdbi, config);
        // Injector injector = createInjectorForHibernate(jdbi, config);
        environment.addResource(injector.getInstance(StoreListResource.class));
        environment.addResource(injector.getInstance(StoreAPIResource.class));

    }

    private Injector createInjectorForFakeData(final DropwizardTestConfiguration conf) {
        return Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(CityDAO.class).to(com.akqa.andrew.dropwizardtest.dao.fake.CityDAO.class);
                bind(StoreDAO.class).to(com.akqa.andrew.dropwizardtest.dao.fake.StoreDAO.class);
            }
        });
    }

    private Injector createInjectorForMySql(final DBI jdbi, final DropwizardTestConfiguration conf) {
        return Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(DBI.class).toInstance(jdbi);
                bind(CityDAO.class).toInstance(jdbi.onDemand(com.akqa.andrew.dropwizardtest.dao.mysql.CityDAO.class));
                bind(StoreDAO.class).toInstance(jdbi.onDemand(com.akqa.andrew.dropwizardtest.dao.mysql.StoreDAO.class));
            }
        });
    }

    private Injector createInjectorForHibernate(final DBI jdbi, final DropwizardTestConfiguration conf) {
        return Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(CityDAO.class).toInstance(new com.akqa.andrew.dropwizardtest.dao.hibernate.CityDAO(hibernate.getSessionFactory()));
                bind(StoreDAO.class).toInstance(new com.akqa.andrew.dropwizardtest.dao.hibernate.StoreDAO(hibernate.getSessionFactory()));
            }
        });
    }
}
