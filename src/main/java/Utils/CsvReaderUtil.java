package Utils;

import Models.Books;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderUtil {
    public static List<Books> readBooks(String filePath) throws IOException {
        List<Books> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // skip empty lines
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    books.add(new Books(name, quantity));
                }
            }
        }

        return books;
    }

}
