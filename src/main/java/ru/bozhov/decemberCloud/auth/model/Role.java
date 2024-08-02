package ru.bozhov.decemberCloud.auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import ru.bozhov.decemberCloud.common.pojo._Role;

@Entity
@Table(name = "roles")
@Data
public class Role extends _Role {
}