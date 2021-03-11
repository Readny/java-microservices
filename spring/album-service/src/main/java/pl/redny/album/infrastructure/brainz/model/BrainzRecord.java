package pl.redny.album.infrastructure.brainz.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BrainzRecord {

    private UUID id;

    private String firstReleaseDate;

    private Long length;

    private Boolean video;

    @JsonProperty("artist-credit")
    private List<BrainzArtistCredit> artistCredit;

    private String disambiguation;

    private String title;

    private List<BrainzRelease> releases;

}
