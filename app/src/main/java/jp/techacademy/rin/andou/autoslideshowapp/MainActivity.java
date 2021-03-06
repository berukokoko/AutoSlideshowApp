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
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.TimerTask;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CODE = 100;
    Cursor cursor;

    Timer mTimer;
    ImageView imageView1;


    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewByIdで結びつける。
        Button button1 = (Button) findViewById(R.id.button1);
        final Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        //ボタン1がクリックされたら
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveon();
            }
        });

        //ボタン2がクリックされたら
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimer == null){
                    // タイマーの作成
                    mTimer = new Timer();
                    // タイマーの始動
                    mTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    playback();
                                }
                            });
                        }
                    }, 2000, 2000);    // 最初に始動させるまで 100ミリ秒、ループの間隔を 100ミリ秒 に設定
                    button2.setText("停止");
                    notClick();

                }else{
                    mTimer.cancel();
                    mTimer = null;
                    button2.setText("再生");
                    okClick();
                }

            }

        });




        //ボタン3がクリックされたら
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Restart();
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
                else {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_CODE);
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
        cursor = resolver.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // データの種類
                    null, // 項目(null = 全項目)
                    null, // フィルタ条件(null = フィルタなし)
                    null, // フィルタ用パラメータ
                    null// ソート (null ソートなし)
            );

        if (cursor.moveToFirst() ) {


            int fieldIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            Long id = cursor.getLong(fieldIndex);
            Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);


            Log.d("ANDROID", "URI : " + imageUri.toString());

            ImageView imageView = (ImageView) findViewById(R.id.imageView1);
            imageView.setImageURI(imageUri);


    }

    }


    //ボタン1メソッド　
    public void moveon(){
        if(cursor.moveToNext()){
            //インデックスからuriを取ってきてイベージビューに設定する。
            int fieldIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            Long id = cursor.getLong(fieldIndex);
            Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);


            Log.d("ANDROID", "URI : " + imageUri.toString());

            ImageView imageView = (ImageView) findViewById(R.id.imageView1);
            imageView.setImageURI(imageUri);
        }
        else{
            cursor.moveToFirst();
            //インデックスからuriを取ってきてイベージビューに設定する。
            int fieldIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            Long id = cursor.getLong(fieldIndex);
            Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);


            Log.d("ANDROID", "URI : " + imageUri.toString());

            ImageView imageView = (ImageView) findViewById(R.id.imageView1);
            imageView.setImageURI(imageUri);
        }

    }


    //ボタン2メソッド　
    public void playback(){

        if(cursor.moveToNext()){
            //インデックスからuriを取ってきてイベージビューに設定する。
            int fieldIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            Long id = cursor.getLong(fieldIndex);
            Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);


            Log.d("ANDROID", "URI : " + imageUri.toString());

            ImageView imageView = (ImageView) findViewById(R.id.imageView1);
            imageView.setImageURI(imageUri);
        }
        else{
            cursor.moveToFirst();
            //インデックスからuriを取ってきてイベージビューに設定する。
            int fieldIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            Long id = cursor.getLong(fieldIndex);
            Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);


            Log.d("ANDROID", "URI : " + imageUri.toString());

            ImageView imageView = (ImageView) findViewById(R.id.imageView1);
            imageView.setImageURI(imageUri);
        }
    }


    //ボタン3メソッド　
    public void Restart(){
        if(cursor.moveToPrevious()){
            //インデックスからuriを取ってきてイベージビューに設定する。
            int fieldIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            Long id = cursor.getLong(fieldIndex);
            Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);


            Log.d("ANDROID", "URI : " + imageUri.toString());

            ImageView imageView = (ImageView) findViewById(R.id.imageView1);
            imageView.setImageURI(imageUri);
        }
        else{
            cursor.moveToLast();
            //インデックスからuriを取ってきてイベージビューに設定する。
            int fieldIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            Long id = cursor.getLong(fieldIndex);
            Uri imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);


            Log.d("ANDROID", "URI : " + imageUri.toString());

            ImageView imageView = (ImageView) findViewById(R.id.imageView1);
            imageView.setImageURI(imageUri);
        }
    }



    //ボタンがクリックできない。
    public void notClick(){
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setEnabled(false);
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setEnabled(false);
    }

    public void okClick(){
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setEnabled(true);
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setEnabled(true);
    }

}