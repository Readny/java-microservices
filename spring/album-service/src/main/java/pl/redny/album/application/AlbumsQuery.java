package pl.redny.album.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.redny.cqrs.query.Query;

@Data
@AllArgsConstructor
public class AlbumsQuery implements Query {

    private final String name;

}
