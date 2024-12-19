import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class App {
    private static ArrayList<ArrayList<Long>> universesAndTimelines = new ArrayList<>(); //[numberUniverses][numberTimelines]
    private static int currentUniverse = 0, currentTimeline = 0, numberUniversesUsed = 1;
    private static ArrayList<Long> numberTimeLinesUsed = new ArrayList<>();
    private static boolean controlComment = false;
    //private static WhileGestor whileGestor = new WhileGestor(controlComment, numberTimeLinesUsed, currentUniverse, currentTimeline, numberUniversesUsed, universesAndTimelines);

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        BufferedReader bufferedReader = null;
        String filename = "";
        boolean error = false;
        char carattere;

        numberTimeLinesUsed.add(0, (long)1);
        universesAndTimelines.add(0, new ArrayList<Long>(0));
        universesAndTimelines.get(currentUniverse).add(currentTimeline, (long)0);

        try {
            System.out.println("Insert the file location: ");
            filename = in.nextLine();
        } catch (Exception e) {
            System.err.println("Error: invalid input!");
            error = true;
        }

        if (!error) {
            try {
                bufferedReader = new BufferedReader(new FileReader(filename));
            } catch (Exception e) {
                System.err.println("Error in the opening of the file!");
                error = true;
            }
        }
        
        if (!error) {
            try {
                while (bufferedReader.ready()) {
                    carattere = (char) bufferedReader.read();
                    
                    try {
                        controlChar(carattere);
                    } catch (Exception e) {
                        System.err.println("Error in the analyses of the character: "+e);
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

    public static void controlChar(char carattere) throws Exception {
        long temp = 0;

        if (controlComment) {
            if (carattere == '\n') {
                controlComment = false;
            }
        }
        /* else if(whileGestor.getWhileControl() == true) {
            controlComment = whileGestor.getControlComment();
            whileGestor.setNumberTimeLinesUsed(numberTimeLinesUsed);
            whileGestor.setNumberUniversesUsed(numberUniversesUsed);
            whileGestor.controlWhile(carattere);
        } */
        else {
            mainSwitch(temp, carattere);
        }
    }

    public static void mainSwitch(long temp, char carattere) throws Exception {
        String stringConverter;
        char charConverter;

        switch (carattere) {
            case 'i':
                temp = universesAndTimelines.get(currentUniverse).get(currentTimeline);
                temp++;
                universesAndTimelines.get(currentUniverse).add(currentTimeline, temp);
                numberTimeLinesUsed.set(currentUniverse, numberTimeLinesUsed.get(currentUniverse)+(long)1); //++  at the number of timelines in the current universe
                break;

            case 'I':
                for (int i = 0; i < numberUniversesUsed; i++) {
                    temp = universesAndTimelines.get(i).get(currentTimeline);
                    temp++;
                    universesAndTimelines.get(i).add(currentTimeline, temp);
                    numberTimeLinesUsed.set(i, numberTimeLinesUsed.get(i)+(long)1);
                }
                break;

            case 'd':
                temp = universesAndTimelines.get(currentUniverse).get(currentTimeline);
                temp--;
                universesAndTimelines.get(currentUniverse).add(currentTimeline, temp);
                numberTimeLinesUsed.set(currentUniverse, numberTimeLinesUsed.get(currentUniverse)+(long)1);
                break;

            case 'D':
                for (int i = 0; i < numberUniversesUsed; i++) {
                    temp = universesAndTimelines.get(i).get(currentTimeline);
                    temp--;
                    universesAndTimelines.get(i).add(currentTimeline, temp);
                    numberTimeLinesUsed.set(i, numberTimeLinesUsed.get(i)+(long)1);
                }
                break;

            case 's':
                temp = universesAndTimelines.get(currentUniverse).get(currentTimeline);
                temp *= temp;
                universesAndTimelines.get(currentUniverse).add(currentTimeline, temp);
                numberTimeLinesUsed.set(currentUniverse, numberTimeLinesUsed.get(currentUniverse)+(long)1);
                break;

            case 'S':
                for (int i = 0; i < numberUniversesUsed; i++) {
                    temp = universesAndTimelines.get(i).get(currentTimeline);
                    temp *= temp;
                    universesAndTimelines.get(i).add(currentTimeline, temp);
                    numberTimeLinesUsed.set(i, numberTimeLinesUsed.get(i)+(long)1);
                }
                break;

            case 'r':
                temp = universesAndTimelines.get(currentUniverse).get(currentTimeline);
                temp = (long)(Math.sqrt(temp));
                universesAndTimelines.get(currentUniverse).add(currentTimeline, temp);
                numberTimeLinesUsed.set(currentUniverse, numberTimeLinesUsed.get(currentUniverse)+(long)1);
                break;

            case 'R':
                for (int i = 0; i < numberUniversesUsed; i++) {
                    temp = universesAndTimelines.get(i).get(currentTimeline);
                    temp = (long)(Math.sqrt(temp));
                    universesAndTimelines.get(i).add(currentTimeline, temp);
                    numberTimeLinesUsed.set(i, numberTimeLinesUsed.get(i)+(long)1);
                }
                break;

            case '0':
                temp = (long)0;
                universesAndTimelines.get(currentUniverse).add(currentTimeline, temp);
                numberTimeLinesUsed.set(currentUniverse, numberTimeLinesUsed.get(currentUniverse)+(long)1);
                break;

            case 'o':
                System.out.println(universesAndTimelines.get(currentUniverse).get(currentTimeline));
                break;

            case 'O':
                for (int i = 0; i < numberUniversesUsed; i++) {
                    temp = universesAndTimelines.get(i).get(currentTimeline);
                    System.out.println(temp);
                }
                break;

            case 'u': //If isn't a character print '', if is bigger than 127 print '?'
                stringConverter = (universesAndTimelines.get(currentUniverse).get(currentTimeline).toString());
                charConverter = (char) Integer.parseInt(stringConverter);
                System.out.println(charConverter);
                break;

            case 'U': //If isn't a character print '', if is bigger than 127 print '?'
                for (int i = 0; i < numberUniversesUsed; i++) {
                    stringConverter = (universesAndTimelines.get(currentUniverse).get(currentTimeline).toString());
                    charConverter = (char) Integer.parseInt(stringConverter);
                    System.out.println(charConverter);
                }
                break;

            case 'G':
                System.out.println(universesAndTimelines);
                break;

            case '/':
                controlComment = true;
                break;

            case '>':
                if ((long) currentTimeline == numberTimeLinesUsed.get(currentUniverse)-1) {
                    numberTimeLinesUsed.set(currentUniverse, numberTimeLinesUsed.get(currentUniverse)+(long)1);
                    universesAndTimelines.get(currentUniverse).add(currentTimeline++, (long)0);
                }
                else {
                    currentTimeline++;
                }
                break;

            case '<':
                if (currentTimeline != 0) {
                    currentTimeline--;
                }
                else {
                    numberTimeLinesUsed.set(currentUniverse, numberTimeLinesUsed.get(currentUniverse)+(long)1);
                    universesAndTimelines.get(currentUniverse).add(0, (long)0);
                }
                break;

            case '[':
                if (currentUniverse == numberUniversesUsed-1) {
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

            case ']':
                if (currentUniverse != 0) {
                    currentUniverse--;
                    currentTimeline = 0;
                }
                else {
                    numberUniversesUsed++;
                    currentUniverse = 0;
                    currentTimeline = 0;

                    numberTimeLinesUsed.add(0, (long)1);

                    universesAndTimelines.add(0, new ArrayList<Long>());
                    universesAndTimelines.get(currentUniverse).add(0, (long)0);
                }
                break;
        
            default:
                /* whileGestor.setNumberTimeLinesUsed(numberTimeLinesUsed);
                whileGestor.setNumberUniversesUsed(numberUniversesUsed);
                whileGestor.controlWhile(carattere); */
                break;
        }
    }
}