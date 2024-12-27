package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Category;
import mk.finki.ukim.wp.lab.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findAllByAlbum_Id(Long albumId);
    List<Song> findAllByCategory(Category categoryId);
    List<Song> findAllByAlbum_IdAndCategory_Id(Long albumId, Long categoryId);
    Optional<Song> findByTrackId(String trackId);
    List<Song> findAll();
    Song save(Song song);
    //Optional<Category> findCategoryByCategory(String category);

}
