#1、标题 
在标题下一行使用=和-标记，一级和二级标题；

这个是一级标题
=
这个是二级标题
-

使用#标记  
可以表示1-6级标题，一级标题对应一个#号，二级标题对应两个#号，以此类推
#一级标题
##二级标题
###三级标题
####四级标题
#####五级标题
######六级标题

#2、段落  
Markdown段落没有特殊的格式，直接编写文字就好，段落的换行是使用两个空格加上回车。  

#3、字体  

*斜体文本*  
两个星号中间，为斜体  
_斜体文本_  
两个下划线中间，为斜体  
**粗体文本**  
四个星号中间，为粗体  
__粗体__  
四个下划线中间，为粗体  
***斜粗体***  
六个星号中间，为斜粗体  
___斜粗体___  
六个下划线中间，为斜粗体  
#4、分割线 
用三个以上的星号、减号、底线建立一个分割线，行内不能有其他东西，也可以在星号或者减号中间插入空格：
***
* * *
******
- - -
-------
#5、删除线
如果段落文字上要添加删除线，在文字两端加上del标签即可（原来的~~标记被去掉了，因为前端预览是js渲染的，后端输出php处理的，两者的markdown语法有差异)  
<del>删除线</del>
#6、下划线
通过u标签实现  
<u>下划线文本</u>
#7、脚注
对文本的补充说明,和超链接一致 
[^要注明的文本]  
用法：  
这是一个链接到谷歌的[^脚注]。
[^脚注]: http://www.google.com
#8、列表
无序列表：使用星号，加号，减号作为列表标记（记得加空格)：  
* 第一
* 第二
* 第三
+ 第一
+ 第二
+ 第三
- 第一
- 第二
- 第三  

有序列表：使用数值加上.号来表示：
1. 第一项
2. 第二项
3. 第三项

列表的嵌套：在子列表的选项中添加四个空格：
1. 第一项目：
	- 第一个元素
	- 第二个元素
2. 第二个项目：
    + 第一个元素
    + 第二个元素

#9、区块
区块引用是在段落开头>符号，然后加上一个空格：
> 区块引用
> 区块第二行
> 区块第三行

嵌套层  
> 最外层
> > 第二层
> > > 第三层

#10、代码
使用两个反引号之间包裹起来的为代码片段(只能显示一行)：
`let a =1`
#####代码区块  
代码区块用4个空格或者一个制表符：  
	let a =1
	a+=1;
	b=a;
也可以用三个反引号包裹一段代码，并指定一种语言，或者不指定  

```javascript
let a=1;
function add(a,b){
	return a+b;
}
$(document).ready(function (){
	alert('runoob');
});
add(a,2);
```
#11、链接
用法：
`[链接名称](链接地址)`或者`[链接名称]<链接地址>`

> 这是一个链接[百度](www.baidu.com)
> 这是一个链接[163](www.163.com)

链接也可以用变量来代替，文档末尾附带变量地址：
这个链接用1作为网站变量：[google][1]
这个链接用runoob作为网站变量:[Runoob][runoob]
然后在文档的结尾为变量赋值(网站)
[1]:http://www.baidu.com
[runoob]:http://www.runoob.com

#12、图片
图片格式语法：
`![alt 属性文本](图片地址)`
`![alt 属性文本](图片地址 "可选标题")`
- 开头一个感叹号！
- 接着一个方括号，里面放上图片的替代文字（不知道有什么用，但是删除不了)
- 截至一个普通括号，里面放上图片的网址，最后可以用引号包住并加上选择性的title属性的文字

![R1UNOOB 图标](http://static.runoob.com/images/runoob-logo.png)
![RU2NOOB 图标](http://static.runoob.com/images/runoob-logo.png "RUNOOB")
对图片网址使用变量：  
这个链接用1作为网址遍历[RUNOOB][1].
然后在文档结尾变量赋值
[1](http://s1tatic.runoob.com/images/runoob-logo.png)
要使用宽高设置的话，用<img>标签

#13、表格
Markdown制作表格使用｜来分隔不同的单元格，使用-来分隔表头和其他行。

| 表头一 |表头二  |
|  ----  | ----  |
| 单元格  | 单元格 |
| 单元格  | 单元格 |

+ -:设置内容和标题居右对齐
+ ：-设置内容和标题居左对齐
+ ：-：设置内容和标题剧中对齐

|  左对齐  |右对齐  |居中对齐  |
|:----|----:|:----:|
|  单元格  |单元格二  |单元格三  |

#14、其他
1. 支持html元素
2. 需要显示特定符号，需要使用转义字符；
```
\   反斜线
`   反引号
*   星号
_   下划线
{}  花括号
[]  方括号
()  小括号
#   井字号
+   加号
-   减号
.   英文句点
!   感叹号
```
3. Mathjax，用于数学公式的显示


#问题：  
1、在使用markdownpad2中，有时会出现列表使用不了的情况；
	解决：在tools下的options,markdown里的markdownprocessor选项选择github~~~(...)的选项，然后把后面的授权key输入就可以了；
##markdownpad2的授权Key

####邮箱地址：
Soar360@live.com
####授权秘钥：
GBPduHjWfJU1mZqcPM3BikjYKF6xKhlKIys3i1MU2eJHqWGImDHzWdD6xhMNLGVpbP2M5SN6bnxn2kSE8qHqNY5QaaRxmO3YSMHxlv2EYpjdwLcPwfeTG7kUdnhKE0vVy4RidP6Y2wZ0q74f47fzsZo45JE2hfQBFi2O9Jldjp1mW8HUpTtLA2a5/sQytXJUQl/QKO0jUQY4pa5CCx20sV1ClOTZtAGngSOJtIOFXK599sBr5aIEFyH0K7H4BoNMiiDMnxt1rD8Vb/ikJdhGMMQr0R4B+L3nWU97eaVPTRKfWGDE8/eAgKzpGwrQQoDh+nzX1xoVQ8NAuH+s4UcSeQ==

