/**
 * This class reads from and writes to specified files
 *
 * @author Lukas Jonkus
 * @version 25th May 2023
 */

import java.io.*;
import java.util.Scanner;

public class FileManager {
    private File currentFile;
    private String filename;
    private String keyFilename;
    private boolean toBeEncrypted;
    private String plainText;
    private String encryptedText;
    private int numericKey;
    private String alphabeticKey;

    /**
     * The constructor automatically has set to no file
     */
    public FileManager() {
        filename = "No File";
    }
    /**
     * This reads from a file that has plain text or encrypted text inside of it
     */
    public void readFromCipherFile() {
        try {
            FileReader fileInput = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileInput);
            Scanner dataFromFile = new Scanner(bufferedReader);
            dataFromFile.useDelimiter("\r?\n|\r");
            while (dataFromFile.hasNextLine()) {
                if(toBeEncrypted) {
                    encryptedText = dataFromFile.nextLine();
                } else {
                    plainText = dataFromFile.nextLine();
                }
            }
            dataFromFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
            filename = "No File";
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
    /**
     * This writes plain or encrypted text to a file
     */
    public void writeToCipherFile() {
        try {
            FileWriter fileOutput = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileOutput);
            PrintWriter dataToFile = new PrintWriter(bufferedWriter);
            if(toBeEncrypted) {
                dataToFile.println(encryptedText);
            } else {
                dataToFile.println(plainText);
            }
            dataToFile.close();
            System.out.println("Successfully wrote to the file.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
    /**
     * This reads from a file that has key information inside of it
     */
    public void readFromKeyFile() {
        try {
            FileReader fileInput = new FileReader(keyFilename);
            BufferedReader bufferedReader = new BufferedReader(fileInput);
            Scanner dataFromFile = new Scanner(bufferedReader);
            dataFromFile.useDelimiter("\r?\n|\r");
            while (dataFromFile.hasNextLine()) {
                if(keyFilename.equals("caesar-key")) {
                    numericKey = Integer.parseInt(dataFromFile.nextLine());
                } else if (keyFilename.equals("keyed-caesar-key")) {
                    numericKey = Integer.parseInt(dataFromFile.nextLine());
                    alphabeticKey = dataFromFile.nextLine();
                } else if (keyFilename.equals("vigenere-key")) {
                    alphabeticKey = dataFromFile.nextLine();
                }
            }
            dataFromFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
            filename = "No File";
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
    /**
     * This writes key information to a file
     */
    public void writeToKeyFile() {
        try {
            FileWriter fileOutput = new FileWriter(keyFilename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileOutput);
            PrintWriter dataToFile = new PrintWriter(bufferedWriter);
                if(keyFilename.equals("caesar-key")) {
                    dataToFile.println(numericKey);
                } else if (keyFilename.equals("keyed-caesar-key")) {
                    dataToFile.println(numericKey);
                    dataToFile.println(alphabeticKey);
                } else if (keyFilename.equals("vigenere-key")) {
                    dataToFile.println(alphabeticKey);
                }
            dataToFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
    /**
     * This creates a new file
     */
    public void createNewFile(){
        try {
            currentFile = new File(filename);
            if (currentFile.createNewFile()) {
                System.out.println("File created: " + currentFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /**
     * Formats text into prepared text
     */
    private String formatText(String text) {
        return text.replaceAll("[^\\p{IsAlphabetic}]", "").toUpperCase();
    }
    /**
     * Sets the encrypted text
     */
    public void setEncryptedText(String givenEncryptedText) {
        encryptedText = givenEncryptedText;
    }
    /**
     * Gets the encrypted text
     */
    public String getEncryptedText() {
        return encryptedText;
    }
    /**
     * Sets the plain text
     */
    public void setPlainText(String givenPlainText) {
        plainText = givenPlainText;
    }
    /**
     * Gets the plain text
     */
    public String getPlainText() {
        return plainText;
    }
    /**
     * Gets the filename
     */
    public String getFilename() {
        return filename;
    }
    /**
     * Sets the filename
     */
    public void setFilename(String givenFileName) {
        filename = givenFileName;
    }
    /**
     * Sets the key filename
     */
    public void setKeyFilename(String givenFileName) {
        keyFilename = givenFileName;
    }
    /**
     * Sets if the text is going to be encrypted
     */
    public void setEncryption(boolean givenToBeEncrypted) {
        toBeEncrypted = givenToBeEncrypted;
    }
    /**
     * Sets the alphabetic key
     */
    public void setAlphabeticKey(String givenAlphabeticKey) {
        alphabeticKey = givenAlphabeticKey;
    }
    /**
     * Gets the alphabetic key
     */
    public String getAlphabeticKey() {
        return alphabeticKey;
    }

    /**
     * Sets the numeric key
     */
    public void setNumericKey(int givenNumericKey) {
        numericKey = givenNumericKey;
    }
    /**
     * Gets the numeric key
     */
    public int getNumericKey() {
        return numericKey;
    }
    /**
     * Gets if the file is going to be encrypted
     */
    public boolean isEncrypted() {
        return toBeEncrypted;
    }
    /**
     * StringBuilder in order to print the contents of the current file
     */
    public StringBuilder stringBuilder() {
        StringBuilder contentsOfFile = new StringBuilder();
        contentsOfFile.append("Contents of file ");
        contentsOfFile.append(filename);
        if(toBeEncrypted) {
            contentsOfFile.append("\nEncrypted Text: ");
            contentsOfFile.append(formatText(encryptedText));
        } else {
            contentsOfFile.append("\nPlain Text: ");
            contentsOfFile.append(formatText(plainText));
        }
        return contentsOfFile;
    }
}
