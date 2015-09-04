var solution = function () {},
	spawn = require('child_process').spawn,
	Promise = require('bluebird'),
	fs = require('fs'),
	writeFile;

solution.prototype.run = function (fileData) {
	return new Promise(function(resolve, reject){
		writeFile(fileData)
		.then(function(){
			var java = spawn('java', ['-jar', 'solution.jar', 'testData.txt']),
				stdout = '',
				stderr = '';
			//this is super bad and will chug for big streams :/
			java.stdout.on('data', function(data){stdout = stdout + data});
    		java.stderr.on('data', function(data){stderr = stderr + data});
    		java.on('close', function(status){
    			if(status !== 0 || stderr!=='') {
    				reject('process exited with code '+status+' and error: \n'+stderr);
    			} else {
    				resolve(stdout);
    			}
    		});
		}).catch(function(err){
			reject('Error while writing test file: \n'+err);
		});
	});
};

writeFile = function (fileData) {
	var pfs = Promise.promisify(fs.writeFile);
	if(fileData.length !== undefined) {
		return pfs('testData.txt', fileData.join('\n'));
	} else {
		return pfs('testData.txt', fileData);
	}
};


module.exports = new solution();