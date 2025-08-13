package com.expenses_traker.db_service.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expenses_traker.db_service.Entity.testData;


@Repository
public interface TestRepository extends JpaRepository<testData, Long> {
    // Additional query methods can be defined here if needed
}
