package pl.redny.accountservice.infrastructure.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Controller;

@Controller
public class HelloQueryResolver implements GraphQLQueryResolver {

    public String hello() {
        return "Hello, GraphQL!";
    }
}