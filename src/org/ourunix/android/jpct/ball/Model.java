package org.ourunix.android.jpct.ball;


import com.threed.jpct.Camera;
import com.threed.jpct.Light;
import com.threed.jpct.Object3D;
import com.threed.jpct.World;

public class Model {
	public static final String	m3gFile					= "/pool.3ds";
	static World				w;					//--����ڵ�
	//static Graphics3D			g3d;				//--�������3Dͼ��Ķ���

	static final int			BALLNUM				= 10;//--�ܹ�10����
	static Object3D					BallModel[];		//--��ģ�ͽڵ�
	static float				BallPos[][]			= new float[BALLNUM][3];	//�������
	
	static Object3D					CueModel;			//���ģ�ͽڵ�
	static float 				CuePos[]	= new float[3];	//��˵�λ��
	static float 				CueGroupPos[] = new float[3];	//������λ��
	
	static Object3D					TableModel;		//̨��ģ��.
	static Object3D					PlaneSign[][];		//�ƶ���ʾ��־ģ�ͽڵ�
	           //static Background			bgModel;
	//	static Sprite3D				handSprite;
		
	//	static Group				CueCamGroup;		//һ���ֳ�ͼ��ڵ�,����һϵ��������ӽڵ�
		
		        //static Group				gameCameraGroup;	//--һ���ֳ�ͼ��ڵ�,����һϵ��������ӽڵ�
		static Camera				gameCameraUPMID;	//���Ϸ����е�
		static Camera				gameCameraUPFRO;	//̨����--��Ҫ��
		
		static Camera				gameCameraCue;		//����ʱ�Ŀ��ӽǶ�
		static Camera				gameCamera0;			//��0���ľ�ͷ
		static Camera				gameCamera1;			//��1���ľ�ͷ
		static Camera				gameCamera2;			//��2���ľ�ͷ
		static Camera				gameCamera3;			//��3���ľ�ͷ
		static Camera				gameCamera4;			//��4���ľ�ͷ
		static Camera				gameCamera5;			//��5���ľ�ͷ	
		
		static Camera				FPCamera;//frontpage��
		

		//----����ڵ���M3G�ļ��е�IDλ��
		//0=134  1=58   2=133   3=136   4=135   5=137
		
		static final int			ID_TABLE0_CAMERA		= 126;
		static final int			ID_TABLE1_CAMERA		= 58;
		static final int			ID_TABLE2_CAMERA		= 125;
		static final int			ID_TABLE3_CAMERA		= 128;
		static final int			ID_TABLE4_CAMERA		= 127;
		static final int			ID_TABLE5_CAMERA		= 129;	
		static final int			ID_CUE_CAMERA			= 149;//�˵ľ�ͷ?	
		static final int			ID_FP_CAMERA			= 155;
		
		static final int			ID_GAME_LIGHT2			= 156;
		static final int			ID_GAME_LIGHT1			= 2;
		
		static final int			ID_TABLE_MODEL			= 13;
		
		//--����,1~9����  ����...,����
		//static final int[]			ID_BALL_MODELS		= {57,24,69,80,91,102,113,124,46,35};	//��Ľڵ�λ
		//static final int[]			ID_BALL_MODELS			= {57,24,113,102,69,91,124,46,80,35};	//��Ľڵ�λ
		static final int[]			ID_BALL_MODELS			= {57,24,69,80,91,102,113,124,46,35};	//��Ľڵ�λ
		
		static final int			ID_CUE_MODEL			= 144;	//ľ��
		static final int			ID_CUECAM_GROUP			= 150;
		static final int[]			ID_PLANES				= {172,164};//���·����ʾ��ͼ
		static final int			ID_BG					= 173;	
		

		static Light l1,l2;
		
}
