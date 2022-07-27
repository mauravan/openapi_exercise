package ch.ti8m.openapi_exercise.customer.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    @NotNull
    private String street;

    private String stateProvince;

    @NotNull
    private String postalCode;

    @NotNull
    private String city;

    @NotNull
    private String country;
}
