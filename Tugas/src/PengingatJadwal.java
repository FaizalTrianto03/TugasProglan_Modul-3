import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Jadwal {
    String nama;
    String tanggal;

    public Jadwal(String nama, String tanggal) {
        this.nama = nama;
        this.tanggal = tanggal;
    }

    @Override
    public String toString() {
        return "Nama: " + nama + ", Tanggal: " + tanggal;
    }
}

public class PengingatJadwal {
    private static final String FILENAME = "jadwal.txt";
    private static List<Jadwal> jadwalList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        bacaFileJadwal();

        while (true) {
            System.out.println("Pengingat Jadwal");
            System.out.println("1. Tambah Jadwal");
            System.out.println("2. Tampilkan Jadwal");
            System.out.println("3. Tampilkan Semua Jadwal");
            System.out.println("4. Hapus Jadwal");
            System.out.println("5. Hapus Semua Jadwal");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline dari input sebelumnya

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama jadwal: ");
                    String namaJadwal = scanner.nextLine();
                    System.out.print("Masukkan tanggal jadwal: ");
                    String tanggalJadwal = scanner.nextLine();
                    tambahJadwal(namaJadwal, tanggalJadwal);
                    break;

                case 2:
                    tampilkanJadwal();
                    break;

                case 3:
                    tampilkanSemuaJadwal();
                    break;

                case 4:
                    System.out.print("Masukkan nama jadwal yang akan dihapus: ");
                    String jadwalHapus = scanner.nextLine();
                    hapusJadwal(jadwalHapus);
                    break;

                case 5:
                    hapusSemuaJadwal();
                    break;

                case 0:
                    simpanFileJadwal();
                    System.out.println("Terima kasih!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private static void tambahJadwal(String namaJadwal, String tanggalJadwal) {
        Jadwal jadwalBaru = new Jadwal(namaJadwal, tanggalJadwal);
        jadwalList.add(jadwalBaru);
        System.out.println("Jadwal berhasil ditambahkan.");
    }

    private static void tampilkanJadwal() {
        System.out.println("Jadwal:");
        for (Jadwal jadwal : jadwalList) {
            System.out.println(jadwal);
        }
    }

    private static void tampilkanSemuaJadwal() {
        tampilkanJadwal();
    }

    private static void hapusJadwal(String namaJadwal) {
        jadwalList.removeIf(j -> j.nama.equals(namaJadwal));
        System.out.println("Jadwal berhasil dihapus.");
    }

    private static void hapusSemuaJadwal() {
        jadwalList.clear();
        System.out.println("Semua jadwal berhasil dihapus.");
    }

    private static void bacaFileJadwal() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 2) {
                    String namaJadwal = parts[0];
                    String tanggalJadwal = parts[1];
                    tambahJadwal(namaJadwal, tanggalJadwal);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void simpanFileJadwal() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Jadwal jadwal : jadwalList) {
                bw.write(jadwal.nama + ", " + jadwal.tanggal);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
