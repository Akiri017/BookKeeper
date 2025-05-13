# 📘 BookKeeper

A personal book tracking app built with Spring Boot.  
Track your reading journey — from current reads to your favorite quotes.

---

## 🌐 Background

BookKeeper is a minimalist backend system that allows users to keep track of the books they've read, are currently reading, or plan to read. It stores information such as the book's title, author, reading status, rating, summary, quotes, and tags — and includes a cover photo link.

---

## 📦 Features

- Add books with custom metadata
- Track reading progress (status, start & finish date) and personalized stats
- Rate and review books
- Save favorite quotes
- Assign custom tags
- Search and filter by tags, author, rating, or status
- Attach a cover image URL
- RESTful API with OpenAPI documentation
- SQLite database for persistent storage

---

## 🚀 Getting Started

### ⚙️ Prerequisites

- Java 21 or later
- Maven

### 🧰 Installation

Clone the repository:

```bash
git clone https://github.com/your-username/bookkeeper-backend.git
cd bookkeeper-backend
```

Run the app with Maven:

```bash
mvn spring-boot:run
```

The application will be available at:

http://localhost:8080

API documentation is available at:

http://localhost:8080/swagger-ui.html

### 📬 API Endpoints

| Method | Endpoint        | Description            |
|--------|-----------------|------------------------|
| GET    | /books          | Get all books          |
| GET    | /books/{id}     | Get a specific book    |
| POST   | /books          | Add a new book         |
| PATCH  | /books/{id}     | Update an existing book|
| DELETE | /books/{id}     | Delete a book          |
| GET    | /books/search   | Search books with filters|

Sample JSON (POST/PATCH)

```json
{
  "title": "Atomic Habits",
  "author": "James Clear",
  "dateStarted": "2023-01-01",
  "dateFinished": "2023-02-01",
  "status": "FINISHED",
  "rating": 5,
  "summary": "A powerful guide to building habits.",
  "addQuotes": [
    {
      "content": "You do not rise to the level of your goals..."
    }
  ],
  "addTags": [
    {
      "name": "Self-help"
    },
    {
      "name": "Psychology"
    }
  ],
  "coverImageUrl": "https://example.com/cover.jpg"
}
```

Search Parameters:
- title: Search by book title
- author: Search by author name
- status: Filter by reading status (TO_READ, IN_PROGRESS, FINISHED)
- rating: Filter by rating (1-5)
- tagName: Filter by tag
- startDate: Filter by start date (yyyy-MM-dd)
- endDate: Filter by end date (yyyy-MM-dd)

---

## ❗ Caveats & Limitations
- No authentication or user system yet
- No pagination implemented yet
- Uses SQLite database for storage
- This project is under active development — expect changes!

---

## 📄 License
MIT License © 2025 Akiri017
