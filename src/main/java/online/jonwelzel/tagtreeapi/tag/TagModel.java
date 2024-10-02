package online.jonwelzel.tagtreeapi.tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import online.jonwelzel.tagtreeapi.user.UserModel;

import java.util.Objects;

@Entity
@Table(name = "tags")
public class TagModel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserModel user;

    public TagModel() {
    }

    public TagModel(String name, String description, UserModel user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagModel tag)) return false;
        return Objects.equals(id, tag.id) && Objects.equals(name, tag.name)
                && Objects.equals(description, tag.description) && Objects.equals(user.getId(), tag.user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, user.getId());
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
