package com.jiyun.zhulong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.jiyun.frame.mvp.ICommonModel;
import com.jiyun.zhulong.R;
import com.jiyun.zhulong.base.BaseMvpActiviy;

public class MyHomeActivity extends BaseMvpActiviy implements NavController.OnDestinationChangedListener {

    private NavController navController;
    private long exitTime=0;


    @Override
    protected int setLayout() {
        return R.layout.activity_my_home;
    }

    @Override
    protected ICommonModel setModel() {
        return null;
    }

    @Override
    protected void initView() {
        navController = Navigation.findNavController(this, R.id.navigation_home);
        navController.addOnDestinationChangedListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onSuccess(int apiConfig, int loadTypeConfig, Object[] objects) {

    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        String label = destination.getLabel().toString();
    }

    //连按两次返回键回退到桌面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一下返回桌面",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                Intent i = new Intent(Intent.ACTION_MAIN);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addCategory(Intent.CATEGORY_HOME);
                startActivity(i);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}