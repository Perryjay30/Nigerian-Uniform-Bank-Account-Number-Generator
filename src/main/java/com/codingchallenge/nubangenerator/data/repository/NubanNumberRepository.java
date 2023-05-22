package com.codingchallenge.nubangenerator.data.repository;

import com.codingchallenge.nubangenerator.data.model.NubanNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NubanNumberRepository extends JpaRepository<NubanNumber, Long> {
}
