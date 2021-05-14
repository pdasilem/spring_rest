package ru.pdasilem.spring_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.pdasilem.spring_rest.model.UserModel;


@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {

    @Query("select um FROM UserModel um inner JOIN FETCH um.roles where um.email = :email")
    UserModel findByUserEmail(@Param("email") String email);

}