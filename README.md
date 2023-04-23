# MVVM

根模块，包含一些常用工具类与基类
[GitHub仓库地址](https://github.com/DL-ZhangTeng/MVVM)

## 引入

### gradle

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

implementation 'com.github.DL-ZhangTeng:MVVM:2.4.0'
```

## 部分工具功能(安装配套插件快速创建模板文件BaseLibraryTemplatePlugin-2.4.0.jar)

### MVVM工具包（com/zhangteng/mvvm）

| 工具包名/类名              | 描述                               |
|----------------------|----------------------------------|
| BaseViewModel        | VM层基类，内置了协程请求网络数据                |
| BaseLoadingViewModel | VM层带加载中状态基类，内置了协程请求网络数据          |
| BaseStateViewModel   | VM层带网络状态基类                       |
| BaseRefreshViewModel | VM层带刷新状态基类                       |
| NetState             | 网络是否可以状态                         |
| NetworkStateManager  | 可观测的网络可以状态                       |
| NetworkStateReceive  | 网络状态广播接收器                        |
| MvvmUtils            | 获取当前类绑定的泛型ViewModel-clazz        |
| databind包            | databind基本数据类型提供了默认值，避免取值的时候还要判空 |
| livedata包            | livedata基本数据类型提供了默认值，避免取值的时候还要判空 |
| BaseMvvmActivity     | 使用MVVM模式Activity基类               |
| BaseListMvvmActivity | 使用MVVM模式列表Activity基类             |
| BaseMvvmFragment     | 使用MVVM模式Fragment基类               |
| BaseListMvvmFragment | 使用MVVM模式列表Fragment基类             |

## 混淆

-keep public class com.zhangteng.**.*{ *; }

## 历史版本

| 版本     | 更新                                            | 更新时间                |
|--------|-----------------------------------------------|---------------------|
| v2.4.0 | 增加MVI模式                                       | 2023/4/23 at 16:51  |
| v2.3.0 | 增加TabLayout 与 ViewPager数据绑定方法&优化Glide加载图片绑定方法 | 2022/12/27 at 13:27 |
| v2.2.0 | db模板使用BindingAdapter，因为List模板增加VH泛型所以与之前版本不兼容 | 2022/12/5 at 23:30  |
| v2.0.1 | 使用BaseLibrary2.0.2                            | 2022/11/25 at 0:24  |
| v2.0.0 | 分离出MVVM库                                      | 2022/9/14 at 23:28  |

## 赞赏

如果您喜欢MVVM，或感觉MVVM帮助到了您，可以点右上角“Star”支持一下，您的支持就是我的动力，谢谢

## 联系我

邮箱：763263311@qq.com/ztxiaoran@foxmail.com

## License

Copyright (c) [2020] [Swing]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
