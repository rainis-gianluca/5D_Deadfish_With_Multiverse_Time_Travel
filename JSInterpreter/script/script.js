/* 
5D Deadfish with Multiverse Time Travel sintax created by: Gianluca Rainis
5D Deadfish with Multiverse Time Travel interpreter created by: Gianluca Rainis
This code is free to use, modify and distribute
To have more information about the sintax and the interpreter go to the GitHub repository: https://github.com/rainis-gianluca/5D_Deadfish_With_Multiverse_Time_Travel.git
This version of the interpreter is written in JavaScript, implemented in HTML and CSS
See the GitHub repository for the other versions of the interpreter
The .vscode folder is used to store the settings of the Visual Studio Code editor, you can delete it if you don't use Visual Studio Code
The comments in the code are written with the help of GitHub Copilot
Last update: 31 December 2024
*/

import { interpreter } from './interpreter.js'; // Import the interpreter function from the interpreter.js file

// Function to run the interpreter with the code from the input field
let runButton = document.getElementById('runButton');
runButton.addEventListener('click', run);

function run() {
    let codeToInterpret = document.getElementById('code').value;
    let output = document.getElementById('output');

    output.innerHTML = '';
    output.innerHTML += interpreter(codeToInterpret);
}