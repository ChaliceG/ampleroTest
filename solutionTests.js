var solution = require('./solution.js'),
	interpret = require('./resultsInterpreter.js'),
	assert = require('assert'),
	lodash = require('lodash'),
	scoreTests = require('./scoreTests.js');


describe('The Solution', function() {

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
	it('should be able to take multiple input lines', function(done){
		solution.run(['kile like', 'ife fie', 'hbel bleh' ])
		.then(function(){
			done();
		}).catch(function(err){
			done(err);
		});
	});

	describe('#DATA DRIVEN SCORING TESTS', function(){
		scoreTests();
	})
});
