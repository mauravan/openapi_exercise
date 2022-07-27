package ch.ti8m.openapi_exercise.customer.endpoint;

import ch.ti8m.api.endpoint.CustomersApi;
import ch.ti8m.connector.api.dto.CustomerDto;
import ch.ti8m.connector.api.dto.ErrorInfoDto;
import ch.ti8m.openapi_exercise.customer.domain.Customer;
import ch.ti8m.openapi_exercise.customer.exception.CustomerIdentifierException;
import ch.ti8m.openapi_exercise.customer.exception.CustomerNotFoundException;
import ch.ti8m.openapi_exercise.customer.exception.UsernameAlreadyExistsException;
import ch.ti8m.openapi_exercise.customer.exception.UsernameNotMatchingException;
import ch.ti8m.openapi_exercise.customer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static ch.ti8m.openapi_exercise.customer.endpoint.mapper.CustomerMapper.mapCustomerDtoToCustomer;
import static ch.ti8m.openapi_exercise.customer.endpoint.mapper.CustomerMapper.mapCustomerToCustomerDto;
import static java.text.MessageFormat.format;
import static org.springframework.http.HttpStatus.*;

@RestController
public class CustomerEndpoint implements CustomersApi {

    private final CustomerService customerService;

    public CustomerEndpoint(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<CustomerDto> _customersIdGet(String id) throws CustomerNotFoundException {
        Customer customer = customerService.findCustomer(Long.parseLong(id));
        return ResponseEntity.ok(mapCustomerToCustomerDto(customer));
    }

    @Override
    public ResponseEntity<CustomerDto> _customersIdPut(Integer id, CustomerDto customerDto) throws CustomerIdentifierException, UsernameNotMatchingException, CustomerNotFoundException {
        if (!id.equals(customerDto.getId())) {
            throw new CustomerIdentifierException(format("Identifiers from path {0} and body {1} not matching", id, customerDto.getId()));
        }

        Customer customer = customerService.updateCustomer(mapCustomerDtoToCustomer(customerDto));
        return ResponseEntity.ok(mapCustomerToCustomerDto(customer));
    }

    @Override
    public ResponseEntity<CustomerDto> _customersPost(CustomerDto customerDto) throws CustomerIdentifierException, UsernameAlreadyExistsException {
        Customer customer = customerService.registerCustomer(mapCustomerDtoToCustomer(customerDto));
        return ResponseEntity.status(CREATED).body(mapCustomerToCustomerDto(customer));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfoDto> handle(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> format("{0} for field: {1} and value: {2}", fieldError.getDefaultMessage(), fieldError.getField(), fieldError.getRejectedValue()))
                .collect(Collectors.joining(", "));

        ErrorInfoDto errorInfoDto = new ErrorInfoDto()
                .timestamp(LocalDateTime.now())
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message(fieldErrors)
                .path(request.getPathInfo());
        return ResponseEntity.status(BAD_REQUEST).body(errorInfoDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfoDto> handle(CustomerNotFoundException ex, HttpServletRequest request) {
        ErrorInfoDto errorInfoDto = new ErrorInfoDto()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getPathInfo())
                .code(ErrorInfoDto.CodeEnum.CUSTOMER_NOT_FOUND);
        return ResponseEntity.status(NOT_FOUND).body(errorInfoDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfoDto> handle(UsernameNotMatchingException ex, HttpServletRequest request) {
        ErrorInfoDto errorInfoDto = new ErrorInfoDto()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getPathInfo())
                .code(ErrorInfoDto.CodeEnum.USERNAME_NOT_MATCHING);
        return ResponseEntity.status(CONFLICT).body(errorInfoDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfoDto> handle(UsernameAlreadyExistsException ex, HttpServletRequest request) {
        ErrorInfoDto errorInfoDto = new ErrorInfoDto()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getPathInfo())
                .code(ErrorInfoDto.CodeEnum.USERNAME_ALREADY_EXISTS);
        return ResponseEntity.status(CONFLICT).body(errorInfoDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfoDto> handle(CustomerIdentifierException ex, HttpServletRequest request) {
        ErrorInfoDto errorInfoDto = new ErrorInfoDto()
                .timestamp(LocalDateTime.now())
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getPathInfo());
        return ResponseEntity.status(BAD_REQUEST).body(errorInfoDto);
    }
}
