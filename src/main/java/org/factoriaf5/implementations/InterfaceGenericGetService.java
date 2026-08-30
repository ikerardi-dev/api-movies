package org.factoriaf5.implementations;

import java.util.List;

/**
 * Generic contract for read operations, reusable by any entity (Movie,
 * Genre, Year, Actor...).
 *
 * @param <T> response DTO (what is returned to the client)
 */
public interface InterfaceGenericGetService<T> {

    List<T> getEntities();

    T getById(Long id);

}
