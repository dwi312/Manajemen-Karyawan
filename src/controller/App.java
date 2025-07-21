package controller;

public class App {
    private MainApp app;

    public App(MainApp app) {
        this.app = app;
    }

    public void menu() {
        System.out.println("\n=== SISTEM MANAJEMEN KARYAWAN ===");
        System.out.println("1. Tambah Karyawan");
        System.out.println("2. Lihat Semua Karyawan");
        System.out.println("3. Cari Karyawan");
        System.out.println("4. Ubah Data Karyawan");
        System.out.println("5. Hapus Karyawan");
        System.out.println("0. Keluar");
        System.out.println("--------------------------------");
        System.out.print("Pilih menu: ");
    }

    public boolean piliMenu(int piliahan) {
        boolean exit = false;
        switch (piliahan) {
            case 1:
                app.tambahKaryawan();
                break;
            case 2:
                app.daftarKaryawan();
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            case 5:
                
                break;
            case 0:
                exit = true;
                break;
        
            default:
                break;
        }

        return exit;
    }

}
