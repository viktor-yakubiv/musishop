package com.yakubiv.musishop.managers;

import com.yakubiv.musishop.models.Album;
import com.yakubiv.musishop.models.Artist;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

class Factory {
    private static SessionFactory factory = null;

    static SessionFactory getInstance() {
        if (factory != null) return factory;

        try {
            factory = new Configuration().
                    configure().
                    addAnnotatedClass(Artist.class).
                    addAnnotatedClass(Album.class).
                    buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object.\n" + ex);
            throw new ExceptionInInitializerError(ex);
        }

        return factory;
    }

    static void close() {
        factory.close();
        factory = null;
    }
}
