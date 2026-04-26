package sistembank;

public class Giro extends Rekening {
    private double limitPenarikan;

    public Giro(String namaPemilik, String nomorRekening, double saldoAwal, double limitPenarikan) {
        super(namaPemilik, nomorRekening, saldoAwal);
        this.limitPenarikan = limitPenarikan;
    }

    @Override
    public void tarik(double jumlah) {
        if (jumlah <= 0) {
            System.out.println("Jumlah penarikan harus lebih dari 0.");
            return;
        }
        if ((saldo - jumlah) < -limitPenarikan) {
            System.out.println("Melebihi limit giro! Limit: Rp " + String.format("%,.0f", limitPenarikan)
                    + " | Saldo saat ini: Rp " + String.format("%,.0f", saldo));
            return;
        }
        saldo -= jumlah;
        String log = "[TARIK GIRO] Rp " + String.format("%,.0f", jumlah) + " | Saldo: Rp " + String.format("%,.0f", saldo);
        riwayatTransaksi.add(log);
        System.out.println("Penarikan giro Rp " + String.format("%,.0f", jumlah)
                + " berhasil. Saldo: Rp " + String.format("%,.0f", saldo));
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("====== REKENING GIRO ======");
        System.out.println("Nama Pemilik    : " + namaPemilik);
        System.out.println("Nomor Rekening  : " + nomorRekening);
        System.out.println("Saldo           : Rp " + String.format("%,.0f", saldo));
        System.out.println("Limit Penarikan : Rp " + String.format("%,.0f", limitPenarikan));
        System.out.println("===========================");
    }

    public double getLimitPenarikan() { return limitPenarikan; }
}