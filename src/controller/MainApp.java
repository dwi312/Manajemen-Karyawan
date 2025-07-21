package controller;

import java.util.Scanner;

public class MainApp {
    Scanner input = new Scanner(System.in);

    private App view = new App(this);

    public void run() {
        int pilihan = -1;
        boolean exit = false;

        while (!exit) {
            view.menu();
            pilihan = input.nextInt();
            exit = view.piliMenu(pilihan);

        }
        System.out.println("Program berhenti.");
        input.close();
    }

    public void tambahKaryawan() {
        System.out.println("\n=== Tambah Karyawan ===");
    }
    
    public void daftarKaryawan() {
        System.out.println("\n=== Daftar Karyawan ===");
    }


}
