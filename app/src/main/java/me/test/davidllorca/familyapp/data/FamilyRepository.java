package me.test.davidllorca.familyapp.data;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import me.test.davidllorca.familyapp.data.model.Member;

/**
 * Repository implementation of data layer.
 * (NOTE: Just local persistence implemented)
 * <p>
 * Created by David Llorca <davidllorcabaron@gmail.com> on 10/11/17.
 */

public class FamilyRepository implements FamilyDataSource {

    @NonNull
    private final FamilyDataSource mFamilyLocalDataSource;

    // Singleton instantiation
    private static final Object LOCK = new Object();
    private static FamilyRepository sInstance;

    private FamilyRepository(@NonNull FamilyDataSource familyLocalDataSource) {
        mFamilyLocalDataSource = familyLocalDataSource;
    }

    /**
     * Return single instance of repository.
     *
     * @param familyLocalDataSource local storage data source.
     * @return {@link FamilyRepository} instance.
     */
    public static synchronized FamilyRepository getInstance(FamilyDataSource familyLocalDataSource) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new FamilyRepository(familyLocalDataSource);
            }
        }
        return sInstance;
    }

    @Override
    public Flowable<List<Member>> getMembers() {
        return mFamilyLocalDataSource.getMembers();
    }

    @Override
    public Flowable<List<Member>> getChildren() {
        return mFamilyLocalDataSource.getChildren();
    }

    @Override
    public Completable saveMembers(@NonNull List<Member> members) {
        return mFamilyLocalDataSource.saveMembers(members);
    }

    public Completable saveMember(@NonNull Member member) {
        List<Member> members = new ArrayList<>();
        members.add(member);
        return mFamilyLocalDataSource.saveMembers(members);
    }
}
