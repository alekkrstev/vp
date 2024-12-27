package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.repository.AlbumRepository;
import mk.finki.ukim.wp.lab.repository.AlbumRepositoryy;
import mk.finki.ukim.wp.lab.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;
    private final AlbumRepositoryy albumRepositoryy;


    public AlbumServiceImpl(AlbumRepository albumRepository, AlbumRepositoryy albumRepositoryy) {
        this.albumRepository = albumRepository;
        this.albumRepositoryy = albumRepositoryy;
    }

    @Override
    public List<Album> findAll() {
        return albumRepositoryy.findAll();
    }

    @Override
    public Album findById(Long albumId) {
        return  this.albumRepositoryy.findById(albumId).orElse(null);
    }
}
