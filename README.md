# Book Price Comparator 

This Java-based automation framework compares book prices across multiple online retailers and enriches book metadata using the Open Library API. It generates a unified JSON report with ISBN, author, publisher, quantity, and platform-specific pricing and availability.

---

##  Features

- ✅ Scrapes book prices and availability from:
    - Amazon
    - Flipkart
    - BuyBooksIndia
- 🔍 Enriches book metadata using Open Library Search API:
    - ISBN
    - Author
    - Publisher
- 📊 Generates a unified JSON report keyed by ISBN or title
- 💸 Compares total cost across platforms and identifies the cheapest one (only if all books are available)

---

## How to Run

1. Clone the repo : git clone https://github.com/apaul2685/BookPriceComparator.git
2. Install dependencies (Selenium, Gson, etc.)
3. Run the test: mvn test

## Cost Comparison Logic

Only platforms with full availability are considered

## API Reference

Open Library Search API : https://openlibrary.org/dev/docs/api/search

## Tech Stack

1. Java
2. Selenium Webdriver
3. Gson
4. TestNG

## Author

Anuj pal
Automation Engineer
