package lkm.starterproject.accounting.repository.register;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lkm.starterproject.accounting.entity.register.QCompendium;

public class CompendiumRepositoryCustomImpl implements CompendiumRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long findMaxCodeByAccountTitleId(Long accountTitleId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QCompendium compendium = QCompendium.compendium;

        Long maxCode = queryFactory
                .select(compendium.code.max())
                .from(compendium)
                .where(compendium.accountTitle.id.eq(accountTitleId))
                .fetchOne();

        return maxCode != null ? maxCode : 0;
    }

}
