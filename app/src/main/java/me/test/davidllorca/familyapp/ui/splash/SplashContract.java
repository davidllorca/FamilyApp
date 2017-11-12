package me.test.davidllorca.familyapp.ui.splash;

/**
 * Contract between the View and the Presenter.
 *
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/11/17.
 */

public interface SplashContract {

    interface View {

        void showInitDatabaseMsg();

        void showInitDatabaseSuccessMsg();

        void showErrorMsg();

        void onFinishDatabaseTask();
    }

    interface Presenter {
        // No features yet
    }

}
