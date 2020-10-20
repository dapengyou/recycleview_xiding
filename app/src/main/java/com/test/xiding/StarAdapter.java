package com.test.xiding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @createTime: 2020/10/19
 * @author: lady_zhou
 * @Description:
 */
public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> {
    private Context mContext;
    private List<Star> mStarList;

    public StarAdapter(Context context, List<Star> starList) {
        mContext = context;
        mStarList = starList;
    }

    public boolean isGroupHeader(int position) {
        if (position == 0) {
            return true;
        } else {
            String currentGroupName = getGroupName(position);
            String preGroupName = getGroupName(position - 1);
            if (preGroupName.equals(currentGroupName)) {
                return false;
            } else {
                return true;
            }
        }
    }

    public String getGroupName(int position) {
        return mStarList.get(position).getGroundName();

    }

    @NonNull
    @Override
    public StarAdapter.StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_start, null);
        return new StarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StarAdapter.StarViewHolder holder, int position) {
        holder.tv.setText(mStarList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mStarList == null ? 0 : mStarList.size();
    }

    public class StarViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv = itemView.findViewById(R.id.tv_star);
        }
    }
}
