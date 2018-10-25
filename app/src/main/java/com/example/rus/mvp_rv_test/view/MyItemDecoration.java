package com.example.rus.mvp_rv_test.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MyItemDecoration extends RecyclerView.ItemDecoration {

    private int itemOffset;

    public MyItemDecoration(int itemOffset) {
        this.itemOffset = itemOffset;
    }

    public MyItemDecoration(Context context, int itemOffsetId) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) != 0){
            outRect.set(itemOffset, itemOffset, itemOffset, 0);
        } else {
            outRect.set(itemOffset, 0, itemOffset, 0);
        }
    }
}
