package com.locationsearch.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.locationsearch.R;
import com.locationsearch.utils.AnimHelper;

/**
 *
 */
public class LoaderView extends RelativeLayout implements OnClickListener {

    private ProgressBar _prb_dot_view;
    private TextView _txt_loader;
    private OnClickListener onClickListener;

    /**
     * @param context
     * @param attrs
     */
    public LoaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    /**
     * @param activity
     */
    public LoaderView(Context activity) {
        super(activity);
        initViews();
    }

    /**
     * @author Android Lead
     * @param activity
     *            Activity
     * */

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public LoaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    /**
     *
     */
    private void initViews() {
        if (isInEditMode())
            return;
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_loader, this, true);
        setGravity(Gravity.CENTER);
        _txt_loader = (TextView) findViewById(R.id.txt_loader);
        _prb_dot_view = (ProgressBar) findViewById(R.id.prb_loader);
        _txt_loader.setOnClickListener(this);
        hideLoading();
    }

    /**
     * @param message
     */

    /**
     * @param message
     * @param onClickListener
     */
    public void setErrorMessage(String message,
                                final OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        setVisibility(VISIBLE);
        _prb_dot_view.setVisibility(View.GONE);
        _txt_loader.setText(message);
    }

    /**
     * @param v
     */
    private void updatClick(final View v) {
        if (onClickListener == null)
            return;
        AnimHelper.applyScale(_txt_loader);

        postDelayed(new Runnable() {

            @Override
            public void run() {
                onClickListener.onClick(v);
            }
        }, 100);

    }

    /**
     * @param message
     */
    /**
     * @param message
     */
    public void setMessage(String message) {
        setVisibility(VISIBLE);
        _prb_dot_view.setVisibility(View.GONE);
        _txt_loader.setText(message);
        onClickListener = null;
    }

    /**
     * @param color
     */
    public void setTextColor(int color) {
        _txt_loader.setTextColor(color);
    }


    public void showLoading() {
        showLoading("");
    }


    public void showLoading(String message) {
        setVisibility(VISIBLE);
        _prb_dot_view.setVisibility(View.VISIBLE);
        _txt_loader.setText(message);
        onClickListener = null;
    }

    /**
     *
     */
    public void hideLoading() {
        setVisibility(GONE);
        _prb_dot_view.setVisibility(View.GONE);
        _txt_loader.setText("");
    }

    @Override
    public void onClick(View v) {
        updatClick(v);
    }
}
