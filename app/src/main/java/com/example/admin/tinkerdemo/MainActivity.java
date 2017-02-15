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

import com.example.admin.tinkerdemo.utils.Utils;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "Tinker.MainActivity";
    private TextView tvTitle;
    private Button btnLoad;
    private Button btnBug;
    private Button btnShow;
    private Button btnFixed;

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

        btnLoad.setOnClickListener(this);
        btnBug.setOnClickListener(this);
        btnShow.setOnClickListener(this);
        btnFixed.setOnClickListener(this);
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
                Toast.makeText(this, "==show bug ~", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "==Button bug is onclicked==");
                break;
            case R.id.btn_show:
                Tinker tinker = Tinker.with(getApplicationContext());
                boolean isLoadSuccess = tinker.isTinkerLoaded();
                Toast.makeText(this, "==show isLoadSuccess ~"+isLoadSuccess, Toast.LENGTH_SHORT).show();
                Log.i(TAG, "==Button show is onclicked==");
                Log.i(TAG, "==Button isLoadSuccess=="+isLoadSuccess);
                break;
            case R.id.btn_fixed:
                Log.i(TAG, "==Button fixed is onclicked==");
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
}
