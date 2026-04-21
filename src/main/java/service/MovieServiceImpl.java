package service;


import model.Movie;
import model.MovieResponse;

import java.net.http.HttpClient;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovieServiceImpl implements MovieService{

    private static final List<Movie> movies = new ArrayList<>();

    private static final HttpClient client =
            HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();

    @Override
    public List<Movie> getAll() {
        initData();
        return movies;
    }

    @Override
    public MovieResponse getDummyMovie() {
        return null;
    }

    private void initData() {
        Random random = new Random();
        for (int i = 1; i <= 40; i++) {
            Movie movie = new Movie();
            movie.setId(random.nextInt(9999));
            movie.setTitle("Pro" + random.nextInt(99));
            movie.setRelease(LocalDate.of(2000 + random.nextInt(25),
                    1 + random.nextInt(12),
                    1 + random.nextInt(28)));
            movie.setRate(
                    Double.valueOf(String.format("%.2f", random.nextDouble(9.99))
                    )
            );
            movie.setTrailer("https://www.youtube.com/watch?v=slDoEtT8wWc" + random.nextInt(99999));
            movies.add(movie);
        }
    }
}
