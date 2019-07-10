package com.sakura.simpleinput.service;

import android.graphics.Typeface;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.sakura.simpleinput.R;
import com.sakura.simpleinput.utils.ClipboardUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author zhangzheng
 * @Date 2019-07-05 16:40
 * @ClassName SimpleInputMethodService
 * <p>
 * Desc :
 */
public class SimpleInputMethodService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {
    private String TAG = SimpleInputMethodService.class.getName();
    /**
     * 自定义的KeyboardView
     */
    private KeyboardView keyboardView;
    /**
     * 自定义的Keyboard
     */
    private Keyboard keyboard;
    private View view;
    private TextView mTitleTv;
    private TextView mContentTv;
    private Typeface typeFace;
    private TimerTask task;
    private Timer timer;
    private int index;
    private String results;
    Handler mHandler = new Handler() {


        public void handleMessage(Message param1Message) {
            if (param1Message.what == 1) {
                if (SimpleInputMethodService.this.index >= SimpleInputMethodService.this.results.length())
                    return;
                InputConnection inputConnection = SimpleInputMethodService.this.getCurrentInputConnection();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(SimpleInputMethodService.this.results.charAt(SimpleInputMethodService.this.index));
                stringBuilder.append("");
                inputConnection.commitText(stringBuilder.toString(), 1);
                index++;
            }
        }
    };


    /**
     * 当软键盘隐藏时停止输入
     *
     * @version
     * @date 2019-07-08 16:43
     * @author zhangzheng
     */
    @Override
    public void onWindowHidden() {
        super.onWindowHidden();
        Log.d(TAG, "onWindowHidden()");
        destroyTime();
    }


    @Override
    public void onWindowShown() {
        super.onWindowShown();
        results = ClipboardUtil.getClipContent(this);
        mContentTv.setText(results);
    }

    /**
     * 键盘 第一次现实的时候调用
     *
     * @return
     */
    @Override
    public View onCreateInputView() {
        view = getLayoutInflater().inflate(R.layout.layout_keyboard_view, null);
        initKeyView(view);
        initView(view);
        return view;
    }

    /**
     * 初始化软键盘相关view
     *
     * @param view
     * @version
     * @date 2019-07-08 16:46
     * @author zhangzheng
     */
    private void initKeyView(View view) {
        // keyboard被创建后，将调用onCreateInputView函数
        keyboardView = view.findViewById(R.id.keyboard);
        keyboard = new Keyboard(this, R.xml.qwerty);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
    }

    /**
     * 初始化标题、内容view
     *
     * @param view
     * @version
     * @date 2019-07-08 16:46
     * @author zhangzheng
     */
    private void initView(View view) {
        typeFace = Typeface.createFromAsset(getAssets(), "font.ttf");
        mTitleTv = view.findViewById(R.id.input_title_tv);
        mTitleTv.setTypeface(typeFace);
        mContentTv = view.findViewById(R.id.input_content_tv);
        mContentTv.setMovementMethod(ScrollingMovementMethod.getInstance());
        results = ClipboardUtil.getClipContent(this);
        mContentTv.setText(results);

    }

    public void deleteText() {
        destroyTime();
        for (byte b = 0; b < this.index; b++) {
            getCurrentInputConnection().deleteSurroundingText(1, 0);
        }
    }

    public void destroyTime() {
        if (this.task != null) {
            this.task.cancel();
            this.task = null;
        }
        if (this.timer != null) {
            this.timer.cancel();
            this.timer = null;
        }
    }

    public void initTimeAndTask() {
        this.timer = new Timer();
        this.task = new TimerTask() {
            public void run() {
                SimpleInputMethodService.this.mHandler.sendEmptyMessage(1);
            }
        };
    }

    public void inputCommitText() {
        this.index = 0;
        startTask();
    }

    public void startTask() {
        destroyTime();
        initTimeAndTask();
        this.timer.schedule(this.task, 0L, 50L);
    }


    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        if (primaryCode == 102)
            inputCommitText();
        if (primaryCode == 101)
            ((InputMethodManager) getSystemService("input_method")).showInputMethodPicker();
        if (primaryCode == 100)
            deleteText();
        if (primaryCode == 103){
            mContentTv.setText("");
            ClipboardUtil.clearClip(this);
        }

    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void onPress(int primaryCode) {
    }

    @Override
    public void onRelease(int primaryCode) {
    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
