package org.factoriaf5.movie;

import org.factoriaf5.actor.ActorRepository;
import org.factoriaf5.genre.GenreEntity;
import org.factoriaf5.genre.GenreRepository;
import org.factoriaf5.globals.exceptions.ApiNotFoundException;
import org.factoriaf5.movie.dtos.MovieDTORequest;
import org.factoriaf5.movie.dtos.MovieDTOResponse;
import org.factoriaf5.year.YearEntity;
import org.factoriaf5.year.YearRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the movie service layer, with mocked repositories so they
 * do not depend on a real database.
 */
@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private GenreRepository genreRepository;
    @Mock
    private YearRepository yearRepository;
    @Mock
    private ActorRepository actorRepository;

    private MovieServiceImpl movieService;

    @BeforeEach
    void setUp() {
        movieService = new MovieServiceImpl(
                movieRepository, genreRepository, yearRepository, actorRepository);
    }

    @Test
    void getById_shouldThrowExceptionIfItDoesNotExist() {
        when(movieRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> movieService.getById(99L))
                .isInstanceOf(ApiNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void store_shouldSaveMovieWithValidGenreAndYear() {
        MovieDTORequest dto = new MovieDTORequest(
                "Interstellar", null, null, null, 1L, 1L, null);

        GenreEntity genre = new GenreEntity(1L, "Science Fiction");
        YearEntity year = new YearEntity(1L, 2014);

        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(yearRepository.findById(1L)).thenReturn(Optional.of(year));
        when(movieRepository.save(any(MovieEntity.class))).thenAnswer(invocation -> {
            MovieEntity m = invocation.getArgument(0);
            m.setId(10L);
            return m;
        });

        MovieDTOResponse result = movieService.storeEntity(dto);

        assertThat(result.id()).isEqualTo(10L);
        assertThat(result.title()).isEqualTo("Interstellar");
        assertThat(result.genre().name()).isEqualTo("Science Fiction");
        verify(movieRepository, times(1)).save(any(MovieEntity.class));
    }

    @Test
    void delete_shouldThrowExceptionIfItDoesNotExist() {
        when(movieRepository.findById(5L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> movieService.deleteEntity(5L))
                .isInstanceOf(ApiNotFoundException.class);

        verify(movieRepository, never()).delete(any());
    }

    @Test
    void search_withoutParameters_shouldReturnAllMovies() {
        when(movieRepository.findAll()).thenReturn(List.of());

        List<MovieDTOResponse> result = movieService.search(null, null);

        assertThat(result).isEmpty();
        verify(movieRepository, times(1)).findAll();
        verify(movieRepository, never()).searchByTitleOrGenre(any(), any());
    }

    @Test
    void search_withTitle_shouldUseTheFilteredQuery() {
        when(movieRepository.searchByTitleOrGenre("matrix", null)).thenReturn(List.of());

        movieService.search("matrix", null);

        verify(movieRepository, times(1)).searchByTitleOrGenre("matrix", null);
        verify(movieRepository, never()).findAll();
    }
}
