package com.launchacademy.marathon.controllers;

import com.launchacademy.marathon.models.Song;
import com.launchacademy.marathon.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/songs")
public class SongsController {
  private SongRepository repo;

  @Autowired
  public SongsController(SongRepository repo) {
    this.repo = repo;
  }

//  @GetMapping
//  public String listSongs(Model model){
//    model.addAttribute("songs", repo.findAll());
//    return "songs/index";
//  }

  // we can test this by adding a query string as so:
  // http://localhost:8080/songs?size=2&page=1
  @GetMapping
  public String listSongs(Pageable pageable, Model model){
    model.addAttribute("songs", repo.findAll(pageable));
    return "songs/index";
  }

  @GetMapping("/new")
  public String newSong (@ModelAttribute Song song) {
    return "songs/new";
  }

//  @GetMapping("/new")
//  public String newSong (Model model) {
//    Song song = new Song();
//    model.addAttribute("song", song);
//    return "songs/new";
//  }

  @PostMapping
  // Mention that this does work!
  public String addSong(@ModelAttribute Song song){
    repo.save(song);
    return "redirect:/songs/new";
  }
}
