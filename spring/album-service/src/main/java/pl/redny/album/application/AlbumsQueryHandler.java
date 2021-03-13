package pl.redny.album.application;

import io.vavr.collection.List;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import pl.redny.album.domain.Album;
import pl.redny.album.domain.AlbumRepository;
import pl.redny.cqrs.query.Query;
import pl.redny.cqrs.query.QueryHandler;

@RequiredArgsConstructor
public class AlbumsQueryHandler implements QueryHandler<AlbumsQuery, List<Album>> {

    private final AlbumRepository albumRepository;

    @Override
    public Try<List<Album>> execute(final AlbumsQuery albumsQuery) {
        return albumRepository.getAlbumsByName(albumsQuery.getName());
    }

    @Override
    public boolean canHandle(final Query query) {
        return query instanceof AlbumsQuery;
    }

}
