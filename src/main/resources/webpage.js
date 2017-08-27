var system = require('system');
var address = system.args[1];//参数，url
var page = require('webpage').create();
var url = address;
page.open(url, function(status) {
	if (status !== 'success') {
		console.log('fail');
	} else {
		console.log(page.content);
	}
	phantom.exit();
});