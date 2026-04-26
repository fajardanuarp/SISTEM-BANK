package sistembank;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void transfer(Rekening dari, Rekening ke, double jumlah) {
        System.out.println("\n--- Transfer dari " + dari.getNamaPemilik()
                + " ke " + ke.getNamaPemilik() + " ---");

        boolean giroKeTabungan = (dari instanceof Giro) && (ke instanceof Tabungan);
        double biayaAdmin = giroKeTabungan ? 5000 : 0;
        double totalDebet = jumlah + biayaAdmin;

        if (dari.getSaldo() < totalDebet && !(dari instanceof Giro)) {
            System.out.println("Saldo tidak cukup untuk transfer.");
            return;
        }

        dari.tarik(totalDebet);
        if (biayaAdmin > 0) {
            System.out.println("Biaya administrasi Giro ke Tabungan: Rp 5.000 dipotong.");
        }
        ke.setor(jumlah);
        System.out.println("Transfer Rp " + String.format("%,.0f", jumlah) + " selesai.\n");
    }

    public static void simulasiBungaBulanan(List<Rekening> daftarRekening, int bulan) {
        System.out.println("\n===== SIMULASI BUNGA BULANAN (" + bulan + " BULAN) =====");
        for (Rekening r : daftarRekening) {
            if (r instanceof Tabungan t) {
                double bunga = t.hitungBunga(bulan);
                t.setor(bunga);
                System.out.println("Bunga ditambahkan ke " + t.getNamaPemilik()
                        + " | Saldo baru: Rp " + String.format("%,.0f", t.getSaldo()));
            } else if (r instanceof Deposito d) {
                double bunga = d.hitungBunga();
                d.setor(bunga);
                System.out.println("Bunga ditambahkan ke " + d.getNamaPemilik()
                        + " | Saldo baru: Rp " + String.format("%,.0f", d.getSaldo()));
            }
        }
        System.out.println("====================================================\n");
    }

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   SOAL 1: CLASS & OBJECT                ║");
        System.out.println("╚══════════════════════════════════════════╝");

        Rekening rekDasar = new Rekening("Budi Santoso", "RK-001", 500_000);
        rekDasar.tampilkanInfo();
        rekDasar.setor(200_000);
        rekDasar.tarik(100_000);
        rekDasar.tarik(700_000);
        rekDasar.tampilkanInfo();

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║   SOAL 2: INHERITANCE & OVERRIDING      ║");
        System.out.println("╚══════════════════════════════════════════╝");

        Tabungan tabungan1 = new Tabungan("Siti Rahayu", "TB-001", 10_000_000, 6.0);
        tabungan1.tampilkanInfo();
        tabungan1.setor(2_000_000);
        tabungan1.tarik(500_000);
        tabungan1.hitungBunga(3);
        tabungan1.tampilkanInfo();

        Giro giro1 = new Giro("Andi Wijaya", "GR-001", 5_000_000, 3_000_000);
        giro1.tampilkanInfo();
        giro1.tarik(7_000_000);
        giro1.tarik(2_000_000);
        giro1.tampilkanInfo();

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║   SOAL 3: OVERLOADING                   ║");
        System.out.println("╚══════════════════════════════════════════╝");

        Tabungan tabungan2 = new Tabungan("Dewi Lestari", "TB-002", 1_000_000, 5.0);
        System.out.println("-- Setor tunai biasa --");
        tabungan2.setor(500_000);
        System.out.println("-- Setor via transfer (ada biaya admin Rp 2.500) --");
        tabungan2.setor(1_000_000, true);
        tabungan2.tampilkanInfo();

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║   SOAL 4: POLIMORFISME – DEPOSITO       ║");
        System.out.println("╚══════════════════════════════════════════╝");

        Deposito deposito1 = new Deposito("Rudi Hartono", "DP-001", 20_000_000, 12, 0.005);
        deposito1.tampilkanInfo();
        deposito1.hitungBunga();
        System.out.println("\n-- Penarikan SEBELUM jatuh tempo (kena denda 10%) --");
        deposito1.tarik(5_000_000);
        deposito1.tampilkanInfo();

        Deposito deposito2 = new Deposito("Maya Putri", "DP-002", 15_000_000, 6, 0.006);
        deposito2.hitungBunga();
        deposito2.setJatuhTempo(true);
        deposito2.tarik(5_000_000);
        deposito2.tampilkanInfo();

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║   SOAL 5: ARRAYLIST – SEMUA REKENING    ║");
        System.out.println("╚══════════════════════════════════════════╝");

        List<Rekening> daftarRekening = new ArrayList<>();
        daftarRekening.add(tabungan1);
        daftarRekening.add(tabungan2);
        daftarRekening.add(giro1);
        daftarRekening.add(deposito1);
        daftarRekening.add(deposito2);

        System.out.println("\n-- Daftar Semua Rekening --");
        for (Rekening r : daftarRekening) {
            r.tampilkanInfo();
        }

        Rekening tertinggi = daftarRekening.get(0);
        Rekening terendah  = daftarRekening.get(0);
        for (Rekening r : daftarRekening) {
            if (r.getSaldo() > tertinggi.getSaldo()) tertinggi = r;
            if (r.getSaldo() < terendah.getSaldo())  terendah  = r;
        }
        System.out.println(">> Saldo TERTINGGI: " + tertinggi.getNamaPemilik()
                + " | Rp " + String.format("%,.0f", tertinggi.getSaldo()));
        System.out.println(">> Saldo TERENDAH : " + terendah.getNamaPemilik()
                + " | Rp " + String.format("%,.0f", terendah.getSaldo()));

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║   OPSIONAL 1: TRANSFER ANTAR REKENING   ║");
        System.out.println("╚══════════════════════════════════════════╝");

        transfer(tabungan1, giro1, 1_000_000);
        transfer(giro1, tabungan2, 500_000);

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║   OPSIONAL 2: SIMULASI BUNGA BULANAN    ║");
        System.out.println("╚══════════════════════════════════════════╝");

        simulasiBungaBulanan(daftarRekening, 1);

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║   OPSIONAL 3: LAPORAN TRANSAKSI         ║");
        System.out.println("╚══════════════════════════════════════════╝");

        for (Rekening r : daftarRekening) {
            r.cetakLaporan();
        }

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   PROGRAM SELESAI                       ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }
}