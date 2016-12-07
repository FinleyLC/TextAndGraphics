package com.linchuan.textandgraphics;

/**图文的数据工具类
 * Created by 林川 on 2016/12/6.
 */

public class DataUtil {

    public static final String TEXT_TAG =  "[#TEXT#]";//文字标识
    public static final String IMAGE_NET_TAG = "[#IMAGE_NET#]";//网络图片标识
    public static final String IMAGE_LOCAL_TAG = "[#IMAGE_LOCAL#]";//本地图片标识(assets目录下)
    public static final String SPLIT_TAG = "\\[\\#SPLIT\\#\\]";//分割处标识

    /**
     * 获取图文的数据，这些数据可以从服务端取，或者写在本地的某个文件也可以
     * @return 图文数据
     */
    public static String getData(){
        //格式：文字开头用TEXT_TAG标识，网络图片地址用IMAGE_NET_TAG标识，本地图片用IMAGE_LOCAL_TAG标识，每两个标识内容之间要用SPLIT_TAG分隔
        String content =
                 "[#TEXT#]2016是VR年，各路巨头都积极布局VR，科技老大哥谷歌的发力当然不容小觑谷歌5月份推出了移动VR平台Daydream，10月份又推出了该平台的硬件新品Daydream View头戴，至于最后谷歌能否在“大鱼小虾混战”的行业现状下实现像安卓一样的大一统，就不得而知了。"
                +"[#SPLIT#]"
                +"[#IMAGE_NET#]http://imgsrc.baidu.com/forum/w%3D580/sign=57d48f1b5edf8db1bc2e7c6c3922dddb/064c05f3d7ca7bcb01f249acbe096b63f724a86e.jpg"
                +"[#SPLIT#]"
                +"[#TEXT#]Daydream View这款头显使用了尼龙材质的两点式松紧头带，佩戴舒服，让用户不需要频繁调整头戴;它拥有NFC芯片，手机一刷就能进入VR模式，同时还有自动图像校准系统，不需要用户去进行多余的设置调整;\n 另外，它拥有配套的控制器手柄，可以用控制器来进行交互式游戏和操作，不需要频繁通过头部进行交互。"
                +"[#SPLIT#]"
                +"[#IMAGE_NET#]http://p4.so.qhmsg.com/bdr/_240_/t01284c1252d9bb3f59.jpg"
                +"[#SPLIT#]"
                +"[#TEXT#]从今天开始，谷歌Daydream View头显将会推出两种新的颜色：深红色和雪白色。现在用户已经可以通过谷歌的在线商店购买到这两种颜色，售价跟岩灰色头显一样是79美元，发货日期为12月8日。不知道三种颜色哪种才是你的所爱呢?"
                +"[#SPLIT#]"
                +"[#IMAGE_NET#]http://p2.so.qhmsg.com/bdr/_240_/t01ffcdaedde9bcb74c.jpg"
                +"[#SPLIT#]"
                +"[#TEXT#]网易科技讯12月6日消息，据外媒报道，谷歌正式推出搭载Android7.1.操作系统的Pixel手机新品，Nougat系统也进行了相应的更新。谷歌同时宣布Android7.1.1系统已经开始推广到其他Android手机，为广大用户提供更多新功能。这些即将运行最新版本Android系统的手机包括Nexus 6，Nexus 5X，Nexus 6P，Nexus 9，Pixel，Pixel XL，Nexus Player，Pixel C和General Mobile 4G（Android One）。"
                +"[#SPLIT#]"
                +"[#TEXT#]程序员的日常"
                +"[#SPLIT#]"
                +"[#IMAGE_LOCAL#]file:///android_asset/local_picture1.png"
                +"[#SPLIT#]"
                +"[#IMAGE_LOCAL#]file:///android_asset/local_picture2.png"
                +"[#SPLIT#]"
                +"[#TEXT#]程序员的日常扫码";

        return content;
    }
}
