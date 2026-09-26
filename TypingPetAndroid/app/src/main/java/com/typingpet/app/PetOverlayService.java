package com.typingpet.app;

import android.app.*;import android.content.*;import android.graphics.PixelFormat;import android.os.*;import android.provider.Settings;import android.view.*;import android.widget.*;import android.graphics.drawable.ColorDrawable;

public class PetOverlayService extends Service {
 public static final String ACTION_POSE="com.typingpet.app.POSE"; public static final String EXTRA_POSE="pose";
 WindowManager wm; ImageView pet; int size=180; boolean added=false;
 @Override public void onCreate(){super.onCreate();
  if(Build.VERSION.SDK_INT>=26){NotificationChannel c=new NotificationChannel("pet","Typing Pet",NotificationManager.IMPORTANCE_LOW);getSystemService(NotificationManager.class).createNotificationChannel(c);}
  Notification.Builder b=Build.VERSION.SDK_INT>=26?new Notification.Builder(this,"pet"):new Notification.Builder(this); b.setContentTitle("Typing Pet 실행 중").setSmallIcon(android.R.drawable.ic_menu_view).setOngoing(true); startForeground(10,b.build());
  IntentFilter f=new IntentFilter(ACTION_POSE);registerReceiver(receiver,f,RECEIVER_NOT_EXPORTED); show();
 }
 void show(){ if(!Settings.canDrawOverlays(this))return; wm=(WindowManager)getSystemService(WINDOW_SERVICE); pet=new ImageView(this);pet.setImageResource(R.drawable.basic);pet.setScaleType(ImageView.ScaleType.CENTER_INSIDE);pet.setBackground(new ColorDrawable(0x00000000));
  final WindowManager.LayoutParams lp=new WindowManager.LayoutParams(size,size,Build.VERSION.SDK_INT>=26?WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY:WindowManager.LayoutParams.TYPE_PHONE,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,PixelFormat.TRANSLUCENT);lp.gravity=Gravity.TOP|Gravity.START;lp.x=30;lp.y=180;
  pet.setOnTouchListener(new View.OnTouchListener(){float dx,dy;public boolean onTouch(View v,MotionEvent e){if(e.getAction()==0){dx=e.getRawX()-lp.x;dy=e.getRawY()-lp.y;return true;}if(e.getAction()==2){lp.x=(int)(e.getRawX()-dx);lp.y=(int)(e.getRawY()-dy);wm.updateViewLayout(pet,lp);return true;}return true;}});
  wm.addView(pet,lp);added=true;
 }
 BroadcastReceiver receiver=new BroadcastReceiver(){public void onReceive(Context c,Intent i){if(pet==null)return;String p=i.getStringExtra(EXTRA_POSE);int r=R.drawable.basic;if("left".equals(p))r=R.drawable.left;else if("right".equals(p))r=R.drawable.right;pet.setImageResource(r);}}
 @Override public int onStartCommand(Intent i,int f,int id){return START_STICKY;}
 @Override public void onDestroy(){try{unregisterReceiver(receiver);}catch(Exception e){}if(added)wm.removeView(pet);super.onDestroy();}
 @Override public android.os.IBinder onBind(Intent i){return null;}
}
