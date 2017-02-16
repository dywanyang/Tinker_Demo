package com.example.admin.tinkerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.tinkerdemo.util.Utils;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "Tinker.MainActivity";
    private TextView tvTitle;
    private Button btnLoad;
    private Button btnBug;
    private Button btnShow;
    private Button btnFixed;
    private Button btnKill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "==This is onCreate()==");
        tvTitle = (TextView) findViewById(R.id.tv_title);

        btnLoad = (Button) findViewById(R.id.btn_load);
        btnBug = (Button) findViewById(R.id.btn_bug);
        btnShow = (Button) findViewById(R.id. btn_show);
        btnFixed = (Button) findViewById(R.id.btn_fixed);
        btnKill = (Button) findViewById(R.id.btn_kill);

        btnLoad.setOnClickListener(this);
        btnBug.setOnClickListener(this);
        btnShow.setOnClickListener(this);
        btnFixed.setOnClickListener(this);
        btnKill.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_load:
                TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),
                        Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
                Log.i(TAG, "==Button load is onclicked==");
                Toast.makeText(this, "Loading Continue ~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_bug:
                tvTitle.setText("==show bug ~");
                Log.i(TAG, "==Button bug is onclicked==");
                break;
            case R.id.btn_show:
                Tinker tinker = Tinker.with(getApplicationContext());
                boolean isLoadSuccess = tinker.isTinkerLoaded();
                tvTitle.setText("==Button isLoadSuccess ~"+isLoadSuccess);
                Log.i(TAG, "==Button isLoadSuccess=="+isLoadSuccess);
                break;
            case R.id.btn_fixed:
                Log.i(TAG, "==Button fixed is onclicked==");
                break;
            case R.id.btn_kill:
                Log.i(TAG, "==Button KILL is onclicked==");
                try {
                    //readAssetsFile(this);
                    InputStream is = this.getResources().getAssets().open("channelname.txt");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();

                    String text = new String(buffer, "GB2312");
                    tvTitle.setText(text);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i(TAG, "==Error=="+e.toString());
                }
                // android.os.Process.killProcess(android.os.Process.myPid());
                break;

            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.setBackground(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Utils.setBackground(true);
    }

    public void readAssetsFile(Activity activity) throws IOException {

    }
}
