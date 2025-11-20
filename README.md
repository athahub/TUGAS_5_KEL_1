# 📱 ProfileApp — Aplikasi Profil Kelompok 1

Ini adalah tugas Pengembangan Apk Mobile ITB Nobel Indonesia

## 👨‍💻 Dibuat oleh

**Kelompok 1** — Sistem dan Teknologi Informasi

* Athaillah Aufa Badila
* Arya Ardy
* Muh. Hikmawan
* Syakila Asisyaikarani

---

## 🚀 Fitur Utama

### 1. **About Screen**

Menampilkan informasi dasar berupa:

* Foto profil placeholder
* Nama, NIM, Program Studi
* Biodata singkat kelompok
* Email, nomor telepon, lokasi
* Tombol untuk **toggle Dark/Light Theme**

### 2. **Skills Screen**

Menampilkan daftar kemampuan lengkap dengan:

* Nama skill
* Ikon skill
* Progress bar tingkat kemampuan
* Persentase skill

### 3. **Contact Screen**

Berisi informasi kontak seperti:

* Email
* Nomor telepon
* Tombol **Send Message** yang memunculkan snackbar
* Tombol Social Media

### 4. **Biodata Kelompok Screen**

Berisi daftar anggota kelompok yang ditampilkan dalam bentuk kartu:

* Nama anggota
* NIM
* Latar belakang singkat

---

## 🛠 Teknologi yang Digunakan

* **Kotlin**
* **Jetpack Compose (Material 3)**
* Scaffold, LazyColumn, Card, SnackbarHost
* State management dengan `remember` dan `mutableStateOf`

---

## 📂 Struktur Utama File

```
ProfileApp.kt
│
├── ProfileApp()                  → Struktur utama aplikasi
│
├── AboutScreen()                 → Informasi profil kelompok
├── SkillsScreen()                → Daftar skill & progress
├── ContactScreen()               → Info kontak & snackbar
└── BiodataKelompokScreen()       → Daftar anggota kelompok
```

---

## 🎨 Tema

Aplikasi mendukung **Dark Mode** dan **Light Mode**, dapat diubah melalui tombol FloatingActionButton pada halaman About.

---

## 📷 Preview (Deskripsi Tampilan)

* Tampilan modern dengan Material 3
* Navigasi bawah menggunakan NavigationBar
* Setiap halaman menggunakan Card dan spacing untuk kenyamanan visual

---

## 📌 Cara Menjalankan

1. Clone repository atau salin file ke dalam project Compose Anda
2. Pastikan sudah menggunakan Compose Material3
3. Letakkan fungsi `ProfileApp()` sebagai root content:

```kotlin
setContent {
    ProfileApp()
}
```

4. Jalankan aplikasi di emulator atau perangkat fisik

---
