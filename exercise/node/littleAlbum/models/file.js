//用于处理所有和文件相关的操作

let fs = require('fs');
//因为是异步的，如果直接返回会出错
//获取uploads路径下的文件夹
exports.getAllAlbums = function (callback) {
	fs.readdir("./uploads", function (err, files) {
		if(err){
			callback('没有上传的文件夹',null);
			return;
		}
		let allAlbums = [];
		(function iterator(i) {
			if(i == files.length){
				//遍历结束
				// console.log(allAlbums)
				callback(null,allAlbums);
				return;
			}
			fs.stat('./uploads/' + files[i], function (err, stats) {
				if(err){
					callback('找不到文件'+files[i],null)
				}
				if(stats.isDirectory()){
					allAlbums.push(files[i]);
				}
				iterator(i+1);
			})
		})(0);
	});

	//集中精力找到文件夹
	return ['xixi', '小ha狗']
}

//通过文件名获取图片
exports.getAllImagesByAlbumName = function (albumName,callback) {
	fs.readdir("./uploads/"+albumName, function (err, files) {
		if(err){
			callback('没有上传的文件夹',null);
			return;
		}
		let allImages = [];
		(function iterator(i) {
			if(i == files.length){
				//遍历结束
				// console.log(allAlbums)
				callback(null,allImages);
				return;
			}
			fs.stat('./uploads/' + +albumName + files[i], function (err, stats) {
				if(err){
					callback('找不到文件'+files[i],null)
				}
				if(stats.isFile()){
					allImages.push(files[i]);
				}
				iterator(i+1);
			})
		})(0);
	});

	//集中精力找到文件夹
	return allImages;

}