package com.eugene.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eugene.dao.AlbumDAO;
import com.eugene.model.Album;
import com.eugene.model.Song;

@Service
@Transactional(readOnly = true)
public class AlbumService {
	
	@Autowired
	private AlbumDAO albumRepository;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Album createOrUpdateAlbum(Album album) {
		//need to add album to each song due to @jsonignore!
		List<Song> l = album.getSongs();
		if (l != null) {
			for (Song s : l) {
				s.setAlbum(album);
			}
		}
		return albumRepository.createOrUpdateAlbum(album);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteAlbum(Long albumID) {
		albumRepository.deleteAlbum(albumID);
	}

	public Album getAlbum(Long albumID) {
		return albumRepository.getAlbum(albumID);
	}

	public List<Album> getAllAlbums() {
		return albumRepository.getAllAlbums();
	}
}
