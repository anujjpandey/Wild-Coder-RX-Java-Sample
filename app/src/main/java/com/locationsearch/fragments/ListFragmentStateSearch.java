package com.locationsearch.fragments;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.locationsearch.BuildConfig;
import com.locationsearch.R;
import com.locationsearch.adapters.AdapterStates;
import com.locationsearch.entites.ApiStateSearchResponse;
import com.locationsearch.entites.State;
import com.locationsearch.interfaces.ApiSearch;
import com.locationsearch.services.RetrofitService;
import com.locationsearch.utils.AppLoger;
import com.locationsearch.utils.BaseListFragment;
import com.locationsearch.utils.Methods;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Wild Coder on 28/09/16.
 */
public class ListFragmentStateSearch extends BaseListFragment {
    // views declaration

    public static String TAG = ListFragmentStateSearch.class.getName();
    private List<State> stateList = new ArrayList<>();
    private AdapterStates adapterStates;

    // classes and actions declaration
    SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            if (isOnline()) {
                if (!newText.isEmpty())
                    callSearch(newText);
            } else
                Methods.noInternetDialog(getActivity());
            return true;
        }
    };
    //variable declaration

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setTitle("Search States");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_ip_search, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle("Search States");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapterStates = new AdapterStates(stateList, R.layout.adapter_item_states, getActivity());
        setListAdapter(adapterStates);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(onQueryTextListener);
        searchView.setQueryHint("Search for State");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the menu_global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    Subscription subscription;

    private void callSearch(String str) {
        if (str.isEmpty()) {
            stateList.clear();
            adapterStates.notifyDataSetChanged();
            return;
        }

        final ApiSearch service = RetrofitService.createRetrofitClient(BuildConfig.BASE_URL);
        subscription = service.getGlobalSearch(str)
                //.skip(1)
                .throttleLast(100, TimeUnit.MILLISECONDS)
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        showMessage("");
        showLoader();

    }

    Observer<ApiStateSearchResponse> observer = new Observer<ApiStateSearchResponse>() {

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
        public void onNext(ApiStateSearchResponse staticData) {
            hideLoader();
            if (staticData == null || staticData.getRestResponse() == null) {
                showMessage("Some error occurs");
            } else {
                List<State> states = staticData.getRestResponse().getResults();
                if (states == null || states.size() == 0) {
                    if (staticData.getRestResponse().getMessages() != null && staticData.getRestResponse().getMessages().size() > 0)
                        showMessage(staticData.getRestResponse().getMessages().get(0));

                    else
                        showMessage("No result found");
                } else {
                    AppLoger.e(TAG, "Data: >>>> size:" + states.size());
                    stateList.clear();
                    stateList.addAll(states);
                    adapterStates.notifyDataSetChanged();
                }
            }
        }
    };


}

