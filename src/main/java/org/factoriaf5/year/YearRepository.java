package org.factoriaf5.year;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface YearRepository extends JpaRepository<YearEntity, Long> {

    Optional<YearEntity> findByValue(Integer value);
}
