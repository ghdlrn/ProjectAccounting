package lkm.starterproject.accounting.repository.register;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lkm.starterproject.accounting.entity.register.QFinance;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FinanceRepositoryCustomImpl implements FinanceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Long> findCodesByCompanyId(Long companyId) {
        QFinance finance = QFinance.finance;

        return queryFactory
                .select(finance.code)
                .from(finance)
                .where(finance.company.id.eq(companyId))
                .orderBy(finance.code.asc())
                .fetch();
    }
}
