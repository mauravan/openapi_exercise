package ch.ti8m.openapi_exercise.customer.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.io.Serializable;

import static javax.persistence.EnumType.STRING;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard implements Serializable {
    @Enumerated(STRING)
    @Column(name = "credit_card_type")
    @NotNull
    private CreditCardType type;

    @Column(name = "credit_card_number")
    @Size(min = 16, max = 16)
    @NotNull
    private String number;

    @Column(name = "credit_card_expiration_month")
    @Min(1)
    @Max(12)
    private int expirationMonth;

    @Column(name = "credit_card_expiration_year")
    @Positive
    private int expirationYear;
}
