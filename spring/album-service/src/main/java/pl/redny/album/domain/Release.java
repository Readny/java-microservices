package pl.redny.album.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Release {
    private LocalDate releaseDate;

    private String country;

    private String status;

}
