package com.yakubiv.musishop.managers;

import java.util.List;

import com.yakubiv.musishop.models.Artist;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;


public class ArtistManager {
    private static SessionFactory factory = Factory.getInstance();

    public static void main(String[] args) {
        ArtistManager manager = new ArtistManager();

        Integer artistId1 = manager.addArtist("The Beatles");
        Integer artistId2 = manager.addArtist("Rolling Stones");
        Integer artistId3 = manager.addArtist("KISS");

        manager.listArtists();

        manager.updateArtist(artistId1, "Beatles");
        manager.deleteArtist(artistId2);

        System.out.println("==========");
        manager.listArtists();

        Factory.close();
    }

    public Integer addArtist(String name) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer artistId = null;
        try {
            tx = session.beginTransaction();
            Artist artist = new Artist(name);
            artistId = (Integer) session.save(artist);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return artistId;
    }

    public void listArtists() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List artists = session.createQuery("FROM Artist").list();
            for (Object artistObj : artists) {
                Artist artist = (Artist) artistObj;
                System.out.println(artist.getName());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateArtist(Integer artistId, String name) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Artist artist =
                    (Artist) session.get(Artist.class, artistId);
            artist.setName(name);
            session.update(artist);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteArtist(Integer artistId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Artist artist =
                    (Artist) session.get(Artist.class, artistId);
            session.delete(artist);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}