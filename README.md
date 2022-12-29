# FragmentNavigator
通过NavigationHandlerFragment类统一管理fragment之间的导航。
## RequestKey的约定
RequestKey必须以companion object形式定义在对应的Fragment内，命名规则为REQUEST_KEY_对应操作名，例如：REQUEST_KEY_REFRESH_CONTACT_LIST。

## 使用方式
1. 创建Fragment并继承NavigationHandlerFragment。
2. 实现registerFragmentResultHandlers方法并注册相应的RequestKey以及Key所描述的操作。例如：
```
override fun registerFragmentResultHandlers(): List<FragmentResultHandler> {
        return listOf(
            FragmentResultHandler(REQUEST_KEY_REFRESH_CONTACT_LIST) {
                // todo exexute contacts refresh function
                println("contact list has been refreshed.")
            }
        )
    }
```
3. 当需要回退到上一个页面并通知上一个页面执行特定操作，需要完成如下2步操作：
首先注册requestKey以及回传参数，代码如下：
```
override fun recordFragmentResults(): List<FragmentResult> {
        return listOf(
            FragmentResult(FirstFragment.REQUEST_KEY_REFRESH_CONTACT_LIST, data)
        )
    }
```
然后在调用导航回退地方通知上一个页面执行对应key的逻辑：
```
    override fun onBackPressed() {
      // 通知上一页面刷新列表
       notifyFragmentResult(FirstFragment.REQUEST_KEY_REFRESH_CONTACT_LIST)
       findNavController().popBackStack()
    }
```
