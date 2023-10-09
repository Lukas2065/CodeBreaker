/**
 * This class is the child class of cipher
 *
 * @author Lukas Jonkus
 * @version 25th May 2023
 */

public class CaeserCipher extends Cipher{

    /**
     * The constructor
     */
    public CaeserCipher() {
    }

    /**
     * Specifies what a caesar cipher is, it inherits plain, encrypted and a numeric key from cipher
     */
    public CaeserCipher(String givenPlainText, String givenEncryptedText,  int givenNumericKey) {
        super.plainText = givenPlainText;
        super.encryptedText = givenEncryptedText;
        super.numericKey = givenNumericKey;
    }

    /**
     * Encrypts using caesar cipher
     */
    public void caesarCipherDecryption() {
        String shiftedAlphabet = shiftAlphabet(ALPHABET,numericKey);
        plainText = convertToDifferentAlphabet(shiftedAlphabet, ALPHABET, encryptedText);
    }
    /**
     * Decrypts using caesar cipher
     */
    public void caesarCipherEncryption() {
        String shiftedAlphabet = shiftAlphabet(ALPHABET,numericKey);
        encryptedText = convertToDifferentAlphabet(ALPHABET, shiftedAlphabet, plainText);
    }

}
