public abstract class Karyawan {
    private String id;
    private String nama;
    private String alamat;
    protected double gaji;

    public Karyawan(String id, String nama, String alamat, double gaji) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.gaji = gaji;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public double getGaji() {
        return gaji;
    }

    public void setGaji(double gaji) {
        this.gaji = gaji;
    }

    public abstract String getjenisKaryawan();

    @Override
    public String toString() {
        return id +" | " +nama+" | "+alamat+" | "+gaji;
    }

}
