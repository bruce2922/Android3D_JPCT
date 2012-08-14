package org.ourunix.android.jpctmd2demo;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.ourunix.android.R;
import org.ourunix.android.RenderBase;


import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.threed.jpct.Camera;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Loader;
import com.threed.jpct.Object3D;
import com.threed.jpct.RGBColor;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;
import com.threed.jpct.util.BitmapHelper;

public class GLRenderer extends RenderBase {
	public Context context;
	//	jpct��Ҫ׼����������ʵ��,�������硢Framebuffer��3D���������
	private World world;
	
	private FrameBuffer fb;
	
	private Object3D soilder;
	
	private String[] texturesName = {"snork"};
	
	private float scale = 0.8f;
	
	// ���߶��� ��ز��� 
	private int an = 2;  
	private float ind = 0; 
	
	public GLRenderer(Context ctx){
		super(ctx);
		context = ctx;
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		
		doAnim();
		// ����ɫ���FrameBuffer
		fb.clear(RGBColor.BLACK);

		// �任�͵ƹ����ж����
		world.renderScene(fb);
		
		// ����
		world.draw(fb);
		
		//��ʾ
		fb.display();
		
	}

	/**
	 * ʵ�ֶ����Ĵ���
	 * */
	private void doAnim() {
		// TODO Auto-generated method stub
		//ÿһ֡��0.018f  
		ind += 0.018f;  
		if (ind > 1f) {  
		ind -= 1f;  
		}  
		// ���ڴ˴�������������ind��ֵΪ0-1(jpct-ae�涨),0��ʾ��һ֡��1Ϊ���һ֡��  
		//����an���������������˼��sub-sequence�����keyframe(3ds��),��Ϊ��һ��  
		//�����Ķ���������seq��sub-sequence����������Ϊ2��ʾִ��sub-sequence�Ķ�����  
		//����������Ϊ2�ҾͲ�̫�����ˣ�����������Ч���᲻��Ȼ�������Ҿ�����ʱ����  
		//����Ϊ2  
		soilder.animate(ind, an);  
	}
	float downX = 0;
	float downY = 0;
	float upX = 0;
	float upY = 0;
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		return super.onTouch(v, event);
	}
	@Override
	public void rightSwipe() {
		soilder.rotateY(-5);
		super.rightSwipe();
	}
	@Override
	public void leftSwipe() {
		soilder.rotateY(5);
		super.leftSwipe();
	}
   @Override
	public boolean handleTouchEvent(MotionEvent event) {
	   int action = event.getAction();

       switch (action) {

       case MotionEvent.ACTION_DOWN:

       	  downX = event.getX();
             downY = event.getY();
           break;

       case MotionEvent.ACTION_MOVE:
           
       	upX = event.getX();
           upY = event.getY();

           float deltaX = downX - upX;
           float deltaY = downY - upY;

           // swipe horizontal?
           if(Math.abs(deltaX) > SWIPE_MIN_DISTANCE){
               // left or right
               if(deltaX < 0) { rightSwipe(); }
               if(deltaX > 0) { leftSwipe();  }
           }
           else {
                   Log.i("", "Swipe was only " + Math.abs(deltaX) + " long, need at least " + SWIPE_MIN_DISTANCE);
                   return false; // We don't consume the event
           }

           // swipe vertical?
           if(Math.abs(deltaY) > SWIPE_MIN_DISTANCE){
               // top or down
               if(deltaY < 0) { downSwipe(); }
               if(deltaY > 0) { upSwipe();}
           }
           else {
                   Log.i("", "Swipe was only " + Math.abs(deltaX) + " long, need at least " + SWIPE_MIN_DISTANCE);
                   return false; // We don't consume the event
           }

           break;

       case MotionEvent.ACTION_UP:break;

       case MotionEvent.ACTION_CANCEL:break;

       }
		return super.handleTouchEvent(event);
	}
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		// ���FrameBuffer��ΪNULL,�ͷ�fb��ռ��Դ
		if (fb != null){
			fb.dispose();
		}
		fb = new FrameBuffer(gl, width, height);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		//����Assets�ļ����µ��ļ�
		new LoadAssets(context.getResources());
		// TODO Auto-generated method stub
		//ʵ������������
		world = new World();
		
		//�����˻�����Դǿ�ȡ���:����������䰵;��:��������һ�С�
		
		world.setAmbientLight(150, 150, 150);
		 TextureManager tm = TextureManager.getInstance();  
		 Texture texture2 = new Texture(BitmapFactory.decodeResource(context.getResources(), R.raw.soilder)); 
         tm.addTexture(texturesName[0], texture2);  
	
		// ��assets�ļ����ж�ȡsoilder.md2�ļ���ʵ����Object3D snork
		soilder = Loader.loadMD2(LoadAssets.loadf("soilder.md2"), scale);
		
		// ��תsoilder����"�ʵ�λ��"  
		soilder.translate(0, 0, -50);
		
		//����ǽ�������ӽ�ȥ
		soilder.setTexture(texturesName[0]);
		
		// �ͷŲ�����Դ  
		soilder.strip();  
		// ����  
		soilder.build();  
		
		// ��snork��ӵ�World������  
		world.addObject(soilder);
		
		//���Camera
		Camera cam = world.getCamera();
		
		cam.setPosition(0, 0, -100);
		
		cam.lookAt(soilder.getTransformedCenter());
		
	}
}

//����assets��
class LoadAssets {
	public static Resources res;

	public LoadAssets(Resources resources) {
		res = resources;
	}

	public static InputStream loadf(String fileName) {
		AssetManager am = res.getAssets();
		try {
			return am.open(fileName, AssetManager.ACCESS_UNKNOWN);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}