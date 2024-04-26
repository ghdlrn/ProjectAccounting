package lkm.starterproject.bulletinboard.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import lkm.starterproject.bulletinboard.entity.BulletinBoard;
import lkm.starterproject.bulletinboard.entity.QBulletinBoard;
import lkm.starterproject.bulletinboard.repository.BulletinBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static lkm.starterproject.auth.entity.QMemberEntity.memberEntity;

@Service
@RequiredArgsConstructor
public class BulletinBoardService {
    private final BulletinBoardRepository bulletinBoardRepository;

    public BulletinBoard saveBulletinBoard(BulletinBoard bulletinBoard) {
        return bulletinBoardRepository.save(bulletinBoard);
    }

    public Optional<BulletinBoard> getBulletinBoardById(Long id) {
        return bulletinBoardRepository.findById(id);
    }

    public List<BulletinBoard> getAllBulletinBoards() {
        return bulletinBoardRepository.findAll();
    }

    public void deleteBulletinBoard(Long id) {
        bulletinBoardRepository.deleteById(id);
    }

    //해당 작성자의 게시글 검색
    public List<BulletinBoard> findByAuthor(String author) {
        QBulletinBoard qBulletinBoard = QBulletinBoard.bulletinBoard;
        BooleanExpression filterByAuthor = qBulletinBoard.memberEntity.eq(memberEntity);
        return (List<BulletinBoard>) bulletinBoardRepository.findAll(filterByAuthor);
    }
}
