package lkm.starterproject.auth.repository;

import lkm.starterproject.auth.entity.MemberCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCompanyRepository extends JpaRepository<MemberCompany, Long> {
}
