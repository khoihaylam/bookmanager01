package ra.run;
import java.text.SimpleDateFormat;
import java.util.*;

import ra.imp.Book;

public class BookRun {
    public static List<Book> Books=new ArrayList<Book>();
    static Scanner sc=new Scanner(System.in);
    static Book temp;
    public static void main(String[] args) {
        do {
            System.out.println();
            System.out.println("************************************Menu*********************************");
            System.out.println("1. Nhập thông tin sách");
            System.out.println("2. Hiển thị thông tin sách");
            System.out.println("3. Cập nhật thông tin sách theo mã sách");
            System.out.println("4. Xóa sách theo mã sách");
            System.out.println("5. Sắp xếp sách theo giá bán tăng dần");
            System.out.println("6. Thống kê sách theo khoảng giá (a-b nhập từ bàn phím)");
            System.out.println("7. Tìm kiếm sách theo tên tác giả");
            System.out.println("8. sap xep theo ten");
            System.out.println("9.Thoat");
            System.out.println("moi ban nhap chuc nang menu:");
            int choice=sc.nextInt();
            switch (choice){
                case 1:
                    BookRun.inputData();
                    break;
                case 2:
                    BookRun.displayData();
                    break;
                case 3:
                    BookRun.update();
                    break;
                case 4:
                    BookRun.delete();
                    break;
                case 5:
                    BookRun.sortexportprice();
                    break;
                case 6:
                    BookRun.report();
                    break;
                case 7:
                    BookRun.findAuthor();
                    break;
                case 8:
                    BookRun.sortName();
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.err.println("chi nhap tu 1-9");
            }
        }while (true);
    }
    public static void inputData (){
        System.out.println("nhap so luong:");
        int n=sc.nextInt();
        for (int i=0;i<n;i++){
            Book book=new Book();
            book.inputData(sc);
            Books.add(book);
        }
    }
    public static void displayData(){
        for (int i=0;i<Books.size();i++){
            Books.get(i).displayData();
        }
    }
    public static void update(){
        System.out.println("nhap ma id muon cap nhat:");
        int id=sc.nextInt();
        int update=findbookId(id);
        if (update>=0){
            boolean exit=true;
            do {
                System.out.println();
                System.out.println("*******************UPDATE*****************");
                System.out.println("1.CAP NHAT TEN SACH");
                System.out.println("2.CAP NHAT gia importprice");
                System.out.println("3.CAP NHAT gia exportprice");
                System.out.println("4.CAP NHAT tac gia sach");
                System.out.println("5.CAP NHAT ngay sinh");
                System.out.println("6.cap nhat mo ta danh muc:");
                System.out.println("7.thoat");
                System.out.println("moi cap nhat chuc nang menu:");
                int choice=sc.nextInt();
                switch (choice){
                    case 1:
                        System.out.println("cap nhat ten sach:");
                        Books.get(update).setBookName(sc.nextLine());
                        break;
                    case 2:
                        System.out.println("cap nhat gia importprice:");
                        Books.get(update).setImportPrice(sc.nextDouble());
                        break;
                    case 3:
                        System.out.println("cap nhat gia exportprice:");
                        Books.get(update).setExportPrice(sc.nextDouble());
                        break;
                    case 4:
                        sc.nextLine();
                        System.out.println("cap nhat ten tác giả:");
                        Books.get(update).setAuthor(sc.nextLine());
                        break;
                    case 5:
                        sc.nextLine();
                        System.out.println("cap nhat nam sinh:");
                        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date birthdate=sdf.parse(sc.nextLine());
                            Books.get(update).setCreated(sdf.format(birthdate));

                        }catch (Exception ex){
                            System.err.println("k dung dinh dang nhap lai:");
                        }
                        break;
                    case 6:
                        sc.nextLine();
                        System.out.println("CAP NHAT MO TA DANH MUC:");
                        Books.get(update).setDescriptions(sc.nextLine());
                        break;
                    default:
                        exit=false;
                }
            }while (exit);
        }
    }

    public static int findbookId(int findid){
        for (int i=0;i<Books.size();i++){
            if(Books.get(i).getBookId()==findid){
                return i;
            }
        }
        return -1;
    }
    public static void delete(){
        System.out.println("nhap ma sach muon xoa");
        int findId=sc.nextInt();
        int delete=findbookId(findId);
        if(delete>=0){
            Books.remove(delete);
        }
        else{
            System.err.println("ma danh muc khong ton tai:");
        }
    }
   public static void sortexportprice(){
        Collections.sort(Books,new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return (int)(o1.getExportPrice()-o2.getExportPrice());
            }
        });
   }
   public static void findAuthor(){
        sc.nextLine();
        System.out.println("nhap tác giả muôn tìm:");
        String author=sc.nextLine();
        int check=-1;
        for(int i=0;i<Books.size();i++){
            if(Books.get(i).getAuthor().equals(author)){
                check=i;
            }
        }
        if(check==-1){
            System.out.println("k tim thay tac gia:");
        }
        else{
            for(int i=0;i<Books.size();i++){
                if(check==i){
                    Books.get(i).displayData();
                }
            }
        }

   }
   public static void report(){
        System.out.println("moi ban nhap a:");
        int a=sc.nextInt();
       System.out.println("moi ban nhap b:");
       int b=sc.nextInt();
       int count=0;
       for(int i=0;i<Books.size();i++){
           if(Books.get(i).getImportPrice()>a&&Books.get(i).getImportPrice()<b){
               count++;
               Books.get(i).displayData();
           }
       }
       if(count==0){
           System.out.println("k tim thay san pham tu a-b");
       }
       else{
           System.out.printf("có %d sach tu gia import price",count);
           System.out.println();
           }
       }
       public static void sortName(){
        Collections.sort(Books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return (int)(o1.getAuthor().compareTo(o2.getAuthor()));
            }
        });
       }
   }




