package com.eugene.dao;

import java.util.List;

import com.eugene.model.Album;

public interface AlbumDAO {
	
	void deleteAlbum(Long albumID);
	
	Album createOrUpdateAlbum(Album album);
	
	Album getAlbum(Long albumID);
	
	List<Album> getAllAlbums();
}
