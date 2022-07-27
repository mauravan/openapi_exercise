package ch.ti8m.openapi_exercise;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class CustomerClient implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // TODO: use the generated client to call the backend service
    }
}
