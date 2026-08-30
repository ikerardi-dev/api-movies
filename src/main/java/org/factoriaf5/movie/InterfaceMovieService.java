package org.factoriaf5.movie;

import org.factoriaf5.movie.dtos.MovieDTOResponse;

import java.util.List;

/**
 * Movie-specific contract for the extra search endpoint (findBy title
 * and/or genre), which does not belong in the generic
 * InterfaceGenericGetService because not every entity needs it (same
 * pattern as the InterfaceCountryService shown in class).
 */
public interface InterfaceMovieService {

    List<MovieDTOResponse> search(String title, String genre);
}
