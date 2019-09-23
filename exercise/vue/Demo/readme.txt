命令行工具（cLi)
脚手架工具
要使用webpack配置

安装
npm install --global vue-cli

创建一个基于webpack打包工具 的项目
vue init webpack [创建的文件夹目录]


脚手架文件
readme.md				项目说明文件
package.json			存放依赖包，开发的软件所需的依赖
package-lock.json		确定开发的软件，所需依赖的版本
LICENSE					开源协议的说明
index.html				项目首页模板
。postcssrc.js 			是postcss的配置选项
.gitignore				使用git上传时，该文件配置隐藏的文件；
.eslintrc.js 			存放了代码的规范，相当于项目的自检测
.eslintignore           在该文件中存放的文件夹不受.eslintrc.js文件的检测 
.editorconfig           配置了编辑器的语法
.babelrc				项目是vue的单文件组件的写法，需要该文件的语法解析器对项目进行转换；
static文件夹				存放静态资源
node_modules 			存放项目依赖文件的第三方包
src						存放整个项目的源代码
|---main.js    			项目入口
|---App.vue 			项目最原始的根组件
|---router
	|---index.js 		存放项目的所有路由
|---components 			存放的小组件
|---assets				存放图片
config 					存放项目的配置信息
|---dev.env.js 			存放开发环境的配置信息
|---index.js 			
|---prod.env.j    		存放线上环境的配置信息
build					存放webpack的配置内容
|---webpack.base.conf.js 存放基础的webpack配置项
|---webpack.dev.conf.js 存放开发环境的配置项
|---webpack.prod.conf.js 存放线上环境的配置项



以.vue后缀的，存放的是单组件文件。