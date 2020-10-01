package com.launchacademy.marathon.controllers;

import com.launchacademy.marathon.models.Song;
import com.launchacademy.marathon.repositories.SongRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SongsAPIController {
  private final SongRepository songRepository;

  @Autowired
  public SongsAPIController(SongRepository songRepository) {
    this.songRepository = songRepository;
  }

  @GetMapping("/v1/songs")
  public Iterable<Song> getSongs() {
    return songRepository.findAll();
  }

  @NoArgsConstructor
  private class SongNotFoundException extends RuntimeException {};

  @ControllerAdvice
  private class SongNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SongNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String songNotFoundHandler(SongNotFoundException exception) {
      return exception.getMessage();
    }
  }

  @GetMapping("/v1/songs/{id}")
  public Song getSingleSong(@PathVariable Integer id) {
    return songRepository.findById(id).orElseThrow(() -> new SongNotFoundException());
  }
}
