package com.htc.delicates.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.htc.delicates.Model.User;
import com.htc.delicates.R;
import com.htc.delicates.Util.PhotoUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;

public class PersonalActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PersonalActivity";

    Toolbar toolbar_personal;

    CircleImageView userPhotoDetail;

    EditText userBirthday;

    EditText userName;

    TextView infoSave;

    RelativeLayout userPhoto;

    RelativeLayout userAdress;

    RelativeLayout userSize;

    RelativeLayout userPreference;

    private final int IMAGE_RESULT_CODE = 2;
    private final int PICK = 1;
    private String url = "";//此处为上传图片地址
    private String imgString = "";
    private Intent intent;
    private Handler mHandler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        toolbar_personal = (Toolbar) findViewById(R.id.toolbar_personal);
        setSupportActionBar(toolbar_personal);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_personal.setNavigationOnClickListener(v -> onBackPressed());

        userPhoto = (RelativeLayout) findViewById(R.id.user_photo);
        userPreference = (RelativeLayout) findViewById(R.id.user_like);
        userAdress = (RelativeLayout) findViewById(R.id.user_address);
        userSize = (RelativeLayout) findViewById(R.id.user_size);
        userName = (EditText) findViewById(R.id.username);
        userBirthday = (EditText) findViewById(R.id.user_birthday);
        infoSave = (TextView) findViewById(R.id.info_save);
        userPhotoDetail = (CircleImageView) findViewById(R.id.user_photo_detail);

        userPhoto.setOnClickListener(this);
        userPreference.setOnClickListener(this);
        userAdress.setOnClickListener(this);
        userSize.setOnClickListener(this);
        infoSave.setOnClickListener(this);

        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_photo:
                showPopueWindow();
                break;
            case R.id.user_address:
                Intent intent1 = new Intent(this, UserAddressActivity.class);
                startActivity(intent1);
                break;
            case R.id.user_size:
                Intent intent2 = new Intent(this, UserSizeActivity.class);
                startActivity(intent2);
                break;
            case R.id.user_like:
                Intent intent3 = new Intent(this, UserPreferenceActivity.class);
                startActivity(intent3);
                break;
            case R.id.info_save:

                break;
        }
    }

    private void init(){
        User user = new User();
    }

    private void showPopueWindow(){
        View popView = View.inflate(this,R.layout.popupwindow_head_image,null);
        Button bt_album = (Button) popView.findViewById(R.id.btn_pop_album);
        Button bt_camera = (Button) popView.findViewById(R.id.btn_pop_camera);
        Button bt_cancle = (Button) popView.findViewById(R.id.btn_pop_cancel);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels*1/3;

        final PopupWindow popupWindow = new PopupWindow(popView,weight,height);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);

        bt_album.setOnClickListener(v -> {
            intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, IMAGE_RESULT_CODE);
            popupWindow.dismiss();
        });
        bt_camera.setOnClickListener(v -> {
            intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, PICK);
            popupWindow.dismiss();
        });
        bt_cancle.setOnClickListener(v -> popupWindow.dismiss());
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.alpha = 1.0f;
            getWindow().setAttributes(lp);
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM,0,50);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 表示 调用照相机拍照
            case PICK:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    imgString = bitmapToBase64(bitmap);
                    uploadImg();
                }
                break;
            // 选择图片库的图片
            case IMAGE_RESULT_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    Bitmap bitmap2 = PhotoUtils.getBitmapFromUri(uri, this);
                    imgString = bitmapToBase64(bitmap2);
                    uploadImg();
                }
                break;
        }
    }

    //如上参需要64位编码可调用此方法，不需要可以忽略
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //上传图片文件的操作
    public void uploadImg() {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
//        //上传图片参数需要与服务端沟通，我就不多做解释了，我添加的都是我们服务端需要的
//        //你们根据情况自行更改
//        //另外网络请求我就不多做解释了
//        FormBody body = new FormBody.Builder().add("dir", "c/image")
//                .add("data", imgString)
//                .add("file", "headicon")
//                .add("ext", "jpg").build();
//        Request request = new Request.Builder().url(url).post(body).build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String data = response.body().string();
//                Gson gson = new Gson();
//                final User user = gson.fromJson(data, User.class);
//                Log.d(TAG, "onResponse: " + data);
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        //加载图片用的Gilde框架，也可以自己自由选择，
//                        //""里面取决于服务端的返回值是否需要自行添加地址
//                        Glide.with(PersonalActivity.this).load(user.imageDate).into(userPhotoDetail);
//                    }
//                });
//            }
//        });
    }
}
