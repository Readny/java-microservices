package pl.redny.album.domain;

import io.vavr.collection.List;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Album {
    private LocalDate originalReleaseDate;

    private Long length;

    private String title;

    private String artist;

    private List<Release> releases;

}
