package org.example.JavaNaumenHW3.Repository;

import org.example.JavaNaumenHW3.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
