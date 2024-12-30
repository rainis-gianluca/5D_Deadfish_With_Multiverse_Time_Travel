""" 5D Deadfish with Multiverse Time Travel sintax created by: Gianluca Rainis
5D Deadfish with Multiverse Time Travel interpreter created by: Gianluca Rainis
To have more information about the sintax and the interpreter go to the GitHub repository: https://github.com/rainis-gianluca/5D_Deadfish_With_Multiverse_Time_Travel.git
This version of the interpreter is written in Python
This version of the interpreter support only the .5dd files
See the GitHub repository for the other versions of the interpreter
The .exe files are in the dist folder, they are builded with PyInstaller
The comments in the code are written with the help of GitHub Copilot
Last update: 30 December 2024
"""

"""
COMMAND SYNTAX: 5Ddf nomefile.5dd
IMPORTANT: The file must have the .5dd extension
REMEMBER: The file must be in the current directory when you run the command
"""

from math import sqrt #Import the square root function
import os #Import the os module
import argparse #Import the argparse module

global universesAndTimelines, currentUniverse, currentTimeline, numberUniversesUsed, numberTimeLinesUsed, controlComment #Global variables

universesAndTimelines = [[0]] #[numberUniverses][numberTimelines]
currentUniverse = 0
currentTimeline = 0
numberUniversesUsed = 1 #The first universe is the 0
numberTimeLinesUsed = [1] #An array with the number of timelines used in each universe
controlComment = False #If is True the interpreter is in a comment


def main():
    global universesAndTimelines, currentUniverse, currentTimeline, numberUniversesUsed, numberTimeLinesUsed, controlComment #Global variables

    filename = "" #The name of the file that will be analyzed
    currentDirectory = "" #The current directory
    error = False #If is True the interpreter will stop
    character = ''#The character that will be analyzed
    parser = argparse.ArgumentParser(prog="5D Deadfish with Multiverse Time Travel command line interpreter", description="5D Deadfish with Multiverse Time Travel interpreter", usage="5Ddf [-h, --help] [-v, --version] fileName.5dd", epilog="If you want to read more about the 5D Deadfish with Multiverse Time Travel, you can visit https://github.com/rainis-gianluca/5D_Deadfish_With_Multiverse_Time_Travel.git for the official GitHub repository, or https://esolangs.org/wiki/5D_Deadfish_with_Multiverse_Time_Travel for the official Esolangs page of the Programming Language.") #Create the parser

    try: #Try to get the file location
        currentDirectory = os.getcwd() #Get the current directory

        parser.add_argument("fileName", type=str, help="The name of the file that will be analyzed. It must have the .5dd extension.") #Add the filename argument
        parser.add_argument('-v', '--version', action='version', version='%(prog)s V1.2') #Add the version argument

        arguments = parser.parse_args() #Parse the arguments

        if (arguments.fileName == ""):
            raise Exception("The filename cannot be empty!")
        
        if (arguments.fileName[-4:] != ".5dd"):
            raise Exception("The file must have the .5dd extension!")

        filename = currentDirectory + "\\" + arguments.fileName #Get the filename
    except Exception as e:
        print("Error in the opening of the file: " + str(e))
        error = True

    if not error:
        bufferedReader = None

        try: #Try to open the file
            with open(filename, 'r') as bufferedReader:
                while not error:
                    character = bufferedReader.read(1)

                    if not character:
                        error = True

                    try:
                        controlChar(character) #Analyze the character
                    except Exception as e:
                        print("Error in the analyses of the character: " + str(e))
                        error = True
        except Exception as e:
            print("Error in the opening of the file: " + str(e))


def controlChar(character): #Control the character
    global universesAndTimelines, currentUniverse, currentTimeline, numberUniversesUsed, numberTimeLinesUsed, controlComment #Global variables

    temp = 0 #The temporal variable that will be used in the operations

    if (controlComment): #If the interpreter is in a comment
        if (character == '\n'): #If the character is a new line the comment ends
            controlComment = False
    else: #If the interpreter isn't in a comment
        mainSwitch(temp, character) #Analyze the character


def mainSwitch(temp, character): #Analyze the character
    global universesAndTimelines, currentUniverse, currentTimeline, numberUniversesUsed, numberTimeLinesUsed, controlComment #Global variables

    if(character == 'i'): #Increment the current timeline value in the current universe
        temp = universesAndTimelines[currentUniverse][currentTimeline]
        temp += 1
        universesAndTimelines[currentUniverse].insert(currentTimeline, temp)
        numberTimeLinesUsed[currentUniverse] += 1 #++ at the number of timelines in the current universe

    elif(character == 'I'): #Increment the current timeline value in all universes
        for i in range(numberUniversesUsed):
            temp = universesAndTimelines[i][currentTimeline]
            temp += 1
            universesAndTimelines[i].insert(currentTimeline, temp)
            numberTimeLinesUsed[i] += 1

    elif(character == 'd'): #Decrement the current timeline value in the current universe
        temp = universesAndTimelines[currentUniverse][currentTimeline]
        temp -= 1
        universesAndTimelines[currentUniverse].insert(currentTimeline, temp)
        numberTimeLinesUsed[currentUniverse] += 1

    elif(character == 'D'): #Decrement the current timeline value in all universes
        for i in range(numberUniversesUsed):
            temp = universesAndTimelines[i][currentTimeline]
            temp -= 1
            universesAndTimelines[i].insert(currentTimeline, temp)
            numberTimeLinesUsed[i] += 1

    elif(character == 's'): #Square the current timeline value in the current universe
        temp = universesAndTimelines[currentUniverse][currentTimeline]
        temp *= temp
        universesAndTimelines[currentUniverse].insert(currentTimeline, temp)
        numberTimeLinesUsed[currentUniverse] += 1

    elif(character == 'S'): #Square the current timeline value in all universes
        for i in range(numberUniversesUsed):
            temp = universesAndTimelines[i][currentTimeline]
            temp *= temp
            universesAndTimelines[i].insert(currentTimeline, temp)
            numberTimeLinesUsed[i] += 1

    elif(character == 'r'): #Square root the current timeline value in the current universe
        temp = universesAndTimelines[currentUniverse][currentTimeline]

        if (temp >= 0):
            temp = sqrt(temp)
        else:
            raise Exception("Cannot take the square root of a negative number.")

        universesAndTimelines[currentUniverse].insert(currentTimeline, temp)
        numberTimeLinesUsed[currentUniverse] += 1
        
    elif(character == 'R'): #Square root the current timeline value in all universes
        for i in range(numberUniversesUsed):
            temp = universesAndTimelines[i][currentTimeline]

            if (temp >= 0):
                temp = sqrt(temp)
            else:
                raise Exception("Cannot take the square root of a negative number.")

            universesAndTimelines[i].insert(currentTimeline, temp)
            numberTimeLinesUsed[i] += 1

    elif(character == '0'): #Set the current timeline value to 0 in the current universe
        temp = 0
        universesAndTimelines[currentUniverse].insert(currentTimeline, temp)
        numberTimeLinesUsed[currentUniverse] += 1

    elif(character == 'o'): #Output the current timeline value in the current universe
        print(universesAndTimelines[currentUniverse][currentTimeline], end="")

    elif(character == 'O'): #Output the current timeline value in all universes
        for i in range(numberUniversesUsed):
            temp = universesAndTimelines[i][currentTimeline]
            print(temp, end="")

    elif(character == 'u'): #Output the current timeline value in the current universe as a character
        if (universesAndTimelines[currentUniverse][currentTimeline] >= 32 and universesAndTimelines[currentUniverse][currentTimeline] < 127):
            print(chr(universesAndTimelines[currentUniverse][currentTimeline]), end="") #Print the character
        elif(universesAndTimelines[currentUniverse][currentTimeline] < 0 or universesAndTimelines[currentUniverse][currentTimeline] > 127):
            print('?', end="") # Print '?' if the value is out of the ASCII range

    elif(character == 'U'): #Output the current timeline value in all universes as a character
        for i in range(numberUniversesUsed):
            if (universesAndTimelines[i][currentTimeline] >= 32 and universesAndTimelines[i][currentTimeline] < 127):
                print(chr(universesAndTimelines[i][currentTimeline]), end="") #Print the character
            elif(universesAndTimelines[i][currentTimeline] < 0 or universesAndTimelines[i][currentTimeline] > 127):
                print('?', end="") # Print '?' if the value is out of the ASCII range

    elif(character == 'G'): #Print the logical scheme of the universes and timelines
        printLogicalScheme()

    elif(character == '/'): #Start a comment
        controlComment = True

    elif(character == '<'): #Go to the previous timeline
        if (currentTimeline == numberTimeLinesUsed[currentUniverse]-1): #If the timeline doesn't exist create it
            numberTimeLinesUsed[currentUniverse] += 1
            universesAndTimelines[currentUniverse].insert(currentTimeline + 1, 0)

        currentTimeline += 1

    elif(character == '>'): #Go to the next timeline
        if (currentTimeline != 0):
            currentTimeline -= 1
        else: #If the timeline doesn't exist create it
            numberTimeLinesUsed[currentUniverse] += 1
            universesAndTimelines[currentUniverse].insert(0, 0)

    elif(character == '['): #Go to the next universe
        if (currentUniverse == numberUniversesUsed-1): #If the universe doesn't exist create it
            numberUniversesUsed += 1
            currentUniverse += 1
            numberTimeLinesUsed.append(1)
            currentTimeline = 0
            
            universesAndTimelines.append([0])
        else:
            currentUniverse += 1
            currentTimeline = 0

    elif(character == ']'): #Go to the previous universe
        if (currentUniverse != 0):
            currentUniverse -= 1
            currentTimeline = 0
        else: #If the universe doesn't exist create it
            numberUniversesUsed += 1
            currentUniverse = 0
            currentTimeline = 0

            numberTimeLinesUsed.insert(0, 1)

            universesAndTimelines.insert(0, [0])

    else: #If the character isn't a command
        if (character != '\n' and character != 13 and character != 10 and character != '' and character != ' ' and character != '\t' and character != '%0'): #If the character isn't a new line, a space, a tab
            raise Exception("Invalid character: \""+character+"\"") #Throw an exception


def printLogicalScheme(): #Print the logical scheme of the universes and timelines
    global universesAndTimelines, currentUniverse, currentTimeline, numberUniversesUsed, numberTimeLinesUsed, controlComment #Global variables

    BORDER = "==="
    maxNumberTimeLinesUsed = 0

    print("\nLogical scheme of the universes and timelines:")

    try:
        for i in range(len(numberTimeLinesUsed)): #Calculate the maximum number of timelines used
            if (numberTimeLinesUsed[i] > maxNumberTimeLinesUsed):
                maxNumberTimeLinesUsed = numberTimeLinesUsed[i]
    except Exception as e:
        raise Exception("Error in the calculation of the maximum number of timelines used: "+e)

    try:
        for i in range(maxNumberTimeLinesUsed):
            print("|", end="") #Print the border of the scheme

            for j in range(numberUniversesUsed): #Print the timelines
                if (i == 0):
                    print(BORDER, end="")
                else:
                    print("   ", end="")

                if (numberTimeLinesUsed[j] > i): #If the timeline exists print it
                    print(universesAndTimelines[j][i], end="")
                else: #If the timeline doesn't exist print a space
                    print(" ", end="")

            if (i == 0):
                print(BORDER+"|", end="")
            else:
                print("   |", end="")

            print()
    except Exception as e:
        raise Exception("Error in the print of the logical scheme: "+e)

    for i in range(numberUniversesUsed): #Print the end border of the scheme
        if (i == 0):
            print("|"+BORDER, end="")
        else:
            print(BORDER+"=", end="")

    print(BORDER+"=|") #Close the scheme and print a new line



main() #Start the interpreter