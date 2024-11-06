package com.example.lab5_java;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    protected abstract int getLayoutId();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        logLifecycle("onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logLifecycle("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        logLifecycle("onCreateView");
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logLifecycle("onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        logLifecycle("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        logLifecycle("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        logLifecycle("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        logLifecycle("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        logLifecycle("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logLifecycle("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        logLifecycle("onDetach");
    }

    private void logLifecycle(@NonNull String event) {
        Log.e(this.getClass().getSimpleName(), event);
    }
}
