package com.overonix.test.repositories;

import com.overonix.test.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> {
}
