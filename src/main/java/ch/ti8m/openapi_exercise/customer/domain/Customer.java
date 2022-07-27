package ch.ti8m.openapi_exercise.customer.domain;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", initialValue = 10_000, allocationSize = 50)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email
    @NotNull
    private String email;

    @Column(unique = true)
    @NotNull
    private String username;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "city", column = @Column(name = "address_city")),
            @AttributeOverride(name = "stateProvince", column = @Column(name = "address_state_province")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "address_postal_code")),
            @AttributeOverride(name = "country", column = @Column(name = "address_country"))
    })
    @NotNull
    @Valid
    private Address address;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "type", column = @Column(name = "credit_card_type")),
            @AttributeOverride(name = "number", column = @Column(name = "credit_card_number")),
            @AttributeOverride(name = "expirationMonth", column = @Column(name = "credit_card_expiration_month")),
            @AttributeOverride(name = "expirationYear", column = @Column(name = "credit_card_expiration_year"))
    })
    @NotNull
    @Valid
    private CreditCard creditCard;
}
