package lkm.starterproject.bulletinboard.repository;

import lkm.starterproject.bulletinboard.entity.BulletinBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BulletinBoardRepository extends JpaRepository<BulletinBoard, Long>, QuerydslPredicateExecutor<BulletinBoard> {
}
