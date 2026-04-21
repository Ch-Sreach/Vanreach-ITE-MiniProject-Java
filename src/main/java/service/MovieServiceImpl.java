package service;

import model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MovieServiceImpl implements MovieService {

    private static final List<Movie> movies = new ArrayList<>();

    private static final HttpClient client =
            HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String API_KEY = "a2bd55a84889f3be6f199cf8725455e2";
    private static final String TOKEN   = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhMmJkNTVhODQ4ODlmM2JlNmYxOTljZjg3MjU0NTVlMiIsIm5iZiI6MTc3NjY3NDM5NC4zNzQsInN1YiI6IjY5ZTVlNjVhZmE1N2NjMTUxYzA2NDFiNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.GWXxGBiwBz4H--g_fT6hta_pmyEjDxn1jkit6LFrXSk";

    // ── Helper to build a standard authenticated GET request ──────────────────
    private HttpRequest buildRequest(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + TOKEN)
                .header("Accept", "application/json")
                .GET()
                .build();
    }

    // ── Helper to send a request and deserialize to MovieResponse ─────────────
    private MovieResponse fetchMovieResponse(String url) {
        try {
            HttpResponse<String> response = client.send(
                    buildRequest(url), HttpResponse.BodyHandlers.ofString());
            return mapper.readValue(response.body(), MovieResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // ── Trailer ───────────────────────────────────────────────────────────────
    @Override
    public String getTrailer(int id) {
        String url = String.format(
                "https://api.themoviedb.org/3/movie/%d/videos?api_key=%s", id, API_KEY);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(
                    request, HttpResponse.BodyHandlers.ofString());
            TrailerResponse trailerData = mapper.readValue(response.body(), TrailerResponse.class);

            return trailerData.getResults().stream()
                    .filter(v -> "Trailer".equalsIgnoreCase(v.getType())
                            && "YouTube".equalsIgnoreCase(v.getSite()))
                    .map(v -> "https://www.youtube.com/watch?v=" + v.getKey())
                    .findFirst()
                    .orElse("N/A");

        } catch (IOException | InterruptedException e) {
            return "N/A";
        }
    }

    @Override
    public MovieResponse getDummyMovie() {
        return null;
    }

    // ── Search ────────────────────────────────────────────────────────────────
    @Override
    public MovieResponse getDummyMovie(String movieName, int page) {
        String url = String.format(
                "https://api.themoviedb.org/3/search/movie?api_key=%s&query=%s&page=%d",
                API_KEY, movieName, page);
        return fetchMovieResponse(url);
    }

    // ── Movie Detail ──────────────────────────────────────────────────────────
    @Override
    public MovieInfo getMovieDetail(int id) {
        String url = String.format(
                "https://api.themoviedb.org/3/movie/%d?api_key=%s", id, API_KEY);
        try {
            HttpResponse<String> response = client.send(
                    buildRequest(url), HttpResponse.BodyHandlers.ofString());
            return mapper.readValue(response.body(), MovieInfo.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // ── Popular ───────────────────────────────────────────────────────────────
    @Override
    public MovieResponse getPopularMovie(int page) {
        String url = String.format(
                "https://api.themoviedb.org/3/movie/popular?api_key=%s&page=%d", API_KEY, page);
        return fetchMovieResponse(url);
    }

    // ── Top Rated ─────────────────────────────────────────────────────────────
    @Override
    public MovieResponse getTopRatedMovie(int page) {
        String url = String.format(
                "https://api.themoviedb.org/3/movie/top_rated?api_key=%s&page=%d", API_KEY, page);
        return fetchMovieResponse(url);
    }

    // ── Upcoming ──────────────────────────────────────────────────────────────
    @Override
    public MovieResponse getUpcoming(int page) {
        String url = String.format(
                "https://api.themoviedb.org/3/movie/upcoming?api_key=%s&page=%d", API_KEY, page);
        return fetchMovieResponse(url);
    }

    // ── Now Playing ───────────────────────────────────────────────────────────
    @Override
    public MovieResponse getNowPlaying(int page) {
        String url = String.format(
                "https://api.themoviedb.org/3/movie/now_playing?api_key=%s&page=%d", API_KEY, page);
        return fetchMovieResponse(url);
    }
}
