//用于处理所有和文件相关的操作

let fs = require('fs');
let path = "./uploads/";
//因为是异步的，如果直接返回会出错
//获取uploads路径下的文件夹
exports.getAllAlbums = function (callback) {
	let folder = [];
	let Images = [];
	fs.readdir(path, function (err, files) {
		if (err) {
			callback('没有上传的文件夹', null);
			return;
		}
		(function iterator(i) {
			if(i == files.length){
				console.log(folder,Images)
				//遍历结束
				callback(null,{
					"folder":folder,
					"Images":Images
				});
				return ;
			}
			fs.stat(path + files[i], function (err, stats) {
				if(err){
					callback('找不到文件'+files[i],null)
				}
				if(stats.isFile()){
					Images.push(files[i]);
				}else if(stats.isDirectory()){
					folder.push(files[i]);
				}
				iterator(i+1);
			})
		})(0);
	});
}

//通过文件名获取图片
exports.getAllImagesByAlbumName = function (albumName, callback) {
	let folder = [];
	let Images = [];
	albumFolder = path + albumName;
	fs.readdir(albumFolder,function(err,files){
		if(err){
			callback(albumName+'文件夹读取出错',null);
			return;
		}
		(function iterator(i){
			if(i == files.length){
				callback(null,{
					"folder":folder,
					"images":Images
				});
				return;
			}
			let newFolder = albumFolder+'/'+files[i];
			// console.log(newFolder);
			fs.stat(newFolder,function(err,stats){
				if(stats.isFile()){
					//如果是文件，放到images列表中；
					Images.push(files[i]);
				}else if(stats.isDirectory()){
					//如果是目录，放到folder数组中
					folder.push(files[i]);
				}
				iterator(i+1);
			});
		})(0);
	});
	
}

// //读取文件夹
// function readDir(albumName, callback) {
// 	let allImages = [];
// 	// albumName = path + albumName;
// 	console.log("加了地址的："+albumName);
// 	fs.readdir(albumName, function (err, files) {
// 		if (err) {
// 			callback('没有上传的文件夹', null);
// 			return;
// 		}
// 		(function iterator(i) {
// 			if (i == files.length) {
// 				//遍历结束
// 				callback(null, allImages);
// 				return;
// 			}
// 			fs.stat(albumName +'/'+ files[i], function (err, stats) {
// 				if (err) {
// 					callback('找不到文件' + files[i], null)
// 				}
// 				if (stats.isFile()) {
// 					allImages.push(files[i]);
// 				} else if (stats.isDirectory()) {
// 					albumName = albumName + files[i];
// 					readDir(albumName, callback);
// 				}
// 				iterator(i + 1);
// 			})
// 		})(0);
// 	});
// }