package org.factoriaf5.implementations;

/**
 * Generic contract for write operations, reusable by any entity (Movie,
 * Genre, Year, Actor...).
 *
 * @param <T> input DTO (what the client sends)
 * @param <S> response DTO (what is returned to the client)
 */
public interface InterfaceGenericEditService<T, S> {

    S storeEntity(T dto);

    S updateEntity(Long id, T dto);

    void deleteEntity(Long id);

}
