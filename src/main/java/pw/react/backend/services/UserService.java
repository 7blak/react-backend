package pw.react.backend.services;

import pw.react.backend.models.User;
import pw.react.backend.web.UserDto;

import java.util.Collection;

public interface UserService {
    User validateAndSave(User user);
    Collection<User> batchSave(Collection<User> users);
    Collection<UserDto> getAllWithIdGreaterThanOne();
}
