import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

class Book {
    private String bookId;
    private String title;
    private String author;
    private String category;
    private int stock;
    private int borrowedCount = 0;
    private LocalDate borrowedDate;
    private int durationDays;

    public Book(String bookId, String title, String author, String category, int stock) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }


    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }

    // Getters and Setters
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public void setBorrowedCount(int borrowedCount) {
        this.borrowedCount = borrowedCount;
    }

    public void borrowBook(int durationDays) {
        if (stock > 0) {
            stock--;
            borrowedCount++;
            setBorrowedDate(LocalDate.now());
            setDurationDays(durationDays);
            System.out.println("Peminjaman buku sukses selama " + durationDays + " hari.");
        } else {
            System.out.println("Tidak ada lagi salinan yang tersedia untuk buku ini.");
        }
    }

    public void returnBook() {
        if (borrowedCount > 0) {
            stock++;
            borrowedCount--;
            borrowedDate = null;
            durationDays = 0;
            System.out.println("Pengembalian buku sukses.");
        } else {
            System.out.println("Tidak ada salinan yang dipinjam untuk buku ini.");
        }
    }
}

class HistoryBook extends Book {
    public HistoryBook(String bookId, String title, String author, String category, int stock) {
        super(bookId, title, author, category, stock);
    }
}

class TextBook extends Book {
    public TextBook(String bookId, String title, String author, String category, int stock) {
        super(bookId, title, author, category, stock);
    }
}

class StoryBook extends Book {
    public StoryBook(String bookId, String title, String author, String category, int stock) {
        super(bookId, title, author, category, stock);
    }
}

// User class
class User {
    public String name;
    protected ArrayList<Book> bookList;

    public User(String name) {
        this.name = name;
        bookList = new ArrayList<>();
    }

    public User(String name, ArrayList<Book> bookList) {
        this.name = name;
        this.bookList = bookList;
    }

    public void displayBooks() {
        System.out.println("\n===== Available Books =====");
        System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-3s ||", "Book ID", "Title", "Author", "Category", "Stock");
        System.out.println("\n================================================================================");
        for (Book book : bookList) {
            System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-3s ||", book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock());
            System.out.println();
        }
    }

    public void addBook(String bookId, String title, String author, String category, int stock, String bookType) {
        Book newBook;
        switch (bookType) {
            case "History":
                newBook = new HistoryBook(bookId, title, author, category, stock);
                break;
            case "Text":
                newBook = new TextBook(bookId, title, author, category, stock);
                break;
            case "Story":
                newBook = new StoryBook(bookId, title, author, category, stock);
                break;
            default:
                System.out.println("Invalid book type.");
                return;
        }
        bookList.add(newBook);
        System.out.println("Book added successfully.");
    }
}

class Admin extends User {
    public Admin(String name) {
        super(name);
    }

    public void addStudent(ArrayList<Student> userStudent) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan Nama Siswa: ");
        String name = scanner.nextLine();
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        if (nim.length() != 15) {
            System.out.println("Nim salah!!!  Nim harus 15 angka.");
            addStudent(userStudent);
            return;
        }
        System.out.print("Masukkan Fakultas: ");
        String faculty = scanner.nextLine();
        System.out.print("Masukkan Program Studi: ");
        String program = scanner.nextLine();

        Student newStudent = new Student(name, nim, faculty, program);
        userStudent.add(newStudent);
        System.out.println("Penambahan Siswa suksess.");
    }

    public void inputBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan ID Buku: ");
        String bookId = scanner.nextLine();
        System.out.print("Masukkan Judul : ");
        String title = scanner.nextLine();
        System.out.print("Masukkan Penulis: ");
        String author = scanner.nextLine();
        System.out.print("Masukkan Kategori (History, Text, or Story): ");
        String category = scanner.nextLine();
        System.out.print("Masukkan Stok: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        String bookType = category;

        super.addBook(bookId, title, author, category, stock, bookType);
    }
}

class Student extends User {
    public String nim;
    public String faculty;
    public String program;

    public Student(String name, String nim, String faculty, String program) {
        super(name);
        this.nim = nim;
        this.faculty = faculty;
        this.program = program;
    }

    public void borrowBook(ArrayList<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan ID Buku untuk meminjam: ");
        String bookId = scanner.nextLine();
        System.out.print("Masukkan Durasi Peminjaman Buku (Hari): ");
        int durationDays = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Book book : bookList) {
            if (book.getBookId().equals(bookId)) {
                found = true;
                book.borrowBook(durationDays);
                break;
            }
        }
        if (!found) {
            System.out.println("Buku dengan Id tersebut Tidak Ditemukan.");
        }
    }

    public void returnBook(ArrayList<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("MAsukkan Id Buku untuk mengembalikan: ");
        String bookId = scanner.nextLine();

        boolean found = false;
        for (Book book : bookList) {
            if (book.getBookId().equals(bookId)) {
                found = true;
                book.returnBook();
                break;
            }
        }
        if (!found) {
            System.out.println("Buku dengan Id tersebuk tidak dipinjam olehmu.");
        }
    }

    public void showBorrowedBook(ArrayList<Book> bookList) {
        System.out.println("\n===== Buku Pinjaman =====");
        boolean hasBorrowedBooks = false;
        for (Book book : bookList) {
            if (book.getBorrowedCount() > 0) {
                hasBorrowedBooks = true;
                System.out.printf("|| %-15s || %-25s || %-25s || %-20s || %-10s || %-10s ||\n", book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getBorrowedDate(), "Due in " + book.getDurationDays() + " days");
            }
        }
        if (!hasBorrowedBooks) {
            System.out.println("Kamu belum meminjam buku apapun!!.");
        }
    }
}

public class Tugas {
    private ArrayList<Student> userStudent;
    private Scanner scanner;
    static Scanner inputuser = new Scanner(System.in);

    static String adminusername = "admin", adminpassword = "buka";
    private Admin admin;
    private User user;

    public Tugas() {
        userStudent = new ArrayList<>();
        scanner = new Scanner(System.in);
        admin = new Admin("Admin");
        user = new User("User", admin.bookList);


    }

    public void menu() {
        System.out.println("\n===== Sistem perpustakaan =====");
        System.out.println("1. Masuk sebagai Siswa");
        System.out.println("2. Masuk sebagai Admin");
        System.out.print("Pilihanmu: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                inputNim();
                break;
            case 2:
                loginadmin();
                menuAdmin();
                break;
            default:
                System.out.println("Pilihan salah, Ulangi!!!.");
                menu();
        }
    }

    private void inputNim() {
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        if (checkNim(nim)) {
            Student currentStudent = getUserByNim(nim);
            menuStudent(currentStudent);
        } else {
            System.out.println("Nim Salah, Masukkan ulang.");
            inputNim();
        }
    }

    private Student getUserByNim(String nim) {
        for (Student student : userStudent) {
            if (student.nim.equals(nim)) {
                return student;
            }
        }
        return null;
    }

    private boolean checkNim(String nim) {
        // Check if nim exists in userStudent
        for (Student student : userStudent) {
            if (student.nim.equals(nim)) {
                return true;
            }
        }
        return false;
    }



    static void loginadmin() {
        int adminloop;
        do {
            System.out.println("\n==== Login ====");
            System.out.print("Masukkan Username: \n");
            String username = inputuser.nextLine();

            System.out.print("Masukkan Password: \n");
            String password = inputuser.nextLine();

            if (username.equals(adminusername) && password.equals(adminpassword)) {
                System.out.println("==== Login Sukses ====\n");
                adminloop = 1;
            } else {
                System.out.println("==== Admin tidak ditemukan ====");
                adminloop = 0;
            }
        } while (adminloop == 0);
    }

    private void menuAdmin() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n===== Menu Admin =====");
            System.out.println("1. Masukkan Buku");
            System.out.println("2. Tampilkan Buku");
            System.out.println("3. Tambahkan Siswa");
            System.out.println("4. Tampilkan Registrasi Siswa");
            System.out.println("5. Keluar");
            System.out.print("Pilihanmu??: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    admin.inputBook();
                    break;
                case 2:
                    admin.displayBooks();
                    break;
                case 3:
                    admin.addStudent(userStudent);
                    break;
                case 4:
                    displayStudent();
                    break;
                case 5:
                    menu();
                    break;
                default:
                    System.out.println("Pilihan Salah, Ulangi.");
            }
        }
    }

    private void menuStudent(Student currentStudent) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n===== Dashboard Siswa =====");
            System.out.println("1. Tampilkan Buku yang tersedia");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Buku pinjaman");
            System.out.println("4. Kembalikan Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilihanmu?: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    user.displayBooks();
                    break;
                case 2:
                    currentStudent.borrowBook(user.bookList);
                    break;
                case 3:
                    currentStudent.showBorrowedBook(user.bookList);
                    break;
                case 4:
                    currentStudent.returnBook(user.bookList);
                    break;
                case 5:
                    menu();
                    break;
                default:
                    System.out.println("Pilihan salah, Ulangi!!.");
            }
        }
    }

    private void displayStudent() {
        System.out.println("\n===== Registrasi Siswa =====");
        for (Student student : userStudent) {
            System.out.println("Nama: " + student.name);
            System.out.println("NIM: " + student.nim);
            System.out.println("Fakultas: " + student.faculty);
            System.out.println("Program Studi: " + student.program);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Tugas app = new Tugas();
        app.menu();
    }
}