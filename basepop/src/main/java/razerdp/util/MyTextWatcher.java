package razerdp.util;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by 任小龙 on 2020/1/9.
 */
public abstract class MyTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        beforeMyTextChanged(s, start, count, after);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        onMyTextChanged(s, start, before, count);
    }

    @Override
    public void afterTextChanged(Editable s) {
        afterMyTextChanged(s);
    }

    public void afterMyTextChanged(Editable s) {

    }

    public void beforeMyTextChanged(CharSequence s, int start, int count, int after) {

    }

    public abstract void onMyTextChanged(CharSequence s, int start, int before, int count);
}
