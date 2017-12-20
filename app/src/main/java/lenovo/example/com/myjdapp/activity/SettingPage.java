package lenovo.example.com.myjdapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.bean.BaseBean;
import lenovo.example.com.myjdapp.bean.InfoBean;
import lenovo.example.com.myjdapp.fragment.Displayer;
import lenovo.example.com.myjdapp.presenter.InfoPresenter;
import lenovo.example.com.myjdapp.untils.ImageUtils;
import lenovo.example.com.myjdapp.untils.IntentUntils;
import lenovo.example.com.myjdapp.untils.LogInterceptor;
import lenovo.example.com.myjdapp.untils.SpUtils;
import lenovo.example.com.myjdapp.untils.ToastUntils;
import lenovo.example.com.myjdapp.view.InfoView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SettingPage extends AppCompatActivity implements InfoView {

    private ImageView image_exit;
    private ImageView user_head;
    private TextView tv_title;
    private TextView tv_name;
    private InfoPresenter infpresenter ;
    private RelativeLayout user_exit;
    private Bitmap head;// 头像Bitmap
    private static String path = "/sdcard/myHead/";// sd路径
    private RelativeLayout relv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);
        getSupportActionBar().hide();
        initView();
    }

    private void loadData() {
          if(getFlag()){
              infpresenter.getInfo(getUid());
          }
    }

    public int getUid(){
       return getIntent().getIntExtra("uidkey",0);
    }

    public boolean getFlag(){
        return getIntent().getBooleanExtra("flagkey",false);
    }

    private void initView() {
        infpresenter = new InfoPresenter(this);
        image_exit = (ImageView) findViewById(R.id.image_left);
        user_head = (ImageView) findViewById(R.id.user_head);
        tv_title = (TextView) findViewById(R.id.text_title);
        tv_name = (TextView) findViewById(R.id.text_name);
        user_exit = (RelativeLayout) findViewById(R.id.linten);
        image_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.leftone, R.anim.rightone);
            }
        });
        relv = (RelativeLayout) findViewById(R.id.linsix);
        user_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTypeDialog();
            }
        });
//        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");// 从SD卡中找头像，转换成Bitmap
//        if (bt != null) {
////            @SuppressWarnings("deprecation")
////            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
//            Bitmap bitmap = ImageUtils.toRoundBitmap(bt);
//            user_head.setImageBitmap(bitmap);
//        } else {
//            /**
//             * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
//             *
//             */
//        }
    }
    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_select_photo, null);
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }
    @Override
    public void Success(BaseBean baseBean) {
        InfoBean baseBean1 = (InfoBean) baseBean;
        final InfoBean.DataBean data = baseBean1.getData();
        tv_title.setText(data.getUsername());
        String nickname = (String) data.getNickname();
        tv_name.setText("用户名:" + nickname);
        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUntils.setIntent(SettingPage.this,UpDataNameActivity.class,"key1",(String) data.getNickname());
                overridePendingTransition(R.anim.leftone, R.anim.rightone);
            }
        });
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .displayer(new Displayer(0))
                    .showImageForEmptyUri(R.mipmap.touxiang)
                    .showImageOnLoading(R.mipmap.touxiang)
                    .showImageOnFail(R.mipmap.touxiang)
                    .build();
            ImageLoader.getInstance().displayImage((String) data.getIcon(),user_head,options);

        user_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingPage.this);
                builder.setTitle("是否退出");
                builder.setNegativeButton("取消",null);
                builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SpUtils.removeFlag(SettingPage.this);
                        ToastUntils.setToast(SettingPage.this,"退出成功");
                        finish();
                        overridePendingTransition(R.anim.leftone, R.anim.rightone);
                    }
                });
                builder.show();
            }
        });
        relv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  IntentUntils.setIntent(SettingPage.this,TakeGoodsActivity.class);
                overridePendingTransition(R.anim.leftone, R.anim.rightone);
            }
        });
    }

    @Override
    public void Error(String msg) {
        ToastUntils.setToast(SettingPage.this,msg);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);// 保存在SD卡中
                        Bitmap bitmap = ImageUtils.toRoundBitmap(head);
                        user_head.setImageBitmap(bitmap);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }
    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file1 = new File(path + "head.jpg");
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LogInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://120.27.23.105/file/")
                .build();
        PostFile_Interface postFile_interface = retrofit.create(PostFile_Interface.class);
        //创建文件体
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file1);

        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file1.getName(), requestFile);

        Call<postfile> getpostfile = postFile_interface.getpostfile(SpUtils.getSpUid(SettingPage.this)+"",body);

        getpostfile.enqueue(new Callback<postfile>() {
            @Override
            public void onResponse(Call<postfile> call, Response<postfile> response) {
                Toast.makeText(SettingPage.this,"修改成功",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<postfile> call, Throwable t) {

            }
        });
    }
}

