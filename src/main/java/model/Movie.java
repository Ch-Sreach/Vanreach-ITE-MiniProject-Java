package model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Movie {
    private Integer id;
    private String title;
    private LocalDate release;
    private Double rate;
    private String trailer;
}
