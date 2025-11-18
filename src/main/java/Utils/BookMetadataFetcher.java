package Utils;

import Models.Books;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class BookMetadataFetcher {
    public static void enrichBookByTitle(Books book) {
        try {
            String encodedTitle = URLEncoder.encode(book.getName(), StandardCharsets.UTF_8);
            String apiUrl = "https://openlibrary.org/search.json?title=" + encodedTitle;

            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");

            JsonObject json = JsonParser.parseReader(new InputStreamReader(connection.getInputStream())).getAsJsonObject();
            JsonArray docs = json.getAsJsonArray("docs");

            if (docs != null && docs.size() > 0) {
                JsonObject firstMatch = docs.get(0).getAsJsonObject();

                if (firstMatch.has("author_name")) {
                    book.setAuthor(firstMatch.getAsJsonArray("author_name").get(0).getAsString());
                }
                if (firstMatch.has("publisher")) {
                    {
                        JsonArray publishers = firstMatch.getAsJsonArray("publisher");
                        if (publishers.size() > 0) {
                            book.setPublisher(publishers.get(0).getAsString());
                        } else {
                            book.setPublisher("Unknown");
                        }
                    }

                }
                if (firstMatch.has("isbn")) {
                    book.setIsbn(firstMatch.getAsJsonArray("isbn").get(0).getAsString());
                }
            }
        } catch (Exception e) {
            System.out.println("Metadata fetch failed for: " + book.getName());
        }
    }

}
