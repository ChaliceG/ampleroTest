var solution = require('./solution.js'),
	interpret = require('./resultsInterpreter.js'),
	assert = require('assert'),
	lodash = require('lodash'),
	scoreTests,
	correctOutput;

correctOutput = function (outputLine) {
	if(!outputLine) {
		assert.fail('output was not a string: '+outputLine);
	}
	var regex = /\w+ is ((not a)|(a poor)|(a hard)|(a fair)) scramble of \w+/;
	assert(outputLine.match(regex), "output was malformed: "+outputLine);
}

scoreTests = function () {
	lodash.each(datas, function(data){
		it('should score '+data.scramble+' '+data.word+' as '+data.difficulty + ' ('+data.reason+')', function(done){
			solution.run([data.scramble+' '+data.word])
			.then(function(resultLine){
				correctOutput(resultLine);
				var r = interpret(resultLine)[0];
				assert.equal(r.scramble, data.scramble);
				assert.equal(r.word, data.word);
				assert.equal(r.difficulty, data.difficulty);
				done();
			}).catch(function(err){
				done(err);
			});
		});
	});
};

var datas = [
	{//Fails because output is "...a not a..."
		scramble: 'IRONY',
		word: 'IRONY',
		difficulty: 'not',
		reason: 'basic not'
	},
	{
		scramble: 'IOYRN',
		word: 'IRONY',
		difficulty: 'poor',
		reason: 'first letter is correct'
	},
	{//The solution scores this as fair, but I'm certian its poor.
	///It has 2 consecutive letters in the same place and "doesn't look real"
	///perhaps the solution thinks 'OI' is premissible?
		scramble: 'YROIN',
		word: 'IRONY',
		difficulty: 'poor',
		reason: "2 consecutive correct chars + doesn't look real"
	},
	{
		scramble: 'ONYRI',
		word: 'IRONY',
		difficulty: 'hard',
		reason: "all characters incorrect + looks real"
	},
	{
		scramble: 'ETHA',
		word: 'HEAT',
		difficulty: 'hard',
		reason: "all characters incorrect + looks real with TH special case"
	},
	{
		scramble: 'LLARALEP',
		word: 'PARALLEL',
		difficulty: 'hard',
		reason: "all characters incorrect + looks real with double consonate"
	},
	{
		scramble: 'ALLLERAP',
		word: 'PARALLEL',
		difficulty: 'fair',
		reason: "looks fake (tripple consonate)"
	},
	{
		scramble: 'INOYR',
		word: 'IRONY',
		difficulty: 'fair',
		reason: "one letter correct in middle of word"
	}
]
module.exports = scoreTests;