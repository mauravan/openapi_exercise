package ch.ti8m.openapi_exercise.customer;

import ch.ti8m.openapi_exercise.customer.domain.Customer;
import ch.ti8m.openapi_exercise.customer.dto.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

    boolean existsByUsername(String username);

    List<CustomerInfo> findByLastNameContainsIgnoreCaseOrFirstNameContainsIgnoreCase(String lastName, String firstName);

    default List<CustomerInfo> findCustomerInfoByFirstNameOrLastName(String name) {
        return findByLastNameContainsIgnoreCaseOrFirstNameContainsIgnoreCase(name, name);
    }
}
