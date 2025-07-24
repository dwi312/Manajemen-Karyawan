# Manajemen Karyawan (Java Console App)
Aplikasi berbasis **Java (console)** untuk mengelola data karyawan, dengan fitur **CRUD (Create, Read, Update, Delete)**, penyimpanan data ke file (`.txt`), dan dukungan **karyawan tetap maupun kontrak**.

## Deskripsi Aplikasi
Sistem Manajemen Karyawan adalah aplikasi berbasis Java Console yang dirancang untuk membantu perusahaan atau HR dalam mengelola data karyawan secara sederhana dan efisien.
Aplikasi ini menggunakan konsep OOP (Object-Oriented Programming), mendukung dua jenis karyawan (Tetap dan Kontrak), serta menyimpan data ke file sehingga data tetap tersimpan meskipun program ditutup.

Proyek ini cocok digunakan sebagai:
- Latihan implementasi OOP di Java (inheritance, polymorphism, abstract class).
- Contoh penerapan file I/O untuk penyimpanan data.
- Dasar untuk pengembangan aplikasi manajemen yang lebih kompleks (bisa dikembangkan ke GUI atau database).

Dengan aplikasi ini, pengguna dapat:
- 1. Menambah karyawan baru dengan data yang lengkap.
- 2. Melihat seluruh data karyawan (dikelompokkan per tipe).
- 3. Mencari karyawan berdasarkan ID atau nama.
- 4. Mengubah data karyawan (nama, gaji, upah, jam kerja).
- 5. Menghapus karyawan dengan konfirmasi.
- 6. Menyimpan dan memuat data otomatis ke file (data/karyawan.txt).
---
## Fitur Utama
- **Tambah Karyawan**  
  Mendukung dua jenis karyawan:
  - Karyawan Tetap (dengan gaji tetap).
  - Karyawan Kontrak (upah per jam × total jam kerja).
- **Lihat Semua Karyawan**.
- **Cari Karyawan** (berdasarkan ID atau Nama).
- **Ubah Data Karyawan** (nama, gaji, upah per jam, jam kerja).
- **Hapus Karyawan** (dengan konfirmasi).
- **Simpan & Muat Data** ke/dari file (`data/karyawan.txt`).

## Struktur Proyek
```bash
src/
├── data/
│   └── karyawan.txt           (file untuk menyimpan data)
│
├── model/
│   ├── Karyawan.java          (class induk)
│   ├── KaryawanTetap.java     (subclass)
│   ├── KaryawanKontrak.java   (subclass)
│
├── service/
│   └── KaryawanService.java   (menangani operasi bisnis(CRUD) terkait karyawan)
│   └── dataService.java       (menangani membaca dan menulis data karyawan dari/ke file teks.)
│
├── controller/
│   └── MainApp.java           (menangani masukan pengguna melalui Scanner, dan mengoordinasikan interaksi antara AppView (tampilan),
│                               KaryawanService (logika bisnis), dan dataService (persistensi data).)
│
└── view/
│   └── AppView.java           (tampilan menu dan penanganan input/output dasar)
│
└── helper/
│   └── AppHelper.java         (menyediakan fungsi-fungsi utilitas, seperti validasi input dan membersihkan layar.)
│
└── Main.java                  (kelas utama yang memulai aplikasi.)
    
```
### Struktur dan OOP
-  menggunakan **pemodelan OOP**:
    - `Karyawan` sebagai kelas abstrak.
    - Subclass `KaryawanTetap` dan `KaryawanKontrak` yang meng-override `hitungGaji()` dan `toString()`.
    - Pemisahan package (`model`, `service`, `view`, `controller`, `helper`).
- Pemisahan tanggung jawab:
    - `DataService` untuk file I/O.
    - `KaryawanService` untuk manipulasi data.
    - `MainApp` sebagai pengendali alur.
    - `AppView` untuk tampilan.

--- 

## Cara Menjalankan
- 1. Clone repo ini:
```bash
   git clone https://github.com/username/nama-repo.git
   cd nama-repo
```
- 2. Kompilasi:
```bash
javac -d bin src/**/*.java
```
- 3. Jalankan:
```bash
java -cp bin Main
```
---