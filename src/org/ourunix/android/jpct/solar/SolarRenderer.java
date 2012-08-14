package org.ourunix.android.jpct.solar;

import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.ourunix.android.GLSurfaceViewActivity;
import org.ourunix.android.R;
import org.ourunix.android.RenderBase;

import com.threed.jpct.*;
import com.threed.jpct.Matrix;
import com.threed.jpct.util.BitmapHelper;
import com.threed.jpct.util.MemoryHelper;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.*;
//http://blog.csdn.net/itde1/article/details/754341
//http://www.jpct.net/jpct-ae/doc/index.html
//http://blog.csdn.net/wangziling100/article/details/7287803
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import org.ourunix.android.jpct.util.Util;
public class SolarRenderer extends RenderBase {

	// FrameBuffer����
	private FrameBuffer fb;
	// World����
	private World world;
	// RGBColor
	// private RGBColor back = null;
	// Object3D����
	private Object3D plane = null;

	private float colors[] = { 
            1.0f, 0.0f, 0.0f, 1.0f, 
            0.0f, 1.0f, 0.0f, 1.0f, 
            0.0f, 0.0f, 1.0f, 1.0f };
	private FloatBuffer colorBuffer;
	
	public final static int BALL_NUM = 8;
	public final static int BALL_RADIUS = 32;
	public final static int TRACK_RADIUS = 180;
	private Object3D ballIndicator= null;
	private Object3D ballCenter = null;
	private Object3D ball[] = new Object3D[8];

	private Mesh mesh1 = null;
	final float TABLE_Y = -60;
	// SimpleVector
	// ͨ�����������x,y,z����������һ��SimpleVector���� ����ʾС����˶�������ٶ�
	private SimpleVector move = new SimpleVector(-4.0, 0.0, 4.0);
	private boolean fire = true; // �Ƿ����

	private boolean collsion = false;// �Ƿ�����ײ
	private SimpleVector tem;

	// FPS
	private int fps = 0;
	private long time = System.currentTimeMillis();
	private GestureDetector gestureDetector;
	
	private int  roateDegree = 0;
	Context mContext;
	// Ĭ�Ϲ���
	// �Ը���Ŀ��һЩ�Ż�
	public SolarRenderer(Context context) {
		super(context);
		mContext = context;
		// ����Resources����
		LoadFile.loadb(context.getResources());
		new LoadFile(context.getResources());
		// ���Ƶ�����Polygon����,Ĭ��Ϊ4096,�˴��������500���򲻻���
		Config.maxPolysVisible = 500;
		// ��Զ�ĺ��ʵ�ƽ��,Ĭ��Ϊ1000
		Config.farPlane = 1500;
		Config.glTransparencyMul = 0.1f;
		Config.glTransparencyOffset = 0.1f;
		// ʹJPCT-AE�������ʹ�ö�������Ƕ������黺�������Ϊ�����ܻ�ʹĳЩӲ������
		// ����Samsung Galaxy,�������ܹ����ĺܺã�����ʹ֮�������������Ĭ��Ϊfalse��ԭ��
		Config.useVBO = true;
		// back = new RGBColor(50, 50, 100);
		Texture.defaultToMipmapping(true);
		Texture.defaultTo4bpp(true);
		
		gestureDetector = new GestureDetector(new MyGestureDetector());
       
        
	}

	public void setFire(boolean fire) {
		this.fire = fire;
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		try {
			if (true) {

				move();// ʵ���������Ҽ�
				if (fire) {
//					SimpleVector axis = new SimpleVector(1, 0, 1);// ����תʱ��ķ���
//					ball1.rotateAxis(axis, (float) Math.toRadians(10));// ʵ������ת
				}
				// �Զ���õ�RGBColor����
				// fb.clear(back);
				fb.clear();
				// �任�͵ƹ����еĶ� ����
				world.renderScene(fb);
				// ������renderScene�����ĳ���
				world.draw(fb);
				// ��Ⱦ��ʾͼ��
				fb.display();
				// fps��1
				fps += 1;
				// ��ӡ���fps
				if (System.currentTimeMillis() - time > 1000) {
					System.out.println(fps + "fps");
					fps = 0;
					time = System.currentTimeMillis();
				}
			} else {
				if (fb != null) {
					fb.dispose();
					fb = null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// ��ӡ�쳣��Ϣ
			Logger.log("Drawing thread terminated!", Logger.MESSAGE);
		}
	}
   
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		if (fb != null) {
			fb = null;
		}
		// �²���һ��FrameBuffer����
		fb = new FrameBuffer(gl, width, height);

	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		Logger.log("onCreate");
		colorBuffer = Util.createFloatBuffer(colors);
		// �����Ⱦ
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
		// �½�world����
		world = new World();
		// ����
		//AndroidRuntime com.threed.jpct.Texture.loadTexture
		//That's because you are loading it as a Drawable. Don't do that. Drawables will be scaled by Android under some circumstances. Use either bitmaps or an inputstream instead.
		 TextureManager tm = TextureManager.getInstance();
		 Texture texture2 = new Texture(LoadFile.bitmap1);
		 Texture texture3 = new Texture(LoadFile.bitmap2);
		 Texture texture4 = new Texture(LoadFile.bitmap3);
		 tm.addTexture("texture2", texture2);
		 tm.addTexture("texture3", texture3);
		 tm.addTexture("texture4",texture4);
		// ��ʼ����3DԪ��
		plane = Primitives.getPlane(20, 10); // �õ�ƽ��
		// Ҳ���Բ�������õ�ƽ��ķ�����������õ��Ǽ���3dsģ��
		plane = loadModel("table.3ds", 4f);
		plane.translate(0, -30, 20);
		plane.rotateX(-(float) Math.PI / 2); // ��jpct-ae��������ת����������ϵ
		plane.rotateY((float) Math.PI / 2);
		world.addObject(plane);
	

		// ����������ͼ��ʽ
		ballIndicator = Primitives.getSphere(BALL_RADIUS); // �õ�����
		ballIndicator.translate(0,TABLE_Y,-TRACK_RADIUS);
		world.addObject(ballIndicator);
		ballIndicator.setVisibility(false);
		
		ballCenter = Primitives.getSphere(BALL_RADIUS); // �õ�����
		ballCenter.translate(0,TABLE_Y,0);
		ballCenter.calcTextureWrapSpherical();
		ballCenter.setTexture("texture4");
		world.addObject(ballCenter);
		ballCenter.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);
		
		for (int i = 0; i < BALL_NUM; i++) {
			ball[i] = Primitives.getSphere(BALL_RADIUS); // �õ�����

			ball[i].translate(((int) (TRACK_RADIUS * Math.sin(Math.toRadians(i * 45)))),
					TABLE_Y, ((int) (TRACK_RADIUS * Math.cos(Math.toRadians(i * 45)))));
			 ball[i].calcTextureWrapSpherical();
			 ball[i].setTexture("texture3");

			world.addObject(ball[i]);
			ball[i].setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);
		}
		// ������ײģʽ
		
		plane.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);
		// ���û�����
		world.setAmbientLight(255, 255, 255);
		// �������ù��յĵط����ɹ���������ʾ���գ���֪��Ϊʲô������������������������
		// ���ù���
		Light light = new Light(world);
		light.setPosition(new SimpleVector(ballIndicator.getTransformedCenter().x,
				ballIndicator.getTransformedCenter().y - 100, ballIndicator
						.getTransformedCenter().z - 50));
		light.setIntensity(255, 0, 0);

		// ����3�δ���û��Ч��
		// �������ж���
		world.buildAllObjects();

		// Camera���
		Camera cam = world.getCamera();
		cam.setPositionToCenter(ballIndicator);
		cam.align(ballIndicator);// �����������������Z��������
		// �������X����ת20��
		cam.rotateCameraX((float) Math.toRadians(40));
		cam.moveCamera(Camera.CAMERA_MOVEOUT, 500);
		// �������ƶ�
		cam.moveCamera(Camera.CAMERA_MOVEUP, 60);

		// cam.lookAt(plane.getTransformedCenter());

		// �����ڴ�
		MemoryHelper.compact();

	}

	// ����ģ��
	private Object3D loadModel(String filename, float scale) {
		// �������3ds�ļ����浽model������
		Object3D[] model = Loader.load3DS(LoadFile.loadf(filename), scale);
		// ȡ��һ��3ds�ļ�
		Object3D o3d = new Object3D(0);
		// ��ʱ����temp
		Object3D temp = null;
		// ����model����
		for (int i = 0; i < model.length; i++) {
			// ��temp����model�����е�ĳһ��
			temp = model[i];
			// ����temp������Ϊ origin (0,0,0)
			temp.setCenter(SimpleVector.ORIGIN);
			// ��x����ת����ϵ������������ϵ(jpct-ae�������е�y,x�Ƿ���)
			temp.rotateX((float) (-.5 * Math.PI));
			// ʹ����ת����ָ���˶�����ת�����ԭʼ����
			temp.rotateMesh();
			// new һ����������Ϊ��ת����
			temp.setRotationMatrix(new Matrix());
			// �ϲ�o3d��temp
			o3d = Object3D.mergeObjects(o3d, temp);
			// ��Ҫ��Ϊ�˴������JPCT��android����Ə�(�����˴�Ϊo3d.build())
			o3d.compile();
		}
		// ����o3d����
		return o3d;
	}
	@Override
	public void leftSwipe() {
		 Log.d(TAG, "---onTouchEvent action:ACTION_LEFT");
		 roateDegree+=10;
		 roateDegree%=360;
		for (int i = 0; i < BALL_NUM; i++) {
			ball[i].clearTranslation();
			ball[i].translate(((int) (TRACK_RADIUS* Math.sin(Math.toRadians(( i * 45 +roateDegree)%360)))),
					TABLE_Y, ((int) (TRACK_RADIUS * Math.cos(Math.toRadians((i * 45 +roateDegree)%360)))));

		}
		super.leftSwipe();
	}
	@Override
	public void rightSwipe() {
		 roateDegree-=10;
		 if(roateDegree<0){
			 roateDegree+=360;
		 }
		 
		 Log.d(TAG, "---onTouchEvent action:ACTION_RIGHT");
        for (int i = 0; i < BALL_NUM; i++) {
        	ball[i].clearTranslation();
			ball[i].translate(((int) (TRACK_RADIUS * Math.sin(Math.toRadians((i * 45 +roateDegree)%360)))),
					TABLE_Y, ((int) (TRACK_RADIUS * Math.cos(Math.toRadians((i * 45 +roateDegree)%360)))));

		}
		super.rightSwipe();
	}
	public void upSwipe(){}
	public void downSwipe(){}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return gestureDetector.onTouchEvent(event);
	}
	public final static String TAG="TAG";
	float downX = 0;
	float downY = 0;
	float upX = 0;
	float upY = 0;
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
                    Log.i(TAG, "Swipe was only " + Math.abs(deltaX) + " long, need at least " + SWIPE_MIN_DISTANCE);
                    return false; // We don't consume the event
            }

            // swipe vertical?
            if(Math.abs(deltaY) > SWIPE_MIN_DISTANCE){
                // top or down
                if(deltaY < 0) { downSwipe(); }
                if(deltaY > 0) { upSwipe();}
            }
            else {
                    Log.i(TAG, "Swipe was only " + Math.abs(deltaX) + " long, need at least " + SWIPE_MIN_DISTANCE);
                    return false; // We don't consume the event
            }

            break;

        case MotionEvent.ACTION_UP:

            //Log.d(TAG, "---onTouchEvent action:ACTION_UP");

            break;

        case MotionEvent.ACTION_CANCEL:

           // Log.d(TAG, "---onTouchEvent action:ACTION_CANCEL");

            break;

        }

    	return super.handleTouchEvent(event);
    }
	public void move() {
		Camera cam = world.getCamera();
		if (GLSurfaceViewActivity.up) { // �����Ϸ����
			cam.moveCamera(cam.getDirection(), -2);// ������������ƶ�
		}
		if (GLSurfaceViewActivity.down) {
			cam.moveCamera(cam.getDirection(), 2);// �����ƶ�
		}
		if (GLSurfaceViewActivity.left) {
			plane.rotateY((float) Math.toRadians(-10));// ������ת
		}
		if (GLSurfaceViewActivity.right) {
			plane.rotateY((float) Math.toRadians(10)); // ������ת
		}

	}
}

// �����ļ�
class LoadFile {
	public static Resources resource;
	public static Bitmap bitmap1;
	public static Bitmap bitmap2;
	public static Bitmap bitmap3;

	public LoadFile(Resources res) {
		resource = res;
	}

	// ����ģ��
	public static InputStream loadf(String fileName) {
		AssetManager am = LoadFile.resource.getAssets();
		try {
			return am.open(fileName);
		} catch (IOException e) {
			return null;
		}
	}

	// ��������ͼƬ
	public static void loadb(Resources res) {
		bitmap1 = BitmapFactory.decodeResource(res, R.raw.table);
		bitmap2 = BitmapFactory.decodeResource(res, R.raw.bool1);
		bitmap3 = BitmapFactory.decodeResource(res, R.raw.bool2);
	}
}
