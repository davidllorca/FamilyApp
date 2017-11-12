package me.test.davidllorca.familyapp.ui.splash;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.test.davidllorca.familyapp.FamilyApplication;
import me.test.davidllorca.familyapp.R;
import me.test.davidllorca.familyapp.data.FamilyRepository;
import me.test.davidllorca.familyapp.data.model.Member;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/11/17.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private static final String LOG_TAG = SplashPresenter.class.getSimpleName();

    private FamilyRepository mRepository;

    private SplashContract.View mView;

    public SplashPresenter(SplashContract.View view, FamilyRepository repository) {
        mView = view;
        mRepository = repository;
        initAppData();
    }

    /**
     * Init database if this is not populated.
     */
    private void initAppData() {
        mRepository.getMembers()
                .delay(1500, TimeUnit.MILLISECONDS) // Simulate long operations
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(members -> {
                    if(members.size() > 0){
                        mView.onFinishDatabaseTask();
                    } else {
                        saveFakeData();
                    }
                }, throwable ->{
                    mView.showErrorMsg();
                    Log.e(LOG_TAG, throwable.getMessage());
                } );
    }

    private void saveFakeData() {
        mView.showInitDatabaseMsg();
        List<Member> members = getFakeMembers();
        mRepository.saveMembers(members)
                .delay(2000, TimeUnit.MILLISECONDS) // Simulate long operations
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                            mView.showInitDatabaseSuccessMsg();
                        },
                        throwable -> {
                            mView.showErrorMsg();
                            Log.e(LOG_TAG, throwable.getMessage());
                        });
    }

    /**
     * Generates fake data.
     *
     * @return List<Member> of fake members.
     */
    private List<Member> getFakeMembers() {
        Gson gson = new Gson();
        Reader reader = new InputStreamReader(FamilyApplication.getContext().getResources().openRawResource(R.raw.fake_data));
        Type type = new TypeToken<List<Member>>() {
        }.getType();
        List<Member> members = gson.fromJson(reader, type);
        return members;
    }


}
