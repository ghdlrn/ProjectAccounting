package lkm.starterproject.accounting.repository.register;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lkm.starterproject.accounting.entity.register.QCustomer;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FinanceRepositoryCustomImpl implements FinanceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Long> findCodesByCompanyId(Long companyId) {
        QCustomer customer = QCustomer.customer;

        return queryFactory
                .select(customer.code)
                .from(customer)
                .where(customer.company.id.eq(companyId))
                .orderBy(customer.code.asc())
                .fetch();
    }
}
