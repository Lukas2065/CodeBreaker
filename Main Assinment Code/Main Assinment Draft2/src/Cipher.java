/**
 * This class is the parent class of all ciphers
 *
 * ALPHABET is the normal alphabet in all capitals
 *
 * @author Lukas Jonkus
 * @version 25th May 2023
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Cipher {
    public String plainText;
    public String encryptedText;
    public String cipherType;
    public int numericKey;
    public String alphabeticKey;
    public static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * The constructor automatically has set to Caesar
     */
    public Cipher() {
        this("","","Caesar",
                0,"");
    }

    /**
     * The constructor for a cipher
     */
    public Cipher(String givenPlainText, String givenEncryptedText, String givenCipherType, int givenNumericKey, String givenAlphabeticKey) {
        plainText = formatText(givenPlainText);
        encryptedText = formatText(givenEncryptedText);
        cipherType = givenCipherType;
        numericKey = givenNumericKey;
        alphabeticKey = formatText(givenAlphabeticKey);
    }

    /**
     * Decrypts cipher text into plain text
     */
    public void decryptCipherText() {
        if(cipherType.equals("Caesar")) {
            CaeserCipher newCipher = new CaeserCipher(plainText,encryptedText,numericKey);
            newCipher.caesarCipherDecryption();
            plainText = newCipher.getPlainText();
        } else if (cipherType.equals("Keyed")) {
            KeyedCipher newCipher = new KeyedCipher(plainText,encryptedText,alphabeticKey,numericKey);
            newCipher.keyedCaesarCipherDecryption();
            plainText = newCipher.getPlainText();
        } else if (cipherType.equals("Vigenere")) {
            VigenereCipher newCipher = new VigenereCipher(plainText,encryptedText,alphabeticKey);
            newCipher.vigenereCipherDecryption();
            plainText = newCipher.getPlainText();
        }
    }

    /**
     * Encrypts plain text into cipher text
     */
    public void encryptPlainText() {
        if(cipherType.equals("Caesar")) {
            CaeserCipher newCipher = new CaeserCipher(plainText,encryptedText,numericKey);
            newCipher.caesarCipherEncryption();
            encryptedText = newCipher.getEncryptedText();
        } else if (cipherType.equals("Keyed")) {
            KeyedCipher newCipher = new KeyedCipher(plainText,encryptedText,alphabeticKey,numericKey);
            newCipher.keyedCaesarCipherEncryption();
            encryptedText = newCipher.getEncryptedText();
        } else if (cipherType.equals("Vigenere")) {
            VigenereCipher newCipher = new VigenereCipher(plainText,encryptedText,alphabeticKey);
            newCipher.vigenereCipherEncryption();
            encryptedText = newCipher.getEncryptedText();
        }
    }

    /**
     * This allows you to encrypt a message from one alphabet to another
     */
    public String convertToDifferentAlphabet(String alphabetFrom, String alphabetTo, String text) {
        char currentLetter;
        StringBuilder finalText = new StringBuilder();
        for(int i=0;i<text.length();i++) {
            currentLetter = text.charAt(i);
            for(int j=0;j<alphabetTo.length();j++) {
                if(currentLetter == alphabetFrom.charAt(j)) {
                    finalText.append(alphabetTo.charAt(j));
                }
            }
        }
        return finalText.toString();
    }

    /**
     * This allows you to shift an alphabet based on a number
     */
    public String shiftAlphabet(String alphabet, int key) {
        StringBuilder secondHalf = new StringBuilder();
        StringBuilder firstHalf = new StringBuilder();
        StringBuilder shiftedAlphabet = new StringBuilder();


        for(int i=0;i<alphabet.length();i++) {
            if(i+key < alphabet.length()) {
                firstHalf.append(alphabet.charAt(i+key));
            } else {
                secondHalf.append(alphabet.charAt((i + key) % 26));
            }
        }

        shiftedAlphabet.append(firstHalf);
        shiftedAlphabet.append(secondHalf);
        return shiftedAlphabet.toString();
    }

    /**
     * Formats text into prepaired text
     */
    private String formatText(String text) {
        return text.replaceAll("[^\\p{IsAlphabetic}]", "").toUpperCase();
    }
    /**
     * Sets the alphabetic Key
     */
    public void setAlphabeticKey(String givenAlphabeticKey) {
        alphabeticKey = formatText(givenAlphabeticKey);
    }
    /**
     * Gets the alphabetic Key
     */
    public String getAlphabeticKey() {
        return formatText(alphabeticKey);
    }
    /**
     * Sets the numeric key
     */
    public void setNumericKey(int givenNumericKey) {
        numericKey = givenNumericKey;
    }
    /**
     * gets the numeric key
     */
    public int getNumericKey() {
        return numericKey;
    }
    /**
     * Sets the type of cipher in use
     */
    public void setCipherType(String givenCipher) {
        cipherType = givenCipher;
    }
    /**
     * Gets the type of cipher in use
     */
    public String getCipherType() {
        return cipherType;
    }
    /**
     * Sets the encrypted text
     */
    public void setEncryptedText(String givenEncryptedText) {
        encryptedText = formatText(givenEncryptedText);
    }
    /**
     * Gets the encrypted text
     */
    public String getEncryptedText() {
        return formatText(encryptedText);
    }
    /**
     * Sets the Plain text
     */
    public void setPlainText(String givenPlainText) {
        plainText = formatText(givenPlainText);
    }
    /**
     * Gets the Plain text
     */
    public String getPlainText() {
        return formatText(plainText);
    }
}
