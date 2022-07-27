package ch.ti8m.openapi_exercise;

import ch.ti8m.client.gateway.DefaultApi;
import ch.ti8m.connector.client.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class CustomerClient implements CommandLineRunner {
    private final DefaultApi defaultApi;

    @Override
    public void run(String... args) throws Exception {
        final CustomerDto customerDto = defaultApi.customersIdGet("1");

        System.out.println(customerDto);

    }
}
