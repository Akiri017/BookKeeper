# 📘 BookKeeper

A personal book tracking app built with Spring Boot.  
Track your reading journey — from current reads to your favorite quotes.

---

## 🌐 Background

BookKeeper is a minimalist backend system that allows users to keep track of the books they’ve read, are currently reading, or plan to read. It stores information such as the book’s title, author, reading status, rating, summary, quotes, and tags — and includes a cover photo link.

---

## 📦 Features

- Add books with custom metadata
- Track reading progress (status, start & finish date) and personalized stats
- Rate and review books
- Save favorite quotes
- Assign custom tags
- Search and filter by tags, author, rating, or status
- Attach a cover image URL
- User login and personalized book log
- Export book summaries and quotes to PDF 

---

## 🚀 Getting Started

### ⚙️ Prerequisites

- Java 17 or later
- Maven

### 🧰 Installation

Clone the repository:

git clone https://github.com/your-username/bookkeeper-backend.git
cd bookkeeper-backend

Run the app with Maven:

mvn spring-boot:run

The application will be available at:

http://localhost:8080

  ⚠️ Requires Java 17+ and Maven. By default, the app uses H2 in-memory database. You can switch to MySQL by editing the application.properties file.

### 📬 API Endpoints

| Method | Endpoint        | Description            |
|--------|-----------------|------------------------|
| GET    | /books          | Get all books          |
| GET    | /books/{id}     | Get a specific book    |
| POST   | /books          | Add a new book         |
| PUT    | /books/{id}     | Update an existing book|
| DELETE | /books/{id}     | Delete a book          |

Sample JSON (POST/PUT)

{
  "title": "Atomic Habits",
  "author": "James Clear",
  "dateStarted": "2023-01-01",
  "dateFinished": "2023-02-01",
  "status": "FINISHED",
  "rating": 5,
  "summary": "A powerful guide to building habits.",
  "favoriteQuotes": "You do not rise to the level of your goals...",
  "tags": "Self-help, Psychology",
  "coverPhotoUrl": "https://example.com/cover.jpg"
}

Testing With Postman:
- Open Postman
- Set the request type to POST, GET, PUT, or DELETE
- Enter the request URL (e.g. http://localhost:8080/books)
- For POST or PUT, go to the Body tab → Select raw → Set format to JSON
- Paste the sample JSON above and click Send
- View the response in the Body section

---

## ❗ Caveats & Limitations
- No authentication or user system yet
- No pagination or filtering in the API
- Relies on in-memory H2 database unless configured otherwise
- This project is under active development — expect changes!

---

## 📄 License
MIT License © 2025 Marianne Angelika B. Santos
