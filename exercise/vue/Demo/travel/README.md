# travel

> A Vue.js project

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report

# run unit tests
npm run unit

# run e2e tests
npm run e2e

# run all tests
npm test
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).

？？？
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
static文件夹				存放静态资源,存放在该文件夹下的文件能直接访问
node_modules 			存放项目依赖文件的第三方包
pages					存放所有网页的集合
|---home				首页
	|---Home.vue        首页模板
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


以.vue后缀的，存放的是单文件组件。-存放的是vue的组件
其单文件的模板，放置template标签中间
其单文件的逻辑，放置script标签中间
其单文件的样式，放置在style组件的中间

路由就是根据网址的不同，返回给用户的内容不同；

刚开始访问的根目录；
index.html显示，
src->main.js开始执行-->创建一个路由router->展示模板内容->在模板的router-view内容中展示路由对应的内容(其内容的选定，是有src->router->index.js指定)

多页面应用，即每次页面跳转，都会请求一个新的页面；
页面跳转->返回HTML
优点：首屏时间快，SEO效果好
缺点：页面切换较慢

单页面应用，Vue应用就是一个单页面应用，js会感知URL变化，使用js动态清除页面内容，和展示页面内容；路由由前端处理；
页面跳转->js渲染
优点：页面切换快
缺点：首屏时间稍慢，SEO差；可以使用类似服务器端渲染技术处理seo差的情况；

项目代码初始化：
创建的是移动端的html，所有要在index.html中的head标签中添加属性：
minimum-scale=1.0,maximum-scale=1.0,user-scalable=no
minimum-scale允许用户缩小的最大比例。
maximum-scale允许用户发大的最大比例
user-scalable是否允许双指缩放

移动端浏览器默认参数匹配：使用reset.css

移动端1像素边框问题；
在一倍屏里，一像素会被显示成多像素的情况；需要引入border.css文件

移动端点击延迟300ms问题：
在某些机型、浏览器中，使用click事件，会有延迟300ms后才处理。使用fastclick模块处理

在项目中使用缩进出现不能使用tab的情况；
是由于使用vue-cli构建项目时，安装了eslint，导致了tab键一使用就报错，必须变成两个空格；
注意：
1、在每个文件末尾都要添加一个换行符，否则会报错
2、在使用router时，每一个value值前面都添加一个空格

header头部导航
需要用到styleus模块，需要npm install stylus -s 安装
还是styleus-loader

在home里创建一个components文件夹，用于存放小模块，Home.vue是整个页面最外层的容器，其内部可以拆分成很多小的组件；


在chrome浏览器中添加插件vue devtools，用于调试vue

在vue中可以使用axios,跨平台的数据请求第三方模块

Vuex,数据框架，vue只能承担视图层的内容，涉及到数据的时候使用vuex的框架 
安装vuex，使用 npm install vuex --save
引入vuex

使用keepalive进行性能优化
//每次路由切换的时候，都会进行请求；
//
在使用真机测试时，使用ip，打不开项目时：
在package.json中的scripts下的dev的值中添加在中间加了一个--host 0.0.0.0表示可以使用ip地址来打开项目

使用低版本的安卓手机显示白屏的情况
1、手机浏览器默认不支持promise：安装第三方包babel-polyfill,如果浏览器没有es6的新特性，会自动添加；

使用npm run build，完成项目的上线；在dist中的代码，就是最后上线的代码；

如果想要在访问的时候访问的时/project的路径对应的是项目的入口文件时，需要在build目录下的config文件夹里的index.js文件中的build选项的assetsPublicPath的值改为/project,这样在输入网址为IP/project时就显示的时项目的入口文件了