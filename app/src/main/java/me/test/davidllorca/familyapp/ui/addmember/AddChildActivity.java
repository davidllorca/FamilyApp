package me.test.davidllorca.familyapp.ui.addmember;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import me.test.davidllorca.familyapp.Injection;
import me.test.davidllorca.familyapp.R;
import me.test.davidllorca.familyapp.data.model.Member;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/11/17.
 */

public class AddChildActivity extends AppCompatActivity implements AddChildContract.View {

    /* VIEWS */
    @BindView(R.id.view_add_child)
    View mMainView;
    @BindView(R.id.toolbar_add_child)
    Toolbar mToolbar;
    @BindView(R.id.et_add_child_name)
    TextInputEditText mNameEditText;
    @BindView(R.id.et_add_child_surname)
    TextInputEditText mSurnameEditText;
    @BindView(R.id.et_add_child_age)
    TextInputEditText mAgeEditText;
    @BindView(R.id.rg_add_child_gender)
    RadioGroup mGenderRadioGroup;
    @BindView(R.id.btn_add_child_save)
    Button mSaveButton;

    /* RESOURCES*/
    @BindArray(R.array.gender_array)
    String[] mGenderArray;

    private AddChildPresenter mAddChildPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
        ButterKnife.bind(this);

        // Init presenter
        mAddChildPresenter = new AddChildPresenter(this,
                Injection.provideFamilyRepository(getApplicationContext()));

        // Init toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAddChildSuccess() {
        Snackbar
                .make(mMainView, getString(R.string.msg_child_save_success), Snackbar.LENGTH_SHORT)
                .addCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        finish();
                    }
                }).show();
    }

    @OnTextChanged({R.id.et_add_child_name, R.id.et_add_child_surname, R.id.et_add_child_age})
    public void onTextChanged() {
        if (isFormValid()) {
            mSaveButton.setEnabled(true);
        } else {
            mSaveButton.setEnabled(false);
        }
    }

    /**
     * Checks content of all form fields without default value.
     *
     * @return true if all form fields are completed, false otherwise.
     */
    private boolean isFormValid() {
        return !TextUtils.isEmpty(mNameEditText.getText()) &&
                !TextUtils.isEmpty(mSurnameEditText.getText()) &&
                !TextUtils.isEmpty(mAgeEditText.getText());
    }

    @OnClick(R.id.btn_add_child_save)
    public void save() {
        // Tranform to Member field types required
        char gender = mGenderRadioGroup.getCheckedRadioButtonId() == R.id.rb_add_member_male ?
                mGenderArray[0].charAt(0) : mGenderArray[1].charAt(0);
        int role = gender == mGenderArray[0].charAt(0) ? Member.Role.SON : Member.Role.DAUGHTER;

        // Creates new member
        Member newMember = new Member(role,
                mNameEditText.getText().toString(),
                mSurnameEditText.getText().toString(),
                Integer.parseInt(mAgeEditText.getText().toString()),
                gender);

        // Save into database
        mAddChildPresenter.addChild(newMember);
    }

}
