package com.eugene.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.eugene.model.Album;
import com.eugene.model.Song;

@ContextConfiguration("file:src/test/resources/test-web-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class AlbumRestControllerTest {

	MediaType mType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
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
	public void testCreateAlbum() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        
		mockMvc.perform(post("/album")
                .contentType(mType)
                .content(mapper.writeValueAsBytes(album))
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(mType))
                .andExpect(content().string("{\"albumID\":5,\"title\":\"New Californication\",\"artist\":\"RHCP\",\"songs\":[{\"songID\":15,\"name\":\"New Around The World\",\"duration\":\"3:59\"},{\"songID\":16,\"name\":\"New Parallel Universe\",\"duration\":\"4:29\"}]}"));
    
	}

	
	@Test
	public void testUpdateAlbum() throws Exception {
		album.setTitle("Change me");
		album.setAlbumID(1l);
		ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        
		mockMvc.perform(put("/album/1")
                .contentType(mType)
                .content(mapper.writeValueAsBytes(album))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(mType))
                .andExpect(content().string("{\"albumID\":4,\"title\":\"Change me\",\"artist\":\"RHCP\",\"songs\":[{\"songID\":13,\"name\":\"New Around The World\",\"duration\":\"3:59\"},{\"songID\":14,\"name\":\"New Parallel Universe\",\"duration\":\"4:29\"}]}"));
    
	}
	
	
	@Test
	public void testDeleteAlbum() throws Exception {
		mockMvc.perform(delete("/album/1"))
        .andExpect(status().isOk())
        .andExpect(content().string(""));
    }

	@Test
	public void testGetAlbum() throws Exception {
		Album album = new Album();
		album.setAlbumID(2l);
		album.setTitle("Californication2");
		album.setArtist("Red Hot Chili Peppers");
		List<Song>songs = new ArrayList<Song>();
		Song s1 = new Song();
		s1.setAlbum(album);
		s1.setDuration("3:59");
		s1.setName("Around The World");
		songs.add(s1);
		album.setSongs(songs);
		ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		mockMvc.perform(post("/album")
                .contentType(mType)
                .content(mapper.writeValueAsBytes(album))
        )
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"albumID\":3,\"title\":\"Californication2\",\"artist\":\"Red Hot Chili Peppers\",\"songs\":[{\"songID\":12,\"name\":\"Around The World\",\"duration\":\"3:59\"}]}"))
		;
                
                
		mockMvc.perform(get("/album/3"))
        .andExpect(status().isOk())
        .andExpect(content().string("{\"albumID\":3,\"title\":\"Californication2\",\"artist\":\"Red Hot Chili Peppers\",\"songs\":[{\"songID\":12,\"name\":\"Around The World\",\"duration\":\"3:59\"}]}"))
		.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"))));

	}

	@Test
	public void testGetAllAlbums() throws Exception {
		//when(mockAlbumDAO.getAllAlbums()).thenReturn(Arrays.asList(album));
		
		mockMvc.perform(get("/album"))
        .andExpect(status().isOk())
        .andExpect(content().string("[{\"albumID\":3,\"title\":\"Californication2\",\"artist\":\"Red Hot Chili Peppers\",\"songs\":[{\"songID\":12,\"name\":\"Around The World\",\"duration\":\"3:59\"}]},{\"albumID\":4,\"title\":\"Change me\",\"artist\":\"RHCP\",\"songs\":[{\"songID\":13,\"name\":\"New Around The World\",\"duration\":\"3:59\"},{\"songID\":14,\"name\":\"New Parallel Universe\",\"duration\":\"4:29\"}]},{\"albumID\":5,\"title\":\"New Californication\",\"artist\":\"RHCP\",\"songs\":[{\"songID\":15,\"name\":\"New Around The World\",\"duration\":\"3:59\"},{\"songID\":16,\"name\":\"New Parallel Universe\",\"duration\":\"4:29\"}]}]"))
		.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"))));

	}

}
