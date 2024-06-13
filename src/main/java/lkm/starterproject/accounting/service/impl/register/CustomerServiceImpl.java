package lkm.starterproject.accounting.service.impl.register;

import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.dto.register.CustomerDto;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.entity.register.Customer;
import lkm.starterproject.accounting.mapper.register.CustomerMapper;
import lkm.starterproject.accounting.repository.basic.LocalTaxRepository;
import lkm.starterproject.accounting.repository.register.CustomerRepository;
import lkm.starterproject.accounting.service.company.CompanyService;
import lkm.starterproject.accounting.service.register.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final LocalTaxRepository localTaxRepository;
    private final CompanyService companyService;

    @Override
    @Transactional
    public CustomerDto createCustomer(String email, CustomerDto customerDto) {
        Company company = companyService.getCurrentCompany(email);
        Customer customer = customerMapper.toEntity(customerDto);
        assignLocalTax(customer, customerDto);
        customer.setCompany(company);
        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> getAllCustomers(String email) {
        Company company = companyService.getCurrentCompany(email);
        List<Customer> customers = customerRepository.findByCompanyId(company.getId());
        return customerMapper.toDtoList(customers);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCustomer(String email, Long id) {
        Company company = companyService.getCurrentCompany(email);
        Customer customer = customerRepository.findByIdAndCompanyId(id, company.getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer 정보를 찾을 수 없음"));
        return customerMapper.toDto(customer);
    }

    @Override
    @Transactional
    public CustomerDto updateCustomer(String email, Long id, CustomerDto customerDto) {
        Company company = companyService.getCurrentCompany(email);
        Customer customer = customerRepository.findByIdAndCompanyId(id, company.getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer 정보를 찾을 수 없음"));
        assignLocalTax(customer, customerDto);
        customerMapper.updateDto(customerDto, customer);
        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(String email, Long id) {
        Company company = companyService.getCurrentCompany(email);
        Customer customer = customerRepository.findByIdAndCompanyId(id, company.getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer 정보를 찾을 수 없음"));
        customerRepository.delete(customer);
    }

    private void assignLocalTax(Customer customer, CustomerDto customerDto) {
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
