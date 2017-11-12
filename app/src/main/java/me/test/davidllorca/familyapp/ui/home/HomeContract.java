package me.test.davidllorca.familyapp.ui.home;

import java.util.List;

import me.test.davidllorca.familyapp.data.model.Member;

/**
 * Contract between the View and the Presenter.
 * <p>
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/11/17.
 */

public interface HomeContract {

    interface View {

        void showMembers(List<Member> members);

    }

    interface Presenter {

        void loadData();

    }

}
