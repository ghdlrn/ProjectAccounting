package lkm.starterproject.accounting.repository.register;

import lkm.starterproject.accounting.entity.register.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
