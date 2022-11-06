package kkn.vishu.mathana.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpacing extends RecyclerView.ItemDecoration {
    private final int spacing;
    private int displayMode;
    private boolean isOnlyRightSpacing;

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int GRID = 2;

    public GridSpacing(int spacing) {
        this(spacing, -1);
        isOnlyRightSpacing = false;
    }

    public GridSpacing(int spacing, int displayMode) {
        this.spacing = spacing;
        this.displayMode = displayMode;
    }

    public void isOnlyRightSpacing(boolean isOnlyRightSpacing) {
        this.isOnlyRightSpacing = isOnlyRightSpacing;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildViewHolder(view).getAdapterPosition();
        int itemCount = state.getItemCount();
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        setSpacingForDirection(outRect, layoutManager,
                view,parent,
                position, itemCount);
    }

    private void setSpacingForDirection(Rect outRect,
                                        RecyclerView.LayoutManager layoutManager,
                                        View view,
                                        RecyclerView parent,
                                        int position,
                                        int itemCount) {

        // Resolve display mode automatically
        if (displayMode == -1) {
            displayMode = resolveDisplayMode(layoutManager);
        }
        outRect.left = spacing;
        outRect.right = spacing;

        if(!isOnlyRightSpacing) {
            outRect.bottom = spacing;
            outRect.top = spacing;
        }

//        switch (displayMode) {
//            case HORIZONTAL:
//                outRect.left = spacing;
//                outRect.right = position == itemCount - 1 ? spacing : 0;
//                outRect.top = spacing;
//                outRect.bottom = spacing;
//                break;
//            case VERTICAL:
//                outRect.left = spacing;
//                outRect.right = spacing;
//                outRect.top = spacing;
//                outRect.bottom = position == itemCount - 1 ? spacing : 0;
//                break;
//            case GRID:
//                if (layoutManager instanceof GridLayoutManager) {
//                    GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
//                    int cols = gridLayoutManager.getSpanCount();
//                    int rows = itemCount / cols;
//
//                    outRect.left = spacing;
//                    outRect.right = position % cols == cols - 1 ? spacing : 0;
//                    outRect.top = spacing;
//                    outRect.bottom = position / cols == rows - 1 ? spacing : 0;
//                }
//                break;
//        }
    }

    private int resolveDisplayMode(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) return GRID;
        if (layoutManager.canScrollHorizontally()) return HORIZONTAL;
        return VERTICAL;
    }
}
