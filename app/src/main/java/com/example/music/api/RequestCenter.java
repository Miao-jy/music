package com.example.music.api;

import com.example.lib_network.okhttp.CommonOkHttpClient;
import com.example.lib_network.okhttp.listener.DisposeDataHandle;
import com.example.lib_network.okhttp.listener.DisposeDataListener;
import com.example.lib_network.okhttp.request.CommonRequest;
import com.example.lib_network.okhttp.request.RequestParams;
import com.example.music.view.home.modle.BaseFriendModel;
import com.example.music.view.login.entity.User;

public class RequestCenter {

    static class HttpConstants {
        private static final String ROOT_URL = "http://39.97.122.129";
        public static String LOGIN = ROOT_URL + "/module_voice/login_phone";
        public static String FRIEND = ROOT_URL + "/module_voice/home_friend";
    }

    public static void login(DisposeDataListener listener) {
        DisposeDataHandle disposeDataHandle = new DisposeDataHandle(listener, User.class);
        RequestParams requestParams = new RequestParams();
        requestParams.put("username", "mm");
        requestParams.put("password", "666");
        CommonOkHttpClient.get(
                CommonRequest.createGetRequest(HttpConstants.LOGIN, requestParams),
                disposeDataHandle);
    }

    public static void friend(DisposeDataListener listener) {
        CommonOkHttpClient.get(
                CommonRequest.createGetRequest(HttpConstants.FRIEND, null),
                new DisposeDataHandle(listener, BaseFriendModel.class));
    }
}
