package me.test.davidllorca.familyapp.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import me.test.davidllorca.familyapp.data.model.Member;

/**
 * DAO to local data.
 *
 * Created by David Llorca <davidllorcabaron@gmail.com> on 10/11/17.
 */
@Dao
public interface MemberDao {

    @Query("SELECT * FROM members ORDER BY age DESC")
    Flowable<List<Member>> getAll();

    @Query("SELECT * FROM members WHERE role IN (:roles) ORDER BY age DESC")
    Flowable<List<Member>> getByRoles(int[] roles);

    @Insert
    Long[] insert(List<Member> members);

}
