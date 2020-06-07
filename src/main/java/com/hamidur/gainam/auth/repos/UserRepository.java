package com.hamidur.gainam.auth.repos;

import com.hamidur.gainam.auth.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>
{
    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE lower(username) = lower(:username)")
    User getUserByUsername(@Param("username") String username);
}
