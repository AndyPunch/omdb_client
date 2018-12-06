package program.java.punch.andr.myapplication.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import program.java.punch.andr.myapplication.R;


public class GeneralUtils {

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        if (progressDialog.getWindow() != null) {
            progressDialog.show();
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.progress_dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        return progressDialog;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService
                (Activity.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();
        if (inputMethodManager != null && focusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken
                    (), 0);
        }
    }

}
