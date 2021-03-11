package pl.redny.album.infrastructure.brainz.model;

import lombok.Data;

import java.util.List;

@Data
public class BrainzRecordingsQuery {

    private String created;

    private Long count;

    private Long offset;

    List<BrainzRecording> recordings;

}
