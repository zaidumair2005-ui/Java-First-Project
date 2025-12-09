# Java-First-Project
Our first team project.

Thai Learning App (Java + Web)

A simple, interactive flashcard-based learning system designed to help beginners practice Thai consonants, basic vocabulary, and recognition skills.
The project includes Java backend logic, static web pages, images, audio, and a small Spark Java web server to serve the learning interface.

 Features

Flashcard system for Thai consonants
Java-based learning modules
Web interface with images and audio
Login page and simple navigation
Static HTML pages served from a lightweight Spark Java server
Organized folder structure with resources in one place

Project Structure

project-root/
cThaiLanguage/
          Flashcard.java
          LearnThai.java
          ThaiConsonantFlashCard.java
          UseCards.java
webservice/
          src/main/java/
          Server.java
          src/main/resources/public/
          audio/
          images/
          index.html
          login.html
          welcome.html
          flashcards.html

pom.xml
README.md

Requirements

Make sure you have:
Java 8+
Maven 3+
VS Code / IntelliJ (optional)

 How to Run the App

Follow these steps inside VS Code terminal or a normal terminal:

1. Build the project using Maven

Windows:

"C:\Users\User\.maven\maven-3.9.11\bin\mvn" clean package

macOS/Linux:
bash mvnw clean package

2. Run the generated JAR
java -jar target/spark-hello-1.0-SNAPSHOT-jar-with-dependencies.jar

3. Open the app in your browser
http://localhost:8080/login.html


Your login page, flashcards, and all other HTML files will load from the public/ folder.





👤 Author

Vila
Vuthy
Zaid
Wunna
Joseph

Thai Language Learning Project – Java + Web
 License
This project is created for educational purposes only.
