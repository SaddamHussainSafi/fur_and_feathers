package com.furandfeathers.repository;

import com.furandfeathers.model.ERole;
import com.furandfeathers.model.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
