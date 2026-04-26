package sistembank;

public class Deposito extends Rekening {
    private int jangkaWaktuBulan;
    private double sukuBunga;
    private boolean sudahJatuhTempo;

    public Deposito(String namaPemilik, String nomorRekening, double saldoAwal,
                    int jangkaWaktuBulan, double sukuBunga) {
        super(namaPemilik, nomorRekening, saldoAwal);
        this.jangkaWaktuBulan = jangkaWaktuBulan;
        this.sukuBunga        = sukuBunga;
        this.sudahJatuhTempo  = false;
    }

    public double hitungBunga() {
        double bunga = sukuBunga * saldo * jangkaWaktuBulan;
        System.out.println("Bunga deposito untuk " + jangkaWaktuBulan + " bulan: Rp " + String.format("%,.2f", bunga));
        return bunga;
    }

    public void setJatuhTempo(boolean status) {
        this.sudahJatuhTempo = status;
        if (status) System.out.println("Status deposito: sudah jatuh tempo.");
    }

    @Override
    public void tarik(double jumlah) {
        if (jumlah <= 0) {
            System.out.println("Jumlah penarikan harus lebih dari 0.");
            return;
        }
        if (!sudahJatuhTempo) {
            double denda = saldo * 0.10;
            System.out.println("PERINGATAN: Penarikan sebelum jatuh tempo!");
            System.out.println("Denda 10% dari saldo: Rp " + String.format("%,.2f", denda));
            saldo -= denda;
            String logDenda = "[DENDA] Rp " + String.format("%,.2f", denda) + " | Saldo setelah denda: Rp " + String.format("%,.0f", saldo);
            riwayatTransaksi.add(logDenda);
        }
        if (jumlah > saldo) {
            System.out.println("Saldo tidak cukup setelah denda. Saldo: Rp " + String.format("%,.0f", saldo));
            return;
        }
        saldo -= jumlah;
        String log = "[TARIK DEPOSITO] Rp " + String.format("%,.0f", jumlah) + " | Saldo: Rp " + String.format("%,.0f", saldo);
        riwayatTransaksi.add(log);
        System.out.println("Penarikan deposito Rp " + String.format("%,.0f", jumlah)
                + " berhasil. Saldo: Rp " + String.format("%,.0f", saldo));
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("====== REKENING DEPOSITO ======");
        System.out.println("Nama Pemilik      : " + namaPemilik);
        System.out.println("Nomor Rekening    : " + nomorRekening);
        System.out.println("Saldo             : Rp " + String.format("%,.0f", saldo));
        System.out.println("Jangka Waktu      : " + jangkaWaktuBulan + " bulan");
        System.out.println("Suku Bunga        : " + (sukuBunga * 100) + "% per bulan");
        System.out.println("Status Jatuh Tempo: " + (sudahJatuhTempo ? "Sudah" : "Belum"));
        System.out.println("===============================");
    }

    public int getJangkaWaktuBulan() { return jangkaWaktuBulan; }
    public double getSukuBunga()     { return sukuBunga; }
    public boolean isSudahJatuhTempo() { return sudahJatuhTempo; }
}