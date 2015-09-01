描述：本module是RecyclerView实现瀑布流后的的下拉刷新和上拉加载，上拉刷新采用的是android.support.v4.widget.SwipeRefreshLayout
      而上拉加载方法是通过重写OnScrollListener的方法；在这里非常感谢博客园大牛们的开源代码
      本module比较简单，仅供参考，不喜忽喷谢谢！
开发工具：As
@author：张捷飞
@Email：hbzhangjiefei@163.com

注意：
    由于本module采用的是DataBindingLibrary填充数据，故需要类库支持
    下载后运行到你的项目空间，你会发现报错，
    然后我们这样做：
    在项目的build.gradle构建文件（不是module 的build.gradle）里添加如下信息即可获取：

    classpath "com.android.databinding:dataBinder:1.0-rc1"
