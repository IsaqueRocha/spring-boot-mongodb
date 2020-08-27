package com.example.springbootmongo.config;

import com.example.springbootmongo.domain.Post;
import com.example.springbootmongo.domain.User;
import com.example.springbootmongo.dto.AuthorDTO;
import com.example.springbootmongo.dto.CommentDTO;
import com.example.springbootmongo.repository.PostRepository;
import com.example.springbootmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
@SuppressWarnings("unused")
public class Instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post2 = new Post(null, sdf.parse("23/03/2018 "), "Bom dia", "Acordei feliz agora!", new AuthorDTO(maria));
        Post post1 = new Post(null, sdf.parse("21/03/2018 "), "Partiu Viagem", "Vou viajar para São Paulo!", new AuthorDTO(maria));

        CommentDTO  c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
        CommentDTO  c2 = new CommentDTO("Aproveite!", sdf.parse("23/03/2018"), new AuthorDTO(bob));
        CommentDTO  c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("25/03/2018"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));

        userRepository.save(maria);

    }
}
