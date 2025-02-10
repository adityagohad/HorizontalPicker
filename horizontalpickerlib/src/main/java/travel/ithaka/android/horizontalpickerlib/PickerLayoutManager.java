package travel.ithaka.android.horizontalpickerlib;

import android.content.Context;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by adityagohad on 06/06/17.
 */
public class PickerLayoutManager extends LinearLayoutManager {
	
	private static final String TAG = "PickerLayoutManager";
	
	private float scaleDownBy = 0.66f; // Maximum scaling effect
	private float scaleDownDistance = 0.9f; // Distance factor for scaling
	private boolean changeAlpha = true; // Whether to change alpha during scaling
	
	private OnScrollStopListener onScrollStopListener;
	
	public PickerLayoutManager(Context context, int orientation, boolean reverseLayout) {
		super(context, orientation, reverseLayout);
	}
	
	@Override
	public void onLayoutChildren(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state) {
		try {
			super.onLayoutChildren(recycler, state);
			scaleDownViews();
			} catch (Exception e) {
			Log.e(TAG, "Error in onLayoutChildren: " + e.getMessage());
		}
	}
	
	@Override
	public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
		if (getOrientation() == HORIZONTAL) {
			int scrolled = super.scrollHorizontallyBy(dx, recycler, state);
			scaleDownViews();
			return scrolled;
		}
		return 0;
	}
	
	private void scaleDownViews() {
		int childCount = getChildCount();
		if (childCount == 0) return;
		
		float mid = getWidth() / 2.0f;
		float unitScaleDownDist = scaleDownDistance * mid;
		
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			if (child == null) continue;
			
			float childMid = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2.0f;
			float distance = Math.abs(mid - childMid);
			float scale = 1.0f - (scaleDownBy * Math.min(unitScaleDownDist, distance) / unitScaleDownDist);
			
			child.setScaleX(scale);
			child.setScaleY(scale);
			if (changeAlpha) {
				child.setAlpha(scale);
			}
		}
	}
	
	@Override
	public void onScrollStateChanged(int state) {
		super.onScrollStateChanged(state);
		if (state == RecyclerView.SCROLL_STATE_IDLE) {
			View selectedView = getCenteredView();
			if (onScrollStopListener != null && selectedView != null) {
				onScrollStopListener.onSelectedView(selectedView);
			}
		}
	}
	
	private View getCenteredView() {
		int childCount = getChildCount();
		if (childCount == 0) return null;
		
		float mid = getWidth() / 2.0f;
		float closestDistance = Float.MAX_VALUE;
		View closestChild = null;
		
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			if (child == null) continue;
			
			float childMid = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2.0f;
			float distance = Math.abs(mid - childMid);
			
			if (distance < closestDistance) {
				closestDistance = distance;
				closestChild = child;
			}
		}
		
		return closestChild;
	}
	
	public void setCurrentItem(RecyclerView recyclerView, int position, boolean smoothScroll) {
		if (recyclerView == null || getItemCount() == 0 || position < 0 || position >= getItemCount()) {
			return;
		}
		
		if (smoothScroll) {
			recyclerView.smoothScrollToPosition(position);
			} else {
			recyclerView.scrollToPosition(position);
		}
	}
	
	// Getter and Setter methods for customization
	public float getScaleDownBy() {
		return scaleDownBy;
	}
	
	public void setScaleDownBy(float scaleDownBy) {
		this.scaleDownBy = scaleDownBy;
	}
	
	public float getScaleDownDistance() {
		return scaleDownDistance;
	}
	
	public void setScaleDownDistance(float scaleDownDistance) {
		this.scaleDownDistance = scaleDownDistance;
	}
	
	public boolean isChangeAlpha() {
		return changeAlpha;
	}
	
	public void setChangeAlpha(boolean changeAlpha) {
		this.changeAlpha = changeAlpha;
	}
	
	public void setOnScrollStopListener(OnScrollStopListener listener) {
		this.onScrollStopListener = listener;
	}
	
	public interface OnScrollStopListener {
		void onSelectedView(View view);
	}
}
