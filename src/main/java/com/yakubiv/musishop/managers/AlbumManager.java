package com.yakubiv.musishop.managers;

import com.yakubiv.musishop.models.Album;
import com.yakubiv.musishop.models.Artist;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class AlbumManager {
    private static SessionFactory factory = Factory.getInstance();

    public static void main(String[] args) {
        AlbumManager albumManager = new AlbumManager();
        ArtistManager artistManager = new ArtistManager();

        Integer artistId = artistManager.addArtist("U2");

        Integer albumId1 = albumManager.addAlbum(artistId, "Boy", 1980);
        Integer albumId2 = albumManager.addAlbum(artistId, "October", 1981);
        Integer albumId3 = albumManager.addAlbum(artistId, "War", 1982);

        albumManager.listAlbums();

        albumManager.updateAlbum(albumId3, "War", 1983);
        albumManager.deleteAlbum(albumId2);

        System.out.println("==========");
        albumManager.listAlbums();

        Factory.close();
    }

    public Integer addAlbum(Integer artistId, String name, Integer releaseYear) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer albumId = null;
        try {
            tx = session.beginTransaction();
            Album album = new Album(session.load(Artist.class, artistId),
                    name, releaseYear);
            albumId = (Integer) session.save(album);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return albumId;
    }

    public List<Album> listAlbums() {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Album> albums = null;
        try {
            tx = session.beginTransaction();
            albums = session.createQuery("FROM Album").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return albums;
    }

    public void updateAlbum(Integer albumId, String name, Integer releaseYear) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Album album =
                    (Album) session.get(Album.class, albumId);
            album.setName(name);
            album.setReleaseYear(releaseYear);
            session.update(album);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteAlbum(Integer albumId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Album album =
                    (Album) session.get(Album.class, albumId);
            session.delete(album);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}