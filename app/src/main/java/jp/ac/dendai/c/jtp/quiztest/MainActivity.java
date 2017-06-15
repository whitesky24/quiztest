package jp.ac.dendai.c.jtp.quiztest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //タイトルバーを非表示にする（必ずsetContentViewの前にすること）
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // レイアウトをセットする
        setContentView(R.layout.activity_main);


    }

    // ボタンがタッチされた時の処理

    // ボタンがタッチされた時の処理
    public void onClick(View v){
        switch (v.getId()) {
            // タッチされたボタンがノーマルの場合
            case R.id.button_normal:
                // 遷移先のActivityを指定
                // Intent intent = new　Intent(このクラスから, このクラスへ)
                Intent intent = new Intent(MainActivity.this, StageSelect.class);
                // 遷移開始
                startActivity(intent);
                break;
            // タッチされたボタンがランダムの場合
            case R.id.button_random:
                // 遷移先のActivityを指定
                // Intent intent = new　Intent(このクラスから, このクラスへ)
                intent = new Intent(MainActivity.this, RandomGame.class);
                // 遷移開始
                startActivity(intent);
                break;
            // タッチされたボタンがタイムアタックの場合
            case R.id.button_time:
                Toast.makeText(this, "タイムアタックがタッチされた！", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}