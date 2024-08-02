package ru.bozhov.decemberCloud.common.pojo;

import jakarta.persistence.*;
import lombok.Data;
import ru.bozhov.decemberCloud.auth.model.Role;

import java.io.Serializable;
import java.util.Set;

@Data
@MappedSuperclass
public class _DecemberUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;


}
