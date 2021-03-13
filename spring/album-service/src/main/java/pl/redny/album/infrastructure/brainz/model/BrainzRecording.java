package pl.redny.album.infrastructure.brainz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BrainzRecording {

    private UUID id;

    private long score;

    private String title;

    private Long length;

    private Boolean video;

    @JsonProperty("artist-credit")
    private List<BrainzArtistCredit> artistCredit;

    @JsonProperty("first-release-date")
    private String firstReleaseDate;

    private List<BrainzRelease> releases;

    private String disambiguation;

    private List<String> isrcs;

}
