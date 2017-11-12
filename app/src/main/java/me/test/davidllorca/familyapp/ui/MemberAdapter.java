package me.test.davidllorca.familyapp.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.test.davidllorca.familyapp.R;
import me.test.davidllorca.familyapp.data.model.Member;

/**
 * Adapter by collection of {@link Member}.
 *
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/11/17.
 */
public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberAdapterViewHolder> {

    private Context mContext;
    private List<Member> mMembers;

    public MemberAdapter(Context context, List<Member> mMembers) {
        this.mContext = context;
        this.mMembers = mMembers;
    }

    @Override
    public MemberAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_member, parent, false);
        view.setFocusable(true);
        return new MemberAdapterViewHolder(view);    }

    @Override
    public void onBindViewHolder(MemberAdapterViewHolder holder, int position) {
        Member member = mMembers.get(position);
        // {"Father", "Mother", "Son",..}
        String[] roles = mContext.getResources().getStringArray(R.array.role_array);

        String text = mContext.getString(R.string.text_member,
                member.getName(),
                member.getSurname(),
                member.getAge(),
                member.getGender(),
                roles[member.getRole()]);
        holder.mText.setText(text);
    }

    @Override
    public int getItemCount() {
        return mMembers.size();
    }

    public void load(List<Member> members) {
        mMembers = members;
        notifyDataSetChanged();
    }

    public class MemberAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_member_text)
        TextView mText;

        public MemberAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
