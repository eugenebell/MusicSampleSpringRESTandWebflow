package com.eugene.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.eugene.model.Album;
import com.eugene.model.Song;

@Service
public class AlbumServiceFindWithCache {
	
	Map<Long, Album> albums = new HashMap<Long, Album>();
	 
	public void init() {
		Album a1 = new Album();
		a1.setAlbumID(1l);
		a1.setArtist("bob");
		a1.setSongs(new ArrayList<Song>());
		a1.setTitle("album 1");
		albums.put(2l, a1);
	}
	
	@Cacheable(value="albums", key="#albumID")
	public Album getAlbum(Long albumID) {
		System.out.println("Call getAlbum for ablum with id : " + albumID);
		return albums.get(albumID);
	}
}
