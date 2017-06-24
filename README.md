# AnimationUtils
> 使用VectorDrawable、贝塞尔曲线(二阶、三阶)、PathMeasure以及属性动画打造市面上热门动画效果。

eg: 注水(水波纹)动画、饿了么加载动画、直播点赞动画、QQ空间圣诞点赞动画等。

### 持续更新中

## 演示
![GitHub直播点赞动画](http://orsggluk8.bkt.clouddn.com/image/github/gifdianzan.gif)
![GitHub小球自由落体](http://orsggluk8.bkt.clouddn.com/image/github/gifziyouluoti.gif)
![GitHub两小球往复运动](http://orsggluk8.bkt.clouddn.com/image/github/giftwoball.gif)
![GitHub6个小球的loading动画](http://orsggluk8.bkt.clouddn.com/image/github/gifsixball_loading.gif)

**2016/12/30**
- 完成了二阶、三阶贝塞尔曲线的Demo，并且添加了多点触控
- 利用贝塞尔曲线来优化绘图板的菱角

**2017/01/01**
- Bezier结合属性动画实现了水波纹进度加载
- Bezier结合属性动画实现加入购物车轨迹动画

**2017/01/02**
- PathMeasure实现的路径动画以及加载动画
- 使用PathMeasure更简单的实现圆形的路径绘制

**2017/01/03**
- 使用PathMeasure的getPosTan()绘制轨迹上点的切线&使用canvas绘制扇形进度控件
- 扇形进度控件添加了动画开始、暂停、回复等控制

**2017/01/04**
- 添加了"直播点赞"动画

**2017/01/05**
- 添加了"小球自由落体"加载动画,随着小球下落高度的变化，底部阴影也动态变化

**2017/03/30**
- 添加了"两个小球绕Y轴往复运动"加载动画，可配置小球的颜色、大小已经运动速率

**2017/06/20**
- 添加动画的GIF动图(PS.公司动荡-祸兮福之所倚，福兮祸之所伏)

**2017/06/21**
- 添加SixBallLoadingView 6个小球做贝塞尔曲线轨迹Loading加载动画,可以看看小球颜色变换部分

这段时间一直在面试，可能会有几天时间不能更新了。有什么问题和想法的可以
- 邮件<pgrammer.ybj@outlook.com>
- 或者加入QQ群："Android格调小窝"千人大群<489873144>探讨

## 致谢
- 感谢徐益生在该库写作中提供的帮助
- 感谢廖子尧（jeasonlzy）在开发中提出的帮助和建议（欢迎关注他的开源项目[okhttp-OkGo](https://github.com/jeasonlzy/okhttp-OkGo)）

## Licenses
```
 Copyright 2016 JackYang(杨保疆)

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
```