package pl.redny.album.infrastructure.brainz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.redny.album.infrastructure.brainz.model.BrainzRecord;
import pl.redny.album.infrastructure.brainz.model.BrainzRecordingsQuery;

public interface MusicBrainzApi {

    @GetMapping(value = "/recording/{id}?inc=artist-credits+releases", headers = {"User-Agent=Educational app (pcpiotr@gmail.com)"},
            produces = "application/json")
    BrainzRecord getRecordById(@PathVariable final String id);

    @GetMapping(value = "/recording", headers = {"User-Agent=Educational app (pcpiotr@gmail.com)"}, produces = "application/json")
    BrainzRecordingsQuery getRecordsByName(@RequestParam("query") final String name);

}
