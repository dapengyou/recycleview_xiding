package com.test.xiding;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @createTime: 2020/10/19
 * @author: lady_zhou
 * @Description:
 */
public class StarDecoration extends RecyclerView.ItemDecoration {
    private int groupHeaderHeight;
    private Paint headerPaint;
    private Paint textPaint;
    private Rect textRect;

    public StarDecoration(Context context) {
        groupHeaderHeight = dp2px(context, 100);

        headerPaint = new Paint();
        headerPaint.setColor(Color.RED);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(50);

        textRect = new Rect();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (parent.getAdapter() instanceof StarAdapter) {
            StarAdapter adapter = (StarAdapter) parent.getAdapter();
            int childCount = parent.getChildCount();//当前屏幕的item的个数
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            for (int i = 0; i < childCount; i++) {
                //获取对应的i 的view
                View view = parent.getChildAt(i);
                //获取view的布局位置
                int position = parent.getChildLayoutPosition(view);
                boolean isGroupHeader = adapter.isGroupHeader(position);//是否是头部

                if (isGroupHeader) {
                    //绘制矩形
                    c.drawRect(left, view.getTop() - groupHeaderHeight, right, view.getTop(), headerPaint);
                    String groupName = adapter.getGroupName(i);
                    textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                    c.drawText(groupName, left + 20, view.getTop() - groupHeaderHeight / 2 + textRect.height() / 2, textPaint);
                } else {
                    //分割线
                    c.drawRect(left, view.getTop() - 1, right, view.getTop() , headerPaint);
                }
            }
        }

    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getAdapter() instanceof StarAdapter) {
            StarAdapter adapter = (StarAdapter) parent.getAdapter();
            int position = parent.getChildLayoutPosition(view);
            boolean isGroupHeader = adapter.isGroupHeader(position);

            if (isGroupHeader) {
                outRect.set(0, groupHeaderHeight, 0, 0);

            } else {
                outRect.set(0, 1, 0, 0);
            }

        }
    }

    private int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale * 0.5f);
    }
}
