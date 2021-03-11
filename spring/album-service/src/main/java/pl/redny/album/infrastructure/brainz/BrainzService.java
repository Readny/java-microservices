package pl.redny.album.infrastructure.brainz;

import io.vavr.collection.List;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.redny.album.domain.Album;
import pl.redny.album.domain.AlbumRepository;
import pl.redny.album.domain.Release;
import pl.redny.album.infrastructure.brainz.model.BrainzRecording;
import pl.redny.album.infrastructure.brainz.model.BrainzRecordingsQuery;
import pl.redny.album.infrastructure.brainz.model.BrainzRelease;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class BrainzService implements AlbumRepository {

    private final MusicBrainzApiClient musicBrainzClient;

    @Override
    public Try<List<Album>> getAlbumsByName(final String name) {
        final BrainzRecordingsQuery recordings = musicBrainzClient.getRecordsByName(name);

        return Try.of(() -> List.ofAll(
                recordings
                        .getRecordings()
                        .stream()
                        .map(this::mapAlbum)
                )
        );
    }

    private Album mapAlbum(final BrainzRecording brainzRecording) {
        return Album.builder()
                .artist(getArtist(brainzRecording))
                .originalReleaseDate(getLocalDate(brainzRecording.getFirstReleaseDate()))
                .length(brainzRecording.getLength())
                .title(brainzRecording.getTitle())
                .releases(List.ofAll(brainzRecording.getReleases().stream().map(this::mapRelease)))
                .build();
    }

    private String getArtist(final BrainzRecording brainzRecording) {
        if (brainzRecording.getArtistCredit() == null
                || brainzRecording.getArtistCredit().get(0) == null
                || brainzRecording.getArtistCredit().get(0).getArtist() == null) {
            return null;
        }

        return brainzRecording.getArtistCredit().get(0).getArtist().getName();
    }

    private LocalDate getLocalDate(final String stringDate) {
        if (stringDate == null) {
            return null;
        }
        Try<LocalDate> full = Try.of(() -> LocalDate.parse(stringDate));
        if (full.isSuccess()) {
            return full.get();
        }

        return null;
    }

    private Release mapRelease(final BrainzRelease brainzRelease) {
        return Release.builder()
                .status(brainzRelease.getStatus())
                .country(brainzRelease.getCountry())
                .build();
    }
}
