package pw.react.backend.services;

import pw.react.backend.models.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    User validateAndSave(User user);
    Collection<User> batchSave(Collection<User> users);
    List<User> getAllWithIdGreaterThanOne();
}
