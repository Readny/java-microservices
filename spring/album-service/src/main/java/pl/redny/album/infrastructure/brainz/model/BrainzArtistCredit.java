package pl.redny.album.infrastructure.brainz.model;

import lombok.Data;

@Data
public class BrainzArtistCredit {

    private String name;

    private BrainzArtist artist;

    private String joinphrase;

}
