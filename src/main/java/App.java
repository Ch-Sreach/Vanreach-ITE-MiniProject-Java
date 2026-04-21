import model.Movie;
import service.MovieService;
import service.MovieServiceImpl;
import utils.TableRenderer;

import java.util.List;
import java.util.Scanner;

public class App {

    private static final MovieService service = new MovieServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);

    private static int pageSize = 5;
    private static int pageNumber = 1;
    private static int skip = (pageNumber - 1) * pageSize;
    private static int totalPages = (service.getAll().size() + pageSize -1) / pageSize;

    private static void updatePageNumber(int pageNum) {
        if (pageNum < 1) {
            pageNumber = 1;
            return;
        }
        if (pageNum > totalPages) {
            pageNumber = totalPages;
            return;
        }
        pageNumber = pageNum;
        skip = (pageNumber - 1) * pageSize;
    }

    static void main(String[] args) {
        List<Movie> movies = service.getAll();

//        TableRenderer.displayTableMovie(movies, skip, pageSize);
//        System.out.printf("Page %d / %d\n", pageNumber, totalPages);
        while (true) {
            TableRenderer.displayTableMovie(movies, skip, pageSize);
            System.out.printf("Page %d / %d\n", pageNumber, totalPages);
            System.out.println("""
                    [n] Next Page
                    [p] Previous Page
                    [gt] Go to Page
                    [e] Exit
                    """);
            System.out.print("Choose an option: ");
            String op = scanner.next();

            switch (op.toLowerCase()) {
                case "n" -> {
                    updatePageNumber(pageNumber + 1);
                }
                case "p" -> {
                    updatePageNumber(pageNumber - 1);
                }
                case "gt" -> {
                    System.out.print("[+] Enter page number: ");
                    int page = scanner.nextInt();
                    updatePageNumber(page);
                }
                case "e" -> System.exit(0);
            }
        }
    }
}
