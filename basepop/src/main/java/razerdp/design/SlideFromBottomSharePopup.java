package razerdp.design;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import razerdp.basepopup.BasePopupWindow;
import razerdp.library.R;

public class SlideFromBottomSharePopup extends BasePopupWindow implements View.OnClickListener {

    private View popupView;


    public SlideFromBottomSharePopup(Activity context) {
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
        return null;
    }

    @Override
    public View onCreatePopupView() {
        popupView = LayoutInflater.from(getContext()).inflate(R.layout.popup_slide_from_bottom_share, null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView != null) {
            TextView shareQQ = popupView.findViewById(R.id.share_qq);
            TextView shareZone = popupView.findViewById(R.id.share_zone);
            TextView copyUrl = popupView.findViewById(R.id.copy_url);
            TextView cancel = popupView.findViewById(R.id.cancel_share);
            setViewClickListener(this, shareQQ, shareZone, copyUrl, cancel);
        }

    }

    public final int QQ_CLICK = 1, ZONE_CLICK = 2, WECHAT_CLICK = 3, CANCEL_CLICK = 4;

    @Override
    public void onClick(View v) {
        if (sBottomPopClick == null) return;
        if (v.getId() == R.id.share_qq) sBottomPopClick.clickFun(QQ_CLICK);
        else if (v.getId() == R.id.share_zone) sBottomPopClick.clickFun(ZONE_CLICK);
        else if (v.getId() == R.id.copy_url) sBottomPopClick.clickFun(WECHAT_CLICK);
        else if (v.getId() == R.id.cancel_share) sBottomPopClick.clickFun(CANCEL_CLICK);
    }

    public interface BottomPopClick {
        void clickFun(int pos);
    }

    private BottomPopClick sBottomPopClick;

    public void setBottomClickListener(BottomPopClick clickListener) {
        sBottomPopClick = clickListener;
    }

}
