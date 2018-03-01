const fs = require('fs');
const args = require('process').argv.slice(2);
const path = require('path');

/**
 * Edits all files in the specified directory.
 * Replaces the first argument with the other.
 *
 * Example:
 *
 *  $ node rename.js $PWD pattern-to-change replacement
 */

for (const file of fs.readdirSync(args[0])) {
    const f = path.join(args[0], file);
    fs.readFile(f, 'utf8', (err, data) => {
        if (err) throw err;
        const newData = data.replace(args[1], args[2]);

        fs.writeFile(f, newData, (err) => {
            if (err) throw err;

            console.log(f + ' changed.');
        });
    });
}
