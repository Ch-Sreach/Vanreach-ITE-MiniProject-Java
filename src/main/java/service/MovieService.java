package service;

import model.Movie;
import model.MovieResponse;

import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    MovieResponse getDummyMovie();
}
