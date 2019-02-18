package net.csc625.checkin.services;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import net.csc625.checkin.utils.Constants;
import net.csc625.checkin.R;


/**
 * Created by sristic on 3/19/18.
 */

public class PopupService {
    Context mContext;
    PopupWindow popupWindow;

    public PopupService(Context mContext) {
        this.mContext = mContext;
    }

    public void showPopup(String text){

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.popup_layout,null);

        //instantiate popup window
        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //display the popup window
        popupWindow.setAnimationStyle(R.style.popup_window_animation_phone);
        popupWindow.showAtLocation(customView, Gravity.CENTER, 0, 0);
        dimBehind(popupWindow);
        TextView closePopupBtn = (TextView) customView.findViewById(R.id.closePopupBtn);
        TextView descText = (TextView) customView.findViewById(R.id.desc);
        if (!text.equals("")) {
            descText.setText(text);
        } else {
            descText.setText(R.string.no_description);
        }
        closePopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public static void dimBehind(PopupWindow popupWindow) {
        View container;
        if (popupWindow.getBackground() == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                container = (View) popupWindow.getContentView().getParent();
            } else {
                container = popupWindow.getContentView();
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                container = (View) popupWindow.getContentView().getParent().getParent();
            } else {
                container = (View) popupWindow.getContentView().getParent();
            }
        }
        Context context = popupWindow.getContentView().getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = Constants.DIM_BACKGROUND;
        wm.updateViewLayout(container, p);
    }
}
