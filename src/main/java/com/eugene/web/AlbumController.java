package com.eugene.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.eugene.service.AlbumService;

@Controller
public class AlbumController {

	@Autowired 
	private AlbumService albumService;

	@RequestMapping(value = "/deletealbum/{albumid}", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public String deleteAlbum(@PathVariable("albumid") Long albumID, Model model) {
		albumService.deleteAlbum(albumID);
		model.addAttribute("albums", albumService.getAllAlbums());
		return "albumlist";
	}

	@RequestMapping(value = "/albumdetails/{albumid}", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public String getAlbum(@PathVariable("albumid") Long albumID, Model model) {
		model.addAttribute("album", albumService.getAlbum(albumID));
		return "albumshow";
	}

	@RequestMapping(value = "/showalbums", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public String getAllAlbums(Model model) {
		model.addAttribute("albums", albumService.getAllAlbums());
		return "albumlist";
	}
	
}
