package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieResponse {
//    private List<Movie> movies;
//    private Integer total;
//    private Integer skip;
//    private Integer limit;

    private List<Movie> results;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("total_results")
    private Integer totalResults;

}
