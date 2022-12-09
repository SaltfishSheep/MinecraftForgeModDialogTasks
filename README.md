# DialogTasks
## ——对话与任务
### 前言
我之所以写这个模组，是因为我对NPCmod的对话框很不满，非常不满！延迟导致多次对话、灵活度低、字体渲染模糊、选项手感差••••••  
所以，我决定自己写一个，就以聊天栏作为对话的模式！  
开始的时候想用Gui来实现编辑对话、任务和绑定实体，但是发现这样非常麻烦！  
最后决定用文本编辑实现了，如果你用过MM（插件：神话怪物）的话，应该会对我的格式感到非常熟悉！  
### 一些额外特性（可以在配置文件关闭）
逻辑客户端有效：  
在对话期间，如果有除了对话和任务以外的消息，会暂时屏蔽，这些消息会被收集起来，等到对话结束（准确的说是锁定解除后）一次性全部发送。  
逻辑服务端有效：  
对话、任务、实体绑定读取文件夹默认为存档内，可在配置文件修改，修改后为 .minecraft/dialogtasks 下或 版本/dialogtasks 下  
### 目录
1. **文件**  
2. **“函数”**  
  * 对话  
  * 任务  
  * 额外运行  
  * 需求  
  * CustomNPCs任务相关  
3. **变量**  
  * 基础  
  * 复合运算与占位符  
### 文件
文件一共有三类，分别是后缀为".dialog",".task",".entity"，分别对应对话，任务，挂载实体  
对话和任务的信息将通过“函数”编辑、添加，而挂载实体文件，文件名即为实体名，其内每一行都对应“挂载”的一个对话。  
对于挂载实体，只有当上一行对应的对话无法执行（即其require不满足时）才会考虑下一行对应的对话。  
文件只要求在 存档目录/dialogtasks下，不论在哪个子文件夹都会检测到。  
### 函数
类似于MM，类似于代码的函数调用，其使用形式是这样的  
`函数名(参数名=输入值;参数二=输入值)`  
用';'分隔不同参数输入  
为了便于参考，下文提供函数、参数名、输入值会采用这样的形式。  

 函数名 | 参数 | 说明  
 ---- | ----- | ------  
 function | var(D)[0.0] | 临时参数  

写出来的效果是"function(var=1.0)"，其中'(D)'表示该参数应输入小数，'[0.0]'表示该参数选填，默认值为0.0  
参数类型：  
D:小数，I:整数,S:文本（可能有格式要求）  
ps:除了有格式要求的文本以外，请尽量不要写空格、缩进，这可能会造成Bug  
#### 对话

 函数名 | 参数 | 说明
 ---- | ----- | ------  
 setID | dialogID(S) | 设置对话的ID
 
二、编辑基本语法  
1、字体格式强调  
 我们可以使用下面的方式给我们的文本添加强调的效果  
*强调*  (示例：斜体)  
 _强调_  (示例：斜体)  
**加重强调**  (示例：粗体)  
 __加重强调__ (示例：粗体)  
***特别强调*** (示例：粗斜体)  
___特别强调___  (示例：粗斜体)  
2、代码  
`<hello world>`  
3、代码块高亮  
```
@Override
protected void onDestroy() {
    EventBus.getDefault().unregister(this);
    super.onDestroy();
}
```  
4、表格 （建议在表格前空一行，否则可能影响表格无法显示）
 
 表头  | 表头  | 表头
 ---- | ----- | ------  
 单元格内容  | 单元格内容 | 单元格内容 
 单元格内容  | 单元格内容 | 单元格内容  
 
5、其他引用
图片  
![图片名称](https://www.baidu.com/img/bd_logo1.png)  
链接  
[链接名称](https://www.baidu.com/)    
6、列表 
1. 项目1  
2. 项目2  
3. 项目3  
   * 项目1 （一个*号会显示为一个黑点，注意⚠️有空格，否则直接显示为*项目1） 
   * 项目2   
 
7、换行（建议直接在前一行后面补两个空格）
直接回车不能换行，  
可以在上一行文本后面补两个空格，  
这样下一行的文本就换行了。
或者就是在两行文本直接加一个空行。
也能实现换行效果，不过这个行间距有点大。  
 
8、引用
> 第一行引用文字  
> 第二行引用文字   
