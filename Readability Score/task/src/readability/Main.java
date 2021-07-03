package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static readability.UtilityClass.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        String[] subStr;
//
//        String delimeter = "[.?!]+";
//        String str = scanner.nextLine();
//
//        subStr = str.split(delimeter);
//
//        for (int i = 0; i < subStr.length; i++) {
//            subStr[i] = subStr[i].trim();
//        }
//
//        int sumWords = 0;
//
//        for (int i = 0; i < subStr.length; i++) {
//            sumWords += subStr[i].split("\\s+").length;
//        }

        /*System.out.println("sentence:" + str);
        System.out.println("total sentences:" + subStr.length);
        System.out.println("total words in text: " + sumWords);*/


//        String pathToFile = args[0];

//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
//        Path currentRelativePath = Paths.get("");
//        String s = currentRelativePath.toAbsolutePath().toString();
//        System.out.println("Current relative path is: " + s);
        

//        String delimeter = "[.?!]+";
//        int sumSentences = 0;
//        int sumCharacters = 0;
//        int sumWords = 0;
//        double score = 0;
//        String age = "";


        /*try {
            String fullLineFile = new String(Files.readAllBytes(Paths.get(pathToFile)));
                sumCharacters += fullLineFile.replaceAll("\\s", "").length();

                String[] subStr = fullLineFile.split(delimeter);

                for (int i = 0; i < subStr.length; i++) {
                    subStr[i] = subStr[i].trim();
                }

                sumSentences += subStr.length;
                sumWords += fullLineFile.split("\\s+").length;

                score = 4.71 * sumCharacters / sumWords + 0.5 * sumWords / sumSentences - 21.43;

                System.out.println("The text is: " + fullLineFile.replaceAll("\\s+", " "));
                System.out.println("Words: " + sumWords);
                System.out.println("Sentences: " + sumSentences);
                System.out.println("Characters: " + sumCharacters);
                System.out.println("The score is: " + score);

                int ceiledScore = (int) Math.ceil(score);

                age = ceiledScore == 1 ? "5-6" :
                        ceiledScore == 2 ? "6-7" :
                        ceiledScore == 3 ? "7-9" :
                        ceiledScore == 4 ? "9-10" :
                        ceiledScore == 5 ? "10-11" :
                        ceiledScore == 6 ? "11-12" :
                        ceiledScore == 7 ? "12-13" :
                        ceiledScore == 8 ? "13-14" :
                        ceiledScore == 9 ? "14-15" :
                        ceiledScore == 10 ? "15-16" :
                        ceiledScore == 11 ? "16-17" :
                        ceiledScore == 12 ? "17-18" :
                        ceiledScore == 13 ? "18-24" :
                        ceiledScore == 14 ? "24+" : "the heck?";

                
            System.out.println("This text should be understood by " + age + "-year-olds.");





        } catch (IOException e) {
            e.printStackTrace();
        }*/

        String fullStr = fileToString(args[0]);
        printMetrics(fullStr,
                totalWords(fullStr),
                totalSentences(fullStr),
                totalCharacters(fullStr),
                totalSyllables(fullStr),
                totalPolySyllables(fullStr));

        printReport(userInput(),
                getAutomatedReadabilityIndex(totalCharacters(fullStr), totalWords(fullStr), totalSentences(fullStr)),
                getFleshKincaid(totalSyllables(fullStr), totalWords(fullStr), totalSentences(fullStr)),
                getSMOG(totalPolySyllables(fullStr), totalSentences(fullStr)),
                getColemanLiauIndex(totalCharacters(fullStr), totalWords(fullStr), totalSentences(fullStr)));
    }
}

class UtilityClass {
    private static Scanner scanner = new Scanner(System.in);

    static final String SENTENCE_DELIMITER = "[.?!]+";
    static final String SYLLABLE_PATTERN = "(?i)[aiou][aeiou]*|e[aeiou]*(?!d?\\b)";
    static final String SYLLABLE_PATTERN_2 = "(?i)[aiouy][aeiouy]*|e[aeiouy]*(?!d?\\b)";

    static String fileToString(String pathToFile) throws IOException {
        return new String(Files.readAllBytes(Paths.get(pathToFile)));
    }

    static int totalCharacters(String str) {
        int totalChars = 0;
        totalChars += str.replaceAll("\\s", "").length();

        return totalChars;
    }

    static int totalSentences(String str) {
        int totalSentences = 0;

        String[] strWithNoSpaces = str.split(SENTENCE_DELIMITER);

        for (int i = 0; i < strWithNoSpaces.length; i++) {
            strWithNoSpaces[i] = strWithNoSpaces[i].trim();
        }

        return strWithNoSpaces.length;
    }

    static int totalWords(String str) {
        int totalWords = 0;
        totalWords += str.split("\\s+").length;

        return totalWords;
    }

    static int totalSyllables(String str) {
//        String ptrn = "(?i)[aiou][aeiou]*|e[aeiou]*(?!d?\\b)";
        int countSyllables = 0;

        String[] words = str.split("\\s+");

        for (int i = 0; i < words.length; i++) {
//            Matcher m = Pattern.compile(SYLLABLE_PATTERN).matcher(words[i]);
            Matcher m = Pattern.compile(SYLLABLE_PATTERN_2).matcher(words[i]);
            int innerCount = 0;

            while (m.find()) {
                innerCount++;
            }

            countSyllables += Math.max(innerCount, 1);
        }

        return countSyllables;
    }

    static int totalPolySyllables(String str) { // which is the number of words with more than 2 syllables.
        int countPolySyllables = 0;

        String[] words = str.split("\\s+");

        for (int i = 0; i < words.length; i++) {
//            Matcher m = Pattern.compile(SYLLABLE_PATTERN).matcher(words[i]);
            Matcher m = Pattern.compile(SYLLABLE_PATTERN_2).matcher(words[i]);
            int innerCount = 0;

            while (m.find()) {
                innerCount++;
            }

            countPolySyllables = innerCount > 2 ?
                    countPolySyllables + 1 : countPolySyllables;
        }

        return countPolySyllables;
    }

    static String ageViaScore(double score) {
        String age = "";
        int ceiledScore = (int) Math.ceil(score);

        switch (ceiledScore) {
            case 1: age = "6";
                break;
            case 2: age = "7";
                break;
            case 3: age = "9";
                break;
            case 4: age = "10";
                break;
            case 5: age = "11";
                break;
            case 6: age = "12";
                break;
            case 7: age = "13";
                break;
            case 8: age = "14";
                break;
            case 9: age = "15";
                break;
            case 10: age = "16";
                break;
            case 11: age = "17";
                break;
            case 12: age = "18";
                break;
            case 13: age = "24";
                break;
            default: age = "24+";
        }

        return age;
    }

    static void printMetrics(String text, int words, int sentences, int chars, int syllables, int polysyllables) {
        System.out.println("The text is:");
        System.out.println(text);
        System.out.println();
        System.out.printf("Words: %d\n", words);
        System.out.printf("Sentences: %d\n", sentences);
        System.out.printf("Characters: %d\n", chars);
        System.out.printf("Syllables: %d\n", syllables);
        System.out.printf("Polysyllables: %d\n", polysyllables);
    }

    static String userInput() {
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        return scanner.next();
    }

    static void printReport(String userInput, double ari, double flesch, double smog, double colemanLiau) {
        System.out.println();

        switch (userInput) {
            case "ARI":
                System.out.printf("Automated Readability Index: %.2f (about %s-year-olds).\n", ari, ageViaScore(ari));
                break;
            case "FK":
                System.out.printf("Flesch-Kincaid readability tests: %.2f (about %s-year-olds).\n", flesch, ageViaScore(flesch));
                break;
            case "SMOG":
                System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s-year-olds).\n", smog, ageViaScore(smog));
                break;
            case "CL":
                System.out.printf("Coleman-Liau index: %.2f (about %s-year-olds).\n", colemanLiau, ageViaScore(colemanLiau));
                break;
            default:
                System.out.printf("Automated Readability Index: %.2f (about %s-year-olds).\n", ari, ageViaScore(ari));
                System.out.printf("Flesch-Kincaid readability tests: %.2f (about %s-year-olds).\n", flesch, ageViaScore(flesch));
                System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s-year-olds).\n", smog, ageViaScore(smog));
                System.out.printf("Coleman-Liau index: %.2f (about %s-year-olds).\n", colemanLiau, ageViaScore(colemanLiau));
        }
    }

    static double getAutomatedReadabilityIndex(int characters, int words, int sentences) {
        return 4.71 * characters / words + 0.5 * words / sentences - 21.43;
    }

    static double getFleshKincaid(int syllables, int words, int sentences) {
        return 0.39 * ((double) words / sentences) + 11.8 * ((double) syllables / words) - 15.59;
    }

    static double getSMOG(int polysyllables, int sentences) {
        return 1.043 * Math.sqrt(polysyllables * (30 / (double) sentences)) + 3.1291;
    }

    static double getColemanLiauIndex(int characters, int words, int sentences) {
        double avgCharacters = (double) characters / (double) words * 100;
        double avgSentences = (double) sentences / (double) words * 100;

        return 0.0588 * avgCharacters - 0.296 * avgSentences - 15.8;

    }
}