package com.utils.zl.libs.utils.swipeMenu;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.zxyilian.nurse.util.LogUtil;


/**
 * 作用：带左划菜单的listView，当然菜单得要设置下
 * 创建时间：2015年12月2日 下午2:08:28
 * 作者：liubp
 *
 * 本代码中将其进行改造，只需要实现pullAble借口即可
 */
public class SwipeMenuListView extends ListView {

	private static final int TOUCH_STATE_NONE = 0;
	private static final int TOUCH_STATE_X = 1;
	private static final int TOUCH_STATE_Y = 2;

	private int MAX_Y = 5;
	private int MAX_X = 3;
	private float mDownX;
	private float mDownY;
	private int mTouchState;
	private int mTouchPosition;
	private SwipeMenuLayout mTouchView;
	private OnSwipeListener mOnSwipeListener;

	private SwipeMenuCreator mMenuCreator;
	private OnMenuItemClickListener mOnMenuItemClickListener;
	private Interpolator mCloseInterpolator;
	private Interpolator mOpenInterpolator;
//
//    private boolean canPullDown = true;
//    private boolean canPullUp = true;

	public SwipeMenuListView(Context context) {
		super(context);
		init();
	}

	public SwipeMenuListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public SwipeMenuListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		MAX_X = dp2px(MAX_X);
		MAX_Y = dp2px(MAX_Y);
		mTouchState = TOUCH_STATE_NONE;
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(new SwipeMenuAdapter(getContext(), adapter) {
			@Override
			public void createMenu(SwipeMenu menu) {
				if (mMenuCreator != null) {
					mMenuCreator.create(menu);
				}
			}

			@Override
			public void onItemClick(SwipeMenuView view, SwipeMenu menu,int index) {
				boolean flag = false;
				if (mOnMenuItemClickListener != null) {
					flag = mOnMenuItemClickListener.onMenuItemClick(view.getPosition(), menu, index);
				}
				if (mTouchView != null && !flag) {
					mTouchView.smoothCloseMenu();
				}
			}
		});
	}

	public void setCloseInterpolator(Interpolator interpolator) {
		mCloseInterpolator = interpolator;
	}

	public void setOpenInterpolator(Interpolator interpolator) {
		mOpenInterpolator = interpolator;
	}

	public Interpolator getOpenInterpolator() {
		return mOpenInterpolator;
	}

	public Interpolator getCloseInterpolator() {
		return mCloseInterpolator;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
        //requestDisallowInterceptTouchEvent(true);
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
        LogUtil.d(this.getClass().getSimpleName(),"onTouchEvent");
		if (ev.getAction() != MotionEvent.ACTION_DOWN && mTouchView == null)
			return super.onTouchEvent(ev);
		int action = MotionEventCompat.getActionMasked(ev);
		action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			int oldPos = mTouchPosition;
			mDownX = ev.getX();
			mDownY = ev.getY();
			mTouchState = TOUCH_STATE_NONE;

			mTouchPosition = pointToPosition((int) ev.getX(), (int) ev.getY());

			if (mTouchPosition == oldPos && mTouchView != null && mTouchView.isOpen()) {
				mTouchState = TOUCH_STATE_X;
				mTouchView.onSwipe(ev);
				return true;
			}

			View view = getChildAt(mTouchPosition - getFirstVisiblePosition());

			if (mTouchView != null && mTouchView.isOpen()) {
				mTouchView.smoothCloseMenu();
				mTouchView = null;
				// return super.onTouchEvent(ev);
				// try to cancel the touch event
				MotionEvent cancelEvent = MotionEvent.obtain(ev);
				cancelEvent.setAction(MotionEvent.ACTION_CANCEL);
				onTouchEvent(cancelEvent);
				return true;
			}
			if (view instanceof SwipeMenuLayout) {
				mTouchView = (SwipeMenuLayout) view;
			}
			if (mTouchView != null) {
				mTouchView.onSwipe(ev);
			}
			break;
		case MotionEvent.ACTION_MOVE:
			float dy = Math.abs((ev.getY() - mDownY));
			float dx = Math.abs((ev.getX() - mDownX));
			if (mTouchState == TOUCH_STATE_X) {
				if (mTouchView != null) {
					mTouchView.onSwipe(ev);
				}
				getSelector().setState(new int[] { 0 });
				ev.setAction(MotionEvent.ACTION_CANCEL);
				super.onTouchEvent(ev);
				return true;
			} else if (mTouchState == TOUCH_STATE_NONE) {
				if (Math.abs(dy) > MAX_Y) {
					mTouchState = TOUCH_STATE_Y;
				} else if (dx > MAX_X) {
					mTouchState = TOUCH_STATE_X;
					if (mOnSwipeListener != null) {
						mOnSwipeListener.onSwipeStart(mTouchPosition);
					}
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			if (mTouchState == TOUCH_STATE_X) {
                LogUtil.d(this.getClass().getSimpleName(),"ACTION_UP");
				if (mTouchView != null) {
                    LogUtil.d(this.getClass().getSimpleName(),"ACTION_UP--Open");
					mTouchView.onSwipe(ev);
					if (!mTouchView.isOpen()) {
						mTouchPosition = -1;
						mTouchView = null;
					}
				}
				if (mOnSwipeListener != null) {
					mOnSwipeListener.onSwipeEnd(mTouchPosition);
				}
				ev.setAction(MotionEvent.ACTION_CANCEL);
				super.onTouchEvent(ev);
				return true;
			}
			break;
		}
		return super.onTouchEvent(ev);
	}

	public void smoothOpenMenu(int position) {
		if (position >= getFirstVisiblePosition()
				&& position <= getLastVisiblePosition()) {
			View view = getChildAt(position - getFirstVisiblePosition());
			if (view instanceof SwipeMenuLayout) {
				mTouchPosition = position;
				if (mTouchView != null && mTouchView.isOpen()) {
					mTouchView.smoothCloseMenu();
				}
				mTouchView = (SwipeMenuLayout) view;
				mTouchView.smoothOpenMenu();
			}
		}
	}

	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getContext().getResources().getDisplayMetrics());
	}

	public void setMenuCreator(SwipeMenuCreator menuCreator) {
		this.mMenuCreator = menuCreator;
	}

	/**
	 * 作用：设置滑出菜单的回调接口
	 * 创建时间：2015年12月3日 上午10:20:41
	 * 作者：liubp
	 * @param onMenuItemClickListener
	 */
	public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
		this.mOnMenuItemClickListener = onMenuItemClickListener;
	}

	public void setOnSwipeListener(OnSwipeListener onSwipeListener) {
		this.mOnSwipeListener = onSwipeListener;
	}



	/**
	 * 作用：滑动出来的菜单点击的接口
	 * 创建时间：2015年12月3日 上午10:17:59
	 * 作者：liubp
	 */
	public static interface OnMenuItemClickListener {
		
		/**
		 * 作用：接口方法
		 * 创建时间：2015年12月3日 上午10:18:44
		 * 作者：liubp
		 * @param position 对应的listview的条目
		 * @param menu SwipeMenu
		 * @param index 点击的那一个，按添加顺序从0开始
		 * @return
		 */
		boolean onMenuItemClick(int position, SwipeMenu menu, int index);
	}

	public static interface OnSwipeListener {
		void onSwipeStart(int position);

		void onSwipeEnd(int position);
	}

//    /////实现Pullable借口部分的代码,因为在当前的医疗项目中需要实现这样的业务要求////////////
//    @Override
//    public boolean canPullDown() {
//
//        if (!canPullDown) {
//            return false;
//        }
//
//        if (getCount() == 0) {
//            // 没有item的时候也可以下拉刷新
//            return true;
//        } else if (getFirstVisiblePosition() == 0 && getChildAt(0).getTop() >= 0) {
//            // 滑到ListView的顶部了
//            return true;
//        } else
//            return false;
//    }
//
//    @Override
//    public boolean canPullUp() {
//
//        if (!canPullUp) {
//            return false;
//        }
//
//        if (getCount() == 0) {
//            // 没有item的时候也可以上拉加载
//            return true;
//        } else if (getLastVisiblePosition() == (getCount() - 1)) {
//            // 滑到底部了
//            if (getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()) != null
//                    && getChildAt(
//                    getLastVisiblePosition()
//                            - getFirstVisiblePosition()).getBottom() <= getMeasuredHeight())
//                return true;
//        }
//        return false;
//    }
//
//
//    @Override
//    public void setPullUp(boolean canPullUp) {
//        this.canPullUp = canPullUp;
//
//    }
//
//    @Override
//    public void setPullDown(boolean canPullDown) {
//        this.canPullDown = canPullDown;
//
//    }
}
