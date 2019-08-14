


module.exports={
	mode:'development',
	entry:{
		'index':'.\\src\\page\\index\\index.js',
		'login':'.\\src\\page\\login\\index.js'
	},
	output:{
		filename:'.\\[name].js',
		path:__dirname + '\\dist\\js'
	}
}