package sistembank;

import java.util.ArrayList;
import java.util.List;

public class Rekening {
    protected String namaPemilik;
    protected String nomorRekening;
    protected double saldo;
    protected List<String> riwayatTransaksi;

    public Rekening(String namaPemilik, String nomorRekening, double saldoAwal) {
        this.namaPemilik    = namaPemilik;
        this.nomorRekening  = nomorRekening;
        this.saldo          = saldoAwal;
        this.riwayatTransaksi = new ArrayList<>();
        riwayatTransaksi.add("[BUKA] Rekening dibuka dengan saldo awal: Rp " + String.format("%,.0f", saldoAwal));
    }

    public void setor(double jumlah) {
        if (jumlah <= 0) {
            System.out.println("Jumlah setoran harus lebih dari 0.");
            return;
        }
        saldo += jumlah;
        String log = "[SETOR] Rp " + String.format("%,.0f", jumlah) + " | Saldo: Rp " + String.format("%,.0f", saldo);
        riwayatTransaksi.add(log);
        System.out.println("Setoran tunai Rp " + String.format("%,.0f", jumlah) + " berhasil. Saldo: Rp " + String.format("%,.0f", saldo));
    }

    public void setor(double jumlah, boolean transfer) {
        if (!transfer) {
            setor(jumlah);
            return;
        }
        if (jumlah <= 0) {
            System.out.println("Jumlah transfer harus lebih dari 0.");
            return;
        }
        double biayaAdmin = 2500;
        saldo += jumlah - biayaAdmin;
        String log = "[TRANSFER MASUK] Rp " + String.format("%,.0f", jumlah)
                   + " | Biaya Admin: Rp " + String.format("%,.0f", biayaAdmin)
                   + " | Saldo: Rp " + String.format("%,.0f", saldo);
        riwayatTransaksi.add(log);
        System.out.println("Transfer masuk Rp " + String.format("%,.0f", jumlah)
                + " (biaya admin Rp 2.500). Saldo: Rp " + String.format("%,.0f", saldo));
    }

    public void tarik(double jumlah) {
        if (jumlah <= 0) {
            System.out.println("Jumlah penarikan harus lebih dari 0.");
            return;
        }
        if (jumlah > saldo) {
            System.out.println("Saldo tidak cukup! Saldo saat ini: Rp " + String.format("%,.0f", saldo));
            return;
        }
        saldo -= jumlah;
        String log = "[TARIK] Rp " + String.format("%,.0f", jumlah) + " | Saldo: Rp " + String.format("%,.0f", saldo);
        riwayatTransaksi.add(log);
        System.out.println("Penarikan Rp " + String.format("%,.0f", jumlah) + " berhasil. Saldo: Rp " + String.format("%,.0f", saldo));
    }

    public void tampilkanInfo() {
        System.out.println("==============================");
        System.out.println("Nama Pemilik    : " + namaPemilik);
        System.out.println("Nomor Rekening  : " + nomorRekening);
        System.out.println("Saldo           : Rp " + String.format("%,.0f", saldo));
        System.out.println("==============================");
    }

    public void cetakLaporan() {
        System.out.println("\n===== LAPORAN TRANSAKSI =====");
        System.out.println("Rekening: " + nomorRekening + " (" + namaPemilik + ")");
        for (String log : riwayatTransaksi) {
            System.out.println("  " + log);
        }
        System.out.println("=============================\n");
    }

    public double getSaldo() { return saldo; }
    public String getNamaPemilik() { return namaPemilik; }
    public String getNomorRekening() { return nomorRekening; }
}