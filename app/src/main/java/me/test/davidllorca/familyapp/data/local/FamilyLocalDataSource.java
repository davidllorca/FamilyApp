package me.test.davidllorca.familyapp.data.local;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import me.test.davidllorca.familyapp.data.FamilyDataSource;
import me.test.davidllorca.familyapp.data.model.Member;

/**
 * Implementation of a data source as local database.
 *
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/11/17.
 */
public class FamilyLocalDataSource implements FamilyDataSource {


    // Singleton instantiation
    private static final Object LOCK = new Object();
    private static FamilyLocalDataSource sInstance;

    private MemberDao mMemberDao;

    private FamilyLocalDataSource(MemberDao memberDao) {
        mMemberDao = memberDao;
    }

    /**
     * Return single instance of local data source.
     *
     * @param memberDao Dao to access local SQLite database.
     * @return {@link FamilyLocalDataSource} instance.
     */
    public synchronized static FamilyLocalDataSource getInstance(@NonNull MemberDao memberDao) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new FamilyLocalDataSource(memberDao);
                }
            }
        }
        return sInstance;
    }

    @Override
    public Flowable<List<Member>> getMembers() {
        return mMemberDao.getAll();
    }

    @Override
    public Flowable<List<Member>> getChildren() {
        return mMemberDao.getByRoles(Member.getChildTypes());
    }

    @Override
    public Completable saveMembers(@NonNull List<Member> members) {
        return Completable.defer(() -> {
            Long[] rows = mMemberDao.insert(members);
            if (rows.length > 0) {
                return Completable.complete();
            } else {
                return Completable.error(new Throwable("Error inserting entries"));
            }
        });
    }
}
