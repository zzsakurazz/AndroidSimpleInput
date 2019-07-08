package com.sakura.simpleinput.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;

/**
 * @author zhangzheng
 * @Date 2019-07-05 18:00
 * @ClassName ClipboardUtil
 * <p>
 * Desc :
 */
public class ClipboardUtil {

    /**
     * 获取当前粘贴板内容
     *
     * @param context
     * @return
     */
    public static String getClipContent(Context context) {
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager != null) {
            if (manager.hasPrimaryClip() && manager.getPrimaryClip().getItemCount() > 0) {
                CharSequence addedText = manager.getPrimaryClip().getItemAt(0).getText();
                String addedTextString = String.valueOf(addedText);
                if (!TextUtils.isEmpty(addedTextString)) {
                    return addedTextString;
                }
            }
        }
        return "";
    }

    public static void clearClip(Context context){
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager!=null){
            manager.setPrimaryClip(ClipData.newPlainText(null, ""));
        }
    }
}
