
//require表示引包，就是引用一个特殊功能
const http = require("http");

//创建服务器，参数为一个回调，表示有请求进来时进行的处理
let server = http.createServer(function(req,res) {
	//req表示强求，request,res表示响应，response
	//设置Http头部，状态码是200，文件类型是html,字符集是utf-8
	res.writeHead(200, {'Content-type': 'text/html;charset=UTF-8'});
	res.end('这是测试');

});

//监听3000端口

server.listen(3000,"127.0.0.1");
