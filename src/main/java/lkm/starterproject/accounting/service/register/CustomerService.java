package lkm.starterproject.accounting.service.register;

import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.register.CustomerDto;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.register.Customer;
import lkm.starterproject.accounting.mapper.register.CustomerMapper;
import lkm.starterproject.accounting.repository.basic.LocalTaxRepository;
import lkm.starterproject.accounting.repository.register.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final LocalTaxRepository localTaxRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper,
                           LocalTaxRepository localTaxRepository) {
        this.customerRepository = customerRepository;
        this.localTaxRepository = localTaxRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional
    public CustomerDto createCustomer(CustomerDto companyDto) {
        Customer customer = customerMapper.toEntity(companyDto);
        customer.setLocalTax(findLocalTax(companyDto.getLocalTax().getId()));

        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Transactional(readOnly = true)
    public List<CustomerDto> getAllCompanies() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerDto getCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(customerMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Customer 정보를 찾을 수 없음"));
    }

    @Transactional
    public CustomerDto updateCustomer(Long id, CustomerDto companyDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer 정보를 찾을 수 없음"));

        customer.setLocalTax(findLocalTax(companyDto.getLocalTax().getId()));
        customerMapper.updateEntityFromDto(companyDto, customer);

        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer 정보를 찾을 수 없음"));
        customerRepository.delete(customer);
    }

    private LocalTax findLocalTax(Long id) {
        return id == null ? null : localTaxRepository.findById(id).orElse(null);
    }
}
