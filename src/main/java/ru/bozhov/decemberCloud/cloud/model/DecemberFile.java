package ru.bozhov.decemberCloud.cloud.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;

@Data
@Entity
@Table(name = "decemberFile")
public class DecemberFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Folder parent;

    @Column(name = "created_date")
    private Calendar createdDate;

    @Column(name = "data")
    private byte[] data;

}
