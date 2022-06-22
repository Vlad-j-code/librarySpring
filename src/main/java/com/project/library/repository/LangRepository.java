package com.project.library.repository;

import com.project.library.entity.Lang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LangRepository extends JpaRepository<Lang, Long> {

    @Query(value = "SELECT * FROM lang WHERE code = ?", nativeQuery = true)
    Lang findByCode(String code);
}
