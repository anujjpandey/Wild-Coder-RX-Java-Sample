package com.locationsearch.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.locationsearch.MapsActivity;
import com.locationsearch.R;
import com.locationsearch.entites.ApiIPSearchResponse;
import com.locationsearch.entites.RestIPResponse;
import com.locationsearch.interfaces.ApiSearch;
import com.locationsearch.services.RetrofitService;
import com.locationsearch.utils.AppLoger;
import com.locationsearch.utils.BaseListFragment;
import com.locationsearch.utils.Methods;

import java.util.concurrent.TimeUnit;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Wild Coder on 29/09/16.
 */
public class ListFragmentIPSearch extends BaseListFragment {
    // views declaration
    public static String TAG = ListFragmentIPSearch.class.getName();


    // classes and actions declaration
    SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            if (isOnline()) {
                if (query.isEmpty())
                    return false;
                if (Methods.validIp(query)) {
                    callSearch(query);
                    Methods.hideSoftKeyboard(getActivity());
                } else {
                    showMessage("Invalid ip");
                }
            } else
                Methods.noInternetDialog(getActivity());
            return true;


        }

        @Override
        public boolean onQueryTextChange(String newText) {
            showMessage("");
            return false;
        }
    };
    //variable declaration

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setTitle("IP Location");
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle("IP Location");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_ip_search, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDividerHeight(0);
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(onQueryTextListener);
        searchView.setQueryHint("Search IP");

    }


    Subscription subscription;

    private void callSearch(String ip) {
        if (!Methods.validIp(ip)) {
            showMessage("Waiting for valid ip");
            return;
        }

        final ApiSearch service = RetrofitService.createRetrofitClient("http://geo.groupkt.com/");
        subscription = service.getIPDetail(ip)
                //.skip(1)
                .throttleLast(100, TimeUnit.MILLISECONDS)
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        showMessage("");
        showLoader();
    }


    Observer<ApiIPSearchResponse> observer = new Observer<ApiIPSearchResponse>() {

        @Override
        public void onCompleted() {
            hideLoader();
            subscription.unsubscribe();
            AppLoger.e(TAG, "Data complete:");
        }

        @Override
        public void onError(Throwable e) {
            // Called when the observable encounters an error
            AppLoger.e(TAG, "Data: >>>> onError gets called : " + e.getMessage());
            showMessage("Error:" + e.getMessage());
            hideLoader();
        }

        @Override
        public void onNext(ApiIPSearchResponse staticData) {
            hideLoader();
            if (staticData == null || staticData.getRestResponse() == null) {
                showMessage("Some error occurs");
            } else {
                RestIPResponse.Loc response = staticData.getRestResponse().getLocation();
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                intent.putExtra("lat", response.getLat());
                intent.putExtra("longi", response.getLat());
                intent.putExtra("ip", response.getIp());

                startActivity(intent);
                AppLoger.e(TAG, "Data: >>>> ip : " + response.getIp());
                AppLoger.e(TAG, "Data: >>>> lat : " + response.getLat());
                AppLoger.e(TAG, "Data: >>>> long : " + response.getLongi());
            }
        }
    };
}
