import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Main {
    public static void main(String[]args){
        Scanner nadzar = new Scanner(System.in);

        System.out.println("Nama :");
        String nama = nadzar.nextLine();

        System.out.println("Kelamin P/L :");
        String kelaminSatu = nadzar.nextLine();
        String kelamin = kelaminSatu.equalsIgnoreCase("L") ? "laki-laki" : "perempuan";

        System.out.println("Tanggal Lahir (tahun-bulan-tanggal) :");
        String tanggalLahirStr = nadzar.nextLine();
        LocalDate tanggalLahir = LocalDate.parse(tanggalLahirStr);

        LocalDate tanggalSekarang = LocalDate.now();
        int usia = Period.between(tanggalLahir,tanggalSekarang).getYears();

        System.out.println("Nama :" + nama);
        System.out.println("Kelamin :" + kelamin);
        System.out.println("Usia :" + usia +"tahun");

        nadzar.close();

    }
}
