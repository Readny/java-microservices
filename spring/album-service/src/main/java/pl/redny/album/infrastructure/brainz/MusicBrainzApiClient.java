package pl.redny.album.infrastructure.brainz;

import feign.FeignException;
import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@FeignClient(value = "music-brainz-service", url = "${app.brainz.client.url:}", configuration =
        MusicBrainzApiClient.MusicBrainzApiClientConfiguration.class)
public interface MusicBrainzApiClient extends MusicBrainzApi {

    class MusicBrainzApiClientConfiguration {
        @Value("${app.brainz.client.retry-max-attempts:1}")
        private Integer retryMaxAttempts;

        @Value("${app.brainz.client.connection-timeout:10000}")
        private Integer connectionTimeout;

        @Value("${app.brainz.client.read-timeout:10000}")
        private Integer readTimeout;

        @Value("${app.brainz.client.logger:FULL}")
        private String logger;

        @Bean("musicBrainzApiRetryer")
        public Retryer retryer() {
            return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1), retryMaxAttempts);
        }

        @Bean("musicBrainzApiErrorDecoder")
        public ErrorDecoder errorDecoder() {
            return MusicBrainzApiClientException::new;
        }

        @Bean("musicBrainzApiLogger")
        public Logger.Level loggerLevel() {
            return Logger.Level.valueOf(logger);
        }

        @Bean("musicBrainzApiRequest")
        public Request.Options options() {
            return new Request.Options(connectionTimeout, readTimeout);
        }
    }
}

@Slf4j
class MusicBrainzApiClientException extends FeignException {

    private static final long serialVersionUID = 891817555619916277L;

    public MusicBrainzApiClientException(final String methodKey, final Response response) {
        super(response.status(), response.body() == null ? response.reason() : MusicBrainzApiClientException.toStringSafe(response.body()));

        if (log.isDebugEnabled()) {
            log.debug("Error for methodKey[" + methodKey + "], status[" + response.status() + "], body: " + getMessage() + ", reason: " + response.reason());
        }
    }

    public static String toStringSafe(final Response.Body body) {
        try {
            return IOUtils.toString(body.asInputStream(), String.valueOf(StandardCharsets.UTF_8));
        } catch (IOException e) {
            if (log.isWarnEnabled()) {
                log.warn("Can't read input stream", e);
            }

            return StringUtils.EMPTY;
        }
    }
}