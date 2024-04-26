package lkm.starterproject.bulletinboard.controller;


import lkm.starterproject.bulletinboard.entity.BulletinBoard;
import lkm.starterproject.bulletinboard.service.BulletinBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bulletin_board")
@RequiredArgsConstructor
public class BulletinBoardController {

    private final BulletinBoardService BulletinBoardService;

    // 게시글 목록 조회
    @GetMapping
    public ResponseEntity<List<BulletinBoard>> getAllBulletinBoards() {
        List<BulletinBoard> posts = BulletinBoardService.getAllBulletinBoards();
        return ResponseEntity.ok(posts);
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<BulletinBoard> getBulletinBoardById(@PathVariable Long id) {
        Optional<BulletinBoard> post = BulletinBoardService.getBulletinBoardById(id);
        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 게시글 생성
    @PostMapping
    public ResponseEntity<BulletinBoard> createBulletinBoard(@RequestBody BulletinBoard post) {
        BulletinBoard savedBulletinBoard = BulletinBoardService.saveBulletinBoard(post);
        return new ResponseEntity<>(savedBulletinBoard, HttpStatus.CREATED);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<BulletinBoard> updateBulletinBoard(@PathVariable Long id, @RequestBody BulletinBoard post) {
        return BulletinBoardService.getBulletinBoardById(id)
                .map(existingBulletinBoard -> {
                    existingBulletinBoard.setTitle(post.getTitle());
                    existingBulletinBoard.setContent(post.getContent());
                    existingBulletinBoard.setMemberEntity(post.getMemberEntity());
                    BulletinBoard updatedBulletinBoard = BulletinBoardService.saveBulletinBoard(existingBulletinBoard);
                    return new ResponseEntity<>(updatedBulletinBoard, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBulletinBoard(@PathVariable Long id) {
        return BulletinBoardService.getBulletinBoardById(id)
                .map(post -> {
                    BulletinBoardService.deleteBulletinBoard(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
