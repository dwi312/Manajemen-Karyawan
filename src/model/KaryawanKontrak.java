package model;

public class KaryawanKontrak extends Karyawan {
    private double upahPerJam;
    private int totalJamKerja;

    public KaryawanKontrak(String id, String nama, double upahPerJam, int totalJamKerja ) {
        super(id, nama, "Kontrak");
        this.upahPerJam = upahPerJam;
        this.totalJamKerja = totalJamKerja;
    }

    public double getUpahPerJam() {
        return upahPerJam;
    }

    public void setUpahPerJam(double upahPerJam) {
        this.upahPerJam = upahPerJam;
    }

    public int getTotalJamKerja() {
        return totalJamKerja;
    }

    public void setTotalJamKerja(int totalJamKerja) {
        this.totalJamKerja = totalJamKerja;
    }

    @Override
    public double  hitungGaji() {
        return upahPerJam * totalJamKerja;
    }

}
