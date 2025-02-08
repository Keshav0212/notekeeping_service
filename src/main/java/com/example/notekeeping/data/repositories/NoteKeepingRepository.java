package com.example.notekeeping.data.repositories;

import com.example.notekeeping.data.entity.NoteKeepingEntity;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteKeepingRepository extends JpaRepository<NoteKeepingEntity, Integer> {
    Optional<NoteKeepingEntity> findById(int id);
}
