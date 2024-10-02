package online.jonwelzel.tagtreeapi.role;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "roles")
public class RoleModel {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    public RoleModel() {
    }

    public RoleModel(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleModel roleModel)) return false;
        return id == roleModel.id && Objects.equals(name, roleModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "RoleModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
