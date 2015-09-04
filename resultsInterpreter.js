var lodash = require('lodash'),
	parseLine;

parseLine = function (line) {
	var words = line.trim().split(' '),
		expectedLength = 7;
	if(words.length !== expectedLength) {
		throw 'Output line is not the expected number of words ('+expectedLength+'): ' + line;
	}
	return {
		scramble: words[0],
		difficulty: words[3] === 'a'?words[2]: words[3],
		word: words[6]
	};
};

module.exports = function (textIn) {
	var text = textIn.trim().split('\n'),
		results = [];
	lodash.each(text, function(line){
		if(line.indexOf('a not') > -1){
			//this is annoying. stupid busted solution.
			line = line.replace('a not', 'not');
		}
		results.push(parseLine(line));
	});
	return results;
};