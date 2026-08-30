package org.factoriaf5.movie;

import org.factoriaf5.actor.ActorEntity;
import org.factoriaf5.actor.ActorRepository;
import org.factoriaf5.genre.GenreEntity;
import org.factoriaf5.genre.GenreRepository;
import org.factoriaf5.globals.exceptions.ApiNotFoundException;
import org.factoriaf5.implementations.InterfaceGenericEditService;
import org.factoriaf5.implementations.InterfaceGenericGetService;
import org.factoriaf5.movie.dtos.MovieDTORequest;
import org.factoriaf5.movie.dtos.MovieDTOResponse;
import org.factoriaf5.movie.mappers.MovieMapper;
import org.factoriaf5.year.YearEntity;
import org.factoriaf5.year.YearRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MovieServiceImpl implements InterfaceGenericGetService<MovieDTOResponse>,
        InterfaceGenericEditService<MovieDTORequest, MovieDTOResponse>, InterfaceMovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final YearRepository yearRepository;
    private final ActorRepository actorRepository;

    public MovieServiceImpl(MovieRepository movieRepository,
                             GenreRepository genreRepository,
                             YearRepository yearRepository,
                             ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.yearRepository = yearRepository;
        this.actorRepository = actorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDTOResponse> getEntities() {
        return MovieMapper.toDTOList(movieRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public MovieDTOResponse getById(Long id) {
        return MovieMapper.toDTO(findEntity(id));
    }

    @Override
    public MovieDTOResponse storeEntity(MovieDTORequest dto) {
        MovieEntity movie = new MovieEntity();
        applyData(movie, dto);
        return MovieMapper.toDTO(movieRepository.save(movie));
    }

    @Override
    public MovieDTOResponse updateEntity(Long id, MovieDTORequest dto) {
        MovieEntity movie = findEntity(id);
        applyData(movie, dto);
        return MovieMapper.toDTO(movieRepository.save(movie));
    }

    @Override
    public void deleteEntity(Long id) {
        MovieEntity movie = findEntity(id);
        // Clear the N:M relation so no orphan rows are left in movie_actor
        movie.getActors().forEach(actor -> actor.getMovies().remove(movie));
        movieRepository.delete(movie);
    }

    /**
     * Endpoint 6 (findBy): searches movies by title and/or genre.
     * If no parameter is given, every movie is returned.
     */
    @Override
    @Transactional(readOnly = true)
    public List<MovieDTOResponse> search(String title, String genre) {
        String titleSearch = StringUtils.hasText(title) ? title.trim() : null;
        String genreSearch = StringUtils.hasText(genre) ? genre.trim() : null;

        if (titleSearch == null && genreSearch == null) {
            return getEntities();
        }
        return MovieMapper.toDTOList(
                movieRepository.searchByTitleOrGenre(titleSearch, genreSearch));
    }

    private void applyData(MovieEntity movie, MovieDTORequest dto) {
        GenreEntity genre = genreRepository.findById(dto.genreId())
                .orElseThrow(() -> ApiNotFoundException.of("Genre", dto.genreId()));
        YearEntity year = yearRepository.findById(dto.yearId())
                .orElseThrow(() -> ApiNotFoundException.of("Year", dto.yearId()));

        movie.setTitle(dto.title());
        movie.setSynopsis(dto.synopsis());
        movie.setDurationMinutes(dto.durationMinutes());
        movie.setDirector(dto.director());
        movie.setGenre(genre);
        movie.setYear(year);

        // Unlink the current actors and reassign the new ones, keeping the
        // N:M relation consistent on both sides.
        movie.getActors().forEach(actor -> actor.getMovies().remove(movie));
        movie.getActors().clear();

        if (dto.actorIds() != null && !dto.actorIds().isEmpty()) {
            Set<ActorEntity> actors = new HashSet<>(actorRepository.findAllById(dto.actorIds()));
            if (actors.size() != new HashSet<>(dto.actorIds()).size()) {
                throw new ApiNotFoundException("One or more of the given actors do not exist");
            }
            actors.forEach(movie::addActor);
        }
    }

    private MovieEntity findEntity(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> ApiNotFoundException.of("Movie", id));
    }
}
