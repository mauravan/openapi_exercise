package ch.ti8m.openapi_exercise.customer.endpoint.mapper;

import ch.ti8m.connector.api.dto.AddressDto;
import ch.ti8m.connector.api.dto.CreditCardDto;
import ch.ti8m.connector.api.dto.CustomerDto;
import ch.ti8m.openapi_exercise.customer.domain.Address;
import ch.ti8m.openapi_exercise.customer.domain.CreditCard;
import ch.ti8m.openapi_exercise.customer.domain.CreditCardType;
import ch.ti8m.openapi_exercise.customer.domain.Customer;

public class CustomerMapper {
    public static Customer mapCustomerDtoToCustomer(CustomerDto customerDto) {
        return new Customer(
                customerDto.getId() == null ? null : Long.valueOf(customerDto.getId()),
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getEmail(),
                customerDto.getUsername(),
                mapAddressDtoToAdress(customerDto.getAddress()),
                mapCreditCardDtoToCreditCard(customerDto.getCreditCard()));
    }

    public static CustomerDto mapCustomerToCustomerDto(Customer customer) {
        return new CustomerDto()
                .id(customer.getId().intValue())
                .username(customer.getUsername())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(mapAddressToAddressDto(customer.getAddress()))
                .creditCard(mapCreditCardToCreditCardDto(customer.getCreditCard()));
    }

    private static AddressDto mapAddressToAddressDto(Address address) {
        return new AddressDto().street(address.getStreet()).city(address.getCity()).country(address.getCountry()).postalCode(address.getPostalCode()).stateProvince(address.getStateProvince());
    }

    private static Address mapAddressDtoToAdress(AddressDto addressDto) {
        return new Address(addressDto.getStreet(), addressDto.getStateProvince(), addressDto.getPostalCode(), addressDto.getCity(), addressDto.getCountry());
    }

    private static CreditCardDto mapCreditCardToCreditCardDto(CreditCard creditCard) {
        return new CreditCardDto().number(creditCard.getNumber()).type(mapTypeEnumToCreditCardType(creditCard.getType())).expirationMonth(creditCard.getExpirationMonth()).expirationYear(creditCard.getExpirationYear());
    }

    private static CreditCard mapCreditCardDtoToCreditCard(CreditCardDto creditCardDto) {
        return new CreditCard(mapCreditCardTypeToTypeEnum(creditCardDto.getType()), creditCardDto.getNumber(), creditCardDto.getExpirationMonth(), creditCardDto.getExpirationYear());
    }

    private static CreditCardDto.TypeEnum mapTypeEnumToCreditCardType(CreditCardType creditCardType) {
        return CreditCardDto.TypeEnum.fromValue(creditCardType.toString());
    }

    private static CreditCardType mapCreditCardTypeToTypeEnum(CreditCardDto.TypeEnum typeEnum) {
        return CreditCardType.valueOf(typeEnum.getValue());
    }
}
