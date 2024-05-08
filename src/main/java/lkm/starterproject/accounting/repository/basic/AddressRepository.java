package lkm.starterproject.accounting.repository.basic;

import lkm.starterproject.accounting.entity.basic.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
