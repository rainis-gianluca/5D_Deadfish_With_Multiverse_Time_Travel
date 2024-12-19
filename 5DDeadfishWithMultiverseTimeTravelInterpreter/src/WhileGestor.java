import java.util.ArrayList;

public class WhileGestor {
    private ArrayList<ArrayList<Long>> universesAndTimelines = new ArrayList<>(); //[numberUniverses][numberTimelines]
    private int currentUniverse = 0, currentTimeline = 0, numberUniversesUsed = 1;
    private ArrayList<Long> numberTimeLinesUsed = new ArrayList<>();
    private boolean controlComment = false;

    private char[] tempMemory = new char[2]; //memory of the control condition
    private boolean whileControl; //if the interpreter is in the while
    private boolean inWhileCondition; //if the interpreter is in the while condition
    private boolean inWhileCommands; //if the interpreter is in the while commands
    private boolean whileControlBeforeControlCondition; //if the interpreter is in the first value of the control condition
    private long firstValueControlInWhile; //first value of the control condition
    private long secondValueControlInWhile; //second value of the control condition
    private long temp;
    private String operator; //operator of the control condition
    private String operationOfTheCondition;
    private String operationToIter;

    public WhileGestor(boolean controlComment, ArrayList<Long> numberTimeLinesUsed, int currentUniverse, int currentTimeline, int numberUniversesUsed, ArrayList<ArrayList<Long>> universesAndTimelines) {
        this.controlComment = controlComment;
        this.numberTimeLinesUsed = numberTimeLinesUsed;
        this.currentUniverse = currentUniverse;
        this.currentTimeline = currentTimeline;
        this.numberUniversesUsed = numberUniversesUsed;
        this.universesAndTimelines = universesAndTimelines;
        firstValueControlInWhile = 0;
        secondValueControlInWhile = 0;
        whileControl = false;
        inWhileCommands = false;
        inWhileCondition = false;
        whileControlBeforeControlCondition = true;
        tempMemory[0] = ' ';
        tempMemory[1] = ' ';
        operationToIter = "";
        operationOfTheCondition = "";
        temp = 0;
    }

    public void controlWhile(char character) throws Exception {
        int tempCurrentTimeline = currentTimeline, tempCurrentUniverse = currentUniverse;
        temp = universesAndTimelines.get(tempCurrentUniverse).get(tempCurrentTimeline); 

        if (inWhileCondition && inWhileCommands) {
            throw new Exception("Exeption in while!");
        }

        if (character == '(') {
            inWhileCondition = true;
            whileControl = true;
        }
        else if(character == ')') {
            inWhileCondition = false;
        }
        else if(character == '{') {
            inWhileCommands = true;
        }
        else if(character == '}') {
            inWhileCommands = false;
            whileControl = false;
            whileMain();
        }
        else if (inWhileCondition) {
            operationOfTheCondition += character;
            if(character == '!' || character == '=' || character == '<' || character == '>') {
                if (tempMemory[0] == ' ') {
                    tempMemory[0] = character;
                }
                else {
                    if (tempMemory[1] == ' ') {
                        tempMemory[1] = character;
                    }
                    else {
                        switchWhile(tempCurrentTimeline, tempCurrentUniverse, tempMemory[0]); //execute tempMemory[0], tempMemory[1] and character
                        switchWhile(tempCurrentTimeline, tempCurrentUniverse, tempMemory[1]);
                        switchWhile(tempCurrentTimeline, tempCurrentUniverse, character);
    
                        tempMemory[0] = ' ';
                        tempMemory[1] = ' ';
                    }
                }
            }
            else {
                if (tempMemory[0] != ' ' && tempMemory[1] != ' ') {
                    operator = String.valueOf(tempMemory[0]) + String.valueOf(tempMemory[1]);
                    
                    if(operator.equals("==")) {
                        whileControlBeforeControlCondition = false;
                    }
                    else if(operator.equals("!=")) {
                        whileControlBeforeControlCondition = false;
                    }
                    else if(operator.equals("<=")) {
                        whileControlBeforeControlCondition = false;
                    }
                    else if(operator.equals(">=")) {
                        whileControlBeforeControlCondition = false;
                    }
                    else if (operator.equals("<<")) {
                        whileControlBeforeControlCondition = false;
                    }
                    else if (operator.equals(">>")) {
                        whileControlBeforeControlCondition = false;
                    }
                    else {
                        operator = "";
                        throw new Exception("Exeption in operator search!");
                    }
    
                    tempMemory[0] = ' ';
                    tempMemory[1] = ' ';
                }
                else if (tempMemory[0] != ' ' && tempMemory[1] == ' ') {
                    switchWhile(tempCurrentTimeline, tempCurrentUniverse, tempMemory[0]); //execute tempMemory[0]
    
                    tempMemory[0] = ' ';
                }
    
                switchWhile(tempCurrentTimeline, tempCurrentUniverse, character);
            }
        }
        else if (inWhileCommands) {
            if (character == '/') {
                controlComment = true;
            }

            if (character == 13 || character == '\n') {
                controlComment = false;
            }

            if (character != ' ' && character != '\t' && character != '\n' && character != 13 && !controlComment) {
                operationToIter += character;
            }
        }
    }

    public void switchWhile(int tempCurrentTimeline, int tempCurrentUniverse, char character) throws Exception {
        if (character == '\n') {
            controlComment = false;
        }
        else if (!controlComment) {
            switch (character) {
                case 'i':
                    temp = universesAndTimelines.get(tempCurrentUniverse).get(tempCurrentTimeline);
                    temp++;
                    break;
    
                case 'd':
                    temp = universesAndTimelines.get(tempCurrentUniverse).get(tempCurrentTimeline);
                    temp--;
                    break;
    
                case 's':
                    temp = universesAndTimelines.get(tempCurrentUniverse).get(tempCurrentTimeline);
                    temp *= temp;
                    break;
    
                case 'r':
                    temp = universesAndTimelines.get(tempCurrentUniverse).get(tempCurrentTimeline);
                    temp = (long)(Math.sqrt(temp));
                    break;
    
                case '0':
                    temp = (long)0;
                    break;
    
                case 'o':
                    if (whileControlBeforeControlCondition) {
                        firstValueControlInWhile = temp;
                        temp = 0;
                    }
                    else {
                        secondValueControlInWhile = temp;
                        temp = 0;
                    }
                    break;
    
                case '/':
                    controlComment = true;
                    break;
    
                case '>':
                    if ((long) tempCurrentTimeline == numberTimeLinesUsed.get(tempCurrentUniverse)-1) {
                        throw new Exception("Exeption in while time travel!");
                    }
                    else {
                        tempCurrentTimeline++;
                        temp = universesAndTimelines.get(tempCurrentUniverse).get(tempCurrentTimeline);
                    }
                    break;
    
                case '<':
                    if (tempCurrentTimeline != 0) {
                        tempCurrentTimeline--;
                        temp = universesAndTimelines.get(tempCurrentUniverse).get(tempCurrentTimeline);
                    }
                    else {
                        throw new Exception("Exeption in while time travel!");
                    }
                    break;
    
                case '[':
                    if (tempCurrentUniverse == numberUniversesUsed-1) {
                        throw new Exception("Exeption in while multiverse travel!");
                    }
                    else {
                        tempCurrentUniverse++;
                        tempCurrentTimeline = 0;
                        temp = universesAndTimelines.get(tempCurrentUniverse).get(tempCurrentTimeline);
                    }
                    break;
    
                case ']':
                    if (tempCurrentUniverse != 0) {
                        tempCurrentUniverse--;
                        tempCurrentTimeline = 0;
                        temp = universesAndTimelines.get(tempCurrentUniverse).get(tempCurrentTimeline);
                    }
                    else {
                        throw new Exception("Exeption in while multiverse travel!");
                    }
                    break;
            
                default:
                    break;
            }
        }
    }

    public void whileMain() throws Exception {
        ArrayList<Long> tempArrayList = new ArrayList<>();

        for (int i = 0; i < numberUniversesUsed; i++) {
            tempArrayList.add(universesAndTimelines.get(i).get(0));
        }

        switch (operator) {
            case "==":
                while (firstValueControlInWhile == secondValueControlInWhile) {
                    for (int i = 0; i < operationToIter.length(); i++) {
                        App.mainSwitch(tempArrayList.get(currentUniverse), operationToIter.charAt(i));
                    }
                }
                break;
            
            case "!=":
                while (firstValueControlInWhile != secondValueControlInWhile) {
                    System.out.println("DEBUG !=: "+firstValueControlInWhile+" "+secondValueControlInWhile); //DEBUG LINE - REMOVE LATER - DEBUG LINE - REMOVE LATER
                }
                break;

            case "<<":
                while (firstValueControlInWhile < secondValueControlInWhile) {
                    System.out.println("DEBUG <<: "+firstValueControlInWhile+" "+secondValueControlInWhile); //DEBUG LINE - REMOVE LATER - DEBUG LINE - REMOVE LATER
                }
                break;

            case ">>":
                while (firstValueControlInWhile > secondValueControlInWhile) {
                    System.out.println("DEBUG >>: "+firstValueControlInWhile+" "+secondValueControlInWhile); //DEBUG LINE - REMOVE LATER - DEBUG LINE - REMOVE LATER
                }
                break;

            case "<=":
                while (firstValueControlInWhile <= secondValueControlInWhile) {
                    System.out.println("DEBUG <=: "+firstValueControlInWhile+" "+secondValueControlInWhile); //DEBUG LINE - REMOVE LATER - DEBUG LINE - REMOVE LATER
                }
                break;

            case ">=":
                while (firstValueControlInWhile >= secondValueControlInWhile) {
                    System.out.println("DEBUG >=: "+firstValueControlInWhile+" "+secondValueControlInWhile); //DEBUG LINE - REMOVE LATER - DEBUG LINE - REMOVE LATER
                }
                break;
        
            default:
                throw new Exception("Exception in the Main part of the while!");
        }
    }

    public long getFirstValueControlInWhile() {
        return firstValueControlInWhile;
    }

    public long getSecondValueControlInWhile() {
        return secondValueControlInWhile;
    }

    public char[] getTempMemory() {
        return tempMemory;
    }

    public boolean getControlComment() {
        return controlComment;
    }

    public boolean getWhileControl() {
        return whileControl;
    }

    public void setControlComment(boolean controlComment) {
        this.controlComment = controlComment;
    }

    public void setFirstValueControlInWhile(long firstValueControlInWhile) {
        this.firstValueControlInWhile = firstValueControlInWhile;
    }

    public void setInWhileCommands(boolean inWhileCommands) {
        this.inWhileCommands = inWhileCommands;
    }

    public void setInWhileCondition(boolean inWhileCondition) {
        this.inWhileCondition = inWhileCondition;
    }

    public void setSecondValueControlInWhile(long secondValueControlInWhile) {
        this.secondValueControlInWhile = secondValueControlInWhile;
    }

    public void setTempMemory(char[] tempMemory) {
        this.tempMemory = tempMemory;
    }

    public void setWhileControl(boolean whileControl) {
        this.whileControl = whileControl;
    }

    public void setNumberUniversesUsed(int numberUniversesUsed) {
        this.numberUniversesUsed = numberUniversesUsed;
    }

    public void setNumberTimeLinesUsed(ArrayList<Long> numberTimeLinesUsed) {
        this.numberTimeLinesUsed = numberTimeLinesUsed;
    }
}