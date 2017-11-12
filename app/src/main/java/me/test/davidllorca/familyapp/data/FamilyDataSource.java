package me.test.davidllorca.familyapp.data;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import me.test.davidllorca.familyapp.data.model.Member;

/**
 * Entry point accessors to data.
 * <p>
 * Created by David Llorca <davidllorcabaron@gmail.com> on 10/11/17.
 */

public interface FamilyDataSource {

    Flowable<List<Member>> getMembers();

    Flowable<List<Member>> getChildren();

    Completable saveMembers(@NonNull List<Member> members);

}
