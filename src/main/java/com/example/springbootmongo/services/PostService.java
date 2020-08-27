package com.example.springbootmongo.services;

import com.example.springbootmongo.domain.Post;
import com.example.springbootmongo.repository.PostRepository;
import com.example.springbootmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository repo;

    public List<Post> findAll() {
        return repo.findAll();
    }

    public Post findById(String id) {
        Optional<Post> userOptional = repo.findById(id);
        return userOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }
}
