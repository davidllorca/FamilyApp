package me.test.davidllorca.familyapp;

import android.content.Context;
import android.support.annotation.NonNull;

import me.test.davidllorca.familyapp.data.FamilyRepository;
import me.test.davidllorca.familyapp.data.local.FamilyDatabase;
import me.test.davidllorca.familyapp.data.local.FamilyLocalDataSource;

/**
 * Provides implementations to inject.
 *
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/11/17.
 */

public class Injection {

    /**
     * Provides implementation of {@link FamilyRepository}.
     *
     * @param context
     * @return FamilyRepository
     */
    public static FamilyRepository provideFamilyRepository(@NonNull Context context) {
        return FamilyRepository
                .getInstance(FamilyLocalDataSource
                        .getInstance(FamilyDatabase.getInstance(context).memberDao()));
    }
}