package pl.redny.album.infrastructure.brainz.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class BrainzRelease {

    private UUID statusID;

    private String title;

    private String disambiguation;

    private List<BrainzArtistCredit> artistCredit;

    private String quality;

    private List<BrainzReleaseEvent> releaseEvents;

    private String date;

    private String textRepresentation;

    private UUID id;

    private String country;

    private String status;

    private Object packaging;

    private Object packagingID;

    private String barcode;

}
