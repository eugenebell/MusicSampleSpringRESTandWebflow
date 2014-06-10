package com.eugene.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eugene.dao.AlbumDAO;

@ContextConfiguration("file:src/test/resources/test-cache-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AlbumServiceFindWithCacheTest {

	@Autowired
	private AlbumServiceFindWithCache albumServiceFindWithCache;
	
	@Test
	public void test() {
		albumServiceFindWithCache.getAlbum(2l);
		System.out.println("About to make second call to albums");
		albumServiceFindWithCache.getAlbum(2l);
		System.out.println("Finished");
	}

}
