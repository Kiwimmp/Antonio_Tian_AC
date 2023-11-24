package Paging.main;

import Paging.Model.Empleado;
import Paging.Util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import java.util.Scanner;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class App {

    private static final int TOTAL_RECORDS_PAGE = 12;

    private static int getTotalRecords(Session session) {
        Query<Long> query = session.createQuery("SELECT COUNT(e) FROM Empleado e", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }

    private static void recordPageChange(Session session, int currentPage) {
        Query<Empleado> query = session.createQuery("FROM Empleado", Empleado.class)
                .setFirstResult((currentPage - 1) * TOTAL_RECORDS_PAGE)
                .setMaxResults(TOTAL_RECORDS_PAGE);

        ArrayList<Empleado> empleados = (ArrayList<Empleado>) query.list();

        System.out.println("");
        System.out.println("--> TABLE OF RECORDS OF EMPLOYEES");

        for (Empleado empleado : empleados) {
            System.out.println(empleado.toString());
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Scanner scanner = new Scanner(System.in);

            int mainPage = 1;
            int records = getTotalRecords(session);
            int totalPages = (int) Math.ceil((double) records / TOTAL_RECORDS_PAGE);

            recordPageChange(session, mainPage);

            while (true) {
                System.out.printf("<Page %d of %d>%n", mainPage, totalPages);
                System.out.println("Options --> <S> NEXT, <A> PREVIOUS, <G> GO TO X, <Q> QUIT");
                System.out.println("");
                System.out.print("> Enter the option: ");

                char option = scanner.next().toUpperCase().charAt(0);

                switch (option) {
                    case 'S' -> {
                        if (mainPage < totalPages) {
                            mainPage++;
                            recordPageChange(session, mainPage);
                        } else {
                            System.out.println("> There are no more pages left.");
                        }
                    }
                    case 'A' -> {
                        if (mainPage > 1) {
                            mainPage--;
                            recordPageChange(session, mainPage);
                        } else {
                            System.out.println("> You are on the first page...");
                        }
                    }
                    case 'G' -> {
                        System.out.print("> Enter the page number: ");
                        int goToPage = scanner.nextInt();

                        if (goToPage >= 1 && goToPage <= totalPages) {
                            mainPage = goToPage;
                            recordPageChange(session, mainPage);
                        } else {
                            System.out.println("> The number you have entered is not valid.");
                        }
                    }
                    case 'Q' -> {
                        transaction.commit();
                        return;
                    }
                    default ->
                        System.out.println("This option does not exist, please check the options again.");
                }
            }
        }
    }

    public Object getGreeting() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
