const http = require('http');
const url = require('url');

let server = http.createServer(function(req,res){
	
	//let pathname = url.parse(req.url).pathname;
	let queryObj = url.parse(req.url,true).query;


	let age = queryObj.age;
	let name = queryObj.name;
	let sex = queryObj.sex;

	res.end('服务器收到了表单'+name + age +sex);

}).listener(3000,'127.0.0.1');