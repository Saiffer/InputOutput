package com.hcl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {

        try{
            printWelcomeMessage();
        }catch(InputMismatchException e) {
            System.out.println("Wrong input");
            printWelcomeMessage();
        }catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    //Menu
    public static  void printWelcomeMessage() throws IOException {
        System.out.println("***********************************************");
        System.out.println("Welcome, please check what would you like to do");
        System.out.println("***********************************************");
        System.out.println("1. Get the directories and file names");
        System.out.println("2. Get files with certain extension");
        System.out.println("3. Count lines in a file");


        //User choice of program flow
        Scanner in = new Scanner(System.in);
        int userInputNum = in.nextInt();


        if (userInputNum == 1) {
            userInputChoice(userInputNum);
        }else if(userInputNum == 2) {
            userInputChoice(userInputNum);
        }else if(userInputNum == 3) {
            userInputChoice(userInputNum);
        }
    }

    //Provide choice from user input. Invoke specific Function
    public static void userInputChoice(int input) throws IOException {
        Scanner in = new Scanner(System.in);
        switch (input) {

            case 1:

                System.out.println("*********************");
                System.out.println("Please enter the path");
                System.out.println("*********************");
                String userInputPath = in.nextLine();
                try{
                    printFoldersAndFiles(userInputPath);
                }catch(NullPointerException e) {
                    System.out.println("Wrong path");
                    printWelcomeMessage();
                }

                break;
            case 2:
                Scanner inExt = new Scanner(System.in);
                System.out.println("*********************");
                System.out.println("Please enter the path");
                System.out.println("*********************");
                String userInputPathWithExt = inExt.nextLine();
                System.out.println("*********************");
                System.out.println("Please enter the extension");
                System.out.println("*********************");
                String extension = inExt.nextLine();
                try{
                    printFoldersAndFiles(userInputPathWithExt, extension);
                }catch(NullPointerException e){
                    System.out.println("Wrong path");
                    printWelcomeMessage();
                }

                break;
            case 3:
                Scanner fileName = new Scanner(System.in);
                System.out.println("**************************");
                System.out.println("Please enter the file name");
                System.out.println("**************************");
                String userInputFileName = fileName.nextLine();
                try{
                    countLinesInFile(userInputFileName);
                }catch(NullPointerException e) {
                    System.out.println("File does not exist");
                    printWelcomeMessage();
                }
                break;
            case 4:
                System.out.println("**************************");
                System.out.println("Please enter the file name");
                System.out.println("**************************");
                String providedFileName = in.nextLine();

            default:
                break;

        }
    }

    //Prints all folders, subfolders and files
    public static void printFoldersAndFiles(String path) throws IOException {

        File folder = new File(path);

        File[] files = folder.listFiles();
        System.out.println(files);
        for(File file : files) {
            if(file.isFile()){
                System.out.println(file.getName());
            }else if(file.isDirectory()) {
                System.out.println(file.getName());
                printFoldersAndFiles(file.getAbsolutePath());
            }
        }
    }


    //Prints files with user defined extension
    public static void printFoldersAndFiles(String path, String ext) {

        File folder = new File(path);

        File[] files = folder.listFiles();

        for(File file : files) {
            if(file.isFile()){
                if(file.getName().endsWith(ext)) {
                    System.out.println(file.getName());
                }
            }else if(file.isDirectory()) {
                //System.out.println(file.getName().endsWith(ext));
                printFoldersAndFiles(file.getAbsolutePath(), ext);
            }
        }
    }

    public static boolean ifFileExists(String path, String name) {
        File folder = new File(path);

        File[] files = folder.listFiles();

        for(File file : files) {
            if(file.isFile()){
                if(file.getName().startsWith(name)) {

                    System.out.println(file.getName() + " is in the folder");
                }else {
                    System.out.println("There is no such file in the folder");
                }
            }else if(file.isDirectory()) {
                //System.out.println(file.getName().endsWith(ext));
                printFoldersAndFiles(file.getAbsolutePath(), name);
            }
        }
    }

    //Counts lines number in a file provided through the path
    public static void countLinesInFile(String fileName) {
        File file = new File(fileName);

        long lines = 0;

        try(LineNumberReader line = new LineNumberReader((new FileReader(file)))) {
            while(line.readLine() != null);

            lines = line.getLineNumber();
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("Provided file - "+ fileName + " has: " +lines);
    }
}



