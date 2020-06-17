package razerdp.design;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import razerdp.basepopup.BasePopupWindow;
import razerdp.library.R;
import razerdp.util.MyTextWatcher;

public class ModifyMessageDialogPopup extends BasePopupWindow implements View.OnClickListener{

    private final EditText mContent;
    private final TextView mTextNum;

    public interface DialogClickCallBack{
        void okClick(String newName);
    }
    private DialogClickCallBack mDialogClickCallBack;

    public void setDialogClickCallBack(DialogClickCallBack callBack){
        this.mDialogClickCallBack = callBack;
    }

    private TextView ok;
    private TextView cancel;

    public ModifyMessageDialogPopup(final Activity context) {
        super(context);
        ok= (TextView) findViewById(R.id.ok);
        cancel= (TextView) findViewById(R.id.cancel);
        mContent = (EditText) findViewById(R.id.content);
        mTextNum = (TextView) findViewById(R.id.text_num);
        mContent.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 16)mTextNum.setText(s.length()+"/16");
                else {
                    Toast.makeText(context, "最多只能16位", Toast.LENGTH_SHORT).show();
                    mContent.setText(s.toString().substring(0,16));
                }
            }
        });
        setViewClickListener(this,ok,cancel);
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
        return null;
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.modify_msg_dialog);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ok) {
            if (mDialogClickCallBack != null) mDialogClickCallBack.okClick(mContent.getText().toString());
        } else if (i == R.id.cancel) {
            this.dismiss();
        }
    }
}
