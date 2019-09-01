#Vue

> 兼容性，不支持IE8及以下浏版本，支持所有兼容ECMAScript 5的浏览器

####引入方式
> 1. 直接下载并用`<script>`标签引入，`Vue` 会被注册成一个全局变量；
> 2. 使用CDN引入。(第二条为明确的版本和构建文件，避免新版本造成不可预期的破坏
>  - `<script src="https://cdn.jsdelivr.net/npm/vue"></script>`
>  - `<script src="https://cdn.jsdelivr.net/npm/vue@2.6.10/dist/vue.js"></script>`


#####开始使用
> **使用一.** 在script中创建vue的实例；
> > let app = new Vue({})  
> > 在实例中的构造函数中的参数  
> 1. el:要接管的范围  
> 2. data:要接管的内容。（其可以是数组)   

> **使用二.** 在html的body中，使用内容；  
> > 1. 在body标签中，对应接管范围内，使用{{data里的key}}来获取值，并显示.
> > 2. 如果是data里的一个key是数组，可以在标签中添加属性v-for(item in key),来遍历展示该标签，使用{{item}}对应显示每个值；

> **使用三.** 绑定事件；
> > 1. 在需要添加事件的标签中，添加属性v-on:click='handlebtnclick',其中click为事件一种，点击事件；
> > 2. 在vue的构造函数中，创建一个methods的键，其值对应的是各个事件的方法名及方法；例子:
```
methods:{   
		handleBtnclick:function(){
			alert('click')
		}
}```

>**使用四.**数据的双向绑定；
> > 1. 在标签中添加v-model属性，值为一个变量值，好像绑定value值；
> > 2. 当含有该model的标签中内容发生变化时，data中对应的key中的value就会发生变化，当data中的value发生变化时，该标签中的value也会变化；
>> 3. 如果在methods中需要用到双向绑定的数据时，使用this.数据获取；  

>**使用五.**全局组件；  
>>1. 使用`Vue.component('zhuName',{template:''})`创建一个全局组件zhuName.在body标签中添加<zhu-name>标签，可以使用template模板中的内容展示；
>>2. 如果在<zhu-name >标签中有遍历循环语句`v-for='value in list'`，需要在<zhu-name>标签中使用`v-bind:contentKey='value'`给全局组件中的zhuName传递参数,而在zhuName中需要使用键名为props的key，其值为['contentKey']获取，展示的话，需要在template中使用{{contentKey}}展示

>**使用六.**局部组件
>>1. 使用和全局组件一样模式的对象创建方式:
`
	let TodoItem = {
		props:['content'],
		template:'<li>{content}}</li>'
	}
`
然后注册到Vue的构建函数中；在Vue中创建一个components的键，值为对象形式，components:{NewTodoItem:TodoItem}
>>2. 在body中使用：
`
	<todo-item v-bind:content='item'
		v-for ='itme in list'>
	</todo-item>
`

>**使用七.**使用子组件传值给父组件
>>1. 在template:组件的标签中添加`@click=clickEnvent`属性,其为`v-on:click=clickEvent`简写
>2. 在template同级，添加一个methods属性,其值为一个对象，键为组件标签的事件名clickEvent，值为事件function(){}；
>3. 在子组件里，使用this.$emit('eventName'),执行一个事件eventName;//this.$emit可以带参数，第二个参数为携带给eventName的参数
>4. 在父组件中，创建一个事件监听`@eventName='event'`，再在父组件中定义一个event事件；

>**使用八.**在V-for="(item,index) in list"中，item为list对应的每一项，而index为对应的序号

>**使用九.**在属性中v-bind：index和：index是一样的；






