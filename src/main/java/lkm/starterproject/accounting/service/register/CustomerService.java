package lkm.starterproject.accounting.service.register;

import lkm.starterproject.accounting.dto.register.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto createCustomer(Long companyId, CustomerDto customerDto);

    List<CustomerDto> getAllCustomers(Long companyId);

    CustomerDto getCustomer(Long id);

    CustomerDto updateCustomer(Long id, CustomerDto customerDto);

    void deleteCustomer(Long id);
}
