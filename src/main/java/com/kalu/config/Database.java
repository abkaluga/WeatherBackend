package com.kalu.config;

import com.kalu.models.db.Measurement;
import com.kalu.models.db.Station;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Optional;
import java.util.Properties;

/**
 * Created by Albert on 14.01.2016.
 */
public abstract class Database {


    private static Optional<Properties> db = Optional.empty();
    private static Optional<SessionFactory> sf = Optional.empty();


    private static Properties getProperties(){
        if (!db.isPresent()) {
            Properties prop= new Properties();
            String userHome = System.getProperty("user.home");
            prop.setProperty("hibernate.connection.url", "jdbc:hsqldb:file:" + userHome + "/backendDevelop/backend;shutdown=true;hsqldb.write_delay=false;");
            prop.setProperty("hibernate.connection.username", "SA");
            prop.setProperty("hibernate.connection.password", "");
            prop.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
            prop.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbc.JDBCDriver");
            prop.setProperty("hibernate.hbm2ddl.auto", "update");
            db = Optional.of(prop);
        }


        return db.get();
    }

    private static SessionFactory getSessionFactory() {
        if (!sf.isPresent()){
            Configuration conf = new Configuration();
            conf.addProperties(getProperties());
            conf.addPackage("com.kalu.models.db");
            conf.addAnnotatedClass(Station.class);
            conf.addAnnotatedClass(Measurement.class);

            StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();
            srb.applySettings(conf.getProperties());

            SessionFactory s = conf.buildSessionFactory(srb.build());

            sf = Optional.of(s);

        }

        return sf.get();
    }


    public static Session getSession(){
        return getSessionFactory().openSession();
    }

    public static StatelessSession getStatelessSesion(){
        return getSessionFactory().openStatelessSession();
    }
}
