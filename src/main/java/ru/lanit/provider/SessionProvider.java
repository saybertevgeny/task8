package ru.lanit.provider;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Logger;

public class SessionProvider {

    private static SessionProvider instance;
    private SessionFactory sessionFactory;
    private Logger logger;

    private SessionProvider(){
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

    public static SessionProvider getInstance(){
        if(instance == null){
            instance  = new SessionProvider();
        }
        return instance;
    }

    public Session getSession(){
        return this.sessionFactory.openSession();
    }
}
