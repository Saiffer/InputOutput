package com.hcl;

import java.io.File;
import java.io.IOException;
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


        //User choice of program flow
        Scanner in = new Scanner(System.in);
        int userInputNum = in.nextInt();


            if (userInputNum == 1) {
                userInputChoice(userInputNum);
            }else if(userInputNum == 2) {
                userInputChoice(userInputNum);
            }
        }

    //Provide choice from user input. Invoke specific Function
    public static void userInputChoice(int input) throws IOException {
        switch (input) {

            case 1:
                Scanner in = new Scanner(System.in);
                System.out.println("*********************");
                System.out.println("Please enter the path");
                System.out.println("*********************");
                String userInputPath = in.nextLine();

                printFoldersAndFiles(userInputPath);
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
                printFoldersAndFiles(userInputPathWithExt, extension);
                break;
            default:
                break;

        }
    }

    //Prints all folders, subfolders and files
    public static void printFoldersAndFiles(String path) {
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
}



