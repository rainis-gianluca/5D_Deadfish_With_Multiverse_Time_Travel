/* 5D Deadfish with Multiverse Time Travel sintax created by: Gianluca Rainis
 * 5D Deadfish with Multiverse Time Travel interpreter created by: Gianluca Rainis
 * This code is free to use, modify and distribute
 * To have more information about the sintax and the interpreter go to the GitHub repository: https://github.com/rainis-gianluca/5D_Deadfish_With_Multiverse_Time_Travel.git
 * This version of the interpreter is written in C++
 * See the GitHub repository for the other versions of the interpreter
 * The .vscode folder is used to store the settings of the Visual Studio Code editor, you can delete it if you don't use Visual Studio Code
 * The comments in the code are written with the help of GitHub Copilot
 * Last update: 24 December 2024
 */

#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <cmath>

using namespace std;

vector<vector<long>> universesAndTimelines; //[numberUniverses][numberTimelines]
int currentUniverse = 0, currentTimeline = 0, numberUniversesUsed = 1; //The first universe is the 0
vector<long> numberTimeLinesUsed; //An array with the number of timelines used in each universe
bool controlComment = false; //If is true the interpreter is in a comment

void printLogicalScheme() { //Print the logical scheme of the universes and timelines
    const string BORDER = "===";
    long maxNumberTimeLinesUsed = 0;

    cout<<("\nLogical scheme of the universes and timelines:")<<endl;

    try {
        for (int i = 0; i < numberTimeLinesUsed.size(); i++) { //Calculate the maximum number of timelines used
            if (numberTimeLinesUsed.at(i) > maxNumberTimeLinesUsed) {
                maxNumberTimeLinesUsed = numberTimeLinesUsed.at(i);
            }
        }
    } catch (const std::exception& e) {
        throw runtime_error(string("Error in the calculation of the maximum number of timelines used: ") + e.what());
    }

    try {
        for (int i = 0; i < maxNumberTimeLinesUsed; i++) {
            cout<<("|"); //Print the border of the scheme

            for (int j = 0; j < numberUniversesUsed; j++) { //Print the timelines
                if (i == 0) {
                    cout<<(BORDER);
                }
                else {
                    cout<<("   ");
                }

                if (numberTimeLinesUsed.at(j) > i) { //If the timeline exists print it
                    cout<<(universesAndTimelines.at(j).at(i));
                }
                else { //If the timeline doesn't exist print a space
                    cout<<(" ");
                }
            }

            if (i == 0) {
                cout<<(BORDER+"|");
            }
            else {
                cout<<("   |");
            }

            cout<<endl;
        }
    } catch (const std::exception& e) {
        throw runtime_error(string("Error in the print of the logical scheme: ") + e.what());
    }

    for (int i = 0; i < numberUniversesUsed; i++) { //Print the end border of the scheme
        if (i == 0) {
            cout<<("|"+BORDER);
        }
        else {
            cout<<(BORDER+"=");
        }
    }

    cout<<(BORDER+"=|"); //Close the scheme and print a new line
}

void mainSwitch(long temp, char character) { //Analyze the character
    switch (character) {
        case 'i': //Increment the current timeline value in the current universe
            temp = universesAndTimelines.at(currentUniverse).at(currentTimeline);
            temp++;
            universesAndTimelines.at(currentUniverse).insert(universesAndTimelines.at(currentUniverse).begin()+currentTimeline, temp);
            numberTimeLinesUsed.at(currentUniverse)++; //++  at the number of timelines in the current universe
            break;

        case 'I': //Increment the current timeline value in all universes
            for (int i = 0; i < numberUniversesUsed; i++) {
                temp = universesAndTimelines.at(i).at(currentTimeline);
                temp++;
                universesAndTimelines.at(i).insert(universesAndTimelines.at(i).begin()+currentTimeline, temp);
                numberTimeLinesUsed.at(currentUniverse)++;
            }
            break;

        case 'd': //Decrement the current timeline value in the current universe
            temp = universesAndTimelines.at(currentUniverse).at(currentTimeline);
            temp--;
            universesAndTimelines.at(currentUniverse).insert(universesAndTimelines.at(currentUniverse).begin()+currentTimeline, temp);
            numberTimeLinesUsed.at(currentUniverse)++;
            break;

        case 'D': //Decrement the current timeline value in all universes
            for (int i = 0; i < numberUniversesUsed; i++) {
                temp = universesAndTimelines.at(i).at(currentTimeline);
                temp--;
                universesAndTimelines.at(i).insert(universesAndTimelines.at(i).begin()+currentTimeline, temp);
                numberTimeLinesUsed.at(currentUniverse)++;
            }
            break;

        case 's': //Square the current timeline value in the current universe
            temp = universesAndTimelines.at(currentUniverse).at(currentTimeline);
            temp *= temp;
            universesAndTimelines.at(currentUniverse).insert(universesAndTimelines.at(currentUniverse).begin()+currentTimeline, temp);
            numberTimeLinesUsed.at(currentUniverse)++;
            break;

        case 'S': //Square the current timeline value in all universes
            for (int i = 0; i < numberUniversesUsed; i++) {
                temp = universesAndTimelines.at(i).at(currentTimeline);
                temp *= temp;
                universesAndTimelines.at(i).insert(universesAndTimelines.at(i).begin()+currentTimeline, temp);
                numberTimeLinesUsed.at(currentUniverse)++;
            }
            break;

        case 'r': //Square root the current timeline value in the current universe
            temp = universesAndTimelines.at(currentUniverse).at(currentTimeline);

            if (temp >= 0) {
                temp = (long)(sqrt(temp));
            } else {
                throw runtime_error("Cannot take the square root of a negative number.");
            }

            universesAndTimelines.at(currentUniverse).insert(universesAndTimelines.at(currentUniverse).begin()+currentTimeline, temp);
            numberTimeLinesUsed.at(currentUniverse)++;
            break;

        case 'R': //Square root the current timeline value in all universes
            for (int i = 0; i < numberUniversesUsed; i++) {
                temp = universesAndTimelines.at(i).at(currentTimeline);

                if (temp >= 0) {
                    temp = (long)(sqrt(temp));
                } else {
                    throw runtime_error("Cannot take the square root of a negative number.");
                }

                universesAndTimelines.at(i).insert(universesAndTimelines.at(i).begin()+currentTimeline, temp);
                numberTimeLinesUsed.at(currentUniverse)++;
            }
            break;

        case '0': //Set the current timeline value to 0 in the current universe
            temp = (long)0;
            universesAndTimelines.at(currentUniverse).insert(universesAndTimelines.at(currentUniverse).begin()+currentTimeline, temp);
            numberTimeLinesUsed.at(currentUniverse)++;
            break;

        case 'o': //Output the current timeline value in the current universe
            cout<<(universesAndTimelines.at(currentUniverse).at(currentTimeline));
            break;

        case 'O': //Output the current timeline value in all universes
            for (int i = 0; i < numberUniversesUsed; i++) {
                temp = universesAndTimelines.at(i).at(currentTimeline);
                cout<<(temp);
            }
            break;

        case 'u': //Output the current timeline value in the current universe as a character
            if (universesAndTimelines.at(currentUniverse).at(currentTimeline) >= 32 && universesAndTimelines.at(currentUniverse).at(currentTimeline) < 127) {
                cout<<(char)universesAndTimelines.at(currentUniverse).at(currentTimeline);
            } else if(universesAndTimelines.at(currentUniverse).at(currentTimeline) < 0 || universesAndTimelines.at(currentUniverse).at(currentTimeline) > 127) {
                cout<<'?'; // Print '?' if the value is out of the ASCII range
            }
            break;

        case 'U': //Output the current timeline value in all universes as a character
            for (int i = 0; i < numberUniversesUsed; i++) {
                if (universesAndTimelines.at(i).at(currentTimeline) >= 32 && universesAndTimelines.at(i).at(currentTimeline) < 127) {
                    cout<<(char)universesAndTimelines.at(i).at(currentTimeline);
                } else if(universesAndTimelines.at(i).at(currentTimeline) < 0 || universesAndTimelines.at(i).at(currentTimeline) > 127) {
                    cout<<'?'; // Print '?' if the value is out of the ASCII range
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
            if ((long) currentTimeline == numberTimeLinesUsed.at(currentUniverse)-1) { //If the timeline doesn't exist create it
                numberTimeLinesUsed.at(currentUniverse)++;
                universesAndTimelines.at(currentUniverse).insert(universesAndTimelines.at(currentUniverse).begin()+currentTimeline+1, (long)0);
                currentTimeline++;
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
                numberTimeLinesUsed.at(currentUniverse)++;
                universesAndTimelines.at(currentUniverse).insert(universesAndTimelines.at(currentUniverse).begin(), (long)0);
            }
            break;

        case '[': //Go to the next universe
            if (currentUniverse == numberUniversesUsed-1) { //If the universe doesn't exist create it
                numberUniversesUsed++;
                currentUniverse++;
                numberTimeLinesUsed.push_back((long)1);
                currentTimeline = 0;
                
                universesAndTimelines.push_back(vector<long>());
                universesAndTimelines.at(currentUniverse).insert(universesAndTimelines.at(currentUniverse).begin(), (long)0);
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

                numberTimeLinesUsed.insert(numberTimeLinesUsed.begin(), (long)1);

                universesAndTimelines.insert(universesAndTimelines.begin(), vector<long>());
                universesAndTimelines.at(currentUniverse).push_back((long)0);
            }
            break;
    
        default: //If the character isn't a command
            if (character != '\n' && character != 13 && character != 10 && character != ' ' && character != '\t' && character != -1) { //If the character isn't a new line, a space, a tab
                cerr<<"DEBUG: "<<(int)character<<endl;
                throw runtime_error("Invalid character: \"" + string(1, character) + "\""); //Throw an exception
            }
    }
}

void controlChar(char character) { //Control the character
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

int main()
{
    fstream bufferedReader; //The buffer that will read the file
    string filename = ""; //The name of the file that will be analyzed
    bool error = false; //If is true the interpreter will stop
    char character; //The character that will be analyzed

    numberTimeLinesUsed.push_back((long)1); //Inizialize the number of timelines in the first universe to 1
    universesAndTimelines.push_back(vector<long>()); //Add the first universe
    universesAndTimelines.at(currentUniverse).push_back((long)0); //Add the first timeline in the first universe    

    try { //Try to at the file location
        cout<<"Insert the file location: "<<endl;
        cin>>filename;
    } catch (const std::exception& e) {
        cerr<<("Error: invalid input!")<<endl;
        error = true;
    }

    if (!error) {
        try { //Try to open the file
            bufferedReader.open(filename, ios::in); //Open the file in read mode
        } catch (const std::exception& e) {
            cerr<<("Error in the opening of the file!")<<endl;
            error = true;
        }
    }
        
    if (!error) {
        try {
            while (!bufferedReader.eof() && !error) { //While the file has characters read them and analyze them
                character = (char) bufferedReader.get(); //at the character
                    
                try {
                    controlChar(character); //Analyze the character
                } catch (const std::exception& e) {
                    cerr<<"Error in the analysis of the character: "<<e.what()<<endl;
                    error = true;
                }
            }
        } catch (const std::exception& e) {
            cerr<<"ERROR: "<<e.what()<<endl;
        }
    }

    system("pause>null"); //Pause the program
    return 0;
}