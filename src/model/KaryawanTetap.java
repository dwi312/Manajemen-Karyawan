package model;

public class KaryawanTetap extends Karyawan {
    private double gaji;

    public KaryawanTetap(String id, String nama, double gaji) {
        super(id, nama, "Tetap");
        this.gaji = gaji;
    }

    public double  getGaji() {
        return gaji;
    }

    public void setGaji(double  gaji) {
        this.gaji = gaji;
    }

    @Override
    public double hitungGaji() {
        return gaji;
    }

}
