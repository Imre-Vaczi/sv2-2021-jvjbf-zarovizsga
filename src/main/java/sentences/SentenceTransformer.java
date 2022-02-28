package sentences;

import java.util.Arrays;

public class SentenceTransformer {

    public String shortenSentence(String sentence) {

        validateInputString(sentence);

        if (sentence.split(" ").length < 5) {
            return sentence;
        } else {
            String firstWord = sentence.split(" ")[0];
            String lastWord = sentence.split(" ")[sentence.split(" ").length-1];
            return firstWord + " ... " + lastWord;
        }
    }

    private void validateInputString(String sentence) {
        if (!Character.isUpperCase(sentence.charAt(0))) {
            throw new IllegalArgumentException("Must start with capital letter!");
        }

        if (!Arrays.asList(".", "!", "?").contains(String.valueOf(sentence.charAt(sentence.length()-1)))) {
            throw new IllegalArgumentException("Must end with . ! or ?");
        }
    }
}
