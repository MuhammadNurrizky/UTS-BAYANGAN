package com.uts.spring_uts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class AppController {

    @Autowired
    private JohnTravoltaService johnTravoltaService;

    @Autowired
    private QuadraticService quadraticService;

    // 1. Web Form Interactive untuk John Travolta
    @GetMapping(value = "/john", produces = "text/html")
    public String formJohn(
            @RequestParam(required = false) Double jam,
            @RequestParam(required = false) Double rate,
            @RequestParam(required = false) Double pengeluaran) {

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html><head><title>Kalkulator John Travolta</title></head>");
        html.append("<body style='font-family: Arial, sans-serif; margin: 40px;'>");
        html.append("<h2>Kalkulator Gaji & Status Tabungan John Travolta</h2>");

        html.append("<form method='GET' action='/john'>");
        html.append("<label>Jam Kerja per Minggu:</label><br>");
        html.append("<input type='number' step='any' name='jam' value='").append(jam != null ? jam : "52").append("' required><br><br>");

        html.append("<label>Tarif Gaji per Jam (Rp):</label><br>");
        html.append("<input type='number' step='any' name='rate' value='").append(rate != null ? rate : "15000").append("' required><br><br>");

        html.append("<label>Pengeluaran (Rp):</label><br>");
        html.append("<input type='number' step='any' name='pengeluaran' value='").append(pengeluaran != null ? pengeluaran : "600000").append("' required><br><br>");

        html.append("<button type='submit' style='padding: 8px 16px;'>Hitung Status</button>");
        html.append("</form>");

        if (jam != null && rate != null && pengeluaran != null) {
            Map<String, Object> hasil = johnTravoltaService.hitungGajiDanTabungan(jam, rate, pengeluaran);

            html.append("<hr><h3>Hasil Perhitungan:</h3>");
            html.append("<p><b>Total Gaji Diterima:</b> Rp ").append(hasil.get("totalGaji")).append("</p>");
            html.append("<p><b>Status: </b>").append(hasil.get("status")).append("</span></p>");

            String status = (String) hasil.get("status");
            if ("bisa menabung".equals(status)) {
                html.append("<p><b>Besar Tabungan:</b> Rp ").append(hasil.get("besarTabungan")).append("</p>");
            } else if ("cari tambahan".equals(status)) {
                html.append("<p><b>Kekurangan Uang:</b> Rp ").append(hasil.get("kurangUang")).append("</p>");
            }
        }

        html.append("</body></html>");
        return html.toString();
    }

    // 2. Web Form Interactive untuk Persamaan Kuadrat
    @GetMapping(value = "/kuadrat", produces = "text/html")
    public String formKuadrat(
            @RequestParam(required = false) Double a,
            @RequestParam(required = false) Double b,
            @RequestParam(required = false) Double c) {

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html><head><title>Kalkulator Persamaan Kuadrat</title></head>");
        html.append("<body style='font-family: Arial, sans-serif; margin: 40px;'>");
        html.append("<h2>Kalkulator Persamaan Kuadrat (ax&sup2; + bx + c = 0)</h2>");

        html.append("<form method='GET' action='/kuadrat'>");
        html.append("<label>Nilai a:</label><br>");
        html.append("<input type='number' step='any' name='a' value='").append(a != null ? a : "1").append("' required><br><br>");

        html.append("<label>Nilai b:</label><br>");
        html.append("<input type='number' step='any' name='b' value='").append(b != null ? b : "-5").append("' required><br><br>");

        html.append("<label>Nilai c:</label><br>");
        html.append("<input type='number' step='any' name='c' value='").append(c != null ? c : "6").append("' required><br><br>");

        html.append("<button type='submit' style='padding: 8px 16px;'>Hitung Akar</button>");
        html.append("</form>");

        if (a != null && b != null && c != null) {
            Map<String, Object> hasil = quadraticService.hitungPersamaanKuadrat(a, b, c);

            html.append("<hr><h3>Hasil Perhitungan:</h3>");
            html.append("<p><b>Nilai Diskriminan (D):</b> ").append(hasil.get("diskriminan")).append("</p>");
            html.append("<p><b>Jenis Akar: </b>").append(hasil.get("jenisAkar")).append("</span></p>");

            if (hasil.containsKey("x1")) {
                html.append("<p><b>Akar x1:</b> ").append(hasil.get("x1")).append("</p>");
                html.append("<p><b>Akar x2:</b> ").append(hasil.get("x2")).append("</p>");
            }
        }

        html.append("</body></html>");
        return html.toString();
    }

    // 3. REST API Endpoint JSON Asli (tetap disiapkan untuk REST)
    @GetMapping("/api/john")
    public Map<String, Object> apiJohn(
            @RequestParam(defaultValue = "52") double jam,
            @RequestParam(defaultValue = "15000") double rate,
            @RequestParam(defaultValue = "600000") double pengeluaran) {
        return johnTravoltaService.hitungGajiDanTabungan(jam, rate, pengeluaran);
    }

    @GetMapping("/api/kuadrat")
    public Map<String, Object> apiKuadrat(
            @RequestParam(defaultValue = "1") double a,
            @RequestParam(defaultValue = "-5") double b,
            @RequestParam(defaultValue = "6") double c) {
        return quadraticService.hitungPersamaanKuadrat(a, b, c);
    }
}