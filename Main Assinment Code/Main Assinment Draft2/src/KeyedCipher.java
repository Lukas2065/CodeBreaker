/**
 * This class is the child class of cipher
 *
 * @author Lukas Jonkus
 * @version 25th May 2023
 */

public class KeyedCipher extends Cipher{

    /**
     * The constructor
     */
    public KeyedCipher() {
    }

    /**
     * Specifies what a keyed caesar cipher is, it inherits plain, encrypted a numeric key
     * and alphabetic key from cipher
     */
    public KeyedCipher(String givenPlainText, String givenEncryptedText,  String givenAlphabeticKey, int givenNumericKey) {
        super.plainText = givenPlainText;
        super.encryptedText = givenEncryptedText;
        super.alphabeticKey = removeDuplicates(givenAlphabeticKey);
        super.numericKey = givenNumericKey;
    }
    /**
     * Decrypts using keyed Caesar cipher
     */
    public void keyedCaesarCipherDecryption() {
        String keyedAlphabet;
        String shiftedKeyedAlphabet;
        String keyedText;

        keyedAlphabet = createKeyedAlphabet(alphabeticKey);
        shiftedKeyedAlphabet = shiftAlphabet(keyedAlphabet, numericKey);
        keyedText = convertToDifferentAlphabet(shiftedKeyedAlphabet, keyedAlphabet, encryptedText);
        plainText = convertToDifferentAlphabet(keyedAlphabet, ALPHABET, keyedText);

    }

    /**
     * Encrypts using keyed Caesar cipher
     */
    public void keyedCaesarCipherEncryption() {
        String keyedAlphabet;
        String shiftedKeyedAlphabet;
        String keyedText;

        keyedAlphabet = createKeyedAlphabet(alphabeticKey);
        shiftedKeyedAlphabet = shiftAlphabet(keyedAlphabet, numericKey);
        keyedText = convertToDifferentAlphabet(ALPHABET, keyedAlphabet, plainText);
        encryptedText = convertToDifferentAlphabet(keyedAlphabet, shiftedKeyedAlphabet, keyedText);
    }

    /**
     * This creates a keyed ceaser alphabet using a numeric and alphabetic key
     */
    private String createKeyedAlphabet(String stringKey) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char currentLetter;
        String tempKeyedAlphabet = "";
        String keyedAlphabet = stringKey;

        for(int i=0;i<stringKey.length();i++) {
            currentLetter = stringKey.charAt(i);
            for(int j=0;j<alphabet.length();j++) {
                if(currentLetter == alphabet.charAt(j)) {
                    tempKeyedAlphabet = removeChar(alphabet,alphabet.charAt(j));
                    alphabet = tempKeyedAlphabet;
                }
            }
        }
        keyedAlphabet += alphabet;
        return keyedAlphabet;
    }

    /**
     * Removes any duplicates from the alphabetic key
     */
    private String removeDuplicates(String text) {
        String newText = "";
        char currentLetter;

        for(int i=0;i<text.length();i++) {
            currentLetter = text.charAt(i);
            if(!newText.contains(String.valueOf(currentLetter))) {
                newText += currentLetter;
            }
        }

        return newText;
    }

    /**
     * Removes specified chars from a letter
     */
    private String removeChar(String string, char unwantedChar) {
        StringBuilder newString = new StringBuilder();
        for(int i=0; i<string.length(); i++) {
            if(string.charAt(i) == unwantedChar) {

            } else {
                newString.append(string.charAt(i));
            }
        }

        return newString.toString();
    }
}
