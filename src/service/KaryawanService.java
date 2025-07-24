package service;

import java.util.ArrayList;
import java.util.Scanner;
import model.Karyawan;
import model.KaryawanKontrak;
import model.KaryawanTetap;

public class KaryawanService {
    // KaryawanService akan menerima daftar karyawan dari dataService
    // dan beroperasi pada daftar yang sama.
    private ArrayList<Karyawan> daftarKaryawan;

    public KaryawanService(ArrayList<Karyawan> daftarKaryawan) {
        this.daftarKaryawan = daftarKaryawan;
    }

    public void tambahKaryawanTetap(String id, String nama, double gaji) {
        Karyawan k = new KaryawanTetap(id, nama, gaji);
        daftarKaryawan.add(k);
        System.out.println("Karyawan Tetap berhasil ditambah.");
        
    }

    public void tambahKaryawanKontrak(String id, String nama, double upahPerJam, int totalJamKerja) {
       Karyawan k = new KaryawanKontrak(id, nama, upahPerJam, totalJamKerja);
        daftarKaryawan.add(k);
        System.out.println("Karyawan Kontrak berhasil ditambah.");
    }

    public void tampilkanSemua() {
        System.out.println("Karyawan Tetap: ");
        for (Karyawan k : daftarKaryawan) {
            if (k.getTipe().equals("Tetap")) {
                System.out.println(k);
            }
        }
        System.out.println();
        System.out.println("--------------------------------");
        
        System.out.println();
        
        System.out.println("Karyawan Kontrak: ");
        for (Karyawan k : daftarKaryawan) {
            if (k.getTipe().equals("Kontrak")) {
                System.out.println(k);
            }
        }
        System.out.println();
        System.out.println("--------------------------------");


    }

    public Karyawan cariKaryawan(String data) {
        for (Karyawan k : daftarKaryawan) {
             if (k != null && (k.getNama().equalsIgnoreCase(data) || k.getId().equalsIgnoreCase(data))) {
                return k;
            }
        }
        return null;
    }

    public void updateKaryawan(String data, Scanner input) {
        Karyawan k = cariKaryawan(data);

        if(k != null) {
            System.out.println();
            System.out.print("*Kosongkan bila tidak inggin diubah*\n");
            System.out.print("Nama Saat ini: " + k.getNama() + "\n");
            System.out.print("Masukan Nama Baru: ");
            String updateNama = input.nextLine();
            if (!updateNama.trim().isEmpty()) {
                k.setNama(updateNama);
            }

            String id = k.getId();

            if(k.getTipe().equalsIgnoreCase("Tetap")) {
                KaryawanTetap kt = (KaryawanTetap) k;

                System.out.print("Gaji Saat ini: " + kt.getGaji() + "\n");
                System.out.print("Masukan Gaji Baru: ");
                String inputGaji = input.nextLine();
                // double upadateGaji = ((KaryawanTetap) k).getGaji();
                
                if (!inputGaji.trim().isEmpty()) {
                    try {
                        double parseGaji = Double.parseDouble(inputGaji);
                        if (parseGaji > 0) {
                            kt.setGaji(parseGaji);
                            System.out.println("Gaji telah diperbarui.");
                        } else {
                            System.out.println("Angka yang dimasukkan tidak valid.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input gaji tidak valid! Harus berupa angka. Menggunakan gaji lama.");
                    }
                } else {
                    System.out.println("Gaji batal diubah.");
                }
                
            } else {
                KaryawanKontrak kk = (KaryawanKontrak) k;

                System.out.print("Upah Per Jam Saat ini: " + kk.getUpahPerJam() + "\n");
                System.out.print("Masukan Upah Per Jam Baru: ");
                String inputUpah = input.nextLine();
                // double upadateUpahPerJam = ((KaryawanKontrak) k).getUpahPerJam();

                if (!inputUpah.trim().isEmpty()) {
                    try {
                        double parseUpah = Double.parseDouble(inputUpah);
                        if (parseUpah > 0) {
                            kk.setUpahPerJam(parseUpah);
                            System.out.println("Upah telah diperbarui.");
                        } else {
                            System.out.println("Angka yang dimasukkan tidak valid.");
                        }                        
                    } catch (NumberFormatException e) {
                       System.out.println("Input upah per jam tidak valid! Harus berupa angka. Menggunakan upah lama.");
                    }
                } else {
                    System.out.println("Upah batal diubah.");
                }
                
                System.out.print("Total Jam Kerja Saat ini: " + kk.getTotalJamKerja() + "\n");
                System.out.print("Masukan Total Jam Kerja Baru: ");
                String inputTotaljam = input.nextLine();
                // int updateTotalJamKerja = ((KaryawanKontrak) k).getTotalJamKerja();

                if (!inputTotaljam.trim().isEmpty()) {
                    try {
                        int parseTotalJam = Integer.parseInt(inputTotaljam);
                        if (parseTotalJam > 0) {
                            kk.setTotalJamKerja(parseTotalJam);
                            System.out.println("Total jam kerja telah diperbarui.");
                        } else {
                            System.out.println("Angka yang dimasukkan tidak valid.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input total jam kerja tidak valid! Harus berupa angka. Menggunakan jam kerja lama.");
                    }
                } else {
                    System.out.println("Total jam kerja batal diubah.");
                }                 
                
                System.out.println("Karyawan berhasil diupdate.");
            }
        } else {
            System.out.println("Karyawan tidak ditemukan.");
        }
        
    }

    public void hapusKaryawan(String data) {
        Karyawan k = cariKaryawan(data);

        if(k == null) {
            return;
        }
            
        daftarKaryawan.remove(k);
        
    }

    public ArrayList<Karyawan> getDaftarKaryawan() {
        return daftarKaryawan;
    }

    public  String generateId() {
        return "K" + String.format("%03d", daftarKaryawan.size() + 1);
    }





    

}
