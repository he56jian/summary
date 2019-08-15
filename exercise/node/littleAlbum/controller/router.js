//向外暴露中间件,用来控制

let file = require('../models/file')


exports.showIndex = function (req,res) {
	//要等获取文件信息后才能执行
	// res.render('index',{
	// 	"albums":file.getAllAlbums()
	// });
	//这就是node.js的编程思维，就是所有的东西，都是异步的
	//所以，内层函数，不是return回来东西，而是调用高层函数提供的回调函数，把数据当做回调函数的参数来使用
	file.getAllAlbums(function (err,allAlabums) {
		if(err){
			res.send(err)
		}
		res.render('index',{
			"albums":allAlabums.albums,		//这个是文件夹
			"albumImages":allAlabums.image,

		})
	})
}

//相册页
exports.showAlbum = function (req,res) {
	// 遍历相册中的所有图片
	// res.send('相册'+req.params.albumName);
	let albumName = req.params.albumName;
	//具体页面交给model
	file.getAllImagesByAlbumName(albumName,function (err,imagesArrar) {
		if(err){
			// console.log('出现错误了')
			res.render('err');
			return;
		}
		res.render('albums',{
			'albumname':req.params.albumName,
			'albums':imagesArrar
		})
	})



}