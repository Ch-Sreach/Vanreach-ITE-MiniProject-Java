package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class MovieInfo {
    private String title;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("vote_average")
    private Double voteAverage;

    private Integer runtime;

    @JsonProperty("budget")
    private Double budget;

    private Genres[] genres;

    @JsonProperty("origin_country")
    private String[] originalCountry;
}
