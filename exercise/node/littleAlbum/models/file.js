//用于处理所有和文件相关的操作

let fs = require('fs');
//因为是异步的，如果直接返回会出错
//获取uploads路径下的文件夹
exports.getAllAlbums = function (callback) {
	path="./uploads/";
	// readDir(albumName,callback);
	let album = [];
	console.log(path);
	fs.readdir(path, function (err, files) {
		if(err){
			callback('没有上传的文件夹',null);
			return;
		}
		(function iterator(i) {
			if(i == files.length){
				console.log(album);
				//遍历结束
				// console.log(allAlbums)
				callback(null,{
					"albums":album,
				});
				return ;
			}
			fs.stat(path + files[i], function (err, stats) {
				// console.log(path+files[i]);
				if(err){
					callback('找不到文件'+files[i],null)
				}
				if(stats.isFile()){
					album.push(files[i]);
				}else if(stats.isDirectory()){
					path = path+files[i];
					readDir(path,callback);
				}
				iterator(i+1);
			})
		})(0);
	});
}

//通过文件名获取图片
exports.getAllImagesByAlbumName = function (albumName,callback) {
	// readDir(albumName,callback);
	//集中精力找到文件夹
	// return allImages;

}
//读取文件夹
function readDir(albumName,callback){
	let allImages = [];
	console.log(albumName);
	fs.readdir(albumName, function (err, files) {
		if(err){
			callback('没有上传的文件夹',null);
			return;
		}
		(function iterator(i) {
			if(i == files.length){
				//遍历结束
				// console.log(allAlbums)
				callback(null,allImages);
				return ;
			}
			fs.stat(albumName + files[i], function (err, stats) {
				if(err){
					callback('找不到文件'+files[i],null)
				}
				if(stats.isFile()){
					allImages.push(files[i]);
				}else if(stats.isDirectory()){
					albumName = albumName+files[i];
					readDir(albumName,callback);
				}
				iterator(i+1);
			})
		})(0);
	});
}