package me.test.davidllorca.familyapp.ui.listchildren;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.test.davidllorca.familyapp.Injection;
import me.test.davidllorca.familyapp.R;
import me.test.davidllorca.familyapp.data.model.Member;
import me.test.davidllorca.familyapp.ui.MemberAdapter;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/11/17.
 */

public class ListChildrenActivity extends AppCompatActivity implements ListChildrenContract.View {

    /* VIEWS */
    @BindView(R.id.toolbar_list_children)
    Toolbar mToolbar;
    @BindView(R.id.rv_list_children)
    RecyclerView mList;

    private ListChildrenPresenter mPresenter;

    private MemberAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_children);
        ButterKnife.bind(this);

        // Init toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Init presenter
        mPresenter = new ListChildrenPresenter(this,
                Injection.provideFamilyRepository(getApplicationContext()));

        // Init views
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MemberAdapter(this, new ArrayList<>());
        mList.setLayoutManager(layoutManager);
        mList.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadData();
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
    public void showChildren(List<Member> members) {
        mAdapter.load(members);
    }

}
