package ru.bozhov.decemberCloud.cloud.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="folder_type")
@Data
public class FolderType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "folder_type")
    private String type;
}
