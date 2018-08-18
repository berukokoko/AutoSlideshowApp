package jp.techacademy.rin.andou.autoslideshowapp;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {



    private static final int PERMISSIONS_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewByIdで結びつける。
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        //ボタン1がクリックされたら
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("UI_PARTS", "ボタンをタップしました");
            }
        });

        //ボタン2がクリックされたら
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("UI_PARTS", "ボタン2をタップしました");
            }
        });

        //ボタン3がクリックされたら
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("UI_PARTS", "ボタン3をタップしました");
            }
        });



        // Android 6.0以降の場合
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // パーミッションの許可状態を確認する
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                // 許可されている
                getContentsInfo();
            } else {
                // 許可されていないので許可ダイアログを表示する
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_CODE);
            }
            // Android 5系以下の場合
        } else {
            getContentsInfo();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContentsInfo();
                }
                break;
            default:
                break;
        }
    }

    //画像を保存する。
    private void getContentsInfo() {

        // 画像の情報を取得する
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // データの種類
                null, // 項目(null = 全項目)
                null, // フィルタ条件(null = フィルタなし)
                null, // フィルタ用パラメータ
                null // ソート (null ソートなし)
        );

        if (cursor.moveToFirst()) {
            do{
            int fieldIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            Long id = cursor.getLong(fieldIndex);
            Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);


            Log.d("ANDROID", "URI : " + imageUri.toString());

            ImageView imageView = (ImageView) findViewById(R.id.imageView1);
            imageView.setImageURI(imageUri);
            ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
            imageView2.setImageURI(imageUri);
            ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
            imageView3.setImageURI(imageUri);
        }while (cursor.moveToNext());
    }
        cursor.close();
    }
}