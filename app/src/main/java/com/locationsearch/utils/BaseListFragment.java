package com.locationsearch.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;

import com.locationsearch.R;
import com.locationsearch.widget.LoaderView;


/**
 * Created by Wild Coder on 28-10-2015.
 */
public class BaseListFragment extends ListFragment implements IFragmentListener {


    private LoaderView _prb_loader;

    public boolean onBackPressed() {
        return true;
    }

    @Override
    public void setTitle(CharSequence charSequence) {
        getActivity().setTitle(charSequence);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view.findViewById(R.id.prb_progress) instanceof LoaderView) {
            _prb_loader = (LoaderView) view.findViewById(R.id.prb_progress);
        }
    }


    @Override
    public SharedPreferences getPreferences() {
        return getActivity().getSharedPreferences(
                "" + getString(R.string.app_name), Context.MODE_PRIVATE);
    }


    @Override
    public boolean isOnline() {
        return new CheckNetwork().checkInternetConnection(getActivity());
    }


    @Override
    public void showErrorMessage(String message, View.OnClickListener onClickListener) {
        if (!isAdded())
            return;
        if (_prb_loader == null) {
            Methods.openShortToast(getActivity(), message);
            return;
        }
        _prb_loader.setErrorMessage(message, onClickListener);
    }

    @Override
    public void showMessage(String message) {
        if (!isAdded())
            return;
        if (_prb_loader == null) {
            Methods.openShortToast(getActivity(), message);
            return;
        }
        _prb_loader.setMessage(message);
    }

    @Override
    public void showLoader() {
        if (!isAdded())
            return;
        if (_prb_loader != null) {
            _prb_loader.showLoading();
        }
    }

    @Override
    public void showLoader(String message) {
        if (!isAdded())
            return;
        if (_prb_loader != null) {
            _prb_loader.showLoading(message);
        }
    }

    @Override
    public void hideLoader() {
        if (_prb_loader != null)
            _prb_loader.hideLoading();
    }

    @Override
    public void showToast(String message) {
        Methods.openShortToast(getActivity(), message);
    }


}
