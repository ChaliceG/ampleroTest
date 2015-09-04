var solution = require('./solution.js'),
	interpret = require('./resultsInterpreter.js'),
	assert = require('assert'),
	lodash = require('lodash'),
	pairwiseTests = require('./pairwiseTests.js');

describe('The Solution', function() {

	/*  DOES THIS THING EVEN WORK TESTS  */
	it('should exit with an error with a garbage file', function(done){
		solution.run(['kjhjhkhkkjhui4wsiltfy;guo8y'])
		.then(function(){
			done(new Error('but it succeded!'));
		}).catch(function(){
			done();
		});
	});
	it('should exit with an error with slightly off input (kile likee)', function(done){
		solution.run(['kile likee'])
		.then(function(){
			done(new Error('but it succeded!'));
		}).catch(function(){
			done();
		});
	});
	it('should be able to take multiple pairs of input', function(done){
		solution.run(['kile like', 'ife fie', 'hbel bleh' ])
		.then(function(){
			done();
		}).catch(function(err){
			done(err);
		});
	});

	/*  BASIC USE CASES  */
	it('should permit double consonates in a "real" word', function(done){
		solution.run(['LLARALEP PARALLEL'])
		.then(function(resultLine){
			var r = interpret(resultLine)[0];
			assert.equal(r.scramble, 'LLARALEP');
			assert.equal(r.word, 'PARALLEL');
			assert.equal(r.difficulty, 'hard');
			done();
		}).catch(function(err){
			done(err);
		});
	});
	it('should recognize "TH" as part of a "real" word', function(done){
		solution.run(['ATHE HEAT'])
		.then(function(resultLine){
			var r = interpret(resultLine)[0];
			assert.equal(r.scramble, 'ATHE');
			assert.equal(r.word, 'HEAT');
			assert.equal(r.difficulty, 'hard');
			done();
		}).catch(function(err){
			done(err);
		});
	});
	it('should not permit tripple consonates as a "real" word', function(done){
		solution.run(['ALLLERAP PARALLEL'])
		.then(function(resultLine){
			assert.equal(interpret(resultLine)[0].difficulty, 'fair');
			done();
		}).catch(function(err){
			done(err);
		});
	});

	describe('#DATA DRIVEN SCORING TESTS', function(){
		pairwiseTests();
	})
});
