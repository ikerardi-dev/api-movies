package org.factoriaf5.actor;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Actor entity.
 */
@Entity
@Table(name = "actors")
public class ActorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "nationality", length = 60)
    private String nationality;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    public ActorEntity() {
    }

    public ActorEntity(String name, String nationality, LocalDate dateOfBirth) {
        this.name = name;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActorEntity)) return false;
        ActorEntity that = (ActorEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
