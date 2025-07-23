# Manajemen Karyawan

## Struktur
```bash
src/
├── model/
│   ├── Karyawan.java          (class induk)
│   ├── KaryawanTetap.java     (subclass)
│   ├── KaryawanKontrak.java   (subclass)
│
├── service/
│   └── KaryawanService.java   (menangani operasi bisnis(CRUD) terkait karyawan)
│
├── controller/
│   └── AppOutput.java         (tampilan menu dan penanganan input/output dasar)
│   └── MainApp.java           (logika aplikasi dan interaksi dengan Service)
│
└── helper/
│
│   └── AppHelper.java         (menyediakan fungsi-fungsi utilitas, seperti validasi input dan membersihkan layar.)
│
└── Main.java                  (kelas utama yang memulai aplikasi.)
    
```