/**
 * This class runs the menu for the program
 *
 * @author Lukas Jonkus
 * @version 25th May 2023
 */

import java.util.Scanner;

public class CodeBreaker {
    private Scanner input;
    private Cipher currentCipher;
    private FileManager currentFile;
    private FileManager currentKeyFile;
    private String currentPlainText;
    private String currentEncryptedText;

    /**
     * When the program is started essential objects are created
     */
    public CodeBreaker() {
        input = new Scanner(System.in);
        currentCipher = new Cipher();
        currentFile = new FileManager();
        currentKeyFile = new FileManager();
        setKeys();
        System.out.println("WELCOME TO THE CODE BREAKER");
    }

    /**
     * Runs the menu which allows the user to enter what they want to do
     */
    private void runMenu() {
        String userChoice;
        do {
            System.out.println("----------------------------------------------------------");
            printCurrentCipher();
            setKeys();
            printCurrentKey();
            printCurrentFile();
            printMenu();
            input = new Scanner(System.in);
            System.out.println("Please Choose an option above: ");
            userChoice = input.nextLine().toUpperCase();
            switch (userChoice) {
                case "1":
                    chooseCipher();
                    break;
                case "2":
                    editKey();
                    break;
                case "3":
                    enterPlainTextFile();
                    break;
                case "4":
                    displayPlainTextFile();
                    break;
                case "5":
                    encryptPlainText();
                    break;
                case "6":
                    displayEncryptedText();
                    break;
                case "7":
                    saveEncryptedTextToFile();
                    break;
                case "8":
                    enterEncryptedTextFile();
                    break;
                case "9":
                    displayEncryptedTextFile();
                    break;
                case "10":
                    decryptEncryptedTextFile();
                    break;
                case "11":
                    displayPlainText();
                    break;
                case "12":
                    saveDecryptedTextToFile();
                    break;
                case "Q":
                    break;
                default:
                    System.out.println("Please enter a valid input.");
            }
        } while (!(userChoice.equals("Q")));
    }

    /**
     * This saves the current decrypted text to a user specified text file
     */
    private void saveDecryptedTextToFile() {
        if(isFile()) {
            String userChoice;
            System.out.println("1 - Create a new file\n" +
                    "2 - write to the current file\n" +
                    "3 - write to existing file\n" +
                    "Q - Quit");
            System.out.println("Please Choose an option above: ");
            userChoice = input.nextLine().toUpperCase();
            switch (userChoice) {
                case "1":
                    System.out.println("Please Enter Name of the new file: ");
                    createNewFile();
                    currentFile.setEncryption(false);
                    currentFile.setPlainText(currentPlainText);
                    currentFile.writeToCipherFile();
                    break;
                case "2":
                    System.out.println("The current contents of the file will be changed if you proceed.\n" +
                            "Do you want to continue(Yes/No): ");
                    boolean proceed = checkForYesOrNo(input.nextLine());
                    if(proceed) {
                        currentFile.setEncryption(false);
                        currentFile.setPlainText(currentPlainText);
                        currentFile.writeToCipherFile();
                    }
                    break;
                case "3":
                    System.out.println("Please Enter Name of the existing file: ");
                    currentFile.setEncryption(false);
                    currentFile.setPlainText(currentPlainText);
                    currentFile.writeToCipherFile();
                    break;
                case "Q":
                    break;
                default:
                    System.out.println("Please enter a valid input.");
            }
        }
    }

    /**
     * This creates a new file
     */
    private void createNewFile() {
        String newFileName = input.nextLine();
        currentFile.setFilename(newFileName);
        currentFile.createNewFile();
    }
    /**
     * Displays the current plain text
     */
    private void displayPlainText() {
        if(currentPlainText != null) {
            System.out.println(currentPlainText);
        } else {
            System.out.println("There is no plain text");
        }
    }
    /**
     * This reads an encrypted text file and decrypts it
     */
    private void decryptEncryptedTextFile() {
        if(isFile()) {
            currentEncryptedText = currentFile.getEncryptedText();
            currentCipher.setEncryptedText(currentEncryptedText);
            currentCipher.decryptCipherText();
            currentPlainText = currentCipher.getPlainText();
            System.out.println("Decryption Completed");
        }
    }
    /**
     * This reads displays the contents of the encrypted text file
     */
    private void displayEncryptedTextFile() {
        if(isFile()) {
            if(currentFile.isEncrypted()) {
                System.out.println(currentFile.stringBuilder());
            } else {
                System.out.println("Your current file is not a Encrypted Text File.");
            }
        }
    }
    /**
     * Allows the user to enter an encrypted text file
     */
    private void enterEncryptedTextFile() {
        currentFile.setEncryption(true);
        System.out.println("Enter filename: ");
        String name = input.nextLine();
        currentFile.setFilename(name);
        currentFile.readFromCipherFile();
    }
    /**
     * Saves encrypted text to a user specified file
     */
    private void saveEncryptedTextToFile() {
        if(isFile()) {
            String userChoice;
            System.out.println("1 - Create a new file\n" +
                    "2 - write to the current file\n" +
                    "3 - write to existing file\n" +
                    "Q - Quit");
            System.out.println("Please Choose an option above: ");
            userChoice = input.nextLine().toUpperCase();
            switch (userChoice) {
                case "1":
                    System.out.println("Please Enter Name of the new file: ");
                    createNewFile();
                    currentFile.setEncryption(true);
                    currentFile.setEncryptedText(currentEncryptedText);
                    currentFile.writeToCipherFile();
                    break;
                case "2":
                    System.out.println("The current contents of the file will be changed if you proceed.\n" +
                            "Do you want to continue(Yes/No): ");
                    boolean proceed = checkForYesOrNo(input.nextLine());
                    if(proceed) {
                        currentFile.setEncryption(true);
                        currentFile.setEncryptedText(currentEncryptedText);
                        currentFile.writeToCipherFile();
                    }
                    break;
                case "3":
                    System.out.println("Please Enter Name of the existing file: ");
                    currentFile.setEncryption(true);
                    currentFile.setEncryptedText(currentEncryptedText);
                    currentFile.writeToCipherFile();
                    break;
                case "Q":
                    break;
                default:
                    System.out.println("Please enter a valid input.");
            }
        }
    }
    /**
     * Displays the current encrypted text
     */
    private void displayEncryptedText() {
        if(currentEncryptedText != null) {
            System.out.println(currentEncryptedText);
        } else {
            System.out.println("There is no encrypted text");
        }
    }
    /**
     * Encrypts a plaintext file
     */
    private void encryptPlainText(){
        if(isFile()) {
            currentPlainText = currentFile.getPlainText();
            currentCipher.setPlainText(currentPlainText);
            currentCipher.encryptPlainText();
            currentEncryptedText = currentCipher.getEncryptedText();
            System.out.println("Encryption Completed");
        }
    }
    /**
     * This displays the contents of a plain text file
     */
    private void displayPlainTextFile(){
        if(isFile()) {
            if(!currentFile.isEncrypted()) {
                System.out.println(currentFile.stringBuilder());
            } else {
                System.out.println("Your current file is not a Plain Text File.");
            }
        }
    }
    /**
     * This reads allows the user to enter a plain text file
     */
    private void enterPlainTextFile() {
        currentFile.setEncryption(false);
        System.out.println("Enter filename: ");
        String name = input.nextLine();
        currentFile.setFilename(name);
        currentFile.readFromCipherFile();

    }
    /**
     * Allows the user to edit the key based on the current cipher
     */
    private void editKey() {
        if(currentCipher.getCipherType().equals("Caesar")) {
            System.out.println("Enter a Numeric Key: ");
            int newKey = checkForNum();
            currentCipher.setNumericKey(newKey);
            currentKeyFile.setKeyFilename("caesar-key");
            currentKeyFile.setNumericKey(currentCipher.getNumericKey());
            currentKeyFile.writeToKeyFile();
        } else if (currentCipher.getCipherType().equals("Keyed")) {
            System.out.println("Enter a Phrase Key (Note anything that is not a letter will be removed): ");
            String newPhraseKey = input.nextLine();
            currentCipher.setAlphabeticKey(newPhraseKey);
            System.out.println("Enter a Numeric Key: ");
            int newKey = checkForNum();
            currentCipher.setNumericKey(newKey);
            currentKeyFile.setKeyFilename("keyed-caesar-key");
            currentKeyFile.setNumericKey(currentCipher.getNumericKey());
            currentKeyFile.setAlphabeticKey(currentCipher.getAlphabeticKey());
            currentKeyFile.writeToKeyFile();
        } else if (currentCipher.getCipherType().equals("Vigenere")) {
            System.out.println("Enter a Phrase Key (Note anything that is not a letter will be removed): ");
            String newPhraseKey = input.nextLine();
            currentCipher.setAlphabeticKey(newPhraseKey);
            currentKeyFile.setKeyFilename("vigenere-key");
            currentKeyFile.setAlphabeticKey(currentCipher.getAlphabeticKey());
            currentKeyFile.writeToKeyFile();
        }
    }

    /**
     * Makes sure if the user has entered nothing when asked for a file
     * an appropriate error message is shown
     */
    private boolean isFile() {
        if(currentFile.getFilename().equals("No File")) {
            System.out.println("No file has been entered.\n" +
                    "Please Enter a file.");
            return false;
        }
        return true;
    }
    /**
     * The user can choose what cipher they want to use
     */
    private void chooseCipher() {
        System.out.print("Please Choose a Number: \n" +
                "1 - Caesar Cipher\n" +
                "2 - Keyed Caesar Cipher\n" +
                "3 - Vigenere Cipher\n");

        String choosenCipher = checkCipher(input.nextLine());

        if(choosenCipher.equals("Caesar")) {
            currentCipher.setCipherType("Caesar");
        } else if (choosenCipher.equals("Keyed")) {
            currentCipher.setCipherType("Keyed");
        } else if (choosenCipher.equals("Vigenere")) {
            currentCipher.setCipherType("Vigenere");
        }
    }
    /**
     * Prints the name of the current file in use
     */
    private void printCurrentFile() {
        System.out.print("Current File: ");
        System.out.println(currentFile.getFilename());
    }
    /**
     * Prints the name of the current cipher in use
     */
    private void printCurrentCipher() {
        System.out.print("Current Cipher: ");
        System.out.println(currentCipher.getCipherType());
    }
    /**
     * Prints the current keys in use
     */
    private void printCurrentKey() {
        if(currentCipher.getCipherType().equals("Caesar")) {
            System.out.print("Current Numeric Key: ");
            System.out.println(currentCipher.getNumericKey());
        } else if(currentCipher.getCipherType().equals("Keyed")) {
            System.out.print("Current Numeric Key: ");
            System.out.println(currentCipher.getNumericKey());
            System.out.print("Current Phrase Key: ");
            System.out.println(currentCipher.getAlphabeticKey());
        } else {
            System.out.print("Current Phrase Key: ");
            System.out.println(currentCipher.getAlphabeticKey());
        }
    }

    /**
     * When the user starts up the program this gets the key information from the 3
     * key files
     */
    private void setKeys() {
        if(currentCipher.getCipherType().equals("Caesar")) {
            currentKeyFile.setKeyFilename("caesar-key");
            currentKeyFile.readFromKeyFile();
            int numKey = currentKeyFile.getNumericKey();
            currentCipher.setNumericKey(numKey);
        } else if (currentCipher.getCipherType().equals("Keyed")) {
            currentKeyFile.setKeyFilename("keyed-caesar-key");
            currentKeyFile.readFromKeyFile();
            int numKey = currentKeyFile.getNumericKey();
            currentCipher.setNumericKey(numKey);
            String alphabeticKey = currentKeyFile.getAlphabeticKey();
            currentCipher.setAlphabeticKey(alphabeticKey);
        } else if (currentCipher.getCipherType().equals("Vigenere")) {
            currentKeyFile.setKeyFilename("vigenere-key");
            currentKeyFile.readFromKeyFile();
            String alphabeticKey = currentKeyFile.getAlphabeticKey();
            currentCipher.setAlphabeticKey(alphabeticKey);
        }
    }
    /**
     * Checks if the user has entered a number
     */
    private int checkForNum() {
        boolean isNumber = false;
        int number = 0;

        while (!isNumber) {
            if(input.hasNextInt()) {
                number = input.nextInt();
                isNumber = true;
            } else {
                System.out.println("Please enter a number: ");
                input.nextLine();
            }
        }

        return number;
    }
    /**
     * checks if the users has entered yes or no
     */
    public boolean checkForYesOrNo(String yesOrNo) {
        Scanner input = new Scanner(System.in);
        boolean trueOrFalse = false;

        while(!(yesOrNo.equalsIgnoreCase("yes") || (yesOrNo.equalsIgnoreCase("no")))) {
            System.out.println("ERROR: Yes or No not entered.\nEnter Yes or No: ");
            yesOrNo = input.next();
        }

        if(yesOrNo.equalsIgnoreCase("yes")) {
            trueOrFalse = true;
        } else if (yesOrNo.equalsIgnoreCase("no")) {
            trueOrFalse = false;
        }
        return trueOrFalse;
    }
    /**
     * Makes sure the user enters the right choice when choosing a cipher
     */
    private String checkCipher(String choice) {
        String cipher = currentCipher.getCipherType();
        switch (choice) {
            case "1":
                cipher = "Caesar";
                break;
            case "2":
                cipher = "Keyed";
                break;
            case "3":
                cipher = "Vigenere";
                break;
            default:
                System.out.println("Please enter a number from 1-3.");
        }
        return cipher;
    }
    /**
     * Prints the menu options
     */
    private void printMenu() {
        System.out.println("----------------------------------------------------------\n" +
                "1 - Choose a Cipher to use. \n" +
                "2 - Edit the key. \n" +
                "3 - Enter plain text file. \n" +
                "4 - Display plain text file. \n" +
                "5 - Encrypt plain text with current cipher and key. \n" +
                "6 - Display encrypted text. \n" +
                "7 - Save encrypted text to a file\n" +
                "8 - Enter encrypted text file. \n" +
                "9 - Display encrypted text file. \n" +
                "10 - Decrypt encrypted text file to plain text. \n" +
                "11 - Display plain text. \n" +
                "12 - Save decrypted text to a file. \n" +
                "Q - Quit the program\n" +
                "----------------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CodeBreaker app = new CodeBreaker();
        app.runMenu();
    }
}