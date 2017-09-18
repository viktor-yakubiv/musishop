package com.yakubiv.musishop.services;

import com.yakubiv.musishop.managers.AlbumManager;
import com.yakubiv.musishop.managers.ArtistManager;
import com.yakubiv.musishop.managers.Factory;
import com.yakubiv.musishop.models.Album;
import com.yakubiv.musishop.models.Artist;

public class SoapService {
	ArtistManager artistManager = new ArtistManager();
	AlbumManager albumManager = new AlbumManager();
	
	public SoapService() {}
	
	public Integer addArtist(String name) {
        return this.artistManager.addArtist(name);
    }

    public String listArtists() {
    	String serialized = "";
    	
        for (Artist artist: this.artistManager.listArtists()) {
        	serialized += artist.getId() + ": " + artist.toString() + "\n";
        }
        
        return serialized;
    }

    public void updateArtist(Integer artistId, String name) {
    	this.artistManager.updateArtist(artistId, name);
    }

    public void deleteArtist(Integer artistId) {
        this.artistManager.deleteArtist(artistId);
    }
    
    public Integer addAlbum(Integer artistId, String name, Integer releaseYear) {
        return this.albumManager.addAlbum(artistId, name, releaseYear);
    }

    public String listAlbums() {
    	String serialized = "";
    	
        for (Album album: this.albumManager.listAlbums()) {
        	serialized += album.getId() + ": " + album.toString() + "\n";
        }
        
        return serialized;
    }

    public void updateAlbum(Integer albumId, String name, Integer releaseYear) {
        this.albumManager.updateAlbum(albumId, name, releaseYear);
    }

    public void deleteAlbum(Integer albumId) {
        this.albumManager.deleteAlbum(albumId);
    }
    
    public static void main(String args[]) {
    	SoapService manager = new SoapService();
    	
    	Integer artistId = manager.addArtist("U2");
    	manager.addAlbum(artistId, "War", 1983);
    	
    	String albums = manager.listAlbums();
    	System.out.print(albums);
    	
    	Factory.close();
    }
}
