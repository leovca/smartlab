package smartlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smartlab.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

}