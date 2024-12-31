/* 
5D Deadfish with Multiverse Time Travel sintax created by: Gianluca Rainis
5D Deadfish with Multiverse Time Travel interpreter created by: Gianluca Rainis
This code is free to use, modify and distribute
To have more information about the sintax and the interpreter go to the GitHub repository: https://github.com/rainis-gianluca/5D_Deadfish_With_Multiverse_Time_Travel.git
This version of the interpreter is written in JavaScript
See the GitHub repository for the other versions of the interpreter
The .vscode folder is used to store the settings of the Visual Studio Code editor, you can delete it if you don't use Visual Studio Code
The comments in the code are written with the help of GitHub Copilot
Last update: 31 December 2024
*/

var universesAndTimelines = [[0]]; //[numberUniverses][numberTimelines]
var currentUniverse = 0, currentTimeline = 0, numberUniversesUsed = 1; //The first universe is the 0
var numberTimeLinesUsed = [1]; //An array with the number of timelines used in each universe
var controlComment = false; //If is true the interpreter is in a comment
var inputString = ""; //The input string
var outputString = ""; //The output string

function interpreter(code) {
    inputString = code;

    main();

    return outputString;
}

function main() {
    let error = false; //If is true the interpreter will stop
    let character = ''; //The character that will be analyzed
    let i = 0; //The index of the character that will be analyzed
    
    if (!error) {
        try {
            while (i < inputString.length && !error) { //While the file has characters read them and analyze them
                character = inputString.charAt(i);
                
                try {
                    controlChar(character); //Analyze the character
                } catch (e) {
                    outputString += ("Error in the analyses of the character: "+e);
                    error = true;
                }
            }
        } catch (e) {
            outputString += ("ERROR: " + e);
        }
    }
}

function controlChar(character) { //Control the character
    let temp = 0; //The temporal variable that will be used in the operations

    if (controlComment) { //If the interpreter is in a comment
        if (character == '\n') { //If the character is a new line the comment ends
            controlComment = false;
        }
    }
    else { //If the interpreter isn't in a comment
        mainSwitch(temp, character); //Analyze the character
    }
}

function mainSwitch(temp, character) { //Analyze the character
    switch (character) {
        case 'i': //Increment the current timeline value in the current universe
            temp = universesAndTimelines[currentUniverse][currentTimeline];
            temp++;
            universesAndTimelines[currentUniverse].splice(currentTimeline, 0, temp);
            numberTimeLinesUsed[currentUniverse]++; //++  at the number of timelines in the current universe
            break;

        case 'I': //Increment the current timeline value in all universes
            for (let i = 0; i < numberUniversesUsed; i++) {
                temp = universesAndTimelines[i][currentTimeline];
                temp++;
                universesAndTimelines[i].splice(currentTimeline, 0, temp);
                numberTimeLinesUsed[currentUniverse]++;
            }
            break;

        case 'd': //Decrement the current timeline value in the current universe
            temp = universesAndTimelines[currentUniverse][currentTimeline];
            temp--;
            universesAndTimelines[currentUniverse].splice(currentTimeline, 0, temp);
            numberTimeLinesUsed[currentUniverse]++;
            break;

        case 'D': //Decrement the current timeline value in all universes
            for (let i = 0; i < numberUniversesUsed; i++) {
                temp = universesAndTimelines[i][currentTimeline];
                temp--;
                universesAndTimelines[i].splice(currentTimeline, 0, temp);
                numberTimeLinesUsed[currentUniverse]++;
            }
            break;

        case 's': //Square the current timeline value in the current universe
            temp = universesAndTimelines[currentUniverse][currentTimeline];
            temp *= temp;
            universesAndTimelines[currentUniverse].splice(currentTimeline, 0, temp);
            numberTimeLinesUsed[currentUniverse]++;
            break;

        case 'S': //Square the current timeline value in all universes
            for (let i = 0; i < numberUniversesUsed; i++) {
                temp = universesAndTimelines[i][currentTimeline];
                temp *= temp;
                universesAndTimelines[i].splice(currentTimeline, 0, temp);
                numberTimeLinesUsed[currentUniverse]++;
            }
            break;

        case 'r': //Square root the current timeline value in the current universe
            temp = universesAndTimelines[currentUniverse][currentTimeline];

            if (temp >= 0) {
                temp = (long)(Math.sqrt(temp));
            } else {
                throw new "Cannot take the square root of a negative number.";
            }

            universesAndTimelines[currentUniverse].splice(currentTimeline, 0, temp);
            numberTimeLinesUsed[currentUniverse]++;
            break;

        case 'R': //Square root the current timeline value in all universes
            for (let i = 0; i < numberUniversesUsed; i++) {
                temp = universesAndTimelines[i][currentTimeline];

                if (temp >= 0) {
                    temp = (long)(Math.sqrt(temp));
                } else {
                    throw new "Cannot take the square root of a negative number.";
                }

                universesAndTimelines[i].splice(currentTimeline, 0, temp);
                numberTimeLinesUsed[currentUniverse]++;
            }
            break;

        case '0': //Set the current timeline value to 0 in the current universe
            temp = 0;
            universesAndTimelines[currentUniverse].splice(currentTimeline, 0, temp);
            numberTimeLinesUsed[currentUniverse]++;
            break;

        case 'o': //Output the current timeline value in the current universe
            outputString += universesAndTimelines[currentUniverse][currentTimeline];
            break;

        case 'O': //Output the current timeline value in all universes
            for (let i = 0; i < numberUniversesUsed; i++) {
                temp = universesAndTimelines[i][currentTimeline];
                outputString += (temp);
            }
            break;

        case 'u': //Output the current timeline value in the current universe as a character
            if (universesAndTimelines[currentUniverse][currentTimeline] >= 32 && universesAndTimelines[currentUniverse][currentTimeline] < 127) {
                outputString += universesAndTimelines[currentUniverse][currentTimeline].toString(); //Print the character
            } else if(universesAndTimelines[currentUniverse][currentTimeline] < 0 || universesAndTimelines[currentUniverse][currentTimeline] > 127) {
                outputString += ('?'); // Print '?' if the value is out of the ASCII range
            }
            break;

        case 'U': //Output the current timeline value in all universes as a character
            for (let i = 0; i < numberUniversesUsed; i++) {
                if (universesAndTimelines[i][currentTimeline] >= 32 && universesAndTimelines[i][currentTimeline] < 127) {
                    outputString += universesAndTimelines[i][currentTimeline].toString(); //Print the character
                } else if(universesAndTimelines[i][currentTimeline] < 0 || universesAndTimelines[i][currentTimeline] > 127) {
                    outputString += ('?'); // Print '?' if the value is out of the ASCII range
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
            if (currentTimeline == numberTimeLinesUsed[currentUniverse]-1) { //If the timeline doesn't exist create it
                numberTimeLinesUsed[currentUniverse]++;
                universesAndTimelines[currentUniverse].splice(currentTimeline++, 0, 0);
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
                numberTimeLinesUsed[currentUniverse]++;
                universesAndTimelines[currentUniverse].splice(0, 0, temp);
            }
            break;

        case '[': //Go to the next universe
            if (currentUniverse == numberUniversesUsed-1) { //If the universe doesn't exist create it
                numberUniversesUsed++;
                currentUniverse++;
                numberTimeLinesUsed.push(1);
                currentTimeline = 0;
                
                universesAndTimelines.push([0]);
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

                numberTimeLinesUsed.push(1);

                universesAndTimelines.push([0]);
            }
            break;
    
        default: //If the character isn't a command
            if (character != '\n' && character != 13 && character != 10 && character != ' ' && character != '\t') { //If the character isn't a new line, a space, a tab
                throw new "Invalid character: \""+character+"\""; //Throw an Exception
            }
    }
}

function printLogicalScheme() { //Print the logical scheme of the universes and timelines
    const BORDER = "===";
    let maxNumberTimeLinesUsed = 0;

    outputString += "\nLogical scheme of the universes and timelines:";

    try {
        for (let i = 0; i < numberTimeLinesUsed.length; i++) { //Calculate the maximum number of timelines used
            if (numberTimeLinesUsed[i] > maxNumberTimeLinesUsed) {
                maxNumberTimeLinesUsed = numberTimeLinesUsed[i];
            }
        }
    } catch (e) {
        throw new "Error in the calculation of the maximum number of timelines used: "+e;
    }

    try {
        for (let i = 0; i < maxNumberTimeLinesUsed; i++) {
            outputString += ("|"); //Print the border of the scheme

            for (let j = 0; j < numberUniversesUsed; j++) { //Print the timelines
                if (i == 0) {
                    outputString += (BORDER);
                }
                else {
                    outputString += ("   ");
                }

                if (numberTimeLinesUsed[j] > i) { //If the timeline exists print it
                    outputString += (universesAndTimelines[j][i]);
                }
                else { //If the timeline doesn't exist print a space
                    outputString += (" ");
                }
            }

            if (i == 0) {
                outputString += (BORDER+"|");
            }
            else {
                outputString += ("   |");
            }

            outputString += "\n";
        }
    } catch (e) {
        throw new "Error in the print of the logical scheme: "+e;
    }

    for (let i = 0; i < numberUniversesUsed; i++) { //Print the end border of the scheme
        if (i == 0) {
            outputString += ("|"+BORDER);
        }
        else {
            outputString += (BORDER+"=");
        }
    }

    outputString += BORDER+"=|\n"; //Close the scheme and print a new line
}