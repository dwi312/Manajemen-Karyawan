package controller;

import helper.AppHelper;
import java.util.Scanner;
import model.Karyawan;
import service.DataService;
import service.KaryawanService;
import view.AppView;

public class MainApp {
    public Scanner input = new Scanner(System.in);

    private AppView view;
    private KaryawanService karyawan;

    public void run() {
        initData();
        view = new AppView(this);

        int pilihan = -1;
        boolean exit = false;

        while (!exit) {
            view.menu();
            pilihan = AppHelper.inputInt(input);
            exit = view.pilihMenu(pilihan);
        }

        saveData();
        System.out.println("Program berhenti.");
        input.close();
    }

    
    
    public void daftarKaryawan() {
        AppHelper.clearScreen();
        System.out.println("\n=== Daftar Karyawan ===");
        System.out.println();
        if (!karyawan.getDaftarKaryawan().isEmpty()) {
            karyawan.tampilkanSemua();
        } else {
            System.out.println("Tidak ada karyawan yang terdaftar.");
        }

        AppHelper.enterToContinue(input);
    }
    
    public void cariKaryawan() {
        AppHelper.clearScreen();
        System.out.println("\n=== Cari Karyawan ===");
        System.out.println();
        System.out.print("Masukan ID/Nama Karyawan: ");
        String keyword = AppHelper.inputStr(input);

        Karyawan cari = karyawan.cariKaryawan(keyword);

        if (cari != null) {
            System.out.println("Data Karyawan: ");
            System.out.println(cari);
        } else {
            System.out.println("Karyawan tidak ditemukan.");
        }

        AppHelper.enterToContinue(input);
    }
    
    public void ubahDataKaryawan() {
        AppHelper.clearScreen();
        System.out.println("\n=== Rubah Data Karyawan ===");
        System.out.println();
        System.out.print("Masukan ID/Nama Karyawan: ");
        String keyword = AppHelper.inputStr(input);

        karyawan.updateKaryawan(keyword, input);
        saveData();
        AppHelper.enterToContinue(input);
    }
    
    public void hapusKaryawan() {
        AppHelper.clearScreen();
        System.out.println("\n=== Hapus Karyawan ===");
        System.out.println();

        System.out.print("Masukan ID/Nama Karyawan: ");
        String keyword = AppHelper.inputStr(input);
        
        Karyawan cari = karyawan.cariKaryawan(keyword);
        if (cari != null) {
            System.out.println("Data Karyawan: ");
            System.out.println("ID Karyawan   : " + cari.getId());
            System.out.println("Nama Karyawan : " + cari.getNama());
            System.out.println("Tipe Karyawan : " + cari.getTipe());
            System.out.println();
            System.out.println("Hapus data karyawan ini? (Y/N)");
            
            boolean ulang = false;
            while (!ulang) {   
                String hapus = AppHelper.inputStr(input);
                if (hapus.equalsIgnoreCase("Y")) {
                    karyawan.hapusKaryawan(keyword);
                    System.out.println("Karyawan berhasil dihapus.");
                    ulang = true;
                } else if (hapus.equalsIgnoreCase("N")) {
                    System.out.println("Karyawan batal dihapus.");
                    ulang = true;
                } else {
                    System.out.println("pilih Y/N");   
                }
            }
        } else {
            System.out.println("Karyawan tidak ditemukan.");
        }
        saveData();
        AppHelper.enterToContinue(input);
    }

    public void getDataKaryawanTetap() {
        AppHelper.clearScreen();
        System.out.println("\n=== Karyawan Tetap ===");
        System.out.println();
        
        String id = karyawan.generateId();
                
        System.out.print("Masukan Nama: ");
        String nama = AppHelper.inputStr(input);
        
        System.out.print("Masukan Gaji: ");
        int angka = AppHelper.inputInt(input);
        double gaji = (double) angka;
        
        karyawan.tambahKaryawanTetap(id, nama, gaji);
        saveData();
        AppHelper.enterToContinue(input);
    }

    public  void getDataKaryawanKontrak() {
        AppHelper.clearScreen();
        System.out.println("\n=== Karyawan Kontrak ===");
        System.out.println();

        String id = karyawan.generateId();

        System.out.print("Masukan Nama: ");
        String nama = AppHelper.inputStr(input);
        
        System.out.print("Masukan Upah: ");
        int angka = AppHelper.inputInt(input);
        double upahPerJam = (double) angka;
        
        System.out.print("Masukan Total Jam Kerja: ");
        int totalJamKerja = AppHelper.inputInt(input);

        karyawan.tambahKaryawanKontrak(id, nama, upahPerJam, totalJamKerja);
        saveData();
        AppHelper.enterToContinue(input);
    }

    private void initData() {
        DataService.bacaDariFile("data/karyawan.txt");

         // Inisialisasi KaryawanService dengan daftar karyawan yang dimuat
        karyawan = new KaryawanService(DataService.getDaftarKaryawan());
    }

    private void saveData() {
        DataService.simpanKeFile("data/karyawan.txt");
    }

}
