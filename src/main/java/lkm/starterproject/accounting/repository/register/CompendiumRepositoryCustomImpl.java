package lkm.starterproject.accounting.repository.register;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lkm.starterproject.accounting.entity.register.QCompendium;

public class CompendiumRepositoryCustomImpl implements CompendiumRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long findMaxCashCodeByAccountTitleId(Long accountTitleId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QCompendium compendium = QCompendium.compendium;

        Long maxCashCode = queryFactory
                .select(compendium.cashCode.max())
                .from(compendium)
                .where(compendium.accountTitle.id.eq(accountTitleId))
                .fetchOne();

        return maxCashCode != null ? maxCashCode : 0;
    }

    @Override
    public Long findMaxReplacementCodeByAccountTitleId(Long accountTitleId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QCompendium compendium = QCompendium.compendium;

        Long maxReplacementCode = queryFactory
                .select(compendium.replacementCode.max())
                .from(compendium)
                .where(compendium.accountTitle.id.eq(accountTitleId))
                .fetchOne();

        return maxReplacementCode != null ? maxReplacementCode : 0;
    }
}
