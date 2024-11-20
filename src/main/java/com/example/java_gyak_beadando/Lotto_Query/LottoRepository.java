package com.example.java_gyak_beadando.Lotto_Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LottoRepository extends JpaRepository<LottoEntity, Long> {

    @Query("SELECT DISTINCT h.ev FROM LottoEntity h")
    List<Integer> findDistinctYears();

    List<LottoEntity> findByEv(int ev);

    List<LottoEntity> findByEvAndHet(int ev, int het);

    @Query(value = "SELECT h.ev, h.het, GROUP_CONCAT(ho.szam ORDER BY ho.szam ASC) AS szamok, " +
            "n.talalat, n.darab, n.ertek " +
            "FROM huzas h " +
            "JOIN huzott ho ON h.id = ho.huzasid " +
            "LEFT JOIN nyeremeny n ON h.id = n.huzasid " +
            "WHERE h.ev = :ev AND h.het = :het " +
            "GROUP BY n.talalat, h.id",
            nativeQuery = true)
    List<Object[]> findHuzasWithDetails(@Param("ev") int ev, @Param("het") int het);
}

