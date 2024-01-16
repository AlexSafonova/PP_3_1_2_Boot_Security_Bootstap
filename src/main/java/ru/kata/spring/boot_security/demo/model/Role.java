package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;
    @ManyToMany
    private List<User> users;

    public String getRole() {
        return role;
    }

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return role.substring(5);
    }

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(getId(), role1.getId()) && Objects.equals(getRole(), role1.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRole());
    }

    @Override
    public String getAuthority() {
        return role;
    }
}