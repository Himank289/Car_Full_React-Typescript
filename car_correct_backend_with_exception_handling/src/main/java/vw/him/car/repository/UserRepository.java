package vw.him.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vw.him.car.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    public User findByEmail(String email);
}
