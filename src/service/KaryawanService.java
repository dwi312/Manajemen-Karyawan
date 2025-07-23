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

    public void updateKaryawan(String data, Scanner input) {
        if(cariKaryawan(data) != null) {
            Karyawan k = cariKaryawan(data);
            System.out.println();
            System.out.print("*Kosongkan bila tidak inggin diubah*\n");
            System.out.print("Masukan Nama Baru: ");
            String updateNama = input.nextLine();
            if (updateNama.isEmpty()) {
                updateNama = k.getNama();
            }

            String id = k.getId();
            k.setNama(updateNama);

            if(k.getTipe().equalsIgnoreCase("Tetap")) {
                System.out.print("Masukan Gaji Baru: ");
                String inputGaji = input.nextLine();
                double upadateGaji = ((KaryawanTetap) k).getGaji();
                
                if (inputGaji.trim().isEmpty()) {
                    try {
                        double parseGaji = Double.parseDouble(inputGaji);
                        if (parseGaji > 0) {
                            upadateGaji = parseGaji;
                        } else {
                            System.out.println("Angka yang dimasukkan tidak valid.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input gaji tidak valid! Harus berupa angka. Menggunakan gaji lama.");
                    }
                }

                ((KaryawanTetap) k).setGaji(upadateGaji);
                System.out.println("Karyawan berhasil diupdate.");
                
            } else {
                System.out.print("Masukan Upah Per Jam Baru: ");
                String inputUpah = input.nextLine();
                double upadateUpahPerJam = ((KaryawanKontrak) k).getUpahPerJam();

                if (inputUpah.trim().isEmpty()) {
                    try {
                        double parseUpah = Double.parseDouble(inputUpah);
                        if (parseUpah > 0) {
                            upadateUpahPerJam = parseUpah;
                        } else {
                            System.out.println("Angka yang dimasukkan tidak valid.");
                        }                        
                    } catch (NumberFormatException e) {
                       System.out.println("Input upah per jam tidak valid! Harus berupa angka. Menggunakan upah lama.");
                    }
                }
                
                System.out.print("Masukan Total Jam Kerja Baru: ");
                String inputTotaljam = input.nextLine();
                int updateTotalJamKerja = ((KaryawanKontrak) k).getTotalJamKerja();

                if (inputTotaljam.trim().isEmpty()) {
                    try {
                        int parseTotalJam = Integer.parseInt(inputTotaljam);
                        if (parseTotalJam > 0) {
                            updateTotalJamKerja = parseTotalJam;
                        } else {
                            System.out.println("Angka yang dimasukkan tidak valid.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input total jam kerja tidak valid! Harus berupa angka. Menggunakan jam kerja lama.");
                    }
                }
                
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
                            double UpahPerJam = Double.parseDouble(parts[3]);
                            int totalJamKerja = Integer.parseInt(parts[4]);

                            Karyawan k = new KaryawanKontrak(id, nama, UpahPerJam, totalJamKerja);
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
