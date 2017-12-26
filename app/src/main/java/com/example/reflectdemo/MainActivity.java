package com.example.reflectdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reflectlibrary.BindOnclick;
import com.example.reflectlibrary.ButtknifeUtils;
import com.example.reflectlibrary.ImitationDeclared;
import com.example.reflectlibrary.InjectView;

public class MainActivity extends AppCompatActivity {

    @ImitationDeclared()
    @InjectView(R.id.reflect_id)
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            ButtknifeUtils.injectView(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        textView.setText("我是自定义注解做出来的");

    }

    @BindOnclick(value = {R.id.image_test, R.id.image_test2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_test:
                Toast.makeText(this, "我是测试的自定义点击注解", Toast.LENGTH_LONG).show();
                break;
            case R.id.image_test2:
                Toast.makeText(this, "我是测试的自定义点击注解22222222", Toast.LENGTH_LONG).show();
                break;
        }
    }


}
