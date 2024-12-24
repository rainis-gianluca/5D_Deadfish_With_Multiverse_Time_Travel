/* 5D Deadfish with Multiverse Time Travel sintax created by: Gianluca Rainis
 * 5D Deadfish with Multiverse Time Travel interpreter created by: Gianluca Rainis
 * This code is free to use, modify and distribute
 * To have more information about the sintax and the interpreter go to the GitHub repository: https://github.com/rainis-gianluca/5D_Deadfish_With_Multiverse_Time_Travel.git
 * This version of the interpreter is written in Java
 * See the GitHub repository for the other versions of the interpreter
 * The .vscode folder is used to store the settings of the Visual Studio Code editor, you can delete it if you don't use Visual Studio Code
 * The comments in the code are written with the help of GitHub Copilot
 * Last update: 24 December 2024
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class App {
    private static ArrayList<ArrayList<Long>> universesAndTimelines = new ArrayList<>(); //[numberUniverses][numberTimelines]
    private static int currentUniverse = 0, currentTimeline = 0, numberUniversesUsed = 1; //The first universe is the 0
    private static ArrayList<Long> numberTimeLinesUsed = new ArrayList<>(); //An array with the number of timelines used in each universe
    private static boolean controlComment = false; //If is true the interpreter is in a comment

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        BufferedReader bufferedReader = null; //The buffer that will read the file
        String filename = ""; //The name of the file that will be analyzed
        boolean error = false; //If is true the interpreter will stop
        char character; //The character that will be analyzed

        numberTimeLinesUsed.add(0, (long)1); //Inizialize the number of timelines in the first universe to 1
        universesAndTimelines.add(0, new ArrayList<Long>(0)); //Add the first universe
        universesAndTimelines.get(currentUniverse).add(currentTimeline, (long)0); //Add the first timeline in the first universe

        try { //Try to get the file location
            System.out.println("Insert the file location: ");
            filename = in.nextLine();
        } catch (Exception e) {
            System.err.println("Error: invalid input!");
            error = true;
        }

        if (!error) {
            try { //Try to open the file
                bufferedReader = new BufferedReader(new FileReader(filename));
            } catch (Exception e) {
                System.err.println("Error in the opening of the file!");
                error = true;
            }
        }
        
        if (!error) {
            try {
                while (bufferedReader.ready() && !error) { //While the file has characters read them and analyze them
                    character = (char) bufferedReader.read();
                    
                    try {
                        controlChar(character); //Analyze the character
                    } catch (Exception e) {
                        System.err.println("Error in the analyses of the character: "+e);
                        error = true;
                    }
                }
            } catch (Exception e) {
                System.err.println("ERROR: " + e);
            }
            finally {
                bufferedReader.close();
            }
        }

        in.close();
    }

    public static void controlChar(char character) throws Exception { //Control the character
        long temp = 0; //The temporal variable that will be used in the operations

        if (controlComment) { //If the interpreter is in a comment
            if (character == '\n') { //If the character is a new line the comment ends
                controlComment = false;
            }
        }
        else { //If the interpreter isn't in a comment
            mainSwitch(temp, character); //Analyze the character
        }
    }

    public static void mainSwitch(long temp, char character) throws Exception { //Analyze the character
        switch (character) {
            case 'i': //Increment the current timeline value in the current universe
                temp = universesAndTimelines.get(currentUniverse).get(currentTimeline);
                temp++;
                universesAndTimelines.get(currentUniverse).add(currentTimeline, temp);
                numberTimeLinesUsed.set(currentUniverse, numberTimeLinesUsed.get(currentUniverse)+(long)1); //++  at the number of timelines in the current universe
                break;

            case 'I': //Increment the current timeline value in all universes
                for (int i = 0; i < numberUniversesUsed; i++) {
                    temp = universesAndTimelines.get(i).get(currentTimeline);
                    temp++;
                    universesAndTimelines.get(i).add(currentTimeline, temp);
                    numberTimeLinesUsed.set(i, numberTimeLinesUsed.get(i)+(long)1);
                }
                break;

            case 'd': //Decrement the current timeline value in the current universe
                temp = universesAndTimelines.get(currentUniverse).get(currentTimeline);
                temp--;
                universesAndTimelines.get(currentUniverse).add(currentTimeline, temp);
                numberTimeLinesUsed.set(currentUniverse, numberTimeLinesUsed.get(currentUniverse)+(long)1);
                break;

            case 'D': //Decrement the current timeline value in all universes
                for (int i = 0; i < numberUniversesUsed; i++) {
                    temp = universesAndTimelines.get(i).get(currentTimeline);
                    temp--;
                    universesAndTimelines.get(i).add(currentTimeline, temp);
                    numberTimeLinesUsed.set(i, numberTimeLinesUsed.get(i)+(long)1);
                }
                break;

            case 's': //Square the current timeline value in the current universe
                temp = universesAndTimelines.get(currentUniverse).get(currentTimeline);
                temp *= temp;
                universesAndTimelines.get(currentUniverse).add(currentTimeline, temp);
                numberTimeLinesUsed.set(currentUniverse, numberTimeLinesUsed.get(currentUniverse)+(long)1);
                break;

            case 'S': //Square the current timeline value in all universes
                for (int i = 0; i < numberUniversesUsed; i++) {
                    temp = universesAndTimelines.get(i).get(currentTimeline);
                    temp *= temp;
                    universesAndTimelines.get(i).add(currentTimeline, temp);
                    numberTimeLinesUsed.set(i, numberTimeLinesUsed.get(i)+(long)1);
                }
                break;

            case 'r': //Square root the current timeline value in the current universe
                temp = universesAndTimelines.get(currentUniverse).get(currentTimeline);

                if (temp >= 0) {
                    temp = (long)(Math.sqrt(temp));
                } else {
                    throw new Exception("Cannot take the square root of a negative number.");
                }

                universesAndTimelines.get(currentUniverse).add(currentTimeline, temp);
                numberTimeLinesUsed.set(currentUniverse, numberTimeLinesUsed.get(currentUniverse)+(long)1);
                break;

            case 'R': //Square root the current timeline value in all universes
                for (int i = 0; i < numberUniversesUsed; i++) {
                    temp = universesAndTimelines.get(i).get(currentTimeline);

                    if (temp >= 0) {
                        temp = (long)(Math.sqrt(temp));
                    } else {
                        throw new Exception("Cannot take the square root of a negative number.");
                    }

                    universesAndTimelines.get(i).add(currentTimeline, temp);
                    numberTimeLinesUsed.set(i, numberTimeLinesUsed.get(i)+(long)1);
                }
                break;

            case '0': //Set the current timeline value to 0 in the current universe
                temp = (long)0;
                universesAndTimelines.get(currentUniverse).add(currentTimeline, temp);
                numberTimeLinesUsed.set(currentUniverse, numberTimeLinesUsed.get(currentUniverse)+(long)1);
                break;

            case 'o': //Output the current timeline value in the current universe
                System.out.print(universesAndTimelines.get(currentUniverse).get(currentTimeline));
                break;

            case 'O': //Output the current timeline value in all universes
                for (int i = 0; i < numberUniversesUsed; i++) {
                    temp = universesAndTimelines.get(i).get(currentTimeline);
                    System.out.print(temp);
                }
                break;

            case 'u': //Output the current timeline value in the current universe as a character
                if (universesAndTimelines.get(currentUniverse).get(currentTimeline) >= 32 && universesAndTimelines.get(currentUniverse).get(currentTimeline) < 127) {
                    System.out.print((char)Integer.parseInt(universesAndTimelines.get(currentUniverse).get(currentTimeline).toString())); //Print the character
                } else if(universesAndTimelines.get(currentUniverse).get(currentTimeline) < 0 || universesAndTimelines.get(currentUniverse).get(currentTimeline) > 127) {
                    System.out.print('?'); // Print '?' if the value is out of the ASCII range
                }
                break;

            case 'U': //Output the current timeline value in all universes as a character
                for (int i = 0; i < numberUniversesUsed; i++) {
                    if (universesAndTimelines.get(i).get(currentTimeline) >= 32 && universesAndTimelines.get(i).get(currentTimeline) < 127) {
                        System.out.print((char)Integer.parseInt(universesAndTimelines.get(i).get(currentTimeline).toString())); //Print the character
                    } else if(universesAndTimelines.get(i).get(currentTimeline) < 0 || universesAndTimelines.get(i).get(currentTimeline) > 127) {
                        System.out.print('?'); // Print '?' if the value is out of the ASCII range
                    }
                }
                break;

            case 'G': //Print the logical scheme of the universes and timelines
                printLogicalScheme();
                break;

            case '/': //Start a comment
                controlComment = true;
                break;

            case '<': //Go to the previous timeline
                if ((long) currentTimeline == numberTimeLinesUsed.get(currentUniverse)-1) { //If the timeline doesn't exist create it
                    numberTimeLinesUsed.set(currentUniverse, numberTimeLinesUsed.get(currentUniverse)+(long)1);
                    universesAndTimelines.get(currentUniverse).add(currentTimeline++, (long)0);
                }
                else {
                    currentTimeline++;
                }
                break;

            case '>': //Go to the next timeline
                if (currentTimeline != 0) {
                    currentTimeline--;
                }
                else { //If the timeline doesn't exist create it
                    numberTimeLinesUsed.set(currentUniverse, numberTimeLinesUsed.get(currentUniverse)+(long)1);
                    universesAndTimelines.get(currentUniverse).add(0, (long)0);
                }
                break;

            case '[': //Go to the next universe
                if (currentUniverse == numberUniversesUsed-1) { //If the universe doesn't exist create it
                    numberUniversesUsed++;
                    currentUniverse++;
                    numberTimeLinesUsed.add((long)1);
                    currentTimeline = 0;
                    
                    universesAndTimelines.add(new ArrayList<Long>());
                    universesAndTimelines.get(currentUniverse).add(0, (long)0);
                }
                else {
                    currentUniverse++;
                    currentTimeline = 0;
                }
                break;

            case ']': //Go to the previous universe
                if (currentUniverse != 0) {
                    currentUniverse--;
                    currentTimeline = 0;
                }
                else { //If the universe doesn't exist create it
                    numberUniversesUsed++;
                    currentUniverse = 0;
                    currentTimeline = 0;

                    numberTimeLinesUsed.add(0, (long)1);

                    universesAndTimelines.add(0, new ArrayList<Long>());
                    universesAndTimelines.get(currentUniverse).add(0, (long)0);
                }
                break;
        
            default: //If the character isn't a command
                if (character != '\n' && character != 13 && character != 10 && character != ' ' && character != '\t') { //If the character isn't a new line, a space, a tab
                    throw new Exception("Invalid character: \""+character+"\""); //Throw an exception
                }
        }
    }

    public static void printLogicalScheme() throws Exception { //Print the logical scheme of the universes and timelines
        final String BORDER = "===";
        long maxNumberTimeLinesUsed = 0;

        System.out.println("\nLogical scheme of the universes and timelines:");

        try {
            for (int i = 0; i < numberTimeLinesUsed.size(); i++) { //Calculate the maximum number of timelines used
                if (numberTimeLinesUsed.get(i) > maxNumberTimeLinesUsed) {
                    maxNumberTimeLinesUsed = numberTimeLinesUsed.get(i);
                }
            }
        } catch (Exception e) {
            throw new Exception("Error in the calculation of the maximum number of timelines used: "+e);
        }

        try {
            for (int i = 0; i < maxNumberTimeLinesUsed; i++) {
                System.out.print("|"); //Print the border of the scheme

                for (int j = 0; j < numberUniversesUsed; j++) { //Print the timelines
                    if (i == 0) {
                        System.out.print(BORDER);
                    }
                    else {
                        System.out.print("   ");
                    }
    
                    if (numberTimeLinesUsed.get(j) > i) { //If the timeline exists print it
                        System.out.print(universesAndTimelines.get(j).get(i));
                    }
                    else { //If the timeline doesn't exist print a space
                        System.out.print(" ");
                    }
                }
    
                if (i == 0) {
                    System.out.print(BORDER+"|");
                }
                else {
                    System.out.print("   |");
                }
    
                System.out.println();
            }
        } catch (Exception e) {
            throw new Exception("Error in the print of the logical scheme: "+e);
        }

        for (int i = 0; i < numberUniversesUsed; i++) { //Print the end border of the scheme
            if (i == 0) {
                System.out.print("|"+BORDER);
            }
            else {
                System.out.print(BORDER+"=");
            }
        }

        System.out.println(BORDER+"=|"); //Close the scheme and print a new line
    }
}