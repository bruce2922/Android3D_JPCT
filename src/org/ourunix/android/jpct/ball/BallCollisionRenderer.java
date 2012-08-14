package org.ourunix.android.jpct.ball;

import java.io.IOException;
import java.io.InputStream;

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
public class BallCollisionRenderer extends RenderBase{

	// FrameBuffer����  
	 private FrameBuffer fb;  
	 // World����  
	 private World world;  
	 // RGBColor  
	 //private RGBColor back = null;  
	 // Object3D����  
	 private Object3D plane=null;
	 private Object3D ball1=null;
	 private Object3D ball2=null; 
	 
	 private Mesh mesh1 = null;
     final float  TABLE_Y  = -39;
	 // SimpleVector  
	 // ͨ�����������x,y,z����������һ��SimpleVector����  ����ʾС����˶�������ٶ�
	 private SimpleVector move = new SimpleVector(-4.0, 0.0, 4.0);  
     private boolean fire = true; //�Ƿ����
     
	 private boolean collsion=false;//�Ƿ�����ײ
	 private SimpleVector tem;
	  
	 // FPS  
	 private int fps = 0;  
	 private long time = System.currentTimeMillis();  
	  
	 // Ĭ�Ϲ���  
	 // �Ը���Ŀ��һЩ�Ż�  
	 public BallCollisionRenderer(Context context) {  
		  super(context);
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
		  //back = new RGBColor(50, 50, 100);  
		  Texture.defaultToMipmapping(true);  
		  Texture.defaultTo4bpp(true);  
	 }
	 public void setFire(boolean fire){
		 this.fire = fire;
	 }
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		 try {  
			   if (true) {  
             
                	move();//ʵ���������Ҽ�
                	if(fire){
                		SimpleVector trsn = ball2.checkForCollisionSpherical(move, 10f);
        			    //���������֮�����
        			    SimpleVector length=new SimpleVector(ball1.getTransformedCenter().x-ball2.getTransformedCenter().x,
        			    		ball1.getTransformedCenter().y-ball2.getTransformedCenter().y,ball1.getTransformedCenter().z-ball2.getTransformedCenter().z);
        			  
        			    //�ж�����֮������Ƿ�С������뾶֮�ͣ���С�ڣ�������ײ
        			    if(length.length()<=20+5){
        			    	collsion=true;//��ײ����
        			    }
        			    //���������ײ
        			    if(collsion){
        			    	//getTranslation :Returns(SimpleVector) the translation of the object (from its origin to its current position)
        			    	//�ж�С���Ƿ��Ѿ���Խƽ����
        			    	if(ball2.getTranslation(tem).z<-100||ball1.getTranslation(tem).z>100){
        			    		ball1.clearTranslation();
        			    		ball2.clearTranslation();
        			    		
        			    	  ball1.translate(0, TABLE_Y, 0);
        			   		  ball2.translate(50, TABLE_Y, -50);
        			    		//ball1.setVisibility(false);//����ʧ
        			    		//ball2.setVisibility(false);
        			   		  fire = false;
        			    	}
        			    	else{//С�������µ��˶�����
        				    	ball1.translate(new SimpleVector(1,0,3));
        				    	ball2.translate(new SimpleVector(-2,0,-2));	
        			    	}
        			    }
        			    else{
        			    	 //����if(!collsion)
        				    //���û����ײ
        			    	ball2.translate(trsn);
        			    	
        			    }  
        			    SimpleVector axis=new SimpleVector(1,0,1);//����תʱ��ķ���
        			    ball2.rotateAxis(axis, (float)Math.toRadians(10));//ʵ������ת               		
                	} 
			    // �Զ���õ�RGBColor����  
			    //fb.clear(back); 
			    fb.clear();
			    // �任�͵ƹ����еĶ� ����  
			    world.renderScene(fb);  
			    // ������renderScene�����ĳ���  
			    world.draw(fb);  
			    // ��Ⱦ��ʾͼ��  
			    fb.display();  
			    // fps��1  
			    fps+=1;  
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
		  // �����Ⱦ  
		  gl.glEnable(GL10.GL_BLEND);  
		  gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);  
		  // �½�world����  
		  world = new World();  
		  // ����  
		  TextureManager tm = TextureManager.getInstance();  
		  Texture texture2 = new Texture(LoadFile.bitmap1); 
		  Texture texture3 = new Texture(LoadFile.bitmap2);
		  Texture texture4 = new Texture(BitmapHelper.rescale(BitmapHelper.convert(LoadFile.resource.getDrawable(R.drawable.ball01)), 256, 256));
		  tm.addTexture("texture2", texture2);  
		  tm.addTexture("texture3", texture3); 
		  tm.addTexture("texture4",texture4);
		  // ��ʼ����3DԪ��  
		  plane = Primitives.getPlane(20, 10); // �õ�ƽ��
		  //Ҳ���Բ�������õ�ƽ��ķ�����������õ��Ǽ���3dsģ��
		  plane=loadModel("table.3ds", 4f);
		  plane.translate(0, -30, 20);
		  plane.rotateX(-(float) Math.PI / 2); // ��jpct-ae��������ת����������ϵ
		  plane.rotateY((float)Math.PI/2);
		  ball1 = Primitives.getSphere(10); // �õ�����
		  ball1.translate(0, -39, 0);
		      
		  ball2=Primitives.getSphere(10);
		  ball2.translate(50, -39, -50);

		  //����������ͼ��ʽ
		  plane.calcTextureWrap();
		  plane.setTexture("texture2");
		  ball1.calcTextureWrapSpherical();
		  ball1.setTexture("texture3");
		  ball2.calcTextureWrapSpherical();
		  ball2.setTexture("texture4");
		   
		  world.addObject(plane);
		  world.addObject(ball1);
		  world.addObject(ball2);
		  //������ײģʽ
		  ball1.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS); 
		  ball2.setCollisionMode(Object3D.COLLISION_CHECK_SELF);
		  plane.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);

		  // ���û�����  
		  world.setAmbientLight(255, 255, 255); 
		  //�������ù��յĵط����ɹ���������ʾ���գ���֪��Ϊʲô������������������������
		  //���ù��� 
		  Light light=new Light(world);
		  light.setPosition(new SimpleVector(ball1.getTransformedCenter().x,
				  ball1.getTransformedCenter().y-100,ball1.getTransformedCenter().z-50));
		  light.setIntensity(255, 0, 0);
		  //����3�δ���û��Ч��
		  // �������ж���  
		  world.buildAllObjects();  
		    
		  // Camera���  
		  Camera cam = world.getCamera();  
		  cam.setPositionToCenter(ball1);
		  cam.align(ball1);//�����������������Z��������
		  //�������X����ת20��
		  cam.rotateCameraX((float) Math.toRadians(20));
		  cam.moveCamera(Camera.CAMERA_MOVEOUT, 250);  
		  // �������ƶ�  
		  cam.moveCamera(Camera.CAMERA_MOVEUP, 60);  
		
		  //cam.lookAt(plane.getTransformedCenter());  
		  
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
	
	public void move() { 
		Camera cam=world.getCamera();
		if (GLSurfaceViewActivity.up) { // �����Ϸ����
			cam.moveCamera(cam.getDirection(), -2);//������������ƶ�
		}
		if (GLSurfaceViewActivity.down) {
			cam.moveCamera(cam.getDirection(), 2);//�����ƶ�
		}
		if (GLSurfaceViewActivity.left) {
			plane.rotateY((float) Math.toRadians(-10));// ������ת
		}
		if (GLSurfaceViewActivity.right) {
			plane.rotateY((float) Math.toRadians(10)); // ������ת
		}
	
	}
}
//�����ļ�
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
	   bitmap3=BitmapFactory.decodeResource(res, R.raw.bool2);
	 }
}

