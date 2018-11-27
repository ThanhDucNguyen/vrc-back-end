package com.vrcserver.vrc.dao.repositories;

import com.vrcserver.vrc.dao.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from user where user_name=?1 and password=?2 and role = 1", nativeQuery = true)
    User findByUserNameAndPassword(String userName, String password);
    @Query(value = "select * from user where user_name=?1", nativeQuery = true)
    User checkUserName(String userName);
}
