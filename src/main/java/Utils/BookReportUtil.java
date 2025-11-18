package Utils;

import Models.Books;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static Utils.BookMetadataFetcher.enrichBookByTitle;

public class BookReportUtil {
    public static String generateCustomJsonReport(List<Books> books) {
        Map<String, Object> report = new LinkedHashMap<>();

        for (Books book : books) {
            enrichBookByTitle(book); // Fetch ISBN, author, publisher

            Map<String, Object> bookDetails = new LinkedHashMap<>();
            bookDetails.put("Book Name", book.getName());
            bookDetails.put("Author", book.getAuthor());
            bookDetails.put("Publisher", book.getPublisher());
            bookDetails.put("Qty", book.getQuantity());

            bookDetails.put("Amazon", createPlatformEntry(book, "Amazon"));
            bookDetails.put("Flipkart", createPlatformEntry(book, "Flipkart"));
            bookDetails.put("BooksIndia", createPlatformEntry(book, "BuyBooksIndia"));

            String key = book.getIsbn();
            if (key == null || key.isEmpty()) {
                key = book.getName(); // fallback to book title
            }
            report.put(key, bookDetails);


        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(report);
    }

    public static void comparePlatformCosts(List<Books> books) {
        Map<String, Double> platformTotals = new LinkedHashMap<>();
        Map<String, Boolean> platformEligible = new LinkedHashMap<>();

        // Initialize totals and eligibility
        for (String platform : List.of("Amazon", "Flipkart", "BuyBooksIndia")) {
            platformTotals.put(platform, 0.0);
            platformEligible.put(platform, true);
        }

        for (Books book : books) {
            for (String platform : platformTotals.keySet()) {
                boolean isAvailable = book.getAvailability().getOrDefault(platform, false);
                if (!isAvailable) {
                    platformEligible.put(platform, false); // Mark platform ineligible
                }

                String priceStr = book.getPrices().getOrDefault(platform, "N/A").replaceAll("[^\\d.]", "");
                if (!priceStr.isEmpty()) {
                    try {
                        double price = Double.parseDouble(priceStr);
                        platformTotals.put(platform, platformTotals.get(platform) + price);
                    } catch (NumberFormatException ignored) {}
                }
            }
        }

        System.out.println("\n Total Cost Comparison (Only platforms with full availability):");
        String cheapest = null;
        double minCost = Double.MAX_VALUE;

        for (String platform : platformTotals.keySet()) {
            if (platformEligible.get(platform)) {
                double total = platformTotals.get(platform);
                System.out.printf("%-15s ₹%.2f%n", platform, total);
                if (total < minCost) {
                    minCost = total;
                    cheapest = platform;
                }
            } else {
                System.out.printf("%-15s Not all books available%n", platform);
            }
        }

        if (cheapest != null) {
            System.out.println("\n Cheapest platform to buy the full set: " + cheapest);
        } else {
            System.out.println("\n⚠ No platform has all books available.");
        }
    }



    private static Map<String, Object> createPlatformEntry(Books book, String platform) {
        Map<String, Object> entry = new LinkedHashMap<>();
        entry.put("Price", book.getPrices().getOrDefault(platform, "N/A"));
        entry.put("Is available", book.getAvailability().getOrDefault(platform, false));
        return entry;
    }

}
