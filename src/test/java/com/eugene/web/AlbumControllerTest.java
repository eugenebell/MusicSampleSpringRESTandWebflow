package com.eugene.web;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.eugene.model.Album;
import com.eugene.model.Song;
import com.eugene.service.AlbumService;

@ContextConfiguration("file:src/test/resources/test-web-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class AlbumControllerTest {

	@Autowired
    private WebApplicationContext ctx;
	private MockMvc mockMvc;
	private Album album;
	
	static {
//		  System.setProperty("spring.profiles.active", "production");
		  System.setProperty("spring.profiles.active", "dev");
	 }
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		album = new Album();
		album.setTitle("New Californication");
		album.setArtist("RHCP");
		List<Song>songs = new ArrayList<Song>();
		Song s1 = new Song();
		s1.setAlbum(album);
		s1.setDuration("3:59");
		s1.setName("New Around The World");
		Song s2 = new Song();
		s2.setAlbum(album);
		s2.setDuration("4:29");
		s2.setName("New Parallel Universe");
		songs.add(s1);
		songs.add(s2);
		album.setSongs(songs);
	}

	@Test
	public void testDeleteAlbum() throws Exception {
		mockMvc.perform(get("/deletealbum/2"))
        .andExpect(status().isOk());
	}

	@Test
	public void testGetAlbum() throws Exception {
		mockMvc.perform(get("/albumdetails/1")).andExpect(status().isOk());
	}

	@Test
	public void testGetAllAlbums() throws Exception {
		mockMvc.perform(get("/showalbums")).andExpect(status().isOk());
	}

}
