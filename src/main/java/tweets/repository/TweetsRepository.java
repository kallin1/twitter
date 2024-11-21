package tweets.repository;


import org.springframework.stereotype.Repository;
import tweets.entity.TweetsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TweetsRepository extends JpaRepository<TweetsEntity, Long> {

}
