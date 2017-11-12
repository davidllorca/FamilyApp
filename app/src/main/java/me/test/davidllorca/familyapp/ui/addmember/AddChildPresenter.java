package me.test.davidllorca.familyapp.ui.addmember;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.test.davidllorca.familyapp.data.FamilyRepository;
import me.test.davidllorca.familyapp.data.model.Member;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/11/17.
 */

public class AddChildPresenter implements AddChildContract.Presenter {

    private static final String LOG_TAG = AddChildPresenter.class.getSimpleName();

    private FamilyRepository mRepository;

    private AddChildContract.View mView;

    public AddChildPresenter(AddChildContract.View view, FamilyRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void addChild(Member member) {
        mRepository.saveMember(member)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> mView.onAddChildSuccess(),
                        throwable -> Log.e(LOG_TAG, throwable.getMessage()));
    }

}
