package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Review;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.repository.ArtistRepository;
import mk.finki.ukim.wp.lab.repository.BookRepository;
import mk.finki.ukim.wp.lab.repository.SongRepository;
import mk.finki.ukim.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository songRepository;
    private final SongRepository songRepo;

    public BookServiceImpl(BookRepository songRepository, SongRepository songRepo) {
        this.songRepository = songRepository;
        this.songRepo = songRepo;
    }

    @Override
    public List<Song> listSongs() {
        return songRepo.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        Song s = songRepo.findById(song.getId()).orElse(null);
        if (s == null) {
            return null;
        }
        s.getPerformers().add(artist);
        songRepo.save(s);
        return artist;
    }

    @Override
    public Song findByTrackId(String trackId) {
        return this.songRepo.findByTrackId(trackId).orElseThrow();
    }

    @Override
    public Song findById(Long id) {
        return this.songRepo.findById(id).orElse(null);
    }

    @Override
    public List<Song> findByCategory(String category) {
        return new ArrayList<>();
    }

    @Override
    public void saveSong(String title, String trackId, String genre, Integer releaseYear, Album album) {
        Song song = new Song();
        song.setTitle(title);
        song.setTrackId(trackId);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);
        this.songRepo.save(song);
    }

    @Override
    public void update(Long songId, String title, String trackId, String genre, int releaseYear, Album album) {
        Song song = this.findById(songId);
        song.setTitle(title);
        song.setTrackId(trackId);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);
        this.songRepo.save(song);
    }

    @Override
    public void delete(Long id) {
       Song s = this.songRepo.findById(id).orElseThrow();
       this.songRepo.delete(s);
    }

    @Override
    public void addReview(Long id, Review review) {
        Song song = this.songRepo.findById(id).orElseThrow();
        //Review review1 = new Review();
        song.getReviews().add(review);
        this.songRepo.save(song);
    }

    @Override
    public List<Song> findAllByAlbum_Id(Long albumId) {
         return this.songRepo.findAllByAlbum_Id(albumId);
    }

    @Override
    public List<Song> filterByGenre(String genre) {
        return songRepo.findAll().stream().filter(s -> s.getGenre().contains(genre)).collect(Collectors.toList());
    }


}
