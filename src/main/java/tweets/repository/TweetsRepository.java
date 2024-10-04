package tweets.repository;


import tweets.entity.TweetsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetsRepository extends JpaRepository<TweetsEntity, Long> {

}
