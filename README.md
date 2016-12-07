# TextAndGraphics
好用的图文混排框架，仿新闻页面。只需简单配置就能实现页面布局，支持网络图片和本地图片

![textandgraphics4](https://cloud.githubusercontent.com/assets/18639409/20962076/cea2c8ec-bca2-11e6-91e4-720812951163.gif)

##使用方式

    public class MainActivity extends AppCompatActivity {

        private ScrollView sv_main;//TextAndGraphicsView的父控件必须是ScrollView
        private String[] mData;//图文数据的列表
        private TextAndGraphicsView mTextAndGraphicsView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            parseData();//解析数据
            initView();//初始化ui
        }

        private void parseData() {
            //根据分割线来把文字和图片地址存入数组中
            mData = DataUtil.getData().split(DataUtil.SPLIT_TAG);
        }

        private void initView() {
            sv_main = (ScrollView) findViewById(R.id.sv_main);
            mTextAndGraphicsView = new TextAndGraphicsView(this,mData);
            sv_main.addView(mTextAndGraphicsView);
        }
    }

##主页布局

        <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:tools="http://schemas.android.com/tools"
            android:id="@+id/activity_main"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            tools:context="com.linchuan.textandgraphics.MainActivity">

            <ScrollView
                android:id="@+id/sv_main"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="vertical"
                android:scrollbars="none"/>
        </RelativeLayout>


##图文数据格式

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
            //格式：文字开头用TEXT_TAG标识，网络图片地址用IMAGE_NET_TAG标识
            //本地图片用IMAGE_LOCAL_TAG标识，每两个标识内容之间要用SPLIT_TAG分隔

            String content = 
            "[#TEXT#]这里是第一段文字"+
            "[#SPLIT#]"+
            "[#IMAGE_NET#]http://www.picture.com"+   /*网络图片*/
            "[#SPLIT#]"+
            "[#TEXT#]这里是第二段文字"+
            "[#SPLIT#]"+
            "[#IMAGE_LOCAL#]file:///android_asset/local_picture2.png";   /*本地图片*/

            return content;
        }
    }
