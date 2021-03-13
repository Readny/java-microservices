package pl.redny.album.domain;

import io.vavr.collection.List;
import io.vavr.control.Try;

public interface AlbumRepository {

    Try<List<Album>> getAlbumsByName(final String name);

}
