package com.ms.training.domain.repositories;

import com.ms.training.domain.entities.training.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findFirstByProfileCode(String profileCode);

}
