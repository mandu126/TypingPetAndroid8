package com.typingpet.app;
import android.inputmethodservice.InputMethodService;import android.view.KeyEvent;import android.view.inputmethod.EditorInfo;import android.view.inputmethod.InputConnection;import android.content.Intent;
public class TypingPetIme extends InputMethodService{
 boolean left=false;
 void pose(String p){Intent i=new Intent(PetOverlayService.ACTION_POSE);i.setPackage(getPackageName());i.putExtra(PetOverlayService.EXTRA_POSE,p);sendBroadcast(i);}
 @Override public boolean onKeyDown(int keyCode,KeyEvent e){if(keyCode==KeyEvent.KEYCODE_ENTER){pose("left");return super.onKeyDown(keyCode,e);}if(keyCode==KeyEvent.KEYCODE_DEL){pose("right");return super.onKeyDown(keyCode,e);}return super.onKeyDown(keyCode,e);}
 @Override public boolean onKeyUp(int keyCode,KeyEvent e){if(keyCode!=KeyEvent.KEYCODE_ENTER&&keyCode!=KeyEvent.KEYCODE_DEL){left=!left;pose(left?"left":"right");}return super.onKeyUp(keyCode,e);}
 @Override public boolean onKeyMultiple(int keyCode,int count,KeyEvent e){left=!left;pose(left?"left":"right");return super.onKeyMultiple(keyCode,count,e);}
 @Override public void onStartInput(EditorInfo info,boolean restarting){super.onStartInput(info,restarting);pose("basic");}
 @Override public boolean onGenericMotionEvent(android.view.MotionEvent e){return super.onGenericMotionEvent(e);}
}
