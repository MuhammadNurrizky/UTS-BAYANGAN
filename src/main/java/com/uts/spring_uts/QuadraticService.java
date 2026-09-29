package com.uts.spring_uts;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class QuadraticService {

    public Map<String, Object> hitungPersamaanKuadrat(double a, double b, double c) {
        Map<String, Object> hasil = new HashMap<>();

        double d = (b * b) - (4 * a * c);
        hasil.put("diskriminan", d);

        if (d > 0) {
            double x1 = (-b + Math.sqrt(d)) / (2 * a);
            double x2 = (-b - Math.sqrt(d)) / (2 * a);
            hasil.put("jenisAkar", "Akar Real Berbeda");
            hasil.put("x1", x1);
            hasil.put("x2", x2);
        } else if (d == 0) {
            double x = -b / (2 * a);
            hasil.put("jenisAkar", "Akar Real Kembar");
            hasil.put("x1", x);
            hasil.put("x2", x);
        } else {
            hasil.put("jenisAkar", "Akar Imajiner");
        }

        return hasil;
    }
}