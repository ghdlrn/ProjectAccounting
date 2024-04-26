package lkm.starterproject.bulletinboard.service;

import lkm.starterproject.bulletinboard.entity.BulletinBoard;
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

}
