document.addEventListener("DOMContentLoaded", function () {
    loadYears(); // Évek betöltése induláskor

    document.getElementById("year").addEventListener("change", function () {
        const year = this.value;
        loadWeeks(year); // Hetek betöltése az adott évhez
    });

    document.getElementById("query-button").addEventListener("click", function () {
        const year = document.getElementById("year").value;
        const week = document.getElementById("week").value;

        if (year && week) {
            fetchLottoResults(year, week); // Lottó adatok lekérdezése
        } else {
            alert("Kérlek, válassz ki egy évet és egy hetet!");
        }
    });
});

function loadYears() {
    fetch("/api/huzas/years") // Évek API végpont
        .then(response => response.json())
        .then(data => {
            const yearSelect = document.getElementById("year");
            yearSelect.innerHTML = '<option value="" disabled selected>Válassz évet</option>';
            data.forEach(year => {
                const option = document.createElement("option");
                option.value = year;
                option.textContent = year;
                yearSelect.appendChild(option);
            });
        });
}

function loadWeeks(year) {
    fetch(`/api/huzas/weeks?year=${year}`) // Hetek API végpont
        .then(response => response.json())
        .then(data => {
            const weekSelect = document.getElementById("week");
            weekSelect.innerHTML = '<option value="" disabled selected>Válassz hetet</option>';
            data.forEach(week => {
                const option = document.createElement("option");
                option.value = week.het;
                option.textContent = week.het;
                weekSelect.appendChild(option);
            });
        });
}

function fetchLottoResults(year, week) {
    fetch(`/api/huzas/results?year=${year}&week=${week}`)
        .then(response => response.json())
        .then(data => {
            const resultsDiv = document.getElementById("results");
            resultsDiv.innerHTML = ''; // Előző eredmények törlése

            if (data.length > 0) {
                data.forEach(result => {
                    // Alap információk (év, hét, számok)
                    const p = document.createElement("p");
                    p.innerHTML = `
                        <strong>Év:</strong> ${result.ev}, <strong>Hét:</strong> ${result.het}<br>
                        <strong>Húzott számok:</strong> ${result.szamok || "N/A"}
                    `;
                    resultsDiv.appendChild(p);

                    // Nyeremények feldolgozása
                    if (result.nyeremenyek && result.nyeremenyek.length > 0) {
                        // Ellenőrizzük, hogy van-e legalább egy érvényes nyeremény adat
                        const validPrizes = result.nyeremenyek.filter(nyeremeny =>
                            nyeremeny.talalat !== 0 ||
                            nyeremeny.darab !== 0 ||
                            nyeremeny.ertek !== 0
                        );

                        if (validPrizes.length > 0) {
                            const prizeHeader = document.createElement("h4");
                            prizeHeader.textContent = "Nyeremények:";
                            resultsDiv.appendChild(prizeHeader);

                            validPrizes.forEach(nyeremeny => {
                                const prizeDiv = document.createElement("div");

                                // Találatok formázása (7 -> 5+1)
                                let talalat = nyeremeny.talalat;
                                if (talalat === 7) {
                                    talalat = "5+1";
                                }

                                // Formázott értékek (3 számjegyenként ponttal)
                                const darab = nyeremeny.darab ? nyeremeny.darab.toLocaleString('hu-HU') : 0;
                                const ertek = nyeremeny.ertek ? nyeremeny.ertek.toLocaleString('hu-HU') : 0;

                                prizeDiv.innerHTML = `
                                    <p><strong>${talalat} találat:</strong></p>
                                    <p>Darab: ${darab}</p>
                                    <p>Érték: ${ertek} HUF</p>
                                `;
                                resultsDiv.appendChild(prizeDiv);
                            });
                        } else {
                            const noPrize = document.createElement("p");
                            noPrize.textContent = "Nincs nyeremény adat.";
                            resultsDiv.appendChild(noPrize);
                        }
                    } else {
                        const noPrize = document.createElement("p");
                        noPrize.textContent = "Nincs nyeremény adat.";
                        resultsDiv.appendChild(noPrize);
                    }
                });
            } else {
                resultsDiv.textContent = "Nincs adat a megadott évre és hétre.";
            }
        })
        .catch(error => {
            console.error("Hiba történt az adatok lekérésekor:", error);
            const resultsDiv = document.getElementById("results");
            resultsDiv.textContent = "Hiba történt az adatok lekérésekor.";
        });
}
