package me.test.davidllorca.familyapp.ui.addmember;

import me.test.davidllorca.familyapp.data.model.Member;

/**
 * Contract between the View and the Presenter.
 * <p>
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/11/17.
 */

public interface AddChildContract {

    interface View {

        void onAddChildSuccess();

    }

    interface Presenter {

        void addChild(Member member);

    }
}
