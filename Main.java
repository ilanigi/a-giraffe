package com.company;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * this is a main class of max min heap generator.
 * @author Gilad Ilani
 * @version 19/05/20
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath;
        int ans1;
        int ans2;
        int ans3;
        int ans4;
        int ans5;
        //     File file = new File();
        System.out.println("Welcome to Max-Min-Heap generator!");
        System.out.println("to make a new heap, press 1.");
        Scanner scan = new Scanner(System.in);
        ans1 = scan.nextInt();
        while (ans1 != 1) {
            System.out.println("error, try again.");
            ans1 = (int) scan.nextDouble();
        }
        maxMinTree tree1 = new maxMinTree();
        System.out.println("new max min heap has constructed.");
        System.out.println("great. now we are ready to go.");
        ans2 = -1;
        while (ans2 != 0) {
            System.out.println("Please choose next action:");
            System.out.println("for Heap-Insert,        enter 1");
            System.out.println("to scan from File       enter 2");
            System.out.println("for Build-Heap,         enter 3");
            System.out.println("for Heap-Extract-Max,   enter 4");
            System.out.println("for Heap-Extract-Min,   enter 5");
            System.out.println("for Heapify             enter 6");
            System.out.println("for Heap-Delete         enter 7");
            System.out.println("to sort,                enter 8");
            System.out.println("to print the Heap,      enter 9");
            System.out.println("to quit,                enter 0");
            System.out.println("enter answer now:");
            ans2 = scan.nextInt();
            switch (ans2) {
                case 1 -> {
                    System.out.println("please enter value to insert:");
                    ans4 = scan.nextInt();
                    tree1.heapInsert(ans4);
                    System.out.println("value inserted.");
                }
                case 2 -> {
                    System.out.println("please enter file path:");
                    filePath =  scan.next();
                    tree1.addFromFile(filePath);
                    System.out.println("tree added from file");
                }
                case 3 -> {
                    tree1.buildHeap();
                    System.out.println("max min tree built");
                }

                case 4 -> System.out.println("max is: " + tree1.extractMax());
                case 5 -> System.out.println("min is : " + tree1.extractMin());
                case 6 -> {
                    System.out.println("please enter index to Heapify:");
                    ans3 = scan.nextInt();
                    tree1.heapify(ans3);
                    System.out.println("index heapified.");
                }
                case 7 -> {
                    System.out.println("please enter index to delete:");
                    ans5 = scan.nextInt();
                    tree1.heapDelete(ans5);
                    System.out.println("index deleted.");
                }
                case 8 -> System.out.println("sorted array is \n" + Arrays.toString(tree1.sort()));
                case 9 -> System.out.println(tree1);
            }//end of switch
        }//end of while
        System.out.println("bye bye!");
    }//end of main
}//end of class
