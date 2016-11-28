package com.locationsearch.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.locationsearch.R;


/**
 * Created by wild coder on 5/12/2015.
 */
public class FragmentFlow {


    /**
     * @param fragmentManager
     * @param fragment
     * @param isFirstFragment
     * @param bundle
     * @param id
     */
    public void openFragment(FragmentManager fragmentManager, Fragment fragment, boolean isFirstFragment, Bundle bundle, int id) {
        openFragment(fragmentManager, fragment, isFirstFragment, bundle, id, AnimType.NONE);
    }

    /**
     * @param fragmentActivity
     * @param fragment
     * @param isFirstFragment
     * @param bundle
     * @param id
     * @param animType
     */
    public void openFragment(FragmentActivity fragmentActivity, Fragment fragment, boolean isFirstFragment, Bundle bundle, int id, AnimType animType) {
        openFragment(fragmentActivity.getSupportFragmentManager(), fragment, isFirstFragment, bundle, id, animType);
    }

    /**
     * @param fragmentManager
     * @param fragment
     * @param isFirstFragment
     * @param bundle
     * @param id
     * @param animType
     */
    public void openFragment(FragmentManager fragmentManager, Fragment fragment, boolean isFirstFragment, Bundle bundle, int id, AnimType animType) {
        try {
            String backStateName = fragment.getClass().getName();
            String fragmentTag = backStateName;
            FragmentTransaction transaction = fragmentManager
                    .beginTransaction();
            switch (animType) {
                case SLIDE:
                    transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                    break;
                case BOTTOM_UP:
                    transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
                    break;
            }


            if (bundle != null)
                fragment.setArguments(bundle);

            // Replace whatever is in the fragment_container
            // view with this
            // fragment,
            // and add the transaction to the back stack

            if (isFirstFragment) {
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                transaction.replace(id, fragment, fragmentTag);
            } else {
                boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName, 0);
                if (!fragmentPopped && fragmentManager.findFragmentByTag(fragmentTag) == null) {
                    //fragment not in back stack, create it.
                    transaction.replace(id, fragment, fragmentTag);
                    transaction.addToBackStack(backStateName);
                }
            }
            transaction.commit();
        } catch (IllegalStateException e) {
            AppLoger.e("", "Stack error:" + e.getMessage());
            e.printStackTrace();
        }
    }


    public enum AnimType {
        NONE, SLIDE, BOTTOM_UP
    }

    /**
     * @param activity
     */
    public void removePreviousFragment(FragmentActivity activity) {
        if (activity == null)
            return;
        FragmentManager fm = activity.getSupportFragmentManager();
        int backEntityCount = fm.getBackStackEntryCount();
        if (backEntityCount > 1)
            fm.popBackStack(fm.getBackStackEntryAt(backEntityCount - 2).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
