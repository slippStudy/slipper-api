package net.slipp.www.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.slipp.www.api.domain.user.SlippUser;

@Repository
public interface SlippUserRepository extends JpaRepository<SlippUser, Long> {

	SlippUser findByEmail(String email);

}
