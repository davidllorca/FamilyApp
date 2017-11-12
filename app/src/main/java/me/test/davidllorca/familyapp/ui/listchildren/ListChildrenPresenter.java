package me.test.davidllorca.familyapp.ui.listchildren;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.test.davidllorca.familyapp.data.FamilyRepository;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/11/17.
 */

public class ListChildrenPresenter implements ListChildrenContract.Presenter {

    private static final String LOG_TAG = ListChildrenPresenter.class.getSimpleName();

    private FamilyRepository mRepository;

    private ListChildrenContract.View mView;

    public ListChildrenPresenter(ListChildrenContract.View view, FamilyRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void loadData() {
        mRepository.getChildren()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(members -> mView.showChildren(members),
                        throwable -> Log.e(LOG_TAG, throwable.getMessage()));
    }
}
