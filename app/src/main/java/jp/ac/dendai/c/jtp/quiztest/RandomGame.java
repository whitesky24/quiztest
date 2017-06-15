package jp.ac.dendai.c.jtp.quiztest;


import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;



public class RandomGame extends Activity {

    String QuestionNo;
    String Seikai;
    // SoundPool(効果音再生)
    private SoundPool mSoundPool;
    private int[] mSoundId = new int[2]; // 使う効果音の数だけ配列作成

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //タイトルバーを非表示にする（必ずsetContentViewの前にすること）
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // レイアウトをセットする
        setContentView(R.layout.activity_main_game);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 問題文セット処理呼び出し
        setQuestion();

        // 効果音を使えるように読み込み
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        mSoundId[0] = mSoundPool.load(getApplicationContext(), R.raw.se_maoudamashii_onepoint15, 1);
        mSoundId[1] = mSoundPool.load(getApplicationContext(), R.raw.se_maoudamashii_onepoint33, 1);

        ((TextView)findViewById(R.id.textNo)).setText("ランダム");
    }
    private void setQuestion(){
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        /*
        SELECT文
        テーブル名 MyTableから _idとQuestionNoがマッチする項目を取得する条件式
         */
        String sql = "SELECT Pref, City0, City1, City2, City3, City4 FROM MyTable WHERE _id=" + QuestionNo;

        // SQL文を実行してカーソルを取得
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        // データベースから取ってきたデータを変数にセット
        String Kenmei = c.getString(c.getColumnIndex("Pref")); // 問題文となる都道府県
        String Choice1 = c.getString(c.getColumnIndex("City1")); // 四択の選択肢1
        String Choice2 = c.getString(c.getColumnIndex("City2")); // 四択の選択肢2
        String Choice3 = c.getString(c.getColumnIndex("City3")); // 四択の選択肢3
        String Choice4 = c.getString(c.getColumnIndex("City4")); // 四択の選択肢4

        Seikai = c.getString(c.getColumnIndex("City0")); // 答え

        // データベースのクローズ処理
        c.close();
        db.close();

        ((TextView)findViewById(R.id.textQuestion)).setText(Kenmei); // 問題文となる都道府県をテキストに表示
        ((Button)findViewById(R.id.button1)).setText(Choice1); // 四択の選択肢1をボタンに表示
        ((Button)findViewById(R.id.button2)).setText(Choice2); // 四択の選択肢2をボタンに表示
        ((Button)findViewById(R.id.button3)).setText(Choice3); // 四択の選択肢3をボタンに表示
        ((Button)findViewById(R.id.button4)).setText(Choice4); // 四択の選択肢4をボタンに表示

    }
    // 選択肢がクリックされた時の処理
    public void onClick(View v){

    }

    // onPauseが呼ばれたら効果音に関する物は全て解放する
    @Override
    protected void onPause() {
        super.onPause();
        // SoundPool 解放
        mSoundPool.unload(mSoundId[0]);
        mSoundPool.unload(mSoundId[1]);

        mSoundPool.release();
    }
}
