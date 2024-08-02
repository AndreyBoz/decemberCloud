package ru.bozhov.decemberCloud.common.pojo;

import jakarta.persistence.*;
import lombok.Data;
import ru.bozhov.decemberCloud.cloud.model.Folder;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

@Data
@MappedSuperclass
public class _DecemberFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "data", columnDefinition = "VARBINARY(MAX)")
    byte[] data;

    @Column(name = "filename")
    String filename;

    @Column(name = "file_type")
    String fileType;

    @Column(name = "date_ins")
    Calendar insDate;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folder;
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof _DecemberFile that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
