package com.hxht.testwaterfallrecyclerview;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.hxht.testwaterfallrecyclerview.adapter.MyRecyclerViewAdapter;
import com.hxht.testwaterfallrecyclerview.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<String> list;
    private int indexLoadMore = 1;
    private int indexRefresh = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();
    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    private void initData() {
        HelpClass mHelpClass = new HelpClass();
        mHelpClass.setLoading("玩命加载中...");

        binding.setHelpclass(mHelpClass);


        list = new ArrayList<>();
        list.clear();
        for (int i = 1; i < 201; i++) {
            list.add("Test" + (i < 10 ? "0" + i : i));
        }

        final MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(MainActivity.this, list);
        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setLayoutManager(manager);


        binding.recyclerview.setOnScrollListener(new OnRcvScrollListener() {
            @Override
            public void onBottom() {
                super.onBottom();
                binding.ll.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                final int position = list.size();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.ll.setVisibility(View.GONE);
                        int pageIndex = indexLoadMore++;
                        for (int i = 1; i < 200; i++) {
                            list.add(pageIndex + "Mo" + (i < 10 ? "0" + i : i));
                        }
                        adapter.notifyDataSetChanged();
                        binding.recyclerview.smoothScrollToPosition(position);
                    }
                }, 5000);
            }
        });

        int[] colors = new int[]{
                Color.RED,
                Color.GREEN,
                Color.YELLOW,
                Color.BLACK,
                Color.BLUE
        };
        binding.swiperefreshlayout.setColorSchemeColors(colors);

        binding.swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        int refreshCount = indexRefresh++;
                        for (int i = 1; i < 201; i++) {
                            list.add(refreshCount + "--Rh" + (i < 10 ? "0" + i : i));
                        }
                        adapter.notifyDataSetChanged();
                        binding.swiperefreshlayout.setRefreshing(false);
                    }
                }, 5000);
            }
        });

    }
}
