/**
 * This class is the child class of cipher
 *
 * @author Lukas Jonkus
 * @version 25th May 2023
 */

public class VigenereCipher extends Cipher{

    /**
     * The constructor
     */
    public VigenereCipher() {
    }

    /**
     * Specifies what a Vigenere cipher is, it inherits plain
     * and alphabetic key from cipher
     */
    public VigenereCipher(String givenPlainText, String givenEncryptedText,  String givenAlphabeticKey) {
        super.plainText = givenPlainText;
        super.encryptedText = givenEncryptedText;
        super.alphabeticKey = formatVigenereKey(plainText,givenAlphabeticKey);
    }

    /**
     * Encrypts using vigenere cipher
     */
    public void vigenereCipherEncryption() {
        encryptedText = vigenereShifts(plainText,alphabeticKey);
    }

    /**
     * Decrypts using vigenere cipher
     */
    public void vigenereCipherDecryption() {
        StringBuilder plainTextBuilder = new StringBuilder();

        String shiftedAlphabet;
        char currentLetter;
        int shift;

        for(int i=0;i<encryptedText.length();i++) {
            currentLetter = encryptedText.charAt(i);
            shift = alphabeticKey.charAt(i) - 65;
            shift = 26 - shift;
            shiftedAlphabet = shiftAlphabet(ALPHABET,shift);
            plainTextBuilder.append(shiftedAlphabet.charAt(currentLetter - 65));
        }

       plainText = plainTextBuilder.toString();

    }

    /**
     * Creates multiple shifts in order to allow us to encrypt using vigenere cipher
     */
    private String vigenereShifts(String plainText, String key) {
        StringBuilder encryptedText = new StringBuilder();
        String shiftedAlphabet;
        char currentLetter;
        int shift;

        for(int i=0;i<plainText.length();i++) {
            currentLetter = plainText.charAt(i);
            shift = key.charAt(i) - 65;
            shiftedAlphabet = shiftAlphabet(ALPHABET,shift);
            encryptedText.append(shiftedAlphabet.charAt(currentLetter - 65));
        }

        return encryptedText.toString();
    }

    /**
     * Formats the vigenre key so there are no duplicates in it
     */
    private String formatVigenereKey(String text, String key) {
        StringBuilder newKey = new StringBuilder();
        int index = 0;

        for(int i=0;i<text.length();i++) {
            if(index < key.length()) {
                newKey.append(key.charAt(index));
            } else {
                newKey.append(key.charAt(0));
                index = 0;
            }
            index++;
        }

        return newKey.toString();
    }
}
