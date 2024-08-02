package ru.bozhov.decemberCloud.auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import ru.bozhov.decemberCloud.common.pojo._DecemberUser;

@Entity
@Table(name = "users")
@Data
public class DecemberUser extends _DecemberUser {

}
