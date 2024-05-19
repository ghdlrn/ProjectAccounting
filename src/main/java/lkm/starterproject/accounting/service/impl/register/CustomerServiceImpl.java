package lkm.starterproject.accounting.service.impl.register;

import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.register.CustomerDto;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.entity.register.Customer;
import lkm.starterproject.accounting.entity.register.Customer;
import lkm.starterproject.accounting.mapper.register.CustomerMapper;
import lkm.starterproject.accounting.repository.basic.LocalTaxRepository;
import lkm.starterproject.accounting.repository.company.CompanyRepository;
import lkm.starterproject.accounting.repository.register.CustomerRepository;
import lkm.starterproject.accounting.service.register.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final LocalTaxRepository localTaxRepository;
    private final CompanyRepository companyRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper,
                               LocalTaxRepository localTaxRepository, CompanyRepository companyRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.localTaxRepository = localTaxRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    @Transactional
    public CustomerDto createCustomer(Long companyId, CustomerDto customerDto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        Customer customer = customerMapper.toEntity(customerDto);
        assignLocalTaxAndTaxOffice(customer, customerDto);
        customer.setCompany(company);
        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> getAllCustomers(Long companyId) {
        companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        List<Customer> customers = customerRepository.findByCompanyId(companyId);
        return customerMapper.toDtoList(customers);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer 정보를 찾을 수 없음"));
        return customerMapper.toDto(customer);
    }

    @Override
    @Transactional
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer 정보를 찾을 수 없음"));
        assignLocalTaxAndTaxOffice(customer, customerDto);
        customerMapper.updateDto(customerDto, customer);
        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer 정보를 찾을 수 없음"));
        customerRepository.delete(customer);
    }

    private void assignLocalTaxAndTaxOffice(Customer customer, CustomerDto customerDto) {
        if (customerDto.getLocalTax() != null && customerDto.getLocalTax().getId() != null) {
            customer.setLocalTax(findLocalTax(customerDto.getLocalTax().getId()));
        } else {
            customer.setLocalTax(null);
        }
    }   //LocalTax null허용

    private LocalTax findLocalTax(Long id) {
        return id == null ? null : localTaxRepository.findById(id).orElse(null);
    }
}
