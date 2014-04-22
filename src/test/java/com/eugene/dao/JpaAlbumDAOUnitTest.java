package com.eugene.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.eugene.model.Album;
import com.eugene.model.Song;

public class JpaAlbumDAOUnitTest {

	private JpaAlbumDAO jpaAlbumDAO;
	private Album album;
	
	@Before
	public void setup() {
		
		album = new Album();
		album.setTitle("Californication");
		album.setArtist("Red Hot Chili Peppers");
		List<Song>songs = new ArrayList<Song>();
		Song s1 = new Song();
		s1.setAlbum(album);
		s1.setDuration("3:59");
		s1.setName("Around The World");
		Song s2 = new Song();
		s2.setAlbum(album);
		s2.setDuration("4:29");
		s2.setName("Parallel Universe");
		songs.add(s1);
		songs.add(s2);
		album.setSongs(songs);
	}

	@Test
	public void testCreateOrUpdateAlbum() {
		jpaAlbumDAO = new JpaAlbumDAO() {
			@Override
			public Album createOrUpdateAlbum(Album album) {
				album.setAlbumID(3l);
				return album;
			}

		};
		
		Album presistedAlbum = jpaAlbumDAO.createOrUpdateAlbum(album);
		assertNotNull(presistedAlbum.getAlbumID());
		assertEquals("Californication", presistedAlbum.getTitle());
		assertEquals("Red Hot Chili Peppers", presistedAlbum.getArtist());
		assertEquals(2, presistedAlbum.getSongs().size());
		//Now change the artist name and update album
		presistedAlbum.setArtist("RHCP");
		Album updatedAlbum = jpaAlbumDAO.createOrUpdateAlbum(presistedAlbum);
		assertEquals(presistedAlbum.getAlbumID(), updatedAlbum.getAlbumID());
		assertEquals("RHCP", presistedAlbum.getArtist());
	}

	@Test
	public void testDeleteAlbum() {
		jpaAlbumDAO = new JpaAlbumDAO() {
			@Override
			public Album createOrUpdateAlbum(Album album) {
				album.setAlbumID(4l);
				return album;
			}

			@Override
			public void deleteAlbum(Long albumID) {
				assertEquals(Long.valueOf(4l), albumID);
				album = null;
			}

			@Override
			public Album getAlbum(Long albumID) {
				return album;
			}
		};
		Album presistedAlbum = jpaAlbumDAO.createOrUpdateAlbum(album);
		assertNotNull(presistedAlbum.getAlbumID());
		jpaAlbumDAO.deleteAlbum(presistedAlbum.getAlbumID());
		Album deleteAlbum = jpaAlbumDAO.getAlbum(presistedAlbum.getAlbumID());
		assertNull(deleteAlbum);
	}

	@Test
	public void testGetAlbum() {
		jpaAlbumDAO = new JpaAlbumDAO() {
			@Override
			public Album getAlbum(Long albumID) {
				album.setAlbumID(1l);
				album.setArtist("Daft Punk");
				album.setTitle("Random Access Memories");
				return album;
			}
		};
		Album album = jpaAlbumDAO.getAlbum(1l);
		assertNotNull(album);
		assertEquals("Random Access Memories", album.getTitle());
		assertEquals("Daft Punk", album.getArtist());
	}

	@Test
	public void testGetAllAlbums() {
		jpaAlbumDAO = new JpaAlbumDAO() {
			@Override
			public List<Album> getAllAlbums() {
				List<Album> l = new ArrayList<Album>();
				l.add(album);
				l.add(album);
				return l;
			}
		};
		List<Album> albums = jpaAlbumDAO.getAllAlbums();
		assertEquals(2, albums.size());
	}

}
