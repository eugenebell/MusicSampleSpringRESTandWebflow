package com.eugene.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eugene.model.Album;
import com.eugene.model.Song;

@ContextConfiguration("file:src/test/resources/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaAlbumDAOIntegrationTest {

	@Autowired
	private AlbumDAO jpaAlbumDAO;
	private Album album;
	
	static {
//		  System.setProperty("spring.profiles.active", "production");
		  System.setProperty("spring.profiles.active", "dev");
	 }
	
	@Before
	public void setUp() throws Exception {
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
	@Transactional
	public void testCreateOrUpdateAlbum() {
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
		Album presistedAlbum = jpaAlbumDAO.createOrUpdateAlbum(album);
		assertNotNull(presistedAlbum.getAlbumID());
		jpaAlbumDAO.deleteAlbum(presistedAlbum.getAlbumID());
		Album deleteAlbum = jpaAlbumDAO.getAlbum(presistedAlbum.getAlbumID());
		assertNull(deleteAlbum);
	}

	@Test
	public void testGetAlbum() {
		Album album = jpaAlbumDAO.getAlbum(1l);
		assertNotNull(album);
		assertEquals("Random Access Memories", album.getTitle());
		assertEquals("Daft Punk", album.getArtist());
	}

	@Test
	public void testGetAllAlbums() {
		List<Album> albums = jpaAlbumDAO.getAllAlbums();
		assertEquals(2, albums.size());
	}
	
	@Test
	public void testCreateOrUpdateAlbumTransaction() {
		assertEquals(2, jpaAlbumDAO.getAllAlbums().size());
		album.setTitle(null);
		try {
			jpaAlbumDAO.createOrUpdateAlbum(album);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		assertEquals(2, jpaAlbumDAO.getAllAlbums().size());
	}

}
