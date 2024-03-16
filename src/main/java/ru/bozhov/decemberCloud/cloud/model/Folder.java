package ru.bozhov.decemberCloud.cloud.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;

import java.util.List;

@Entity
@Table(name = "folder")
@Data
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_folder")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "folder_type",
            joinColumns = @JoinColumn(name = "folder_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "type_id", referencedColumnName = "id")
    )
    private FolderType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Folder parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Folder> subFolders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<DecemberUser> decemberUsers;

}
