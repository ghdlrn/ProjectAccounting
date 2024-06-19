package lkm.starterproject.accounting.repository.register;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lkm.starterproject.accounting.entity.register.QCard;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CardRepositoryCustomImpl implements CardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Long> findCodesByCompanyId(Long companyId) {
        QCard card = QCard.card;

        return queryFactory
                .select(card.code)
                .from(card)
                .where(card.company.id.eq(companyId))
                .orderBy(card.code.asc())
                .fetch();
    }
}
