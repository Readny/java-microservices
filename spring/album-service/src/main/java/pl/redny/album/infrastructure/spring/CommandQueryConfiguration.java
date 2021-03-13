package pl.redny.album.infrastructure.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.redny.album.application.AlbumsQueryHandler;
import pl.redny.album.domain.AlbumRepository;
import pl.redny.cqrs.DefaultDispatcher;
import pl.redny.cqrs.command.Command;
import pl.redny.cqrs.command.CommandHandler;
import pl.redny.cqrs.query.Query;
import pl.redny.cqrs.query.QueryHandler;

import java.util.List;

@Configuration
public class CommandQueryConfiguration {

    @Bean
    public DefaultDispatcher defaultDispatcher(
            final List<CommandHandler<Command>> commandHandlers,
            final List<QueryHandler<Query, ?>> queryHandlers
    ) {
        return new DefaultDispatcher(commandHandlers, queryHandlers);
    }

    @Bean
    public QueryHandler albumsQueryHandler(final AlbumRepository albumRepository) {
        return new AlbumsQueryHandler(albumRepository);
    }


}
