package lkm.starterproject.accounting.service.register;

import lkm.starterproject.accounting.dto.register.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto createCustomer(String email, CustomerDto customerDto);

    List<CustomerDto> getAllCustomers(String email);

    CustomerDto getCustomer(String email, Long id);

    CustomerDto updateCustomer(String email, Long id, CustomerDto customerDto);

    void deleteCustomer(String email, Long id);
}
