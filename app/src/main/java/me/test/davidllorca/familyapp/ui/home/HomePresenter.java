package me.test.davidllorca.familyapp.ui.home;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.test.davidllorca.familyapp.data.FamilyRepository;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/11/17.
 */

public class HomePresenter implements HomeContract.Presenter {

    private static final String LOG_TAG = HomePresenter.class.getSimpleName();

    private FamilyRepository mRepository;

    private HomeContract.View mView;

    public HomePresenter(HomeContract.View view, FamilyRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void loadData() {
        mRepository.getMembers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(members -> {
                    mView.showMembers(members);
                }, throwable -> {
                    Log.e(LOG_TAG, throwable.getMessage());
                });
    }
}
