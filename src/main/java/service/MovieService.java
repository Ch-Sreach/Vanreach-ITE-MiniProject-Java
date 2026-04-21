package service;

import model.Movie;
import model.MovieInfo;
import model.MovieResponse;

import java.util.List;

public interface MovieService {

//    List<Movie> getAll();

    MovieResponse getDummyMovie();
    MovieResponse getDummyMovie(String movieName, int page);
    String getTrailer(int id);
    MovieInfo getMovieDetail(int id);
    MovieResponse getPopularMovie(int page);
    MovieResponse getTopRatedMovie(int page);
    MovieResponse getUpcoming(int page);
    MovieResponse getNowPlaying(int page);
}
