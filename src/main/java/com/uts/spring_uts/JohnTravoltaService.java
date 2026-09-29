package com.uts.spring_uts;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class JohnTravoltaService {

    public Map<String, Object> hitungGajiDanTabungan(double jamKerja, double rate, double pengeluaran) {
        Map<String, Object> hasil = new HashMap<>();

        double gajiPokok = 0;
        double gajiLembur = 0;

        // 1. Logika Hitung Gaji (Normal 40 jam, lembur 1.5x)
        if (jamKerja > 40) {
            gajiPokok = 40 * rate;
            double jamLembur = jamKerja - 40;
            gajiLembur = jamLembur * 1.5 * rate;
        } else {
            gajiPokok = jamKerja * rate;
        }

        double totalGaji = gajiPokok + gajiLembur;

        // 2. Logika Status Tabungan
        String status;
        double tabungan = 0;
        double kurangUang = 0;

        if (totalGaji > pengeluaran) {
            status = "bisa menabung";
            tabungan = totalGaji - pengeluaran;
        } else if (totalGaji == pengeluaran) {
            status = "tidak bisa menabung";
        } else {
            status = "cari tambahan";
            kurangUang = pengeluaran - totalGaji;
        }

        // Output Data ke Web
        hasil.put("jamKerja", jamKerja);
        hasil.put("ratePerJam", rate);
        hasil.put("totalGaji", totalGaji);
        hasil.put("pengeluaran", pengeluaran);
        hasil.put("status", status);
        hasil.put("besarTabungan", tabungan);
        if (kurangUang > 0) {
            hasil.put("kurangUang", kurangUang);
        }

        return hasil;
    }
}