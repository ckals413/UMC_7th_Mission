package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    //Jpa 에서 메서드 이름으로만 sql을 만들어주는 기능, PageRequest는 페이징 옵션도 포함
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
    Page<Review> findAllByMember(Member member, PageRequest pageRequest);
}
