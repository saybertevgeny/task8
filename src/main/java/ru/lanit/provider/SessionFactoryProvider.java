package ru.lanit.provider;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Logger;

public class SessionFactoryProvider {

    private static SessionFactoryProvider instance;
    private SessionFactory sessionFactory;
    private Logger logger;

    private SessionFactoryProvider(){
        initLoger();
        try {
            sessionFactory = (new Configuration()).configure().buildSessionFactory();
        }catch (Exception e){
            logger.warning(e.getMessage());
        }
    }

    private void initLoger(){
        logger = Logger.getLogger(this.getClass().getName());
    }

    public static SessionFactoryProvider getInstance(){
        if(instance == null){
            instance  = new SessionFactoryProvider();
        }
        return instance;
    }

    public SessionFactory getSessionFactory(){
        return this.sessionFactory;
    }
}
