package razerdp.design;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import razerdp.basepopup.BasePopupWindow;
import razerdp.library.R;

public class GroupTabPopup extends BasePopupWindow {

    private View popupView;
    public RecyclerView popRecycle;

    public GroupTabPopup(Activity context) {
        super(context, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        bindEvent();
    }

    @Override
    protected Animation initShowAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, -dipToPx(350f), 0);
        translateAnimation.setDuration(450);
        translateAnimation.setInterpolator(new OvershootInterpolator(1));
        return translateAnimation;
    }

    @Override
    protected Animation initExitAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, 0, -dipToPx(350f));
        translateAnimation.setDuration(0);
        return translateAnimation;
    }

    @Override
    public View getClickToDismissView() {
        return findViewById(R.id.touch_dismiss);
    }

    @Override
    public View onCreatePopupView() {
        popupView = LayoutInflater.from(getContext()).inflate(R.layout.popup_slide_from_bottom, null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.group_pop_recycle);
    }

    private void bindEvent() {
        if (popupView != null) {
            popRecycle = popupView.findViewById(R.id.group_pop_recycle);
        }
    }
}
