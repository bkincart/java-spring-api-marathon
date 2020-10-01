package com.launchacademy.marathon.repositories;

import com.launchacademy.marathon.models.Song;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends PagingAndSortingRepository<Song, Integer> {
//  public Song findByTitle(String title);
//  public List<Song> findAllByGenre(String genre);
}
