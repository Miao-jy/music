package com.example.music.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lib_commin_ui.base.BaseActivity;
import com.example.lib_network.okhttp.listener.DisposeDataListener;
import com.example.music.R;
import com.example.music.api.RequestCenter;
import com.example.music.view.login.entity.LoginEvent;
import com.example.music.view.login.entity.User;
import com.example.music.view.login.manager.UserManager;

import org.greenrobot.eventbus.EventBus;

public class LoginActivity extends BaseActivity {

    /**
     * 给外部提供的启动LoginActivity的方法
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        findViewById(R.id.login_view).setOnClickListener(v -> {
            RequestCenter.login(new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    // 登录成功逻辑
                    User user = (User) responseObj;
                    UserManager.getInstance().saveUser(user);
                    EventBus.getDefault().post(new LoginEvent());
                    finish();
                }

                @Override
                public void onFailure(Object reasonObj) {
                    // 登录失败逻辑，一般弹出提示
                }
            });
        });
    }
}
