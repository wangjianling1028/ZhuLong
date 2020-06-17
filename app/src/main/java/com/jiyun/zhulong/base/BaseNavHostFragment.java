package com.jiyun.zhulong.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Map;

/**
 * Created by 任小龙 on 2020/1/2.
 */
public class BaseNavHostFragment extends NavHostFragment {
    @NonNull
    @Override
    protected Navigator<? extends FragmentNavigator.Destination> createFragmentNavigator() {
        return new MyNavigator(requireContext(), getChildFragmentManager(), getId());
    }

    @Navigator.Name("tab_fragment")
    class MyNavigator extends FragmentNavigator {
        Context mContext;
        FragmentManager mFragmentManager;
        int containerId;

        public MyNavigator(@NonNull Context context, @NonNull FragmentManager manager, int containerId) {
            super(context, manager, containerId);
            mContext = context;
            mFragmentManager = manager;
            this.containerId = containerId;
        }

        @Nullable
        @Override
        public NavDestination navigate(@NonNull Destination destination, @Nullable Bundle args, @Nullable NavOptions navOptions, @Nullable Navigator.Extras navigatorExtras) {
            try {
                Field mBackStackField = FragmentNavigator.class.getDeclaredField("mBackStack");
                mBackStackField.setAccessible(true);
                ArrayDeque<Integer> mBackStack = (ArrayDeque<Integer>) mBackStackField.get(this);
                Field mIsPendingBackStackOperationField = FragmentNavigator.class.getDeclaredField("mIsPendingBackStackOperation");
                mIsPendingBackStackOperationField.setAccessible(true);
                if (mFragmentManager.isStateSaved()) return null;
                String className = destination.getClassName();
                FragmentTransaction ft = mFragmentManager.beginTransaction();
                int enterAnim = navOptions != null ? navOptions.getEnterAnim() : 0;
                int exitAnim = navOptions != null ? navOptions.getExitAnim() : 0;
                int popEnterAnim = navOptions != null ? navOptions.getPopEnterAnim() : 0;
                int popExitAnim = navOptions != null ? navOptions.getPopExitAnim() : 0;
                if (enterAnim != 0 || exitAnim != 0 || popEnterAnim != 0 || popExitAnim != 0) {
                    ft.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim);
                }
                String tag = destination.getId() + "";
                Fragment currentFragment = mFragmentManager.getPrimaryNavigationFragment();
                if (currentFragment != null) ft.hide(currentFragment);
                Fragment frag = mFragmentManager.findFragmentByTag(tag);
                if (frag == null) {
                    frag = instantiateFragment(mContext, mFragmentManager, className, args);
                    frag.setArguments(args);
                    ft.add(containerId, frag, tag);
                } else ft.show(frag);
                ft.setPrimaryNavigationFragment(frag);
                @IdRes
                int localId = destination.getId();
                boolean initialNavigation = mBackStack.isEmpty();
                boolean isSingleTopReplacement = navOptions != null && !initialNavigation && navOptions.shouldLaunchSingleTop() && mBackStack.peekLast() == localId;
                boolean isAdded;
                if (initialNavigation) isAdded = true;
                else if (isSingleTopReplacement) {
                    if (mBackStack.size() > 1) {
                        mFragmentManager.popBackStack(generateMyBackStackName(mBackStack.size(), mBackStack.peekLast()), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        ft.addToBackStack(generateMyBackStackName(mBackStack.size(), localId));
                        mIsPendingBackStackOperationField.set(this, true);
                    }
                    isAdded = false;
                } else {
                    ft.addToBackStack(generateMyBackStackName(mBackStack.size() + 1, localId));
                    mIsPendingBackStackOperationField.set(this, true);
                    isAdded = true;
                }
                if (navigatorExtras instanceof Extras) {
                    Extras extras = (Extras) navigatorExtras;
                    Map<View, String> elements = extras.getSharedElements();
                    for (View key : elements.keySet()) {
                        ft.addSharedElement(key, elements.get(key));
                    }
                }
                ft.setReorderingAllowed(true);
                ft.commit();
                if (isAdded) mBackStack.add(localId);
                return isAdded ? destination : null;
            } catch (Exception pE) {
                pE.printStackTrace();
                return super.navigate(destination, args, navOptions, navigatorExtras);
            }
        }
    }

    private String generateMyBackStackName(int backStackIndex, int destId) {
        return backStackIndex + "-" + destId;
    }
}
