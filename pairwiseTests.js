var solution = require('./solution.js'),
	interpret = require('./resultsInterpreter.js'),
	assert = require('assert'),
	lodash = require('lodash'),
	pairwiseTests;

pairwiseTests = function () {
	lodash.each(datas, function(data){
		it('should score '+data.scramble+' '+data.word+' as '+data.difficulty, function(done){
			solution.run([data.scramble+' '+data.word])
			.then(function(resultLine){
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
	{
		scramble: 'ONYRI',
		word: 'IRONY',
		difficulty: 'hard'
	},
	{
		scramble: 'INOYR',
		word: 'IRONY',
		difficulty: 'fair'
	},
	{
		scramble: 'IOYRN',
		word: 'IRONY',
		difficulty: 'poor'
	},
	{
		scramble: 'IRONY',
		word: 'IRONY',
		difficulty: 'not'
	}
]
module.exports = pairwiseTests;