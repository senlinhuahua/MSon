package com.xsl.mson.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xsl.mson.R;
import com.xsl.mson.adapter.TimeDataAdapter;
import com.xsl.mson.base.BaseActivity;
import com.xsl.mson.dagger.DaggerActivityComponent;
import com.xsl.mson.entity.User;
import com.xsl.mson.location.activity.LocationAmapActivity;
import com.xsl.mson.ui.widget.CircleImageView;
import com.xsl.mson.utils.manager.DataManager;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.activity_main_drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_tt)
    TextView textView;
    @BindView(R.id.main_navview)
    NavigationView navigationView;
    @BindView(R.id.data_main_list)
    ListView listView;
   // @BindView(R.id.floating_search_view)
   // FloatingSearchView mSearchView;
    @BindView(R.id.banner_guide_content)
    BGABanner mBanner;

    @Inject User user;

    //private SearchResultsListAdapter mSearchResultsAdapter;
    private TimeDataAdapter timeDataAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setupFloatingSearch();
        Toast.makeText(this,user.getName(),Toast.LENGTH_LONG).show();

        mBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(MainActivity.this)
                        .load(model)
                        .placeholder(R.drawable.we_you)
                        .error(R.drawable.we_you)
                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);
            }
        });

        mBanner.setData(Arrays.asList(DataManager.bannerUri),
                Arrays.asList("提示文字1", "提示文字2", "提示文字3"));

        mBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
                Toast.makeText(banner.getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,LoginActivity.class);
                startActivity(intent);
            }
        });


        //2
//        List<View> views = new ArrayList<>();
//        views.add(BGABannerUtil.getItemImageView(this, R.drawable.ic_guide_1));
//        views.add(BGABannerUtil.getItemImageView(this, R.drawable.ic_guide_2));
//        views.add(BGABannerUtil.getItemImageView(this, R.drawable.ic_guide_3));
//        mContentBanner.setData(views);
        //跳过与进入
//        mBanner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
//            @Override
//            public void onClickEnterOrSkip() {
//                startActivity(new Intent(GuideActivity.this, MainActivity.class));
//                finish();
//            }
//        });

    }

    @Override
    protected void initData() {
        setToolbar();
        navigationView.setNavigationItemSelectedListener(this);
        textView.setText("mmmmmmmm");
        timeDataAdapter = new TimeDataAdapter(DataManager.getNativeUserData(),this);
        listView.setAdapter(timeDataAdapter);

    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void initView() {
        DaggerActivityComponent.builder().build().inject(this);
        user.setName("forest");

        View viewHeader = navigationView.getHeaderView(0);
        CircleImageView she = (CircleImageView) viewHeader.findViewById(R.id.nav_imgtitle_her);
        she.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SheActivity.start(mContext,"forest");

            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent();
        switch (item.getItemId()){
            case R.id.nav_message:
                intent.setClass(this, GuNiangActivity.class);
                startActivity(intent);
                break;
            case R.id.one:
                intent.setClass(this, LocationAmapActivity.class);
                startActivity(intent);
                break;
            default:
                closeDrawer();
                break;
        }

        return true;
    }

    private boolean closeDrawer(){
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawers();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
