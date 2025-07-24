package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Karyawan;
import model.KaryawanKontrak;
import model.KaryawanTetap;

public class DataService {
    private static ArrayList<Karyawan> daftarKaryawan = new ArrayList<>();

    /**
     * Metode untuk membaca data karyawan dari file.
     * Data diharapkan dalam format:
     * Untuk Karyawan Tetap: ID|Nama|Tetap|Gaji
     * Untuk Karyawan Kontrak: ID|Nama|Kontrak|UpahPerJam|TotalJamKerja
     *
     * @param filePath Lokasi file yang akan dibaca.
     */
    public static void bacaDariFile(String filePath) {
        daftarKaryawan.clear(); 
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 0) {
                    String id = parts[0];
                    String nama = parts[1];
                    String tipe = parts[2];
                    
                    if (tipe.equals("Tetap")) {
                        if (parts.length >= 4) {
                            double gaji = Double.parseDouble(parts[3]);
                            Karyawan k = new KaryawanTetap(id, nama, gaji);
                            daftarKaryawan.add(k);
                        } else {
                            System.out.println("Peringatan: Baris tidak valid untuk Karyawan Tetap, dilewati: " + line);
                        }
                    } else if (tipe.equals("Kontrak")) {
                        if (parts.length >= 5) {
                            double upahPerJam = Double.parseDouble(parts[3]);
                            int totalJamKerja = Integer.parseInt(parts[4]);

                            Karyawan k = new KaryawanKontrak(id, nama, upahPerJam, totalJamKerja);
                            daftarKaryawan.add(k);                       
                        } else {
                            System.out.println("Peringatan: Baris tidak valid untuk Karyawan Kontrak, dilewati: " + line);
                        }
                    } else {
                        System.out.println("Peringatan: Tipe karyawan tidak valid, dilewati: " + line);
                    }
                } else {
                    System.out.println("Peringatan: Baris tidak valid, dilewati: " + line);
                }
            }
            System.out.println("Data Karyawan selesai dimuat.");
        } catch (IOException e) {
            System.out.println("Gagal memuat data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Gagal mengurai angka dari file: " + e.getMessage() + ". Periksa format data file.");
        }
    }

   /**
     * Metode untuk menyimpan data karyawan ke file.
     * Nilai hitungGaji() tidak disimpan karena merupakan hasil perhitungan.
     *
     * @param filePath Lokasi file tempat data akan disimpan.
     */
    public static void simpanKeFile(String filePath) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Karyawan k : daftarKaryawan) {
                if(k != null) {
                    if(k.getTipe().equals("Tetap")) {
                        writer.write(k.getId() + "|" +
                                 k.getNama() + "|" +
                                 k.getTipe() + "|" +
                                 ((KaryawanTetap) k).getGaji());
                    writer.newLine();

                    }else if (k.getTipe().equals("Kontrak")) {
                        writer.write(k.getId() + "|" +
                                 k.getNama() + "|" +
                                 k.getTipe() + "|" +
                                 ((KaryawanKontrak) k).getUpahPerJam() + "|" +
                                 ((KaryawanKontrak) k).getTotalJamKerja());
                    writer.newLine();

                    }
                }
            }
            System.out.println("Data Karyawan berhasil disimpan ke file.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data karyawan: " + e.getMessage());
        }

    }

    /**
     * Mengembalikan daftar karyawan yang dikelola oleh dataService.
     * @return ArrayList<Karyawan> daftar karyawan
     */
    public static ArrayList<Karyawan> getDaftarKaryawan() {
        return daftarKaryawan;
    }

   

}
