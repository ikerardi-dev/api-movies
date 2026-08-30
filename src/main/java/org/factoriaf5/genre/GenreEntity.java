package org.factoriaf5.genre;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Genre entity.
 */
@Entity
@Table(name = "genres")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 60)
    private String name;

    public GenreEntity() {
    }

    public GenreEntity(String name) {
        this.name = name;
    }

    public GenreEntity(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenreEntity)) return false;
        GenreEntity that = (GenreEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
