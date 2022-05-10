package com.example.music.view.home.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.music.R;

public class DiscoveryFragment extends Fragment {

    private static volatile DiscoveryFragment discoveryFragment;

    private DiscoveryFragment() {
    }

    public static DiscoveryFragment newInstance() {
        if (discoveryFragment == null) {
            synchronized (DiscoveryFragment.class) {
                if (discoveryFragment == null) {
                    discoveryFragment = new DiscoveryFragment();
                }
            }
        }
        return discoveryFragment;
    }

    private WebView discoveryWebView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discory_layout, container, false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        discoveryWebView = view.findViewById(R.id.discovery_web_view);
        discoveryWebView.getSettings().setJavaScriptEnabled(true);
        discoveryWebView.setWebViewClient(new WebViewClient());
        discoveryWebView.loadUrl("https://www.baidu.com");
    }
}
