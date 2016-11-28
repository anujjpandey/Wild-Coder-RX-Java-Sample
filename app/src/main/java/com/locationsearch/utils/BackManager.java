package com.locationsearch.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by Wild Coder on 28-10-2015.
 */
public class BackManager {
    public static boolean onBackPressed(FragmentManager fm) {
        try {
            if (fm == null)
                return true;
            List<Fragment> _list_fragments = fm.getFragments();
            if (_list_fragments == null || _list_fragments.size() == 0)
                return true;
            boolean isToCallBack = true;
            for (Fragment f : _list_fragments) {
                if (f == null)
                    break;
                if (f instanceof BaseFragment) {
                    if (isToCallBack)
                        isToCallBack = ((BaseFragment) f).onBackPressed();
                    else
                        ((BaseFragment) f).onBackPressed();
                } else if (f instanceof BaseListFragment) {
                    if (isToCallBack)
                        isToCallBack = ((BaseListFragment) f).onBackPressed();
                    else
                        ((BaseListFragment) f).onBackPressed();
                }
            }
            return isToCallBack;
        } catch (Exception e) {
        }
        return true;
    }
}