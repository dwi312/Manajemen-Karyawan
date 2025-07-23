package controller;

import helper.AppHelper;
import java.util.Scanner;
import service.KaryawanService;

public class MainApp {
    Scanner input = new Scanner(System.in);

    private AppOutput view = new AppOutput(this);
    private KaryawanService karyawan = new KaryawanService();

    public void run() {
        initData();
        int pilihan = -1;
        boolean exit = false;

        while (!exit) {
            view.menu();
            pilihan = AppHelper.inputInt(input);
            exit = view.piliMenu(pilihan);
        }
        saveData();
        System.out.println("Program berhenti.");
        input.close();
    }

    public void tambahKaryawan() {
        int num = AppHelper.inputInt(input);

        switch (num) {
            case 1:
                getDataKaryawanTetap();
                AppHelper.enterToContinue(input);
                saveData();
                break;
            case 2:
                getDataKaryawanKontrak();
                AppHelper.enterToContinue(input);
                saveData();
                break;
            case 3:
                karyawan.tampilkanSemua();
                AppHelper.enterToContinue(input);
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }
    }
    
    public void daftarKaryawan() {
        AppHelper.clearScreen();
        System.out.println("\n=== Daftar Karyawan ===");
        System.out.println();
        karyawan.tampilkanSemua();
        AppHelper.enterToContinue(input);
    }
    
    public void cariKaryawan() {
        AppHelper.clearScreen();
        System.out.println("\n=== Cari Karyawan ===");
        System.out.println();
        System.out.print("Masukan Nama Karyawan: ");
        String nama = AppHelper.inputStr(input);

        if (karyawan.cariKaryawan(nama) != null) {
            System.out.println("Data Karyawan: ");
            System.out.println(karyawan.cariKaryawan(nama));
        }

        AppHelper.enterToContinue(input);
    }
    
    public void ubahDataKaryawan() {
        AppHelper.clearScreen();
        System.out.println("\n=== Rubah Data Karyawan ===");
        System.out.println();
        System.out.print("Masukan ID/Nama Karyawan: ");
        String keyword = AppHelper.inputStr(input);

        karyawan.updateKaryawan(keyword);

        AppHelper.enterToContinue(input);
    }
    
    public void hapusKaryawan() {
        AppHelper.clearScreen();
        System.out.println("\n=== Hapus Karyawan ===");
        System.out.println();

        System.out.print("Masukan ID/Nama Karyawan: ");
        String keyword = AppHelper.inputStr(input);

        karyawan.hapusKaryawan(keyword);

        AppHelper.enterToContinue(input);
    }

    private void getDataKaryawanTetap() {
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

    }

    private void getDataKaryawanKontrak() {
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
    }

    private void initData() {
        karyawan.bacaDariFile("data/karyawan.txt");
    }

    private void saveData() {
        karyawan.simpanKeFile("data/karyawan.txt");
    }


    



}
