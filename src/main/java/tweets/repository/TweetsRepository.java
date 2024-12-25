package tweets.repository;


import org.springframework.stereotype.Repository;
import tweets.entity.TweetsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface TweetsRepository extends JpaRepository<TweetsEntity, Long> {
    // update board_table set board_hits=board_hits+1 where id=?
    @Modifying
    @Query(value = "update TweetsEntity b set b.view=b.view+1 where b.id=:id")
    static void view(@Param("id") Long id){};
}
