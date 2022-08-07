import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.Scanner;

public class BookRunner {
    private static SessionFactory sessionFactory; //используется для операций с БД
    private static BookRunner bookRunner;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory(); // инициализация объекта sessionFactory
        bookRunner = new BookRunner();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. View all notes");
            System.out.println("2. Add new note");
            System.out.println("3. Edit note");
            System.out.println("4. Delete note");
            System.out.println("5. Exit");
            System.out.println("Your choice:");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    List<Book> books = bookRunner.listBook(); // список всех записей в БД
                    for (Book book : books) {  //вывод всех записей
                        System.out.println(book);
                    }
                    System.out.println("===================================");
                }
                case 2 -> {

                    System.out.println("Enter bookId: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter book name: ");
                    String bookName = scanner.nextLine();

                    System.out.println("Enter book author: ");
                    String bookAuthor = scanner.nextLine();

                    System.out.println("Enter book year: ");
                    String bookYear = scanner.nextLine();

                    System.out.println("Enter book genre: ");
                    String bookGenre = scanner.nextLine();

                    System.out.println("Enter book price: ");
                    String bookPrice = scanner.nextLine();

                    System.out.println("Enter book rate: ");
                    String bookRate = scanner.nextLine();

                    bookRunner.addBook(bookId, bookName, bookAuthor, bookYear, bookGenre, bookPrice, bookRate); // добавление новой записи в список
                }
                case 3 -> {
                    System.out.println("Enter bookId: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter book year: ");
                    String bookYear = scanner.nextLine();

                    bookRunner.updateBook(bookId, bookYear);  // обновление данных о записи в БД
                }
                case 4 -> {
                    System.out.print("Enter book Id to delete: ");
                    int bookId = scanner.nextInt();
                    bookRunner.removeBook(bookId); // удаление записи
                }
                case 5 -> {
                    System.out.println("Program finished");
                    return;
                }
            }
        }
    }

    public void addBook(int bookId, String name, String author, String year, String genre, String price, String rate) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Book book = new Book(bookId, name, author, year, genre, price, rate);
        session.save(book);
        transaction.commit();
        session.close();
    }

    public List<Book> listBook() {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        List<Book> books = session.createQuery("FROM Book").list();
        transaction.commit();
        session.close();
        return books;
    }

    public void updateBook(int bookId, String year) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Book book = (Book) session.get(Book.class, bookId);
        book.setYear(year);
        session.update(book);
        transaction.commit();
        session.close();
    }

    public void removeBook(int bookId) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null; // для связи с БД

        transaction = session.beginTransaction(); // соединение открывается
        Book book = (Book) session.get(Book.class, bookId); // сама операция
        session.delete(book);
        transaction.commit(); // конец транзакции
        session.close();
    }

}
