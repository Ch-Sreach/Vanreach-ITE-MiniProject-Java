package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class TrailerResponse {
    private List<Trailer> results;
}
