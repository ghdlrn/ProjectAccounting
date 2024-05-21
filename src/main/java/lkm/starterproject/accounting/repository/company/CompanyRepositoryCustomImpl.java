package lkm.starterproject.accounting.repository.company;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.entity.company.QCompany;
import lkm.starterproject.auth.entity.Member;
import lkm.starterproject.auth.entity.QMemberCompany;

import java.util.List;

public class CompanyRepositoryCustomImpl implements  CompanyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CompanyRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Company> findByMember(Member member) {
        QCompany company = QCompany.company;
        QMemberCompany memberCompany = QMemberCompany.memberCompany;

        return queryFactory
                .selectFrom(company)
                .join(company.memberCompanies, memberCompany)
                .where(memberCompany.member.eq(member))
                .fetch();
    }
}
