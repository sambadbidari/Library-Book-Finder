package com.example.android.lbf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by sambad on 2/14/18.
 */

public class first_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
        Button u = (Button) findViewById(R.id.btn_studentlogin);
         Button r = (Button) findViewById(R.id.btn_studentregister);
         Button a = (Button) findViewById(R.id.btn_adminlogin);
        u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(first_activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(first_activity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(first_activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
