package com.typingpet.app;
import android.app.*;import android.os.*;import android.content.*;import android.graphics.Color;import android.net.Uri;import android.provider.Settings;import android.view.*;import android.widget.*;import android.view.inputmethod.InputMethodManager;
public class MainActivity extends Activity{
 LinearLayout root; TextView status;
 @Override public void onCreate(Bundle b){super.onCreate(b);root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setPadding(28,40,28,28); TextView title=new TextView(this);title.setText("Typing Pet");title.setTextSize(30);title.setTextColor(Color.rgb(22,184,170));root.addView(title,new LinearLayout.LayoutParams(-1,-2));status=new TextView(this);status.setText("휴대폰 화면 위에 펫을 띄우고 키보드 입력에 반응하게 합니다.");status.setPadding(0,20,0,20);root.addView(status);
 Button overlay=new Button(this);overlay.setText("① 화면 위 표시 권한");overlay.setOnClickListener(v->{if(!Settings.canDrawOverlays(this))startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:"+getPackageName())));else startPet();});root.addView(overlay);
 Button keyboard=new Button(this);keyboard.setText("② Typing Pet 키보드 켜기");keyboard.setOnClickListener(v->{startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));});root.addView(keyboard);
 Button choose=new Button(this);choose.setText("③ Typing Pet 키보드 선택");choose.setOnClickListener(v->{((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).showInputMethodPicker();});root.addView(choose);
 Button start=new Button(this);start.setText("펫 띄우기");start.setOnClickListener(v->startPet());root.addView(start);
 setContentView(root); }
 void startPet(){if(!Settings.canDrawOverlays(this)){status.setText("먼저 화면 위 표시 권한을 허용해 주세요.");return;}Intent i=new Intent(this,PetOverlayService.class);if(Build.VERSION.SDK_INT>=26)startForegroundService(i);else startService(i);status.setText("펫 실행 중! 키보드에서 Typing Pet을 선택하면 입력에 반응합니다.");}
}
