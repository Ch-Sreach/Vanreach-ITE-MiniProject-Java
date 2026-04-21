import model.MovieInfo;
import model.MovieResponse;
import service.MovieService;
import service.MovieServiceImpl;
import utils.TableRenderer;

import java.util.Scanner;

public class App {

    public static final String green  = "\u001B[32m";
    public static final String blue   = "\u001B[34m";
    public static final String yellow = "\u001B[33m";
    public static final String purple = "\u001B[35m";
    public static final String red    = "\u001B[31m";
    public static final String cyan   = "\u001B[36m";
    public static final String white  = "\u001B[37m";
    public static final String reset  = "\u001B[0m";

    private static final Scanner scanner = new Scanner(System.in);
    private static final MovieService service = new MovieServiceImpl();

    // ── Popular Movies ────────────────────────────────────────────────────────
    private static void popularMovie() {
        int p = 1;
        sideMenu:
        while (true) {
            MovieResponse popularMovie = service.getPopularMovie(p);
            TableRenderer.displayTableMovie(popularMovie);

            System.out.println(purple + """
                    [n]  Next Page
                    [p]  Previous Page
                    [gt] Go To Page
                    [md] Movie Detail
                    [b]  Back to Main Menu
                    [e]  Exit
                    """);
            System.out.print(cyan + "Choose option: ");
            String op = scanner.next();

            switch (op.toLowerCase()) {
                case "n"  -> p = (p == popularMovie.getTotalPages()) ? p : p + 1;
                case "p"  -> p = (p == 1) ? 1 : p - 1;
                case "gt" -> p = goToPage(p, popularMovie.getTotalPages());
                case "md" -> showMovieDetail();
                case "b"  -> { break sideMenu; }
                case "e"  -> System.exit(0);
            }
        }
    }

    // ── Top Rated Movies ──────────────────────────────────────────────────────
    private static void topRatedMovie() {
        int p = 1;
        sideMenu:
        while (true) {
            MovieResponse topRated = service.getTopRatedMovie(p);
            TableRenderer.displayTableMovie(topRated);

            System.out.println(blue + """
                    [n]  Next Page
                    [p]  Previous Page
                    [gt] Go To Page
                    [md] Movie Detail
                    [b]  Back to Main Menu
                    [e]  Exit
                    """);
            System.out.print(cyan + "Choose option: ");
            String op = scanner.next();

            switch (op.toLowerCase()) {
                case "n"  -> p = (p == topRated.getTotalPages()) ? p : p + 1;
                case "p"  -> p = (p == 1) ? 1 : p - 1;
                case "gt" -> p = goToPage(p, topRated.getTotalPages());
                case "md" -> showMovieDetail();
                case "b"  -> { break sideMenu; }
                case "e"  -> System.exit(0);
            }
        }
    }

    // ── Now Playing Movies ────────────────────────────────────────────────────
    private static void nowPlayingMovie() {
        int p = 1;
        sideMenu:
        while (true) {
            MovieResponse nowPlaying = service.getNowPlaying(p);
            TableRenderer.displayTableMovie(nowPlaying);

            System.out.println(purple + """
                    [n]  Next Page
                    [p]  Previous Page
                    [gt] Go To Page
                    [md] Movie Detail
                    [b]  Back to Main Menu
                    [e]  Exit
                    """);
            System.out.print(cyan + "Choose option: ");
            String op = scanner.next();

            switch (op.toLowerCase()) {
                case "n"  -> p = (p == nowPlaying.getTotalPages()) ? p : p + 1;
                case "p"  -> p = (p == 1) ? 1 : p - 1;
                case "gt" -> p = goToPage(p, nowPlaying.getTotalPages());
                case "md" -> showMovieDetail();
                case "b"  -> { break sideMenu; }
                case "e"  -> System.exit(0);
            }
        }
    }

    // ── Upcoming Movies ───────────────────────────────────────────────────────
    private static void upcomingMovie() {
        int p = 1;
        sideMenu:
        while (true) {
            MovieResponse upcoming = service.getUpcoming(p);
            TableRenderer.displayTableMovie(upcoming);

            System.out.println(blue + """
                    [n]  Next Page
                    [p]  Previous Page
                    [gt] Go To Page
                    [md] Movie Detail
                    [b]  Back to Main Menu
                    [e]  Exit
                    """);
            System.out.print(cyan + "Choose option: ");
            String op = scanner.next();

            switch (op.toLowerCase()) {
                case "n"  -> p = (p == upcoming.getTotalPages()) ? p : p + 1;
                case "p"  -> p = (p == 1) ? 1 : p - 1;
                case "gt" -> p = goToPage(p, upcoming.getTotalPages());
                case "md" -> showMovieDetail();
                case "b"  -> { break sideMenu; }
                case "e"  -> System.exit(0);
            }
        }
    }

    // ── Search Menu ───────────────────────────────────────────────────────────
    private static void searchMenu() {
        System.out.print(cyan + "Enter Movie Name: ");
        String name = scanner.next();
        int p = 1;

        menu:
        while (true) {
            MovieResponse response = service.getDummyMovie(name, p);
            TableRenderer.displayTableMovie(response);

            System.out.println(purple + """
                    [n]  Next Page
                    [p]  Previous Page
                    [gt] Go To Page
                    [md] Movie Detail
                    [b]  Back
                    [e]  Exit
                    """);
            System.out.print(cyan + "Choose option: ");
            String op = scanner.next();

            switch (op.toLowerCase()) {
                case "n"  -> p = (p == response.getTotalPages()) ? p : p + 1;
                case "p"  -> p = (p == 1) ? 1 : p - 1;
                case "gt" -> p = goToPage(p, response.getTotalPages());
                case "md" -> showMovieDetail();
                case "b"  -> { break menu; }
                case "e"  -> System.exit(0);
            }
        }
    }

    // ── Shared Helpers ────────────────────────────────────────────────────────
    private static int goToPage(int current, int totalPages) {
        while (true) {
            System.out.print(cyan + "[!] Enter page number: ");
            int page = scanner.nextInt();
            if (page > totalPages) {
                System.out.println(yellow + "There are only " + totalPages + " pages! Please enter another number.");
            } else if (page <= 0) {
                System.out.println(yellow + "Please enter a page number greater than 0!");
            } else {
                return page;
            }
        }
    }

    private static void showMovieDetail() {
        System.out.print(cyan + "[!] Enter Movie Id: ");
        int id = scanner.nextInt();
        MovieInfo movieInfo = service.getMovieDetail(id);
        TableRenderer.displayTableMovieInformation(movieInfo);
    }

    // ── Main ──────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        while (true) {
            System.out.println(green + """
                    ███╗   ███╗ ██████╗ ██╗   ██╗██╗███████╗    ███████╗███████╗ █████╗ ██████╗  ██████╗██╗  ██╗
                    ████╗ ████║██╔═══██╗██║   ██║██║██╔════╝    ██╔════╝██╔════╝██╔══██╗██╔══██╗██╔════╝██║  ██║
                    ██╔████╔██║██║   ██║██║   ██║██║█████╗      ███████╗█████╗  ███████║██████╔╝██║     ███████║
                    ██║╚██╔╝██║██║   ██║╚██╗ ██╔╝██║██╔══╝      ╚════██║██╔══╝  ██╔══██║██╔══██╗██║     ██╔══██║
                    ██║ ╚═╝ ██║╚██████╔╝ ╚████╔╝ ██║███████╗    ███████║███████╗██║  ██║██║  ██║╚██████╗██║  ██║
                    ╚═╝     ╚═╝ ╚═════╝   ╚═══╝  ╚═╝╚══════╝    ╚══════╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝
                    """ + reset);

            System.out.println(blue + """
                    Choose Option
                    [s] Search Movie
                    [p] Get Popular Movie
                    [t] Get Top Rated Movie
                    [u] Upcoming Movie
                    [n] Now Playing Movie
                    [e] Exit
                    """ + reset);

            System.out.print(cyan + "Enter Option: " + reset);
            String op = scanner.next();

            switch (op.toLowerCase()) {
                case "s" -> {
                    System.out.println(purple + "=========== [[ Search Movie ]] ===========" + reset);
                    searchMenu();
                }
                case "p" -> {
                    System.out.println(purple + "=========== [[ Popular Movie ]] ===========" + reset);
                    popularMovie();
                }
                case "t" -> {
                    System.out.println(blue + "=========== [[ Top Rated Movie ]] ===========" + reset);
                    topRatedMovie();
                }
                case "u" -> {
                    System.out.println(blue + "=========== [[ Upcoming Movie ]] ===========" + reset);
                    upcomingMovie();
                }
                case "n" -> {
                    System.out.println(purple + "=========== [[ Now Playing Movie ]] ===========" + reset);
                    nowPlayingMovie();
                }
                case "e" -> System.exit(0);
            }
        }
    }
}
