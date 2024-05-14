package lkm.starterproject.accounting.repository.register;


import lkm.starterproject.accounting.entity.register.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
