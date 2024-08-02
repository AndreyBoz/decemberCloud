package ru.bozhov.decemberCloud.cloud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import ru.bozhov.decemberCloud.common.pojo._DecemberFile;

@Data
@Entity
@Table(name = "decemberFile")
public class DecemberFile extends _DecemberFile {

}
