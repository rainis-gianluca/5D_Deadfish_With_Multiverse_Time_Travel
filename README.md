5D Deadfish with Multiverse Time Travel
========================================
# Overview

# Deadfish Language Features

The 5D Deadfish with Multiverse Time Travel programming language have 18 simple commands.

|cmd| description                                                                               |
|:-:|:------------------------------------------------------------------------------------------|
| i | increment current timeline's current universe's number                                    |
| I | increment every number                                                                    |
| d | decrement current timeline's current universe's number                                    |
| D | decrement every number                                                                    |
| s | square current timeline's current universe's number                                       |
| S | square every number                                                                       |
| r | square root current timeline's current universe's number                                  |
| R | square root every number                                                                  |
| > | go to the next timeline                                                                   |
| < | go to the previous timeline                                                               |
| [ | go to the next universe                                                                   |
| ] | go to the previous universe                                                               |
| 0 | reset current timeline's current universe's number to 0                                   |
| o | output current timeline's current universe's number                                       |
| O | output every number                                                                       |
| u | output current timeline's current universe's ASCII                                        |
| U | output every ASCII                                                                        |
| / | start the comment                                                                         |
| G | output the logic diagramm of the multiverse and the timelines                             |

While

|cmd| description                                                                                |
|:-:|:-------------------------------------------------------------------------------------------|
| { | start the while                                                                            |
| } | end the while                                                                              |
| ( | start the condition of the while                                                           |
| ) | end the condition of the while                                                             |
| == | equal comparison operator                                                                 |
| != | different comparison operator                                                             |
| << | minor comparison operator                                                                 |
| >> | greater comparison operator                                                               |
| <= | minor or equal comparison operator                                                        |
| >= | greater or equal comparison operator                                                      |
| o | read current timeline's current universe's number                                          |

While syntax

```
/Other commands
(comparison){commands}
/Other commands
```
See While Example for more information.

Remember: you can't use in the comparison the different timelines.

Important: in while comparison, you can't use 'I', 'D', 'S', 'R', 'O', 'U', 'G'.

Important: in while comparison, when you write the comparison operator, YOU MUST WRITE A SPACE BEFORE AND AFTER THE OPERATOR!

# Important notes
When you travel between the universes, you automaticaly go to the timeline 0 of the universe.

In the while, when you travel throwght universes and timelines, YOU CAN'T CREATE NEW UNIVERSES OR TIMELINES! This should create an exception.

# Implementation
To interpret the 5D Deadfish with Multiverse Time Travel, you can use the java interpreter, and you just have to have java installed in your computer.

# Example

Hello World
```
>>> it print "Hello, world!" in numbers

iiisdsiiiiiiiioiiiiiiiiiiiiiiiiiiiiiiiiiiiiioiiiiiiiooiiioddddddddddddddddddddd
ddddddddddddddddddddddddddddddddddddddddddddddoddddddddddddoddddddddddddddddddd
ddsddoddddddddoiiioddddddoddddddddodddddddddddddddddddddddddddddddddddddddddddd
dddddddddddddddddddddddo
```

```
>>> it print "Hello, world!" in ASCII

iiisdsiiiiiiiiuiiiiiiiiiiiiiiiiiiiiiiiiiiiiiuiiiiiiiuuiiiuddddddddddddddddddddd
ddddddddddddddddddddddddddddddddddddddddddddddudddddddddddduddddddddddddddddddd
ddsddudddddddduiiiudddddduddddddddudddddddddddddddddddddddddddddddddddddddddddd
dddddddddddddddddddddddu
```

```
>>> it print "Hello, world!" in ASCII with different universes

iiiiiiiisiiiiiiii[
iiiiiiiiiisi[
iiiiiiiiiisiiiiiiii[
iiiiiiiiiisiiiiiiii[
iiiiiiiiiisiiiiiiiiiii[
iiiiiisdddd[
iiiiiiiiisiiiiii[
iiiiiiiiiisiiiiiiiiiii[
iiiiiiiiiisiiiiiiiiiiiiii[
iiiiiiiiiisiiiiiiii[
iiiiiiiiiis
U
```

While Example
```
iii /3 in universe number 1
[iiii /4 in universe number 2
]

(o >> [o){d} / in the control see if the current universe's number is bigger than the next universe's number
/in the execution while universe's number 1 is minor than the universe's number 2, decrement the universe's number 2 value

/Other commands
```