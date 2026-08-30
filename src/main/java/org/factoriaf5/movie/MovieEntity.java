package org.factoriaf5.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.factoriaf5.actor.ActorEntity;
import org.factoriaf5.genre.GenreEntity;
import org.factoriaf5.year.YearEntity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Movie entity. The central entity of the model.
 *
 * Relations:
 *  - N:1 with GenreEntity (many movies can share the same genre)
 *  - N:1 with YearEntity  (many movies can share the same release year)
 *  - N:M with ActorEntity (a movie has several actors and an actor appears
 *                          in several movies), materialized in "movie_actor"
 */
@Entity
@Table(name = "movies")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "synopsis", length = 2000)
    private String synopsis;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(name = "director", length = 100)
    private String director;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "genre_id", nullable = false)
    @JsonIgnoreProperties({"movies"})
    private GenreEntity genre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "year_id", nullable = false)
    @JsonIgnoreProperties({"movies"})
    private YearEntity year;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @JsonIgnoreProperties({"movies"})
    private Set<ActorEntity> actors = new HashSet<>();

    public MovieEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

    public YearEntity getYear() {
        return year;
    }

    public void setYear(YearEntity year) {
        this.year = year;
    }

    public Set<ActorEntity> getActors() {
        return actors;
    }

    public void setActors(Set<ActorEntity> actors) {
        this.actors = actors;
    }

    public void addActor(ActorEntity actor) {
        this.actors.add(actor);
        actor.getMovies().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieEntity)) return false;
        MovieEntity that = (MovieEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
