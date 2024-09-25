package com.Test.app.Test_app.io.repository;


import com.Test.app.Test_app.io.entities.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<users,Integer> {
    public users findByName (String name);
}
