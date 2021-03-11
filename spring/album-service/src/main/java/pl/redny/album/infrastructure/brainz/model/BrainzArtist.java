package pl.redny.album.infrastructure.brainz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BrainzArtist {

    private String type;

    private UUID id;

    private String disambiguation;

    private String name;

    @JsonProperty("sort-name")
    private String sortName;

    @JsonProperty("type-id")
    private UUID typeID;

    private List<String> iso31661Codes;

}
