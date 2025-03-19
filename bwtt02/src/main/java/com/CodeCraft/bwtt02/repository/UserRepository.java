package com.CodeCraft.bwtt02.repository;

import com.CodeCraft.bwtt02.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDetails, UUID> {

}
