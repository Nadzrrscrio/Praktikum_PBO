import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String nama;
    String fakultas;
    String programStudi;

    Student(String nama, String fakultas, String programStudi) {
        this.nama = nama;
        this.fakultas = fakultas;
        this.programStudi = programStudi;
    }

    void logOut() {
        System.out.println("Program keluar...");
    }

    void displayBooks() {
        System.out.println("Tampilan Buku");
        System.out.println("===========================================================================");
        System.out.println("|| No. || Judul Buku            || Penulis      || Genre       || Stok    |");
        System.out.println("|| 1   || One Piece             || Ichiro oda   || Action      || 4       |");
        System.out.println("|| 2   || Fantastic Beast       || JK Rouling   || Fiksi       || 5       |");
    }
}

class Admin {
    String adminUsername = "admin";
    String adminPassword = "nyalainSendiri";
    ArrayList<Student> daftarSiswa = new ArrayList<>();

    void displayStudents() {
        System.out.println("\nList Registrasi Siswa:");
        for (Student student : daftarSiswa) {
            System.out.println("Nama: " + student.nama + "\nFakultas: " + student.fakultas + "\nNIM: " + student.programStudi);
        }
    }

    void addStudent(Scanner nadzar) {
        System.out.print("Masukkan Nama Siswa: ");
        String name = nadzar.nextLine();

        String nim;
        do {
            System.out.print("Masukkan NIM Siswa (15 digit): ");
            nim = nadzar.nextLine();
            if (nim.length() != 15) {
                System.out.println("NIM Harus 15 digit!");
            }
        } while (nim.length() != 15);

        System.out.print("Masukkan Fakultas: ");
        String faculty = nadzar.nextLine();

        System.out.print("Masukkan Program Studi: ");
        String program = nadzar.nextLine();

        Student secario = new Student(name, faculty, program);
        daftarSiswa.add(secario);
        System.out.println("Registrasi Siswa Berhasil.");
    }
}

public class Main {
    static ArrayList<String> listBuku = new ArrayList<>();
    static ArrayList<Student> userSiswa = new ArrayList<>();
    static Scanner menyala = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }

    void menu() {
        int choice;
        do {
            System.out.println("\n===== Sistem Perpustakaan =====");
            System.out.println("1. Masuk Sebagai Siswa");
            System.out.println("2. Masuk Sebagai Admin");
            System.out.println("3. Keluar");
            System.out.print("Pilih Menu (1-3): ");
            choice = menyala.nextInt();
            menyala.nextLine();

            switch (choice) {
                case 1:
                    menuSiswa();
                    break;
                case 2:
                    menuAdmin();
                    break;
                case 3:
                    System.out.println("Terima Kasih.");
                    break;
                default:
                    System.out.println("Pilihan Kacau Abangkuhhh!");
            }
        } while (choice != 3);
    }

    void menuSiswa() {
        int option;
        Student student = new Student("", "", "");
        do {
            System.out.println("\n==== Student Menu ====");
            System.out.println("1. Tampilkan Buku Pinjaman");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Keluar");
            System.out.print("Choose option (1-3): ");
            option = menyala.nextInt();
            menyala.nextLine();

            switch (option) {
                case 1:
                    student.displayBooks();
                    break;
                case 2:
                    break;
                case 3:
                    student.logOut();
                    userSiswa.clear();
                    break;
                default:
                    System.out.println("Pilihan Kacau Abangkuhhh!");
            }
        } while (option != 3);
    }

    void menuAdmin() {
        Admin admin = new Admin();
        String username, password;

        do {
            System.out.print("\nMasukkan Username (admin): ");
            username = menyala.nextLine();

            System.out.print("Masukkan Password : ");
            password = menyala.nextLine();

            if (!username.equals(admin.adminUsername) || !password.equals(admin.adminPassword)) {
                System.out.println("Tidak Valid!!!!");
            }
        } while (!username.equals(admin.adminUsername) || !password.equals(admin.adminPassword));

        int option;
        do {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. Daftarkan Siswa");
            System.out.println("2. Tampilkan Registrasi Siswa");
            System.out.println("3. Keluar");
            System.out.print("Pilih Menu (1-3): ");
            option = menyala.nextInt();
            menyala.nextLine();

            switch (option) {
                case 1:
                    admin.addStudent(menyala);
                    break;
                case 2:
                    admin.displayStudents();
                    break;
                case 3:
                    System.out.println("Masuk ke Akun Admin.");
                    break;
                default:
                    System.out.println("Pilihan Kacau Abangkuhhh!");
            }
        } while (option != 3);
    }
}