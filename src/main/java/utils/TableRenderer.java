package utils;

import model.Movie;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class TableRenderer {

    public static void displayTableMovie(List<Movie> movieList, int skip, int limit) {
        Table table = new Table(
                5,
                BorderStyle.CLASSIC_WIDE,
                ShownBorders.ALL
        );
        CellStyle center = new CellStyle(
                CellStyle.HorizontalAlign.CENTER
        );

        String[] columns = {"ID", "Title", "Release", "Rated", "Trailer"};
//        table.addCell("ID");
//        table.addCell("Title");
//        table.addCell("Release");
//        table.addCell("Rated");
//        table.addCell("Trailer");
        for (String column : columns) {
            table.addCell(column, center);
        }

        List<Movie> partialData = movieList.stream().skip(skip).limit(limit).toList();

        for (Movie movie : partialData) {
            table.addCell(movie.getId().toString());
            table.addCell(movie.getTitle());
            table.addCell(movie.getRelease().toString());
            table.addCell(movie.getRate().toString());
            table.addCell(movie.getTrailer());
        }

        System.out.println(table.render());
    }
}
