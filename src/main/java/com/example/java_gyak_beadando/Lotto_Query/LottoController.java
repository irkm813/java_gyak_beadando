package com.example.java_gyak_beadando.Lotto_Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/huzas") // Minden végpont ezen az alapon lesz elérhető
public class LottoController {

    private final LottoService huzasService;

    @Autowired
    public LottoController(LottoService huzasService) {
        this.huzasService = huzasService;
    }

    @GetMapping("/years")
    public List<Integer> getYears() {
        // Az évek lekérdezése a HuzasService használatával
        return huzasService.getDistinctYears();
    }

    @GetMapping("/weeks")
    public List<LottoEntity> getWeeksForYear(@RequestParam int year) {
        // Adott évhez tartozó hetek lekérdezése
        return huzasService.getWeeksForYear(year);
    }

    @GetMapping("/results")
    public List<LottoDto> getResults(@RequestParam int year, @RequestParam int week) {
        // Adott év és hét részletei, beleértve a nyereményeket is
        return huzasService.getHuzasWithDetails(year, week);
    }
}
