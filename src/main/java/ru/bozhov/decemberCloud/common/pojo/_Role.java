package ru.bozhov.decemberCloud.common.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@MappedSuperclass
public class _Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof _Role role)) return false;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}