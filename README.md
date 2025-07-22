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
│   └── KaryawanService.java   (logika data ArrayList & file I/O)
│
├── controller/
│   └── MainApp.java           (menu utama dan interaksi user)
│
└── util/
    └── FileUtil.java          (helper untuk baca/tulis file)
    
```