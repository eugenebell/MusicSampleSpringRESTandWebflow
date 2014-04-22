package com.eugene.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.eugene.model.Album;
import com.eugene.service.AlbumService;

@Controller
public class AlbumRestController {

	@Autowired 
	private AlbumService albumService;
	
	//curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X PUT -d '{"albumID":6,"title":"Bad Blood22","artist":"Bastille","songs":[{"name":"Pompeii","duration":"3:34"},{"name":"Pompeii22","duration":"2:22"}]}' http://localhost:8080/MusicChallengeTwo/album/6
	@RequestMapping(value = "/album/{albumid}", method = RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody Album updateAlbum(@RequestBody Album album) {
		return albumService.createOrUpdateAlbum(album);
	}
	
	//curl -i -H "Accept: application/json" -H "Content-Type:application/json" -X POST -d '{"title":"Bad Blood","artist":"Bastille","songs":[{"name":"Pompeii","duration":"3:34"}]}' http://localhost:8080/MusicChallengeTwo/album
	@RequestMapping(value = "/album", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public @ResponseBody Album createAlbum(@RequestBody Album album) {
		return albumService.createOrUpdateAlbum(album);
	}

	//curl -i -H "Accept: application/json" -X DELETE http://localhost:8080/MusicChallengeTwo/album/1
	@RequestMapping(value = "/album/{albumid}", method = RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.OK)
	public void deleteAlbum(@PathVariable("albumid") Long albumID) {
		albumService.deleteAlbum(albumID);
	}

	//curl -i -H "Accept: application/json" http://localhost:8080/MusicChallengeTwo/album/1
	@RequestMapping(value = "/album/{albumid}", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody Album getAlbum(@PathVariable("albumid") Long albumID) {
		return albumService.getAlbum(albumID);
	}

	@RequestMapping(value = "/album", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody List<Album> getAllAlbums() {
		return albumService.getAllAlbums();
	}
	
}
