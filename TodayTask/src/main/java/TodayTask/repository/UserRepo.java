package TodayTask.repository;

import TodayTask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findTop1ByMobileNo(String mobileNo);

    Optional<User> findTop1ByEmail(String email);

    User findByEmailAndIsActiveTrue(String userEmail);
}
