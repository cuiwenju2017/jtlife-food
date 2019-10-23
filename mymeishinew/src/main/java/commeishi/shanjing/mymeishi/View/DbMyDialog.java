package commeishi.shanjing.mymeishi.View;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2018/10/29.
 */

public class DbMyDialog extends Dialog {
    //    style引用style样式
    public DbMyDialog(Context context, int width, int height, View layout, int style) {

        super(context, style);

        setContentView(layout);

        Window window = getWindow();

        WindowManager.LayoutParams params = window.getAttributes();

        params.gravity = Gravity.BOTTOM;
        params.width= WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
    }
}
