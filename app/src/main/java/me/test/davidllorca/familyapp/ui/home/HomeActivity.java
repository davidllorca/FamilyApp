package me.test.davidllorca.familyapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.test.davidllorca.familyapp.Injection;
import me.test.davidllorca.familyapp.R;
import me.test.davidllorca.familyapp.data.model.Member;
import me.test.davidllorca.familyapp.ui.MemberAdapter;
import me.test.davidllorca.familyapp.ui.addmember.AddChildActivity;
import me.test.davidllorca.familyapp.ui.listchildren.ListChildrenActivity;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/11/17.
 */

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    /* VIEWS */
    @BindView(R.id.toolbar_home)
    Toolbar mToolbar;
    @BindView(R.id.rv_home)
    RecyclerView mList;
    @BindView(R.id.fab_home)
    FloatingActionButton mFab;

    private HomePresenter mPresenter;

    private MemberAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        // Init presenter
        mPresenter = new HomePresenter(this,
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_see_children) {
            launchChildrenActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void launchChildrenActivity() {
        Intent intent = new Intent(this, ListChildrenActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.fab_home)
    public void launchAddMemberActivity() {
        Intent intent = new Intent(this, AddChildActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMembers(List<Member> members) {
        mAdapter.load(members);
    }
}
