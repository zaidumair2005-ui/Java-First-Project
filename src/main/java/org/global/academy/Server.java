package org.global.academy;

import static spark.Spark.*;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.Random;

public class Server {
    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/public");
        // Simple CORS
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*"); // or specific origin
            response.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type,Authorization");
        });
        options("/*", (req, res) -> {
            String accessControlRequestHeaders = req.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                res.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = req.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                res.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });

        Gson gson = new Gson();

        // Flashcards endpoint - 44 Thai consonants
        get("/flashcards", (req, res) -> {
            res.type("application/json");
            java.util.List<Flashcard> cards = new java.util.ArrayList<>();

            // Mid-class consonants (9 characters)
            cards.add(new Flashcard("ก", "ก", "ko kai", "(chicken)", "MIDDLE"));
            cards.add(new Flashcard("จ", "จ", "cho chan", "(plate)", "MIDDLE"));
            cards.add(new Flashcard("ฎ", "ฎ", "do chada", "(crown)", "MIDDLE"));
            cards.add(new Flashcard("ฏ", "ฏ", "to patak", "(goad)", "MIDDLE"));
            cards.add(new Flashcard("ด", "ด", "do dek", "(child)", "MIDDLE"));
            cards.add(new Flashcard("ต", "ต", "to tao", "(turtle)", "MIDDLE"));
            cards.add(new Flashcard("บ", "บ", "bo baimai", "(leaf)", "MIDDLE"));
            cards.add(new Flashcard("ป", "ป", "po pla", "(fish)", "MIDDLE"));
            cards.add(new Flashcard("อ", "อ", "o ang", "(basin)", "MIDDLE"));

            // High-class consonants (11 characters)
            cards.add(new Flashcard("ข", "ข", "kho khai", "(egg)", "HIGH"));
            cards.add(new Flashcard("ฃ", "ฃ", "kho khuat", "(bottle)", "HIGH"));
            cards.add(new Flashcard("ฉ", "ฉ", "cho ching", "(cymbal)", "HIGH"));
            cards.add(new Flashcard("ฐ", "ฐ", "tho than", "(base)", "HIGH"));
            cards.add(new Flashcard("ถ", "ถ", "tho thung", "(bag)", "HIGH"));
            cards.add(new Flashcard("ผ", "ผ", "pho phueng", "(bee)", "HIGH"));
            cards.add(new Flashcard("ฝ", "ฝ", "fo fa", "(lid)", "HIGH"));
            cards.add(new Flashcard("ศ", "ศ", "so sala", "(pavilion)", "HIGH"));
            cards.add(new Flashcard("ษ", "ษ", "so rue si", "(hermit)", "HIGH"));
            cards.add(new Flashcard("ส", "ส", "so suea", "(tiger)", "HIGH"));
            cards.add(new Flashcard("ห", "ห", "ho hip", "(chest)", "HIGH"));

            // Low-class consonants (24 characters)
            cards.add(new Flashcard("ค", "ค", "kho khwai", "(buffalo)", "LOW"));
            cards.add(new Flashcard("ฅ", "ฅ", "kho khon", "(person)", "LOW"));
            cards.add(new Flashcard("ฆ", "ฆ", "kho rakhang", "(bell)", "LOW"));
            cards.add(new Flashcard("ง", "ง", "ngo ngu", "(snake)", "LOW"));
            cards.add(new Flashcard("ช", "ช", "cho chang", "(elephant)", "LOW"));
            cards.add(new Flashcard("ซ", "ซ", "so so", "(chain)", "LOW"));
            cards.add(new Flashcard("ฌ", "ฌ", "cho choe", "(tree)", "LOW"));
            cards.add(new Flashcard("ญ", "ญ", "yo ying", "(woman)", "LOW"));
            cards.add(new Flashcard("ฑ", "ฑ", "tho montho", "(mandodari)", "LOW"));
            cards.add(new Flashcard("ฒ", "ฒ", "tho phu thao", "(elder)", "LOW"));
            cards.add(new Flashcard("ณ", "ณ", "no nen", "(novice)", "LOW"));
            cards.add(new Flashcard("ท", "ท", "tho thahan", "(soldier)", "LOW"));
            cards.add(new Flashcard("ธ", "ธ", "tho thong", "(flag)", "LOW"));
            cards.add(new Flashcard("น", "น", "no nu", "(mouse)", "LOW"));
            cards.add(new Flashcard("พ", "พ", "pho phan", "(tray)", "LOW"));
            cards.add(new Flashcard("ฟ", "ฟ", "fo fan", "(teeth)", "LOW"));
            cards.add(new Flashcard("ภ", "ภ", "pho samphao", "(sailboat)", "LOW"));
            cards.add(new Flashcard("ม", "ม", "mo ma", "(horse)", "LOW"));
            cards.add(new Flashcard("ย", "ย", "yo yak", "(giant)", "LOW"));
            cards.add(new Flashcard("ร", "ร", "ro ruea", "(boat)", "LOW"));
            cards.add(new Flashcard("ล", "ล", "lo ling", "(monkey)", "LOW"));
            cards.add(new Flashcard("ว", "ว", "wo waen", "(ring)", "LOW"));
            cards.add(new Flashcard("ฬ", "ฬ", "lo chula", "(kite)", "LOW"));
            cards.add(new Flashcard("ฮ", "ฮ", "ho nokhuk", "(owl)", "LOW"));

            return gson.toJson(cards);
        });
        post("/login", (req, res) -> {
            System.out.println("Received /login request with body: " + req.body());
            LoginRequest lr = gson.fromJson(req.body(), LoginRequest.class);

            if ("alice".equals(lr.username) && "secret".equals(lr.password)) {
                res.type("application/json");
                return gson.toJson(new LoginResponse("a-fake-token", lr.username));
            } else {
                res.status(401);
                res.type("application/json");
                return gson.toJson(new ErrorResponse("Invalid credentials"));
            }
        });

        // Simple random endpoint used by the welcome page (returns JSON with 1..13)
        get("/random", (req, res) -> {
            res.type("application/json");
            int value = new Random().nextInt(13) + 1; // 1..13
            return gson.toJson(new RandomResponse(value));
        });

        System.out.println("Server started on port 8080 and serving /public resources");
    }

    static class LoginRequest {
        String username;
        String password;
    }

    static class LoginResponse {
        String token;
        String username;

        LoginResponse(String t, String u) {
            token = t;
            username = u;
        }
    }

    static class ErrorResponse {
        String error;

        ErrorResponse(String e) {
            error = e;
        }
    }

    static class RandomResponse {
        int value;

        RandomResponse(int v) {
            value = v;
        }
    }

    // Added Flashcard class from Flashcard.java so Server can use it directly.
    static class Flashcard {
        private String front;
        private String back;
        private String pronunciation;
        private String meaning;

        @SerializedName("class")
        private String classType; // "MIDDLE", "HIGH", or "LOW"

        private boolean learned;
        private String difficulty;

        // Constructor
        public Flashcard(String front, String back, String pronunciation, String meaning, String classType) {
            this.front = front;
            this.back = back;
            this.pronunciation = pronunciation;
            this.meaning = meaning;
            this.classType = classType;
            this.learned = false;
            this.difficulty = null;
        }

        // Old constructor for backward compatibility
        public Flashcard(String front, String back) {
            this.front = front;
            this.back = back;
            this.pronunciation = "";
            this.meaning = "";
            this.classType = "MIDDLE";
            this.learned = false;
            this.difficulty = null;
        }

        // Getters
        public String getFront() {
            return front;
        }

        public String getBack() {
            return back;
        }

        public String getPronunciation() {
            return pronunciation;
        }

        public String getMeaning() {
            return meaning;
        }

        public String getClassType() {
            return classType;
        }

        public boolean isLearned() {
            return learned;
        }

        public String getDifficulty() {
            return difficulty;
        }

        // Setters
        public void setFront(String front) {
            this.front = front;
        }

        public void setBack(String back) {
            this.back = back;
        }

        public void setPronunciation(String pronunciation) {
            this.pronunciation = pronunciation;
        }

        public void setMeaning(String meaning) {
            this.meaning = meaning;
        }

        public void setClass(String classType) {
            this.classType = classType;
        }

        public void setLearned(boolean learned) {
            this.learned = learned;
        }

        public void setDifficulty(String difficulty) {
            this.difficulty = difficulty;
        }

        @Override
        public String toString() {
            return "Flashcard{" +
                    "front='" + front + '\'' +
                    ", back='" + back + '\'' +
                    ", pronunciation='" + pronunciation + '\'' +
                    ", meaning='" + meaning + '\'' +
                    ", class='" + classType + '\'' +
                    ", learned=" + learned +
                    ", difficulty='" + difficulty + '\'' +
                    '}';
        }
    }
}