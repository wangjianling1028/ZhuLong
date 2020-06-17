package razerdp.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import razerdp.basepopup.BasePopupWindow;
import razerdp.library.R;

public class ReportPopup extends BasePopupWindow {

    private View popupView;
    private TextView mRubbish;
    private TextView mReport;
    private TextView mNotInterested;
    private TextView mCancel;
    public final int RUBBISH = 1, REPORT = 2, INTERESTED = 3, CANCEL = 4;

    public ReportPopup(Context context) {
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
        popupView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_news_cross, null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView != null) {
            mRubbish = popupView.findViewById(R.id.rubbish);
            mReport = popupView.findViewById(R.id.report);
            mNotInterested = popupView.findViewById(R.id.not_interested);
            mCancel = popupView.findViewById(R.id.cancel);
            mRubbish.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (sBottomPopClick != null)
                                sBottomPopClick.reportClick(RUBBISH);
                        }
                    });
            mReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sBottomPopClick != null)
                        sBottomPopClick.reportClick(REPORT);
                }
            });
            mNotInterested.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sBottomPopClick != null)
                        sBottomPopClick.reportClick(INTERESTED);
                }
            });
            mCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sBottomPopClick != null) sBottomPopClick.reportClick(CANCEL);
                }
            });
        }

    }

    public interface BottomPopClick {
        void reportClick(int type);
    }

    public BottomPopClick sBottomPopClick;

    public void setBottomClickListener(BottomPopClick clickListener) {
        sBottomPopClick = clickListener;
    }

}
