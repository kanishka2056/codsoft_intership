import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizGame {
    static Scanner scanner = new Scanner(System.in);
    static int score = 0;
    static boolean answered = false;

    // Question class to store questions, options, and correct answer
    static class Question {
        String questionText;
        String[] options;
        int correctAnswer;

        Question(String questionText, String[] options, int correctAnswer) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    // Array of questions
    static Question[] questions = {
        new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Paris", "3. Madrid", "4. Rome"}, 2),
        new Question("What is the largest planet in our solar system?", new String[]{"1. Earth", "2. Jupiter", "3. Saturn", "4. Mars"}, 2),
        new Question("Which element has the chemical symbol 'O'?", new String[]{"1. Oxygen", "2. Gold", "3. Hydrogen", "4. Helium"}, 1)
    };

    public static void main(String[] args) {
        // Loop through each question
        for (Question question : questions) {
            presentQuestion(question);
        }

        // Display final results
        displayResults();
    }

    // Method to display a question and manage timer
    static void presentQuestion(Question question) {
        System.out.println("\n" + question.questionText);
        for (String option : question.options) {
            System.out.println(option);
        }

        answered = false;

        // Create a timer to limit answering time to 10 seconds
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!answered) {
                    System.out.println("\nTime's up! You didn't answer in time.");
                    answered = true; // To prevent answering after timeout
                }
            }
        }, 10000); // 10 seconds timer

        // Wait for user answer, if they respond in time
        int userAnswer = getUserAnswer();
        timer.cancel();  // Cancel timer if answered within time

        if (answered) return; // Skip if time ran out

        // Check if the answer is correct and update score
        if (userAnswer == question.correctAnswer) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Wrong answer.");
        }

        answered = true;
    }

    // Method to get user answer
    static int getUserAnswer() {
        int userAnswer = -1;
        while (!answered) {
            try {
                System.out.print("Your answer (1-4): ");
                userAnswer = Integer.parseInt(scanner.nextLine());
                if (userAnswer >= 1 && userAnswer <= 4) {
                    answered = true;
                } else {
                    System.out.println("Please select a valid option (1-4).");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
            }
        }
        return userAnswer;
    }

    // Method to display final results
    static void displayResults() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score is: " + score + "/" + questions.length);
        System.out.println("Thank you for playing!");
    }
}









    



