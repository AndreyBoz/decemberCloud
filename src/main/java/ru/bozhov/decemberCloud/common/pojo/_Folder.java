package ru.bozhov.decemberCloud.common.pojo;

import jakarta.persistence.*;
import lombok.Data;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;
import ru.bozhov.decemberCloud.cloud.model.DecemberFile;
import ru.bozhov.decemberCloud.cloud.model.Folder;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@MappedSuperclass
public class _Folder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "folder_name", nullable = false)
    private String folderName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "folder")
    private List<DecemberFile> decemberFiles;

    @OneToMany(mappedBy = "parentFolder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Folder> innerFolders;

    @ManyToOne
    @JoinColumn(name = "parent_folder_id")
    private Folder parentFolder;

    @ManyToMany
    @JoinTable(name = "users_folder",
            joinColumns = @JoinColumn(name = "folder_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<DecemberUser> accessedUsers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof _Folder that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
