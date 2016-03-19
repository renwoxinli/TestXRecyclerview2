package com.lixu.testxrecyclerview;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MainActivity extends AppCompatActivity {
    private XRecyclerView mXRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] imgs = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10,
                R.drawable.img11, R.drawable.img12, R.drawable.img13, R.drawable.img14, R.drawable.img15, R.drawable.img16, R.drawable.img17, R.drawable.img18, R.drawable.img19, R.drawable.img20,
                R.drawable.meinv1, R.drawable.meinv2, R.drawable.meinv3, R.drawable.meinv4, R.drawable.meinv5, R.drawable.meinv6, R.drawable.meinv7, R.drawable.meinv8, R.drawable.meinv9, R.drawable.meinv10,
                R.drawable.meinv11, R.drawable.meinv12, R.drawable.meinv13, R.drawable.meinv14, R.drawable.meinv15, R.drawable.meinv16, R.drawable.meinv17, R.drawable.meinv18, R.drawable.meinv19, R.drawable.meinv20,
        };

        mXRecyclerView = (XRecyclerView) findViewById(R.id.xrecyclerview);
        //新建瀑布流的布局
        StaggeredGridLayoutManager sgm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //设置滑动方向
        sgm.setOrientation(StaggeredGridLayoutManager.HORIZONTAL);
        //设置布局
        mXRecyclerView.setLayoutManager(sgm);
        mXRecyclerView.setPullRefreshEnabled(true);
        MyAdapter myAdapter= new MyAdapter(imgs,this);
        mXRecyclerView.setAdapter(myAdapter);
        //设置点击事件
        myAdapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener() {
            @Override
            public void onitemclick(View view, int pos) {
                Toast.makeText(getApplicationContext(),"第"+pos+"被点击",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onitemlongclick(View view, int pos) {
                Toast.makeText(getApplicationContext(),"第"+pos+"被长按",Toast.LENGTH_SHORT).show();
            }
        });
        //添加头部
        View header = LayoutInflater.from(this).inflate(R.layout.header, (ViewGroup) findViewById(android.R.id.content), false);
        mXRecyclerView.addHeaderView(header);
        //设置 上拉下拉刷新监听器
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {

            @Override
            public void onRefresh() {
                mXRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mXRecyclerView.loadMoreComplete();
            }
        });

    }



}
