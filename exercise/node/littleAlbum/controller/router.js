//向外暴露中间件,用来控制

let file = require('../models/file');
let formialbe = require('formidable');
let path = require('path');
let fs = require('fs');


exports.showIndex = function (req, res) {
	//要等获取文件信息后才能执行
	// res.render('index',{
	// 	"albums":file.getAllAlbums()
	// });
	//这就是node.js的编程思维，就是所有的东西，都是异步的
	//所以，内层函数，不是return回来东西，而是调用高层函数提供的回调函数，把数据当做回调函数的参数来使用
	file.getAllAlbums(function (err, allAlabums) {
		if (err) {
			res.send(err)
		}
		res.render('index', {
			"albums": allAlabums
		})
	})
}

//相册页
exports.showAlbum = function (req, res) {
	// 遍历相册中的所有图片
	// res.send('相册'+req.params.albumName);
	let albumName = req.params.albumName;
	//具体页面交给model
	file.getAllImagesByAlbumName(albumName, function (err, allAlabums) {
		if (err) {
			res.render('err');
			return;
		}
		res.render('albums', {
			'albumsname': albumName,
			'allAlabums': allAlabums
		})
	})
}

//上传
exports.doUp = function (req, res) {

	//获取所有的文件夹
	file.getAllAlbums(function (err, allAlabums) {
		if (err) {
			res.send(err)
		}
		res.render('up', {
			"albums": allAlabums.folder
		});
	})


}
//操作上传的post请求
exports.doPost = function (req, res,next) {
	//要用到第三框架，formidable;

	//图片的上传路径           ,如果以/开头为C盘根目录
	let form = new formialbe.IncomingForm();
	form.uploadDir = path.normalize(__dirname + "/../tempup/");

	//传进来
	form.parse(req, function (err, fileId, files) {
		// console.log(fileId);
		// console.log(files);
		if (err) {
			next();
			//不受理这个中间件请求，往下运行
			console.log('出错了');
			res.send('出错了');
			return;
		}

		//判断文件尺寸
		let size = parseInt(files.file.size);
		// console.log(size)  		//size为字节数
		if(size > 100*1024){                          //100KB,要比较大小，要转成字节比较
			res.send('图片应该小于1M')
			//删除图片
			fs.unlink(files.file.path,function (err) {
				if(err){
					res.send(err)
				}
			})
			return
		}

		//改名
		let oldpath = files.file.path;
		let newName = new Date().getTime() +'_'+ files.file.name;
		let newpath = path.normalize(__dirname + "/../uploads/"+fileId.folder+'/'+newName)
		fs.rename(oldpath, newpath, function (err) {
			if (err) {
				console.log('出错了'+err);
				next();
			}
			res.send('上传成功')  ;
		});
	})
	   return;
}