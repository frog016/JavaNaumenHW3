package org.example.JavaNaumenHW3.Repository;

import org.example.JavaNaumenHW3.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "users")
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByFullName(String fullName);
}
