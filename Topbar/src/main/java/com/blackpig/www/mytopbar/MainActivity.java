package com.blackpig.www.mytopbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Topbar topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topbar = (Topbar) findViewById(R.id.topbar);

        topbar.setOnTopBarClickListener(new Topbar.topBarClickListener() {
            @Override
            public void onLeftClick() {
                Toast.makeText(MainActivity.this, "LeftClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick() {
                Toast.makeText(MainActivity.this, "RightClick", Toast.LENGTH_SHORT).show();
            }
        });

        topbar.setVisiable(false);
    }
}
