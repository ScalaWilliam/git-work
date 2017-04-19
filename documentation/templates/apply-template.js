const fs = require('fs');
const assert = require('assert');

assert(process.argv.length > 2, "Should have a filename");
const filename = process.argv.pop();
const fileContents = fs.readFileSync(filename, 'utf8');
const fileLines = fileContents.split("\n");
const navigationStart = "<!-- navigation -->";
const navigationEnd = "<!-- /navigation -->";
const navigationStartLine = fileLines.indexOf(navigationStart);
const navigationEndLine = fileLines.indexOf(navigationEnd);
if (navigationStartLine === -1 || navigationEndLine === -1) {
    process.exit();
}
const navigationFilename = __dirname + '/navigation.md';
const navigation = fs.readFileSync(navigationFilename, 'utf8');

// console.log(navigation);
const output = fileLines.slice(0, navigationStartLine + 1).concat([navigation]).concat(fileLines.slice(navigationEndLine));
const outputText = output.join("\n");
fs.writeFileSync(filename, outputText);
