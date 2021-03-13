package pl.redny.album.infrastructure.brainz.model;

import lombok.Data;


@Data
public class BrainzReleaseEvent {

    private String date;

    private BrainzArtist area;

}
