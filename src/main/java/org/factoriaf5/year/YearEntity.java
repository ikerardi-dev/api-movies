package org.factoriaf5.year;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Year entity.
 */
@Entity
@Table(name = "years")
public class YearEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mapped to "year_value" instead of "value": VALUE is a reserved word in H2
    // and breaks CREATE TABLE if used unquoted as a column name.
    @Column(name = "year_value", nullable = false, unique = true)
    private Integer value;

    public YearEntity() {
    }

    public YearEntity(Integer value) {
        this.value = value;
    }

    public YearEntity(Long id, Integer value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof YearEntity)) return false;
        YearEntity that = (YearEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
