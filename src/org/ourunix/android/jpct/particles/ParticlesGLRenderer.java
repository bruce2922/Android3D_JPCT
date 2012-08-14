package org.ourunix.android.jpct.particles;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.ourunix.android.R;
import org.ourunix.android.RenderBase;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.opengl.GLSurfaceView.Renderer;
/**
 * 
 * 
 * http://www.ourunix.org/android/post/72.html
 * */
public class ParticlesGLRenderer extends RenderBase {
	public Context context;
	// �����
	private Random random;
	// �������ڵ�������
	private static int maxParticles = 1000;
	// ��������
	private float slowdown;
	// X������ٶ�
	private float xSpeed;
	// Y������ٶ�
	private float ySpeed;
	// ��Z������
	private float zoom;
	// ѭ������
	private int loop;

	// ����һ����ΪParticle������,�洢MAX_PARTICLES��Ԫ��
	private Particles particles[] = new Particles[maxParticles];

	// �洢12�ֲ�ͬ��ɫ
	private float colors[][] = { { 1.0f, 0.75f, 0.5f }, { 1.0f, 0.75f, 0.5f },
			{ 1.0f, 1.0f, 0.5f }, { 0.75f, 1.0f, 0.5f }, { 0.5f, 1.0f, 0.5f },
			{ 0.5f, 1.0f, 0.75f }, { 0.5f, 1.0f, 1.0f }, { 0.5f, 0.75f, 1.0f },
			{ 0.5f, 0.5f, 1.0f }, { 0.75f, 0.5f, 1.0f }, { 1.0f, 0.5f, 1.0f },
			{ 1.0f, 0.5f, 0.75f } };

	// vertexBuffer
	private FloatBuffer vertexBuffer;
	// texCoordBuffer
	private FloatBuffer texCoordBuffer;
	// vertex
	private float[] vertex = new float[12];
	// texCoord
	private float[] texCoord = new float[8];

	// LoadBuffer
	public void LoadBuffer(GL10 gl) {
		ByteBuffer vertexByteBuffer = ByteBuffer
				.allocateDirect(vertex.length * 4);
		vertexByteBuffer.order(ByteOrder.nativeOrder());
		vertexBuffer = vertexByteBuffer.asFloatBuffer();
		vertexBuffer.put(vertex);
		vertexBuffer.position(0);

		ByteBuffer texCoordByteBuffer = ByteBuffer
				.allocateDirect(texCoord.length * 4);
		texCoordByteBuffer.order(ByteOrder.nativeOrder());
		texCoordBuffer = texCoordByteBuffer.asFloatBuffer();
		texCoordBuffer.put(texCoord);
		texCoordBuffer.position(0);
	}

	public ParticlesGLRenderer(Context ctx) {
		super(ctx);
		context = ctx;
		random = new Random();
		slowdown = 0.5f;
		xSpeed = 20;
		ySpeed = 20;
		zoom = -30.0f;
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		LoadBuffer(gl);
		// �����Ļ����ɫ����
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// ����ģ�ͱ任����
		gl.glLoadIdentity();

		// ������������״̬
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texCoordBuffer);
		
		for (loop = 0; loop < maxParticles; loop++) {
			// �������Ϊ�����
			if (particles[loop].active) {
				// ����X���λ��
				float x = particles[loop].x;
				// ����Y���λ��
				float y = particles[loop].y;
				// ����Z���λ��
				float z = particles[loop].z + zoom;
				// ����������ɫ
				gl.glColor4f(particles[loop].r, particles[loop].g,
						particles[loop].b, particles[loop].life);
				// ��ʼ׼������"���ǵش�"(���ֵֹֹ�)
				texCoordBuffer.clear();
				vertexBuffer.clear();
				texCoordBuffer.put(1.0f);
				texCoordBuffer.put(1.0f);
				vertexBuffer.put(x + 0.5f);
				vertexBuffer.put(y + 0.5f);
				vertexBuffer.put(z);
				texCoordBuffer.put(1.0f);
				texCoordBuffer.put(0.0f);
				vertexBuffer.put(x + 0.5f);
				vertexBuffer.put(y);
				vertexBuffer.put(z);
				texCoordBuffer.put(0.0f);
				texCoordBuffer.put(1.0f);
				vertexBuffer.put(x);
				vertexBuffer.put(y + 0.5f);
				vertexBuffer.put(z);
				texCoordBuffer.put(0.0f);
				texCoordBuffer.put(0.0f);
				vertexBuffer.put(x);
				vertexBuffer.put(y);
				vertexBuffer.put(z);
				// ����
				gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
				// ����X�����λ��
				particles[loop].x += particles[loop].xi / (slowdown * 1000);
				// ����Y�����λ��
				particles[loop].y += particles[loop].yi / (slowdown * 1000);
				// ����Z�����λ��
				particles[loop].z += particles[loop].zi / (slowdown * 1000);

				// ����X�᷽���ٶȴ�С
				particles[loop].xi += particles[loop].xg;
				// ����Y�᷽���ٶȴ�С
				particles[loop].yi += particles[loop].yg;
				// ����Z�᷽���ٶȴ�С
				particles[loop].zi += particles[loop].zg;

				// �������ӵ�����ֵ
				particles[loop].life -= particles[loop].fade;

				// �����������С��0
				if (particles[loop].life < 0.0f) {
					float xi, yi, zi;
					xi = xSpeed + (float) ((rand() % 60) - 32.0f);
					yi = ySpeed + (float) ((rand() % 60) - 30.0f);
					zi = (float) ((rand() % 60) - 30.0f);
					initParticles(loop, random.nextInt(12), xi, yi, zi);
				}

			}
		}
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		float ratio = (float) width / height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);// ����ΪͶӰ����ģʽ
		gl.glLoadIdentity();// ����
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 100);// �����ӽ�
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		// ͸������
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		// �ú�ɫ��������
		gl.glClearColor(0, 0, 0, 0);
		// ��Ӱƽ��
		gl.glShadeModel(GL10.GL_SMOOTH);
		// �ر���Ȳ���
		gl.glDisable(GL10.GL_DEPTH_TEST);
		// �������
		gl.glEnable(GL10.GL_BLEND);

		// ������ͼ
		gl.glEnable(GL10.GL_TEXTURE_2D);

		IntBuffer intBuffer = IntBuffer.allocate(1);
		// ��������
		gl.glGenTextures(1, intBuffer);

		int texture = intBuffer.get();

		// ������
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);

		// ��������
		Bitmap bmp = LoadImag.loadI(context.getResources(), R.drawable.star);

		// ��������
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0);

		// �����˲�
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_LINEAR);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR);
		
		for (loop = 0; loop < maxParticles; loop++) {
            float xi, yi, zi;
            xi = (float) ((rand() % 50) - 26.0f) * 10.0f;
            yi = zi = (float) ((rand() % 50) - 25.0f) * 10.0f;
            // ��ʼ������
            initParticles(loop, random.nextInt(12), xi, yi, zi);
        }
	}

	public int rand() {
		return Math.abs(random.nextInt(1000));
	}

	// initParticle��ʼ������
	public void initParticles(int num, int color, float xDir, float yDir,
			float zDir) {
		Particles par = new Particles();
		// ʹ��������Ϊ����״̬
		par.active = true;
		// ������������ֵΪ���
		par.life = 1.0f;
		// �������˥��(0~99)/1000+0.003f
		par.fade = rand() % 100 / 1000.0f + 0.003f;

		// ����������ɫ����r,g,b
		// r
		par.r = colors[color][0];
		// g
		par.g = colors[color][1];
		// b
		par.b = colors[color][2];

		// �趨���ӷ���xi,yi,zi
		// xi
		par.xi = xDir;
		// yi
		par.yi = yDir;
		// zi
		par.zi = zDir;

		// x,y,z������ٶ�
		// xg
		par.xg = 0.0f;
		// yg
		par.yg = -0.5f;
		// zg
		par.zg = 0.0f;

		particles[loop] = par;
	}

}
class LoadImag{
	public static Bitmap bitmap;
	public static Bitmap loadI(Resources res, int id){
		bitmap = BitmapFactory.decodeResource(res, id);
		return bitmap;
	}
}
