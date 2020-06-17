package razerdp.design;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import razerdp.basepopup.BasePopupWindow;
import razerdp.library.R;

public class UpdateDialogPopup extends BasePopupWindow implements View.OnClickListener{


    public TextView mVersionNum;
    public TextView mClose;
    private final TextView mConfirm;
    public View mCutView;

    public interface DialogClickCallBack{
        void addClick();
    }
    private DialogClickCallBack mDialogClickCallBack;

    public void setDialogClickCallBack(DialogClickCallBack callBack){
        this.mDialogClickCallBack = callBack;
    }


    public UpdateDialogPopup(Activity context) {
        super(context);
        mVersionNum = (TextView) findViewById(R.id.version_num);
        mClose = (TextView) findViewById(R.id.tv_close);
        mConfirm = (TextView) findViewById(R.id.tv_sure);
        mCutView = findViewById(R.id.vertical_cut_view);
        setViewClickListener(this,mClose,mConfirm);
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
        return createPopupById(R.layout.update_popup_dialog);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_sure) {
            if (mDialogClickCallBack != null) mDialogClickCallBack.addClick();
        } else if (i == R.id.tv_close)this.dismiss();
    }
}
