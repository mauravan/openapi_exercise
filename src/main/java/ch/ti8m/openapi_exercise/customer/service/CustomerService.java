package ch.ti8m.openapi_exercise.customer.service;

import ch.ti8m.openapi_exercise.customer.CustomerRepository;
import ch.ti8m.openapi_exercise.customer.domain.Customer;
import ch.ti8m.openapi_exercise.customer.exception.CustomerIdentifierException;
import ch.ti8m.openapi_exercise.customer.exception.CustomerNotFoundException;
import ch.ti8m.openapi_exercise.customer.exception.UsernameAlreadyExistsException;
import ch.ti8m.openapi_exercise.customer.exception.UsernameNotMatchingException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static java.text.MessageFormat.format;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Finds a customer by identifier.
     *
     * @param id the identifier of the customer
     * @return the data of the found customer
     * @throws CustomerNotFoundException if no customer with the specified identifier exists
     */
    public Customer findCustomer(long id) throws CustomerNotFoundException {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(format("No Customer with id: {0} found", id)));
    }

    /**
     * Registers a customer.
     *
     * @param customer the data of the customer (identifier must be null)
     * @return the data of the registered customer
     * @throws UsernameAlreadyExistsException if the username already exists
     * @throws CustomerIdentifierException if the id is not empty
     */
    public Customer registerCustomer(Customer customer) throws UsernameAlreadyExistsException, CustomerIdentifierException {
        Objects.requireNonNull(customer, "customer must not be null");

        if (customer.getId() != null) {
            throw new CustomerIdentifierException(format("the id field must be null. given: {0}", customer.getId()));
        }

        boolean customerAlreadyExists = customerRepository.existsByUsername(customer.getUsername());
        if (customerAlreadyExists) {
            throw new UsernameAlreadyExistsException(format("Username {0} already taken", customer.getUsername()));
        }
        return customerRepository.save(customer);
    }

    /**
     * Updates the data of a customer.
     *
     * @param customer the new data of the customer (username must not change)
     * @return the data of the updated customer
     * @throws CustomerNotFoundException if no customer with the corresponding identifier exists
     * @throws IllegalArgumentException  if the username changed
     */
    public Customer updateCustomer(Customer customer) throws CustomerNotFoundException, UsernameNotMatchingException {
        Objects.requireNonNull(customer, "customer must not be null");

        Optional<Customer> oldUser = customerRepository.findById(customer.getId());
        if (oldUser.isEmpty()) {
            throw new CustomerNotFoundException(format("No Customer with id: {0} found", customer.getId()));
        }

        if (!oldUser.get().getUsername().equals(customer.getUsername())) {
            throw new UsernameNotMatchingException(format("The username must not change. was: {0} given: {1}", oldUser.get().getUsername(), customer.getUsername()));
        }

        return customerRepository.save(customer);
    }

}
