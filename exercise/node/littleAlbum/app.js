//app为最高层，router控制，models用于打工，就是干活

let express = require('express');
let app = express();

//已经在package.json中写入了入口文件，不用写对应文件
let router = require('./controller');
//设置模板引擎               ，后面使用的res.render()中直接写模板名字即可
app.set('view engine','ejs');

//路由中间件,因为是使用 的中间件，回调的时候自动会传递req,res，所以不用传参数
//静态资源，如果先找到静态资源，后面的就不会运行
app.use(express.static('./public'));
app.use(express.static('./uploads'));
//首页
app.get('/',router.showIndex);
app.get('/up',router.doUp);
app.get('/:albumName',router.showAlbum);
app.post('/up',router.doPost);

//404页面
app.use(function (req,res) {
	res.render('err');
})

app.listen(3000,function () {
	
})