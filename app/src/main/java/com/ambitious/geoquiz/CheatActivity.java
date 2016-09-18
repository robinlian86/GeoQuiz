package com.ambitious.geoquiz;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private TextView mAnswerTextView;
    private TextView mApiLevelTextView;
    private Button mShowAnswer;
    private boolean mAnswerIsTrue;
    private boolean mAnswerIsShown;
    private static final String KEY_IS_SHOWN = "is_shown";
    //private static final String KEY_IS_TRUE = "is_true";
    public static final String EXTRA_ANSWER_IS_TRUE =
            "com.ambitious.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN =
            "com.ambitious.geoquiz.answer_shown";

    private void setAnswerShowResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    private void showAnswer(boolean isAnswerTrue) {
        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
        if(isAnswerTrue){
            mAnswerTextView.setText(R.string.true_button);
        }else{
            mAnswerTextView.setText(R.string.false_button);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        if (savedInstanceState != null) {
            mAnswerIsShown = savedInstanceState.getBoolean(KEY_IS_SHOWN, false);
        }

        if (mAnswerIsShown) {
            showAnswer(mAnswerIsTrue);
        }
        setAnswerShowResult(mAnswerIsShown);

        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mAnswerIsShown = true;
                showAnswer(mAnswerIsTrue);
                setAnswerShowResult(mAnswerIsShown);
            }
        });

        mApiLevelTextView = (TextView)findViewById(R.id.apiLevelTextView);
        int apiLevel = Build.VERSION.SDK_INT;
        String strApiLevel = "API level: " + String.valueOf(apiLevel);
        mApiLevelTextView.setText(strApiLevel);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_IS_SHOWN, mAnswerIsShown);
    }
}
