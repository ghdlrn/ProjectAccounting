package lkm.starterproject.auth.repository;

import jakarta.transaction.Transactional;
import lkm.starterproject.auth.entity.Refresh;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshRepository extends JpaRepository<Refresh, Long> {

    Boolean existsByRefresh(String refresh);        //Refresh토큰이 존재하는지 확인

    @Transactional
    void deleteByRefresh(String refresh);       //DB에서 Refresh토큰을 지움
}