package controller;

import helper.AppHelper;

public class AppOutput {
    private MainApp app;

    public AppOutput(MainApp app) {
        this.app = app;
    }

    public void menu() {
        AppHelper.clearScreen();
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

    public boolean piliMenu(int pilihan) {
        boolean exit = false;
        switch (pilihan) {
            case 1:
                pilihKaryawan();
                break;
            case 2:
                app.daftarKaryawan();
                break;
            case 3:
                app.cariKaryawan();
                break;
            case 4:
                app.ubahDataKaryawan();
                break;
            case 5:
                app.hapusKaryawan();
                break;
            case 0:
                exit = true;
                break;
        
            default:
                break;
        }

        return exit;
    }

    public void pilihKaryawan() {
        AppHelper.clearScreen();
        System.out.println("\n=== Tambah Karyawan ===");
        System.out.println();
        System.out.println("1. Karyawan Tetap");
        System.out.println("2. Karyawan Kontrak");
        System.out.print("Pilih: ");
        app.tambahKaryawan();
    }

}
