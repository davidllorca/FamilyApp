package me.test.davidllorca.familyapp.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import me.test.davidllorca.familyapp.Injection;
import me.test.davidllorca.familyapp.R;
import me.test.davidllorca.familyapp.ui.home.HomeActivity;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/11/17.
 */

public class SplashActivity extends AppCompatActivity implements SplashContract.View{

    private SplashContract.Presenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Init presenter
        mPresenter =
                new SplashPresenter(this, Injection.provideFamilyRepository(this));
    }

    @Override
    public void showInitDatabaseMsg() {
        Toast.makeText(this, R.string.msg_populate_database_init, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInitDatabaseSuccessMsg() {
        Toast.makeText(this, R.string.msg_populate_database_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMsg() {
        Toast.makeText(this, R.string.msg_error_generic, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onFinishDatabaseTask() {
        launchHomeActivity();
    }

    private void launchHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
