package sistembank;

public class Tabungan extends Rekening {
    private double bungaTahunan;

    public Tabungan(String namaPemilik, String nomorRekening, double saldoAwal, double bungaTahunan) {
        super(namaPemilik, nomorRekening, saldoAwal);
        this.bungaTahunan = bungaTahunan;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("====== REKENING TABUNGAN ======");
        System.out.println("Nama Pemilik    : " + namaPemilik);
        System.out.println("Nomor Rekening  : " + nomorRekening);
        System.out.println("Saldo           : Rp " + String.format("%,.0f", saldo));
        System.out.println("Bunga Tahunan   : " + bungaTahunan + "%");
        System.out.println("==============================");
    }

    public double hitungBunga(int bulan) {
        double bunga = (bungaTahunan / 100 / 12) * saldo * bulan;
        System.out.println("Bunga untuk " + bulan + " bulan: Rp " + String.format("%,.2f", bunga));
        return bunga;
    }

    public double getBungaTahunan() { return bungaTahunan; }
}