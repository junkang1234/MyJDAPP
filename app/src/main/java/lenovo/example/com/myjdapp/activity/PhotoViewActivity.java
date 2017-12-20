package lenovo.example.com.myjdapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import lenovo.example.com.myjdapp.R;


public class PhotoViewActivity extends AppCompatActivity {

    private ViewPager vip;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        final ArrayList<String> stringlist = intent.getStringArrayListExtra("stringlist");
        vip = (ViewPager) findViewById(R.id.vip3);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(position+1+"/"+stringlist.size());
        ImageView imageView = (ImageView) findViewById(R.id.exit_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.leftone, R.anim.rightone);
            }
        });
        //log测试输出
        Log.d("qq","position" + position + "stringlist" + stringlist.toString());

        vip.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return stringlist.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View v = View.inflate(PhotoViewActivity.this,R.layout.viewpager_layout,null);
                PhotoView photo = (PhotoView) v.findViewById(R.id.photoview);
                ImageLoader.getInstance().displayImage(stringlist.get(position),photo);
                container.addView(v);
                return v;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
                container.removeView((View) object);
            }
        });
        vip.setCurrentItem(position);
        vip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_title.setText(position+1+"/"+stringlist.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
