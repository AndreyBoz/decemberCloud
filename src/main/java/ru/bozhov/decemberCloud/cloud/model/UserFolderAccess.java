package ru.bozhov.decemberCloud.cloud.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.bozhov.decemberCloud.auth.model.DecemberUser;

@Entity
@Table(name = "user_folder_access")
@Data
public class UserFolderAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private DecemberUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Folder folder;

}
