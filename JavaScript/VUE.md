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
>> 3. 使用@click,简写v-on:click.两个是一样的；
>> 4. 如果在html里的模板标签中添加事件监听，点击时不会触发主组件中的函数,要执行自定义事件，必须要使用this.$emit('click'),例子：
```
//这个click是自定义事件
<child @click="handleClick"></child>
<script>
Vue.component('child',{
	//这个click是div的原生事件、
	template:"<div @click='handleChildClick'></div>"
	methods:{
		handleChildClick:function(){
			//原生事件的触发执行
			this.$emit('click')
		}
	}
</script>
```
>> 5. 如果使用<child @click.native="handleClick"></child>,可以达到，在点击时，直接触发原生事件

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

>**使用十.**使用实例.$data获取实例对象的数据  
>**使用十一.**使用实例.$el获取实例接管的dom元素  
>**使用十二.**使用实例.$destroy()销毁vue实例。只销毁后续的使用。前面的使用不会有影响；

>**使用十三.**生命周期图示
>> 1. 在new Vue中创建实例，其在内部会自动执行一些函数；
>> 2. 刚开始会初始化事件，和生命周期相关的内容
>> 3. 之后会自动执行beforeCreate函数；
>> 4. 执行了beforeCreate函数之后，会处理外部注入，双向绑定的相关内容；
>> 5. 之后执行created函数；
>> 6. 执行created函数之后，会查询是否有el选项；
>> 7. 如果有则在查询是否有template属性；
>>  - 如果有template,则使用其模板渲染页面；
>>  - 如果没有template,则使用其el外层当作模板；
>> 8. 在页面渲染之前，会执行beforeMount函数；
>> 9. 模板和数据最终生成的dom元素，挂载到页面上；
>> 10. mounted会自动执行
>> 11. 当dom数据发生改变之前，beforeUpdate会自动执行
>> 12. 当dom数据发生改变之后，updated会自动执行
>> 13. 当vm(即vue实例)被销毁（调用vm.$destroy会销毁vm)之前，beforeDestroy函数会自动执行
>> 14. 当vm被销毁之后，destroyed会自动执行；  

> **注意**：
>Vue实例的生命周期函数，并不放在method中，会直接放到vue对象中；

>**使用十四.**模板语法
>> 1. 插值表达式；
>在vue实例中创建el,data。在dom元素中使用{{name}}获取data值并展示
>> 2. 
>在vue实例中创建el,data,在dom元素中，在标签中使用V-text="name",使标签中的innerText的内容变成变量name的值；
>> 3. 
>在vue实例中创建el,data,在dom元素中，在标签中使用V-html="name",使标签中的innerHTML的值变为变量的值；

>>**说明**:在vue中看到V-的都是表示其内部值，都不再是一个字符串，而是一个js表达式； 

>**使用十五.**计算属性
>>1. 在标签中要使用data里面的值来计算的结果时，用computed属性操作；其值是一个对象，键为变量名，值是计算函数，要有返回值，返回值返回给键；  
>>**注意**：计算属性是内置缓存的；如果下次页面渲染时，内部需要的属性没有改变，它会直接调用之前的值，而不会再运行一次；
>>2. 使用函数，即在标签中使用getFullName()执行函数方法，获取其值，也可以获取计算结果；但是使用函数的方式没有缓存机制，即无论 是否更改都会执行一次；
>>3. 使用侦听器watch,其值是一个对象，键为侦听改变的对象，值为其改变之后的回调；不过只会在其值改变之后才会执行回调；
>>4. 使用computed属性中操作，其计算函数，添加了get方法，和set方法，分别对应一个获取方法，和设置方法；如果在set里面，把get依赖的值改变了，会直接执行get方法；

>**使用十六.**样式绑定 
>> **1.class的对象绑定**  
>在标签中添加：class="{className:isAction}"，把class中的值与isAction这个变量做了绑定，当isAction为true时，会把class变为className;  
>> **2.class的值绑定**  
>在标签中添加：class="[keyName]",把class与keyName绑定，keyName为一个data里的变量，其值作为class传递；因为其值为数组，所以 可以添加多个，以：class="[keyName,keyName2]"显示在标签中；  
>> **3.style样式绑定**  
>在标签中添加：style="styleObj",把该标签中的style与data数据中的data的styleObj绑定，styleObj中以style的内联样式方式书写；使用多个时，可以使用:style="[styleObj,{font-size:'20px'}]"绑定多个样式

>**使用十七.**条件渲染
>> **1.**
>在标签中添加v-if="keyName",使用keyName变量的值来决定是否在页面中添加该标签；如果为true，则添加，如果为false，则不添加该标签；在后面可以添加一个标签，其属性存在v-else-if="keyName === b",用于在keyName等于b的时候的展示;在后面可以直接添加一个标签，其属性存在v-else，不需要添加值；**注意**v-if和v-else必须紧贴在一起使用；  
>>**2.**
>在标签中添加v-show="keyName",使用keyName变量的值来决定是否在页面显示该标签，如果为true,则把该标签的display变为block,如果为false,则把该标签的display变为none;
>**注意**v-if在其变量值为false时，其html中是看不到该标签存在的，即不存在于dom上；使用v-show性能会高，因为不会多次操作dom;

>**使用十八.**列表渲染
>> **1.**
>在标签中使用v-for属性，而其属性值为item of list;list为data中的数组对象，item为每一项的内容；也可以写成(item,index) of list,item为每一项的内容，index为对应的角标；为了提升循环显示的性能，会给每个循环项添加唯一key值，：key中的值，一般使用后端返回过来的id;  
>**注意:**我们添加数组内容时，不能通过list[num]的方式添加内容，因为这种方式添加了，vue不会重新渲染到页面中；只能用vm.list.push(vlaue)添加，达到数据改变，页面重新渲染的效果；能有该效果的对数组的操作：pop(删除最后一项),push(在最后添加),shift(删除第一项),unshift(在第一项添加),splice(数组截取或者操作),sort(数组排序),reverse(数组取反)  
>> **2.**
>改变其数组对象的引用，也会使页面发生重新渲染；
>>**说明**，使用template占位符，使用循环是，会帮助我们包裹元素，但在循环过程中并不会真正的显示在页面上；
>> **3.**对象循环
>在标签中使用v-for，其值为item of userInfo,userInfo为一个对象，item为该对象中每一个列表的值；也可以写成（item,key,index) of userInfo，key为key值，index为标识；**注意**在添加一个对象的key及value时，要使用全新的引用进行修改；也可以使用Vue.set(vm.userInfo,"address","beijing")方法修改vue里的userInfo对象的address的值，改变后会重新渲染页面；也可以用vm.$set(vm.userInfo,"address","bejing"),和之前的一样；   
>
>**使用十九.**set方法
>>**1.**在js中使用Vue.set(vm.userInfo,"address","beijing")，设置data中的userInfo对象中的address为beijing。如果在页面中有使用该对象，则更新后会自动渲染；  
>>**2.**在js中使用vm.$set(vm.userInfo,"address","beijing"),设置data中的userInfo对象中的address为背景，如果在页面中有使用该对象，则更新后会自动重新渲染页面；  
>>**3.**在js中使用Vue.set(vm.userInfo,1,5),是当userInfo为数组的情况下，进行的修改，意思是对userInfo[1] = 5,并且重新渲染页面；  
>>**4.**在js中使用vm.set(vm.userInfo,1,5)，时当userInfo为数组的情况下进行的修改，意思是对userInfo[1]=5,并且重新渲染页面；

>**使用二十.**组件的使用
>>**1.**在html5规范中，table下的tbody里面只能写tr标签，如果使用模板template，代替tr标签，有时会出现问题；建议还是使用tr标签，但是里面添加一个is属性，值为模板名；
>>**2.**在根组件中定义data为对象，不会有问题；但是如果在非根组件中定义data为对象，就会出错；在非根组件定义data时，其值是一个函数，其必须有返回值，还必须包含返回的数据，返回的是一个对象；
>>**3.**在vue中操作dom,在标签中添加ref属性，使用this.$refs.refVlaue获取ref属性值为refValue的dom节点；如果是在模板组件同添加ref属性，其使用this.$refs.refValue获取的是组件的引用；

>**使用二十一.**父子组件传递值
>>**1.**父组件给子组件传值：
>>> - 先在标签中添加一个属性，其属性名为要传递的参数名，值为初始值；
> - 然后在子组件中使用props属性以['value']的方式保存，值为父组件传递给子组件的变量； 
> -  最后在template中使用其变量就可以了  
> 
>>**2.**子组件给父组件传值：
>>> - 通过事件来传递；先在子组件中添加事件，比如点击事件，在其方法中调用触发函数this.$emit('change',value),第一个是标签的事件名，第二个是参数
> - 在标签中添加@change="handleFun", 添加了一个事件监听change,在触发时，执行handleFun函数，再在父组件中添加methods里的handleFun函数，其形参就是this.$emit('change',value)中传递过来的值；

>>**3.注意**，单向数据流；
>父组件可以随意的通过属性传递参数，但是子组件不能随意的修改父组件的参数；

>**使用二十二.**组件校验
>>**1.**
>在子组件中的props中，值为对象，其内容的键为传递进来的参数，值为约束的类型；  
>>**2.** 
>其约束的类型，可以写成数组，[Number,String]表示要么是字符串，要么是数字类型；  
>>**3.**
>写成对象，content
>
>:{type:String，required:true，default:value,validator:function(value){return (value.length>5)}},
>>> - type表示其约束类型是String类型,
>>> - required表示是否是必传的,
>>> - default表示为在标签中没有content属性的时候的默认值；
>>> - validator为校验器，其值为一个函数，value为传入的内容，只有return true才不会报错，如果过长会报错

>**使用二十三.**props特性
>>**props特性**：父组件调用子组件的时候调用了content,而且在子组件中的props中声明了content,父子组件有个对应关系，这就是props特性；
特点：  
>>1. 其content="hello"的内容不会在标签中显示，会直接显示在值里面；  
>>2. 父子组件有对应关系后，在子组件中能使用插值表达式获取其传递的参数；

>**使用二十四.**非props特性
>>1. 如果在子组件中没有使用props，即父组件向子组件传递了content,但是子组件并没有使用props接过来，如果使用了content就会报错；
>>2. 如果使用了非props特性，其content="value"是会显示在页面中

>**使用二十五.**给组件绑定原生事件
>>1. 在template中的标签属性中添加一个事件监听@click='function',再在function方法中执行this.$emit('click')事件执行，执行body里的模板标签内的click监听，就可以执行其自定义函数了； 
>>2. 直接在事件监听后面添加.native，就可以直接使用自定义事件了

>**使用二十六.**非父子组件间的数值传递
>>1. 先定义一个Vue.prototype.bus = new Vue(),是bus指向Vue实例，可以使用其内部的方法；
>>2. 然后在Vue局部组件中添加内部值，把传递过来的值赋值给内部值，
>3. 再在template模板中添加原生事件监听，在methods中执行原生事件时，执行this.bus.$emit('change',value);
>4. 在定义一个钩子函数，在页面重新渲染完成之后执行的mounted函数，其内部定义了一个事件监听(注意其this的指向已经发生了改变),使用this.bus.$on('change',function(msg){ this.selContent = msg}); 

>**使用二十七.**插槽
>> 1. 插槽，就是指在模板标签中添加的标签；
>> 2. 如果按照之前的传值方式，在模板标签中添加key="value"的属性，其中value要展示的标签时，在模板中添加的话，展示出来的是以字符串形式；要展示，必须添加一个div之类的盒子，在其属性v-html中传入value的值才行；但是，势必会多一个盒子；所以vue在面对这种情况时，引入了插槽
>> 3. 插槽的使用：
>>> - 在模板标签中，直接添加标签；
>>> - 在模板中，使用slot标签获取所有的插槽内容；
>>> - 在slot标签内，可以使用输入值，表示，没有输入插槽内容时所要展示的内容；
>>> - 在插槽有多个，想要获取对应的插槽时，需要在插槽中添加属性slot，其值为name值，在模板中的slot标签中添加属性name其值为对应的想要获取的name值，即可获取对应的插槽； 
 
>>**4. 作用域插槽**
>>> - 在插槽中添加的是template模板标签；在模板标签下，写入常规标签；
>>> - 在template标签中添加slot-scope属性，其值是一个变量；用于存放在模板中的传递过来的值；
>>> - 在模板中，在slot标签里，添加属性v-for，使用item of list遍历每个list，使用：items=item来把每一个传递给items,并传递给了模板template标签中的slot-scope="key"中的值；要获取每个值，需要使用key.items来获取对应的值；

>**使用二十八.**动态组件
>> 在页面中添加component标签，即动态组件标签；其内部添加`:is=type`，页面会根据type查找组件，查找到了就会销毁之前的组件，动态加载组件；

>**使用二十九.**v-once指令
>> 在模板标签中使用v-once指令，会在第一次加载后把该模板添加到缓存中，下次使用的时候，直接调用缓存中的模板；如果不添加，修改时会直接销毁，再添加；

vue渲染页面时，会去尝试复用页面中已经存在的dom,其内容不会被清空；
解决方式：在标签中添加key="key",在标签中添加key属性时，vue会知道该标签是页面中唯一的元素；