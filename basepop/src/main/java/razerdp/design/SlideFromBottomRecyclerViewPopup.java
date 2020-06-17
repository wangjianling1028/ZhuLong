package razerdp.design;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;
import razerdp.library.R;

public class SlideFromBottomRecyclerViewPopup extends BasePopupWindow {

    private View popupView;
    private RecyclerView mRecyclerView;
    public List<String> list = new ArrayList<>();
    public SlideFromBottomRecycleAdapter mAdapter;

    public SlideFromBottomRecyclerViewPopup(Activity context) {
        super(context);
        bindEvent();
    }

    @Override
    protected Animation initShowAnimation() {
        return getTranslateVerticalAnimation(1f, 0, 500);
    }

    @Override
    protected Animation initExitAnimation() {
        return getTranslateVerticalAnimation(0, 1f, 500);
    }

    @Override
    public View getClickToDismissView() {
        return popupView.findViewById(R.id.click_to_dismiss);
    }

    @Override
    public View onCreatePopupView() {
        popupView = LayoutInflater.from(getContext()).inflate(R.layout.popup_slide_from_bottom_recycle, null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.recyclerView);
    }

    private void bindEvent() {
        if (popupView != null) {
            mRecyclerView = popupView.findViewById(R.id.recyclerView);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mAdapter = new SlideFromBottomRecycleAdapter(list);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
