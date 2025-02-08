package com.example.notekeeping.data.entity;

import jakarta.persistence.*;

import lombok.Data;
import org.springframework.validation.annotation.Validated;




@Data
@Validated
@Table(schema = "name = notebooks")
@Entity
public class NoteKeepingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String note;
}
