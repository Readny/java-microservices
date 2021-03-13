package pl.redny.album.infrastructure.controller;

import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.redny.album.application.AlbumsQuery;
import pl.redny.album.domain.Album;
import pl.redny.cqrs.query.Query;
import pl.redny.cqrs.query.QueryDispatcher;

@RestController
@RequiredArgsConstructor
public class AlbumController {

    private static final int HTTP_SERVICE_ERROR_CODE = 441;
    private final QueryDispatcher queryDispatcher;

    @GetMapping("/album")
    public List<Album> getAlbums(@RequestParam("name") final String name) throws ControllerException {
        final Query query = new AlbumsQuery(name);
        return queryDispatcher.<List<Album>>dispatchQuery(query)
                .getOrElseThrow(ControllerException::new);
    }

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<String> controllerException(final ControllerException e) {
        return ResponseEntity.status(HTTP_SERVICE_ERROR_CODE).body(e.getMessage());
    }

}
