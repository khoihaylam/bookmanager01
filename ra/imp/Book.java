package ra.imp;
import ra.IBook;
import ra.run.BookRun;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.List;
public class Book implements IBook{
    private int bookId;
    private String bookName;
    private double importPrice;
    private double exportPrice;
    private String author;
    private String created;
    private String descriptions;

    public Book() {
    }

    public Book(int bookId, String bookName, double importPrice, double exportPrice, String author, String created, String descriptions) {
        this.bookId = bookId;
        this.bookName=bookName;
        this.importPrice=importPrice;
        this.exportPrice=exportPrice;
        this.author=author;
        this.created=created;
        this.descriptions=descriptions;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.bookId=inputbookId();
        this.bookName=inputbookName(scanner);
        this.importPrice=inputimportPrice(scanner);
        this.exportPrice=inputexportPrice(scanner);
        this.author=inputauthor(scanner);
        this.created=inputCreated(scanner);
        this.descriptions=inputdescriptions(scanner);
    }
    public int inputbookId(){
        int max;
        if (BookRun.Books.size()==0){
            max=0;
        }
        else{
            max=BookRun.Books.get(0).getBookId();
            for (int i=0;i<BookRun.Books.size();i++){
                if (BookRun.Books.get(i).getBookId()>max){
                    max=BookRun.Books.get(i).getBookId();
                }
            }
        }
        return max+1;
    }
    public String inputbookName(Scanner scanner){
        scanner.nextLine();
        System.out.println("moi ban nhap ten sach:");
        do {
            String bookName=scanner.nextLine();
            if (bookName.length()==4){
                if (bookName.charAt(0)=='B'){
                    return bookName;
                }
                else {
                    System.err.println("ten ky tu bat dau chu B");
                }
            }
            else{
                System.err.println("ten sach phai la 4 ky tu");
            }
        }while (true);
    }
    public double inputimportPrice(Scanner scanner){
        System.out.println("moi ban nhap gia sach:");
        do {
            double importPrice=scanner.nextDouble();
            try {
                if (importPrice>0){
                    return importPrice;
                }
                else{
                    System.err.println("ten gia sach  phai lon hon 0");
                }
            }
            catch (NumberFormatException ex){
                System.err.println("khong phai la float vui long nhap lai");
            }

        }while (true);
    }
    public double inputexportPrice(Scanner scanner){
        System.out.println("moi ban nhap gia sach exportPrice :");
        do {
            double exportPrice=scanner.nextDouble();
            try {
                if (exportPrice>importPrice){
                    return exportPrice;
                }
                else{
                    System.err.println("ten gia sach  phai lon hon importPrice ");
                }
            }
            catch (NumberFormatException ex){
                System.err.println("khong phai la float vui long nhap lai");
            }

        }while (true);
    }
    public String inputauthor(Scanner scanner){
        scanner.nextLine();
        System.out.println("moi ban nhap ten tac gia :");
        do {
            String author=scanner.nextLine();
            if(author.length()>=6&&author.length()<=50){
                return author;
            }
            else{
                System.err.println("ten tac gia khong nho hon 6-50 ky tu");
            }
        }while (true);
    }
    public String inputCreated(Scanner scanner){
        System.out.println("moi ban nhap nam sinh cua ban:");
        do {
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date birthday=sdf.parse(scanner.nextLine());
                return sdf.format(birthday);
            }catch (Exception ex){
                System.err.println("khong dung dinh dang vui long nhap lai:");
            }

        }while (true);
    }
    public String inputdescriptions(Scanner scanner){
        System.out.println("moi ban nhap mo ta :");
        do {
            String descriptions=scanner.nextLine();
            if(descriptions.length()<=500){
                return descriptions;
            }
            else{
                System.err.println("ten mo ta  nho hon 500 ky tu");
            }
        }while (true);
    }
    @Override
    public void displayData() {
        System.out.printf("%5d %5s %.1f %.1f %5s %5s %6s\n",this.bookId,this.bookName,this.importPrice,this.exportPrice,this.author,this.created,this.descriptions);

    }
}
