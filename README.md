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
│   └── KaryawanService.java   (logika data & file I/O)
│
├── controller/
│   └── AppOutput.java         (output mencetak ke console)
│   └── MainApp.java           (controller utama dan penghubung ke service)
│
└── helper/
    └── AppHelper.java         (helper untuk validasi input)
    
```