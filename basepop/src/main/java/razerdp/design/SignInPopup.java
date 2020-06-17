package razerdp.design;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import razerdp.basepopup.BasePopupWindow;
import razerdp.library.R;
import razerdp.util.MyTextWatcher;

public class SignInPopup extends BasePopupWindow implements View.OnClickListener{

    private ImageView close;
    private ImageView animation;
    public TextView num;
    private AnimationDrawable drawable;

    public SignInPopup(final Activity context) {
        super(context);
    }

    @Override
    public void setUpView() {
        close= (ImageView) findViewById(R.id.iv_home_sign_close);
        close.setOnClickListener(this);
        animation = (ImageView) findViewById(R.id.dialog_bg);
        num = (TextView) findViewById(R.id.tv_earn_coin);
        drawable = (AnimationDrawable) animation.getDrawable();
    }

    public void startAnimation(){
        drawable.start();
    }

    public void stopAnimation(){
        drawable.stop();
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.sign_in_layout);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_home_sign_close) {
           this.dismiss();
        }
    }
}
