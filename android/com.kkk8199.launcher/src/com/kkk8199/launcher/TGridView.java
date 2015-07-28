package com.kkk8199.launcher;

	import java.util.ArrayList; 
import java.util.List; 

import android.app.Activity; 
import android.content.ComponentName;
import android.content.Context; 
import android.content.Intent;
import android.os.Bundle; 
import android.util.Log;
import android.view.LayoutInflater; 
import android.view.MotionEvent;
import android.view.View; 
import android.view.View.OnLongClickListener;
import android.view.ViewGroup; 
import android.view.View.OnTouchListener;
import android.widget.AdapterView; 
import android.widget.BaseAdapter; 
import android.widget.GridView; 
import android.widget.ImageView; 
import android.widget.TextView; 
import android.widget.Toast; 
import android.widget.AdapterView.OnItemClickListener; 

	 
	public class TGridView 
	{ 
		String TAG="wx";
	    private GridView mgridView; 
	    private Context mContext;
	    //图片的文字标题 
	    private String[] titles1 = new String[] 
	    { "视频", "音乐", "图片", "资源管理", "日历", "设置"}; 
	    //图片ID数组 
	    private int[] images1 = new int[]{
	            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,  
	            R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,  
	    }; 
	    private String[] titles2 = new String[] 
	    	    { "天天酷跑", "象棋巫师", "雷电急速", "brik_free", "block_out", "火柴人单车"}; 
	    	    //图片ID数组 
	    private int[] images2 = new int[]{
	    		R.drawable.pic7, R.drawable.pic8, R.drawable.pic9,  
	    	    R.drawable.pic10, R.drawable.pic11, R.drawable.pic12,  
	    }; 
	    
	    private String[] mIntent1 = new String[]
	    		{ "com.tudou.android" + "#" + "com.tudou.ui.activity.WelcomeActivity",
	    		  //"com.mxtech.videoplayer.ad" + "#" + "com.mxtech.videoplayer.ad.ActivityMediaList",
	    		  //"com.android.music"+ "#" + "com.android.music.MusicBrowserActivity",
	    		  "com.sds.android.ttpod" + "#" + "com.sds.android.ttpod.EntryActivity",
	    		  //"com.duomi.android" + "#" + "com.duomi.android.DMLauncher",
	    		  "com.andconsd" + "#" + "com.andconsd.ui.WelcomeActivity",
	    		  //"com.android.gallery3d" + "#" + "com.android.gallery3d.app.Gallery",
            	  "com.estrongs.android.pop" + "#" + "com.estrongs.android.pop.view.FileExplorerActivity",
            	  "com.android.calendar" + "#" + "com.android.calendar.AllInOneActivity",
            	  "com.android.settings" + "#" + "com.android.settings.Settings"
	    		};
	    
	    private String[] mIntent2 = new String[]
	    		{ "com.lt.parkour2014_df" + "#" +"com.lt.parkour2014_df.HelloLoad",
	    		  "com.jpcxc.xqwdroid" + "#" +"com.jpcxc.xqwdroid.GameActivity",
	    		  "com.ldkb42.wsy" + "#" + "com.ldkb42.wsy.ArcadeRaiden",
	    		  "com.gamezen.brixfree_G" + "#" + "com.gamezen.brixfree_G.BrixFree",
	    		  "com.kidga.blockouthd" + "#" + "com.kidga.blockouthd.BlockOutHD",
	    		  "com.Studio17.drawriderzzc" + "#" + "com.ansca.corona.CoronaActivity"
	    		};
	    
		public  TGridView(GridView gridView, int  i,final Context context){ 
	    	PictureAdapter adapter = null;
	    	mContext=context;
	    	if(i==1){
	    		 adapter = new PictureAdapter(titles1, mIntent1 , images1, context); 
	    	}
	    	else if(i==2){
	    		 adapter = new PictureAdapter(titles2, mIntent2 , images2, context); 
	    	}
	    	
	    	gridView.setAdapter(adapter); 
	    	
		}
		
	//自定义适配器 
	class PictureAdapter extends BaseAdapter{ 
	    private LayoutInflater inflater; 
	    private List<Picture> pictures; 
	 
	    public PictureAdapter(String[] titles, String[] vintent , int[] images, Context context) 
	    { 
	        super(); 
	        pictures = new ArrayList<Picture>(); 
	        inflater = LayoutInflater.from(context); 
	        for (int i = 0; i < images.length; i++) 
	        { 
	            Picture picture = new Picture(titles[i], vintent[i] , images[i] ); 
	            pictures.add(picture); 
	        } 
	    } 
	 
	    @Override
	    public int getCount() 
	    { 
	        if (null != pictures) 
	        { 
	            return pictures.size(); 
	        } else
	        { 
	            return 0; 
	        } 
	    } 
	 
	    @Override
	    public Object getItem(int position) 
	    { 
	        return pictures.get(position); 
	    } 
	 
	    @Override
	    public long getItemId(int position) 
	    { 
	        return position; 
	    } 
	 
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) 
	    { 
	    	//在ListView第一次显示列表项目的时候，convertView是null值,每显示一个新的行就调用一次getView
	    	//当item比较大时再使用第一种方式就不是很好的选中了，因为不停地创建View会占用系统的很多内存。
	    	//正确方式是当convertView不为空的时候直接重新使用convertView从而减少了很多不必要的View的创建，
	    	//然后加载数据
	        ViewHolder viewHolder; 
	        if (convertView == null) 
	        { 
	        	//Log.i("wangxi", "getView.convertView==null");
	            convertView = inflater.inflate(R.layout.picture_item, null); 
	            viewHolder = new ViewHolder(); 
	            viewHolder.title = (TextView) convertView.findViewById(R.id.title); 
	            viewHolder.image = (ImageView) convertView.findViewById(R.id.image); 
	            convertView.setTag(viewHolder); //与else对应
	        } else
	        { 
	            viewHolder = (ViewHolder) convertView.getTag(); 
	            //Log.i("wangxi", "getView.convertView!=null "+(ViewHolder) convertView.getTag());
	        } 
	        viewHolder.title.setText(pictures.get(position).getTitle()); 
	        viewHolder.image.setImageResource(pictures.get(position).getImageId()); 
	        viewHolder.vintent=pictures.get(position).getIntent();
	        viewHolder.image.setOnTouchListener(new MyImageOnTouchListener(viewHolder,viewHolder.vintent));
	        viewHolder.image.setOnLongClickListener(new MyImageOnLongClickListener(viewHolder.vintent));
	        return convertView; 
	    } 
	 
	} 
	
	public class MyImageOnTouchListener implements OnTouchListener {
		ViewHolder vh;
		String vintent;
		public MyImageOnTouchListener(ViewHolder vh , String vintent ) {
			super();
			this.vh = vh;
			this.vintent=vintent;
			vh.last_action = MotionEvent.ACTION_UP;
		}

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			String mpk,mcl;
			int i;
			if (event.getAction() == MotionEvent.ACTION_UP) {
				if (vh.last_action == MotionEvent.ACTION_DOWN) {
					Intent intent = new Intent();
					i=vintent.indexOf('#');
					Log.i("wangxi ",vintent+" i= "+i);
					mpk=vintent.substring(0,i);
					mcl=vintent.substring(i+1);
					Log.i("wangxi :","mpk="+mpk+" mcl="+mcl);
					intent.setComponent(new ComponentName(mpk,mcl));
					mContext.startActivity(intent);
				}
				return true;
			} else {
				vh.last_action = event.getAction();
				return false;
			}
		}
		
	}
	 
	public class MyImageOnLongClickListener implements OnLongClickListener {
		String vintent;
		public MyImageOnLongClickListener(String vintent ) {
			super();
			this.vintent=vintent;
		}

		@Override
		public boolean onLongClick(View v) {
			String mpk,mcl;
			int i;
			Intent intent = new Intent();
			i=vintent.indexOf('#');
			mpk=vintent.substring(0, i);
			mcl=vintent.substring(i+1);
			Log.i("wangxi1 :","mpk="+mpk+" mcl="+mcl);
			intent.setComponent(new ComponentName(mpk,mcl));
			mContext.startActivity(intent);
			return true;
		}
		
	}
	
	class ViewHolder 
	{ 
		public TextView title; 
	    public ImageView image; 
	    public int last_action;
	    public String vintent;
	} 
	 
	class Picture 
	{ 
	    private String title; 
	    private int imageId;
	    private String vintent;
	 
	    public Picture() 
	    { 
	        super(); 
	    } 
	 
	    public Picture(String title, String vintent , int imageId) 
	    { 
	        super(); 
	        this.title = title; 
	        this.imageId = imageId;
	        this.vintent=vintent;
	    } 
	 
	    public String getTitle() 
	    { 
	        return title; 
	    } 
	 
	    public void setTitle(String title) 
	    { 
	        this.title = title; 
	    } 
	 
	    public int getImageId() 
	    { 
	        return imageId; 
	    } 
	 
	    public String getIntent(){
	    	return this.vintent;
	    }
	    
	    public void setImageId(int imageId) 
	    { 
	        this.imageId = imageId; 
	    } 
	} 
	}
