package ru.pdasilem.spring_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pdasilem.spring_rest.model.Roles;


@Repository
public interface RoleRepo extends JpaRepository<Roles, Long> {

    Roles findByRole(String role);
}
