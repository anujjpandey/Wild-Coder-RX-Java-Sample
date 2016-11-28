package com.locationsearch.utils;

import android.content.SharedPreferences;
import android.view.View;

/**
 * Created by Wild Coder on 02-12-2015.
 */
public interface IFragmentListener {
    /**
     * @return
     */
    boolean onBackPressed();

    /**
     * @return AppPreferences
     */
    SharedPreferences getPreferences();


    /**
     * @return check whether Internet connection is available or not
     * @author Android Lead
     */
    boolean isOnline();


    /**
     * @param message         String error message
     * @param onClickListener Listener to retry
     * @description on screen error message with lister
     */
    void showErrorMessage(String message, View.OnClickListener onClickListener);

    /**
     * @param message String error message
     * @description on screen error message with lister
     */
    void showMessage(String message);

    /**
     * @description show the loading dialog
     */
    void showLoader();

    /**
     * @param message
     * @description show the loading dialog
     */
    void showLoader(String message);

    /**
     * @description hide the loading dialog
     */
    void hideLoader();

    /**
     * @param message
     * @description show message when content is on screen
     */
    void showToast(String message);

    /**
     * @param charSequence
     */
    public void setTitle(CharSequence charSequence)
    ;
}
