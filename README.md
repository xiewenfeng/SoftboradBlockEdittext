# 键盘挡住输入框终极解决办法
## 方法一：非透明状态栏下使用adjustResize和adjustPan，或是透明状态栏下使用fitsSystemWindows=true属性
## 方法二：在界面最外层布局包裹ScrollView，结合adjustResize
## 方法三：使用scrollTo方法，当键盘弹起时，让界面整体上移；键盘收起，让界面整体下移；
对于一般登录或注册界面有此需求，如希望键盘在登录按钮上方，此方法对于一般手机来说没有bug，但对于键盘在切换输入法，键盘高度改变时可能会出现不适配情况。于是出现了方法四。
## 方法四：适应软键盘高度变化情况，界面变化时，使用scrollView的smoothScrollTo属性平移界面；
使用比较复杂一些，但能适配键盘高度改变情况。
## 方法五：监听Activity顶层View，判断软键盘是否弹起，对界面重新绘制
进行华为、小米等手机适配。
详情的总结见我的博文：http://blog.csdn.net/smileiam/article/details/69055963
