package razerdp.design;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import razerdp.basepopup.BasePopupWindow;
import razerdp.library.R;

public class CopyAddWechatDialogPopup extends BasePopupWindow implements View.OnClickListener{


    public ImageView mHead;
    public TextView mNum;

    public interface DialogClickCallBack{
        void addClick();
    }
    private DialogClickCallBack mDialogClickCallBack;

    public void setDialogClickCallBack(DialogClickCallBack callBack){
        this.mDialogClickCallBack = callBack;
    }


    public CopyAddWechatDialogPopup(Activity context) {
        super(context);
        mHead = (ImageView) findViewById(R.id.head_image);
        mNum = (TextView) findViewById(R.id.wechat_num);
        setViewClickListener(this,findViewById(R.id.click_to_add));
    }

    @Override
    protected Animation initShowAnimation() {
        AnimationSet set=new AnimationSet(false);
        Animation shakeAnima=new RotateAnimation(0,15, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        shakeAnima.setInterpolator(new CycleInterpolator(5));
        shakeAnima.setDuration(400);
        set.addAnimation(getDefaultAlphaAnimation());
        set.addAnimation(shakeAnima);
        return set;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.add_wechatpopup_dialog);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.click_to_add) {
            if (mDialogClickCallBack != null) mDialogClickCallBack.addClick();
        }
    }
}
