package com.example.java_gyak_beadando.Lotto_Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LottoService {

    private final LottoRepository huzasRepository;

    @Autowired
    public LottoService(LottoRepository huzasRepository) {
        this.huzasRepository = huzasRepository;
    }

    public List<Integer> getDistinctYears() {
        // Az évek lekérdezése a repository-ból
        return huzasRepository.findDistinctYears();
    }

    public List<LottoEntity> getWeeksForYear(int year) {
        // Adott évhez tartozó hetek lekérdezése
        return huzasRepository.findByEv(year);
    }

    public List<LottoDto> getHuzasWithDetails(int year, int week) {
        List<Object[]> results = huzasRepository.findHuzasWithDetails(year, week);
        List<LottoDto> dtos = new ArrayList<>();

        LottoDto dto = null;
        for (Object[] result : results) {
            if (dto == null || dto.getHet() != (Integer) result[1]) {
                dto = new LottoDto();
                dto.setEv((Integer) result[0]);
                dto.setHet((Integer) result[1]);
                dto.setSzamok((String) result[2]);
                dto.setNyeremenyek(new ArrayList<>());
                dtos.add(dto);
            }

            NyeremenyDto nyeremeny = new NyeremenyDto();
            nyeremeny.setTalalat((Integer) result[3]);
            nyeremeny.setDarab((Integer) result[4]);
            nyeremeny.setErtek((Integer) result[5]);
            dto.getNyeremenyek().add(nyeremeny);
        }

        return dtos;
    }
}
