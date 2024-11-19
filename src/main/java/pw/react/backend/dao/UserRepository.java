package pw.react.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.react.backend.models.User;
import pw.react.backend.web.UserDto;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findAllByUsernameIn(Collection<String> userNames);
    Collection<UserDto> findAllByIdGreaterThan(long id);
}
