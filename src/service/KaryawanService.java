package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Karyawan;
import model.KaryawanKontrak;
import model.KaryawanTetap;

public class KaryawanService {
    private ArrayList<Karyawan> daftarKaryawan = new ArrayList<>();

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
        
        System.out.println("Karyawan tidak ditemukan.");
        return null;
    }

    public void updateKaryawan(String data) {
        if(cariKaryawan(data) != null) {
            Karyawan k = cariKaryawan(data);
            System.out.println();
            System.out.print("*Kosongkan bila tidak inggin diubah*\n");
            System.out.print("Masukan Nama Baru: ");
            String updateNama = new Scanner(System.in).nextLine();
            if (updateNama.isEmpty()) {
                updateNama = k.getNama();
            }

            String id = k.getId();

            if(k.getTipe().equalsIgnoreCase("Tetap")) {
                System.out.print("Masukan Gaji Baru: ");
                String input = new Scanner(System.in).nextLine();
                double upadateGaji;
                
                if (input.trim().isEmpty()) {
                    upadateGaji = ((KaryawanTetap) k).getGaji();
                } else {
                    upadateGaji = Double.parseDouble(input);
                    if (upadateGaji <= 0) {
                        System.out.println("Angka yang dimasukkan tidak valid.");
                        upadateGaji = ((KaryawanTetap) k).getGaji();
                        return;
                    }
                }

                k.setNama(updateNama);
                ((KaryawanTetap) k).setGaji(upadateGaji);

                System.out.println("Karyawan berhasil diupdate.");
                
            } else {
                System.out.print("Masukan Upah Per Jam Baru: ");
                String inputUpah = new Scanner(System.in).nextLine();
                double upadateUpahPerJam;

                if (inputUpah.trim().isEmpty()) {
                    upadateUpahPerJam = ((KaryawanKontrak) k).getUpahPerJam();
                } else {
                    upadateUpahPerJam = Double.parseDouble(inputUpah);
                    if (upadateUpahPerJam <= 0) {
                        System.out.println("Angka yang dimasukkan tidak valid.");
                        upadateUpahPerJam = ((KaryawanKontrak) k).getUpahPerJam();
                        return;
                    }
                }
                
                System.out.print("Masukan Total Jam Kerja Baru: ");
                String inputTotaljam = new Scanner(System.in).nextLine();
                int updateTotalJamKerja;

                if (inputTotaljam.trim().isEmpty()) {
                    updateTotalJamKerja = ((KaryawanKontrak) k).getTotalJamKerja();
                } else {
                    updateTotalJamKerja = Integer.parseInt(inputTotaljam);
                    if ( updateTotalJamKerja <= 0) {
                        System.out.println("Angka yang dimasukkan tidak valid");
                        updateTotalJamKerja = ((KaryawanKontrak) k).getTotalJamKerja();
                        return;
                    }
                }
                
                k.setNama(updateNama);
                ((KaryawanKontrak) k).setUpahPerJam(upadateUpahPerJam);
                ((KaryawanKontrak) k).setTotalJamKerja(updateTotalJamKerja);
                
                System.out.println("Karyawan berhasil diupdate.");
            }
        } else {
            System.out.println("Karyawan tidak ditemukan.");
        }
        
    }

    public void hapusKaryawan(String data) {
        if(cariKaryawan(data) != null) {
            daftarKaryawan.remove(cariKaryawan(data));
            System.out.println("Karyawan berhasil dihapus.");
        }
    }

    public void bacaDariFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int nextIndex = daftarKaryawan.size();

            while ((line = reader.readLine()) != null && nextIndex != -1 ) {
                String[] parts = line.split("\\|");
                if (parts.length != 0) {
                    String id = parts[0];
                    String nama = parts[1];
                    String tipe = parts[2];
                    
                    if (tipe.equals("Tetap")) {
                        double gaji = Double.parseDouble(parts[3]);
                        
                        Karyawan k = new KaryawanTetap(id, nama, gaji);
                        daftarKaryawan.add(k);
                        
                    } else if (tipe.equals("Kontrak")) {
                        double gaji = Double.parseDouble(parts[3]);
                        int totalJamKerja = Integer.parseInt(parts[4]);

                        Karyawan k = new KaryawanKontrak(id, nama, gaji, totalJamKerja);
                        daftarKaryawan.add(k);
                    }
                    nextIndex = daftarKaryawan.size();
                }
            }
            System.out.println("Data Karyawan selesai dimuat.");
        } catch (IOException e) {
            System.out.println("Gagal memuat data: " + e.getMessage());
        }
        
    }

   
    public void simpanKeFile(String filePath) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Karyawan k : daftarKaryawan) {
                if(k != null) {
                    if(k.getTipe().equals("Tetap")) {
                        writer.write(k.getId() + "|" +
                                 k.getNama() + "|" +
                                 k.getTipe() + "|" +
                                 ((KaryawanTetap) k).getGaji() + "|" +
                                 k.hitungGaji());
                    writer.newLine();

                    }else if (k.getTipe().equals("Kontrak")) {
                        writer.write(k.getId() + "|" +
                                 k.getNama() + "|" +
                                 k.getTipe() + "|" +
                                 ((KaryawanKontrak) k).getUpahPerJam() + "|" +
                                 ((KaryawanKontrak) k).getTotalJamKerja() + "|" +
                                 ((KaryawanKontrak) k).hitungGaji());
                    writer.newLine();

                    }
                }
            }
            System.out.println("Data Karyawan berhasil disimpan ke file.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data karyawan: " + e.getMessage());
        }

    }

   
    public ArrayList<Karyawan> getDaftarKaryawan() {
        return daftarKaryawan;
    }

    public  String generateId() {
        return "K" + String.format("%03d", daftarKaryawan.size() + 1);
    }





    

}
