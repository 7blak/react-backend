package pw.react.backend.services;

import pw.react.backend.models.User;

import java.util.Collection;

public interface UserService {
    User validateAndSave(User user);
    Collection<User> batchSave(Collection<User> users);
    Collection<User> getAllGreaterThanId(long startingId);
}
