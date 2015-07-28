package com.kkk8199.launcher;

		import java.io.File;  
		import java.io.FileOutputStream;  
		import java.io.IOException;  
		import java.io.InputStream;  
		import java.util.ArrayList;  
		import java.util.List;  
		  
		import android.app.Activity;  
		import android.app.AlertDialog;  
		import android.app.AlertDialog.Builder;  
		import android.content.BroadcastReceiver;  
		import android.content.ComponentName;  
		import android.content.Context;  
		import android.content.DialogInterface;  
		import android.content.Intent;  
		import android.content.IntentFilter;  
		import android.content.pm.PackageInfo;  
		import android.content.pm.PackageManager;  
		import android.content.res.AssetManager;  
		import android.content.res.Configuration;  
		import android.graphics.Color;  
		import android.net.Uri;  
		import android.os.Bundle;  
		import android.util.Log;  
		import android.view.GestureDetector;  
		import android.view.GestureDetector.OnGestureListener;  
		import android.view.KeyEvent;  
		import android.view.Menu;  
		import android.view.MenuItem;  
		import android.view.MotionEvent;  
		import android.view.View;  
		import android.view.View.OnClickListener;  
		import android.view.View.OnTouchListener;  
		import android.view.animation.TranslateAnimation;  
		import android.widget.GridView;
		import android.widget.ImageView;  
		import android.widget.LinearLayout;  
		import android.widget.LinearLayout.LayoutParams;  
		import android.widget.TextView;  
		import android.widget.Toast;  
		  
		public class MainActivity extends Activity {  
		    GestureDetector mGestureDetector;  
		    LinearLayout layFirst;  
		    LinearLayout laySec;  
		    LinearLayout layContain;  
		    //当前显示的layout  
		    LinearLayout layCur;  
		    //左边的layout  
		    LinearLayout layLeft;  
		    //右边的layout  
		    LinearLayout layRight;  
		    int screenWidth;  
		    GridView gridView1;
		    GridView gridView2;
		    static boolean page;
		    
		  //  ArrayList<MyAppInfo> mApplications = new ArrayList<MyAppInfo>();  
		      
		    TextView roll_dot1,roll_dot2;  
		      
		    ArrayList<String> packagNameList ;  
		    private final int MENU_EXIT = 01;  
		   // private MyReceiver receiver;  
		          
		    private OnTouchListener myTouch = new OnTouchListener(){  
		  
		          
		        @Override  
		        public boolean onTouch(View v, MotionEvent event) {  
		        	return mGestureDetector.onTouchEvent(event);
		        }  
		          
		    };
		    
		    @Override
		    public boolean dispatchTouchEvent(MotionEvent ev) {
				    mGestureDetector.onTouchEvent(ev);
				    return super.dispatchTouchEvent(ev);
		    } 

		    public void onCreate(Bundle savedInstanceState) {  
		        super.onCreate(savedInstanceState);  
		        setContentView(R.layout.activity_main);  
		     //   initpackagNameList();  
		        //监听系统新安装程序的广播  
		          
		        
		        layContain = (LinearLayout) this.findViewById(R.id.layContain);  
		        layFirst = (LinearLayout) this.findViewById(R.id.layFirst);  
		        laySec = (LinearLayout) this.findViewById(R.id.laySec);  

		          
		        roll_dot1 = (TextView) findViewById(R.id.roll_dot1);  
		        roll_dot2 = (TextView) findViewById(R.id.roll_dot2);  
		 
		        //加载gridView
		        gridView1 = (GridView) findViewById(R.id.gridview1);
		        new TGridView(gridView1,1,this);
		        gridView2 = (GridView) findViewById(R.id.gridview2);
		        new TGridView(gridView2,2,this);
		          
		        layCur = layFirst;  
		        layLeft = null;  
		        layRight = laySec;  
		          
		        layFirst.setOnTouchListener(myTouch);  
		        laySec.setOnTouchListener(myTouch);  
		
		        //设置宽度  
		        screenWidth = getWindowManager().getDefaultDisplay().getWidth();  
		        layFirst.getLayoutParams().width = screenWidth;  
		        laySec.getLayoutParams().width = screenWidth;  
		   
		         mGestureDetector = new GestureDetector(this, new OnGestureListener(){  
		            @Override  
		            public boolean onDown(MotionEvent e) {  
		                return true;  
		            }  
		  
		            @Override  
		            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {  
		                int x = (int) (e2.getX() - e1.getX()); 
		                int y = (int) (e2.getY() - e1.getY());
		                Log.i("zhenghui","layLeft="+layLeft+"layRight="+layRight);
		                
		                if( Math.abs(x) > Math.abs(y) ){//横向移动
		                //判断方向  
		                boolean dir = x>0;//如果大于0，为true，说明向右移动 
		                page=false;//第1页
		                if(dir){  
		                    if(layLeft == null)return false;  
		                    LinearLayout.LayoutParams llp = (LayoutParams) layLeft.getLayoutParams();  
		              
		                    TranslateAnimation anim1 = new TranslateAnimation(llp.leftMargin,0,0,0);  
		                    anim1.setDuration(500l);  
		                    layLeft.startAnimation(anim1);  
		                      
		                    //layLeft.setAnimation(anim);  
		                    llp.setMargins(0, 0, 0, 0);  
		                    layLeft.setLayoutParams(llp);  
		                    if(layLeft == layFirst){  
		                        layLeft = null;  
		                        layCur = layFirst;  
		                        layRight =laySec ;  
		                        //设置屏幕下方的小点随着页面的切换而改变  
		                        roll_dot2.setTextColor(Color.RED);  
		                        roll_dot1.setTextColor(Color.WHITE);  
		                    }
		                }else{  
		                	page=true;//第二页
		                    if(layRight == null)return false;  //防止继续向左移动,加载右边的layout
		                    //只是为了要宽度而已!!!!!
		                    LinearLayout.LayoutParams llp = (LayoutParams) layCur.getLayoutParams();  
		                    
		                    int width = layCur.getWidth();  //一个用宽度,一个用margin
		                    TranslateAnimation anim = new TranslateAnimation(width,0,0,0);  
		                      
		                    anim.setDuration(500l);  
		                    layRight.startAnimation(anim);  
		                      
		                    llp.setMargins(-width, 0, 0, 0);  
		                    layCur.setLayoutParams(llp);  
		                    if(layCur == layFirst){  
		                        layLeft = layFirst;  
		                        layCur = laySec;  
		                        layRight =null ;  
		                        roll_dot1.setTextColor(Color.RED);  
		                        roll_dot2.setTextColor(Color.WHITE);  
		                    }
		                }  
		                }
		                return true;  
		            }  
		            @Override  
		            public void onLongPress(MotionEvent e) {  
		                // TODO Auto-generated method stub  
		                  
		            }  
		            @Override  
		            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {  
		                // TODO Auto-generated method stub  
		                return false;  
		            }  
		            @Override  
		            public void onShowPress(MotionEvent e) {  
		                // TODO Auto-generated method stub  
		                  
		            }  
		            @Override  
		            public boolean onSingleTapUp(MotionEvent e) {  
		                // TODO Auto-generated method stub  
		                return false;  
		            }  
		              
		        });  
		}  
		}
