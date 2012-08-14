package org.ourunix.android.jpct.ball;
import java.util.Random;

import com.threed.jpct.Object3D;


/*
 * ����˵��:����Ϊ��Ķ�����,������������в���ֵ,�������ƶ��ĺ���
 * ��Ļ��Ʒ����� 
 * 
 */

class Ball3D
{	
	static final float	TOP_SPEED		= 6.0f;		//���������
	static final float	MIN_SPEED		= 0.6f;		//���������
	static final float	DEFAULT_SPEED	= 3.6f;		//
	
	static final float	WEIGHT			= 1.5f;		//����
	static final float	RADIUS			= 1.25f;	//-��뾶
	
	static final float	stopvi			= 0.1f;		//����ֹͣ�ƶ����ٶ�
	static float		friction 		= 0.0338f;	//������̨��ĥ����,���ٶȲ�ͬ���ı�,����ֻ�ö�ֵ
	
	Object3D				model;				//--����ģ����m3g��Ľڵ�
	int 				index;				//--���Ӻ���	
	static int			whiteballnum 	= 0;	//indexΪ0���ǰ���	
	
	float[]				position		= new float[3];	//���xyzλ��
	float[]				velocity		= new float[2];	//����ٶ�����
	float[]				extForce		= new float[2];	//�����������ķ�������,��������ǰ����
	
	float				power			= 0;					//��ǰ��������.	
	float				direction		= 0;	//�������ƶ�����Ƕ�0~360,Ĭ��Ϊ0,������
					
	//������ת�����ķ�ΧΪ-30��30,����ֵԽ��,��������ת��Խ��
	float					MAXCIR			= 50;	//����ٶȵ���תΪ1��60��	
	float					UDcir			= 0;	//������ת����
	float					Zcir			= 0;	//������ת����
	//	��ʱ��̨��ĥ������	
	float				nowUDPower		= 0;	//(float)(2*7*UDcir*3.1416f/180*RADIUS)
	float				UDpower			= 0;	//ǰ��������Ҫ�ﵽ������С
	float				UDsignx			= 0;	//��־��ԭ����ʼ�������ٶ���������
	float				UDsigny			= 0;	//	
	
	//����ת����Ľ��ٶ���Ƕȱ���
	//---ǰ�����п���ϵԵX��Yת��,Ҫ�����ĸ�λ�ô�,����������һ��ԵZתת��.	
	float					xcir			= 0;	//����̨�Ͻ������¹���ʱ��ת���ٶ�
	float					ycir			= 0;	//����̨�Ͻ������ҹ���ʱ����ԵY�����ת���ٶ�
	float					xyanglev		= 0;	//����ǰ������ת���ٶ�
	float					udanglev		= 0;	//ǰ����ת����ת���ٶ�
	float					zanglev			= 0;	//����̨�Ͻ���������תʱԵZ�����ת���ٶ�		

	
	boolean				willinPocket	= false;//��Ҫ���ı�־,�������Ƿ����´��������ж�.
	float[]				willposi		= new float[2];	//�������λ��,���˵��Ƶ�����ʾ,�ÿ�Щ.	
	//boolean				willColl		= false;		//�ж����Ƿ񽫻���ײ,��Ϊ����ײ,���겻���ƶ�λ�ñ�־
	boolean				inPocket		= false;//���ѽ��ı�־	
	
	//-----��Ϸ������������õ���,ÿ��һ�ֶ����ò���һ�ε�.
	boolean				istouchwall 	= false;	//�Ƿ�����ǽ
	int					inwhichp		= -1;		//��¼����������ǵڼ��ŵ���0 1 2 3 4 5		]
	boolean				isindirectIN	= false;	//�Ƿ��ӵĽ���.	
	
	
	//---��Ϸ�����ǰͳ������ÿһ����ƶ�·���Ĳ���..
	//boolean	recordposition	= false;
	static final int	runsLength		=	200;
	float	runsPosition[][]			= new float[runsLength][3];	//����Ϊ��,X,Y ������¼ÿ�������򾭹���λ��	
	float	runsState[][] 				= new float[runsLength][6];	//udcir,z,direction��¼��ʱ�����ת����
	
	
	float tempud=0,tempxy=0,tempz=0;//������¼�����ת����
	
	Ball3D(int _index)
	{
		try{
			index = _index;	//--���	
			
//			if (index < Model.BALLNUM)//�������10����������ʱ�õ���,������ģ�Ͷ�Ӧ...
//				model = Model.BallModel[index];//--ȡ��Model���Ѿ���M3G��ȡ�õĳ����ڵ�	
			
			resetBall();	

		}catch(Exception e){System.out.println("new ball error"+e);}
		//init();
	}
	
	//--��ʼ�����������Ϣ
	void resetBall()
	{
		tempud=0;
		tempxy=0;
		tempz=0;
		
		//torque = 0;		//��ת��
		power = 0;		//����			
		direction = 0;//360 288 216 144 72 ������ĳ�ͷ����????		
		
		velocity[0] = 0;	//�ٶ�����
		velocity[1] = 0;
		//angVelocity = 0;	//���ٶ�
		
		extForce[0] =0;		//����������
		extForce[1] =0;				
		
		//model = Model.BallModel[index];//--ȡ��Model���Ѿ���M3G��ȡ�õĳ����ڵ�				
		
//		if (index < Model.BALLNUM)
//		{
//			position[0] = Model.BallPos[index][0];//---ȡ��Model��ģ�͵��趨������
//			position[1] = Model.BallPos[index][1];//---
//			position[2] = Model.BallPos[index][2];//---
//		}
		
		willinPocket	= false;
		inPocket		= false;
		inwhichp		= -1;
		isindirectIN    = false;
		willposi[0]		= 0;
		willposi[1]		= 0;	
		
		//LRcir			= 0;
		UDcir			= 0;
		UDpower			= 0;
		nowUDPower		= 0;
		Zcir			= 0;
		zanglev			= 0;
		udanglev		= 0;
		
		xyanglev		= 0;	
		xcir			= 0;
		ycir			= 0;				

		UDsigny			= 0;
		UDsignx			= 0;
		
		//willColl		= false;
		//collPosition[0]	= 0;
		//collPosition[1] = 0;
		
//		if (index < Model.BALLNUM)
//			resetRunsposition();
		
		//updateballModel();
	}
	
	
	//���Ʒ��ð����λ��
	float moveoffsetxy = 0.7f;
	public void moveUp()
	{
		position[1]+=moveoffsetxy;
		//Map.m_cuegroupPos[1]+=moveoffsetxy;
	}
	public void moveDown()
	{
		position[1]-=moveoffsetxy;
		//Map.m_cuegroupPos[1]-=moveoffsetxy;
	}
	public void moveLeft()
	{
		position[0]-=moveoffsetxy;
		//Map.m_cuegroupPos[0]-=moveoffsetxy;
	}
	public void moveRight()
	{
		position[0]+=moveoffsetxy;
		//Map.m_cuegroupPos[0]+=moveoffsetxy;
	}

	//��������------------
	public void hitBall()//ǰ������
	{
//		power = Map.m_hitpower;	//TOP_SPEED;	
//		Map.m_framepower = Map.m_hitpower;	//��Ӧ�������ȵ���������ٶ�
//		
//		Map.m_hitpower = DEFAULT_SPEED;		//
//		
//		
//		Map.runscounter = 0;//---��һ����ʱ����ƶ���������
//		Map.maxrunscounter = 0;
		
		if (UDcir < 0)//
		{
			//-----effectPower = -(float)(UDcir*3.1416/180*RADIUS);//��UDcirΪ60����ʱ,����ֵΪ1.3
			//effectPower = effectPower/2.0f*power;		//�����Լ����
			UDpower = (float)(-2f/7f*UDcir*3.1416f/180f*RADIUS)*power/TOP_SPEED;//�����ת������Ҫ�ﵽ�Ĵ�С0.374?  2/7
			nowUDPower = 0;
		}
		else if (UDcir > 0)//
		{
			//-----effectPower = -(float)(UDcir*3.1416/180*RADIUS);//��UDcirΪ60����ʱ,����ֵΪ1.3
			//effectPower = effectPower/2.0f*power;		//�����Լ����
			UDpower = (float)(2f/7f*UDcir*3.1416f/180f*RADIUS)*power/TOP_SPEED;//�����ת������Ҫ�ﵽ�Ĵ�С0.374?  2/7
			nowUDPower = 0;
		}
		
//		Map.m_playershotnum[Map.m_whoturn]++;//�����ߵ�ÿ�ִ�ĸ�������
	}	
	
	
	public void turnLeft()//ת��
	{	
		//����������ƶ�����Ĵ���Ƕ�,��Ҫ����һ�ΰ���������һ������·��		
//		direction += Map.turnangle;//--��ĳ�������ı�
		if (direction >=360)
			direction -= 360;
		//System.out.println(""+Map.turnangle);
	}
	public void turnRight()//ת��
	{		
//		direction -= Map.turnangle;
		if (direction <0)
			direction += 360;
	}
	
	
	//���ƻ������λ��-----------
	public void hitLeft()
	{		
		Zcir -= 3;
		if (!checkHitpoint()) 
		{
			///�ڶ����ж�,�����ڱ����ƶ�
			if (UDcir>0) UDcir-=3;
			else if (UDcir<0) UDcir+=3;
			if (!checkHitpoint()) 
			{
				Zcir += 3;
				return;
			}
		} 
	}
	public void hitRight()
	{
		Zcir += 3;
		if (!checkHitpoint()) 
		{
			if (UDcir>0) UDcir-=3;
			else if (UDcir<0) UDcir+=3;
			if (!checkHitpoint()) 
			{
				Zcir -= 3;
				return;
			}
		}
	}
	public void hitUp()
	{
		UDcir += 3;
		if (!checkHitpoint()) 
		{
			if (Zcir>0) Zcir-=3;
			else if (Zcir<0) Zcir+=3;
			if (!checkHitpoint()) 
			{
				UDcir -= 3;
				return; 
			}
		}
	}
	public void hitDown()
	{
		UDcir -= 3;
		if (!checkHitpoint()) 
		{
			if (Zcir>0) Zcir-=3;
			else if (Zcir<0) Zcir+=3;
			if (!checkHitpoint()) 
			{
				UDcir += 3;
				return; 
			} 
		}
	}
	public boolean checkHitpoint()
	{
		int x1=0,y1=0;
//		x1-=Map.m_whiteball.Zcir;
//		y1-=Map.m_whiteball.UDcir;
//		
		//����ѡ�����ֵ�ķ�Χ
		if ((float)Math.sqrt((double)(x1*x1+y1*y1)) > MAXCIR)
			return false;		
		return true;
	}
	//---------
	
	
	/*
	 *	������δ�����ٶ�,��δ���ķ���. 
	 *	����Ҫ������Ļ�������ǰ����
	 */
	public void ballmove()
	{	
		//--toRadians  �Ƕȱ仡��
		//1����=PI/180   ����=�Ƕ�*PI/180
		//--�������ƶ��Ƕȷ�������
		float[] dir = { 0, 0 };
		//ǣ���� �ƶ��ķ�������*�ƶ����ٶ�=��Ҫ�ƶ���x,y����
		float[] tractiveForce = {0,0};//ֻ�ڴ����һ˲����
		
		if (power!=0)//�Ż��㷨��....ֻ������ʱ������.
		{			
			dir[0]=(float) Math.cos(Math.toRadians(direction));
			dir[1]=(float) Math.sin(Math.toRadians(direction));
			tractiveForce[0]=dir[0] * power;
			tractiveForce[1]=dir[1] * power;   
			
			if (Tools.myabs(tractiveForce[0]) < stopvi) tractiveForce[0]=0;
			if (Tools.myabs(tractiveForce[1]) < stopvi) tractiveForce[1]=0;			
				
			power = 0;//--�����������������ʧ��,֮��Ϳ����Ի���ת����.
		}	
		
		
		//��ĥ������������ٶȲ�ͬ���ı�
		/*float xx,yy;
		xx=Tools.myabs(velocity[0]);		
		yy = Tools.myabs(velocity[1]);
		
		if (xx>=7f || yy>=7f)
			friction = 0.033f;		
		else if (xx>=6f || yy>=6f)
			friction = 0.033f;
		else if (xx>=5f || yy>=5f)
			friction = 0.033f;
		else if (xx>=4f || yy>=4f)
			friction = 0.033f;
		else if (xx>=3f || yy>=3f)
			friction = 0.033f;
		else if (xx>=2f || yy>=2f)
			friction = 0.033f;
		else if (xx>=1f || yy>=1f)
			friction = 0.033f;
		else if (xx>=0.4f || yy>=0.4f)
			friction = 0.033f;
		else*/
			//friction = 0.033f;////����if ,���������Ż��㷨...
		
		float[] frictionalForce = { -velocity[0] * friction, -velocity[1] * friction };		
		float[] netForce = { 0, 0 };	//���ƶ��ľ��ƶ�X,Yֵ,������֮��	
		
		/*float[]	bv = {0,0};
		bv[0] = velocity[0];
		bv[1] = velocity[1];*/		
		//float nowpower = (float)Math.sqrt(velocity[0]*velocity[0]+velocity[1]*velocity[1]);
		
		if (UDcir < 0)//�������
		{		
			nowUDPower += UDpower/20f;//�������Ӵ�				
			
			//System.out.println("nowUDPower:"+nowUDPower+" UDpower:"+UDpower);
			if (nowUDPower >= UDpower)//����������Ѿ�������߷�,�����ٶ���ĥ����ͣ��
			{
				//System.out.println("end cir");
				UDcir = 0;
				nowUDPower = 0;
			}
			else//���ٶȼ��ϻ���������ٶ�
			{				
				//System.out.println(velocity[0]+" "+velocity[1]);
				//System.out.println("reduce v "+UDsignx+" "+UDsigny);
				float x1,y1;
				//�˴����directionֵ��������ǰ�ƶ��ķ���,������ʱ,���ƶ�����ٶ�ͻȻ��Ϊ0
				//������п�������ƶ�,�����ʱ��direction������ǰ��..
				x1 =Tools.myabs(nowUDPower/2*(float)Math.cos(Math.toRadians(direction)));
				y1 =Tools.myabs(nowUDPower/2*(float)Math.sin(Math.toRadians(direction)));
				extForce[0] = -UDsignx*x1;				
				extForce[1] = -UDsigny*y1;				
			}	
			
		}
		else if (UDcir > 0)//ǰ�����
		{
			nowUDPower += UDpower/20f;//ǰ�����Ӵ�	

			if (nowUDPower >= UDpower)//����������Ѿ�������߷�,�����ٶ���ĥ����ͣ��
			{
				//System.out.println("end cir");
				UDcir = 0;
				nowUDPower = 0;
			}
			else//���ٶȼ��ϻ���������ٶ�
			{				
				//System.out.println(velocity[0]+" "+velocity[1]);
				//System.out.println("reduce v "+UDsignx+" "+UDsigny);
				float x1,y1;
				x1 =Tools.myabs(nowUDPower/2*(float)Math.cos(Math.toRadians(direction)));
				y1 =Tools.myabs(nowUDPower/2*(float)Math.sin(Math.toRadians(direction)));
				extForce[0] = UDsignx*x1;				
				extForce[1] = UDsigny*y1;				
			}	
		}		
		
		//�ܵķ�������Ϊǰ���ļӷ��ֵ�Ħ���������ķ�����֮��
		netForce[0] = frictionalForce[0] + tractiveForce[0] + extForce[0];
		netForce[1] = frictionalForce[1] + tractiveForce[1] + extForce[1];	
				
		/*if (Map.runscounter>2 && runsPosition[Map.runscounter-1][0] == runsPosition[Map.runscounter-2][0]&&
			runsPosition[Map.runscounter-1][1] == runsPosition[Map.runscounter-2][1]&&		
			runsPosition[Map.runscounter-1][2] == runsPosition[Map.runscounter-2][2])
		{
			//--�����û���ƶ����Ͳ�����
			
		}
		else
		{*/
			velocity[0] += netForce[0];//ԭ�����ٶ��������� �������������ı��ٶ�
			velocity[1] += netForce[1];	
		//}
		
		extForce[0] = 0;
		extForce[1] = 0;
		
		if (UDsignx == 0 && UDsigny == 0)//û�ǹ��ľͼ���֮ǰ�ƶ���XY����
		{
			UDsignx = numbersign(velocity[0]);
			UDsigny = numbersign(velocity[1]);
		}					
		
		///--����ٶ�̫����,��ͣ����.��Ϊǰ�ߵļ��ٲ��������ٶ�Ϊ��λ��,������жϻ�һֱ����ȥ.
		if (UDcir==0 && Tools.myabs(velocity[0])<stopvi && Math.abs(velocity[1])<stopvi)
		{
			velocity[0] = 0;
			velocity[1] = 0;
			UDsignx = 0;
			UDsigny = 0;
			UDcir = 0;
			nowUDPower = 0;		
		}	

	}
	
	//--������֪������ٶ��뷽���ƶ�����
	public void recordruns() //��������λ�õ���Ϣ
	{			
		if (direction >= 360)//---����ƶ�����Ƕ�
			direction -= 360;
		if (direction < 0)
			direction += 360;	
		
		//��¼��ÿ��ʱÿ�����λ��
//		runsPosition[Map.runscounter][0] = position[0];
//		runsPosition[Map.runscounter][1] = position[1];		
//		runsPosition[Map.runscounter][2] = position[2];	
//		
//		runsState[Map.runscounter][0]	 = direction;
//		
//		float lv = (float)Math.sqrt(velocity[0]*velocity[0]+velocity[1]*velocity[1]);
//		float av = (float)(lv/(3.1416f/180f*RADIUS));//��Ȼ��תʱǰ���Ľ��ٶ�,Ҳ����Y����ת������
//		runsState[Map.runscounter][3]	 = av;
//		runsState[Map.runscounter][4]	 = -numbersign(velocity[0]);
//		runsState[Map.runscounter][5]	 = -numbersign(velocity[1]);
//		
//		if (runsState[Map.runscounter][3]!=0) udanglev = UDcir*2;//����ٶ�Ϊ0ʱ��ת��,��Ϊ��ʱת����Ϊ0
//		else	udanglev = 0;		
//		runsState[Map.runscounter][1]	 = udanglev;
//		zanglev = Zcir*2;
//		runsState[Map.runscounter][2]	 = zanglev;
		
		//updateballModel();//�˴��Ѳ����ٸ��������ʾλ��,���������������ڲ��Բ鿴
	}
	
	public void updatePosition()
	{		
//		position[0] = runsPosition[Map.runscounter][0];
//		position[1] = runsPosition[Map.runscounter][1];
//		position[2] = runsPosition[Map.runscounter][2];	
//		
//		direction 	= runsState[Map.runscounter][0];
//		udanglev    = runsState[Map.runscounter][1];
//		zanglev 	= runsState[Map.runscounter][2];
//		xyanglev	= runsState[Map.runscounter][3];
//		xcir		= runsState[Map.runscounter][4];
//		ycir		= runsState[Map.runscounter][5];		
		
		
		updateballModel();
	}
	
	//--��ʼ������ƶ�·��
	public void resetRunsposition()
	{
		for (int i = 0; i <runsLength; i++)
		{
			runsPosition[i][0] = -99;
			runsPosition[i][1] = -99;
			runsPosition[i][2] = -99;
			runsState[i][0]	   = 0;
			runsState[i][1]	   = 0;
			runsState[i][2]	   = 0;
			runsState[i][3]	   = 0;
			runsState[i][4]	   = 0;
			runsState[i][5]	   = 0;
		//	Game.playsoundlist[i] = -1;
		}
	}
	

	
	public void updateballModel() 
	{				
		//--��Ť�������ʾ��Ҫ��ʾ�Ķ�̬ģ��	
		try{
			//model.animate(0);
			
//			model.setTranslation(position[0], position[1], position[2]);//����ģ�͵�ƽ�Ƶ�λ�õ�����
//			
//
//			tempz += -zanglev;
//			tempud += udanglev;
//			tempxy += xyanglev;
//			if (zanglev!=0)
//			{
//				//model.preRotate(-zanglev,0,0,1);
//				model.setOrientation(tempz, 0, 0, 1);//--����ģ�͵�˳ʱ����ת�ĽǶ�����ת��
//			}
//			else if (udanglev!=0)
//			{
//				//model.preRotate(udanglev,ycir,-xcir, 0);//���ٺ���
//				model.setOrientation(tempud, ycir, -xcir, 0);//--����ģ�͵�˳ʱ����ת�ĽǶ�����ת��
//			}
//			else if (xyanglev!=0)
//			{
//				//model.preRotate(xyanglev,ycir,-xcir, 0);
//				model.setOrientation(tempxy, ycir, -xcir, 0);//--����ģ�͵�˳ʱ����ת�ĽǶ�����ת��
//			}
			
		
			//������ת���ĽǶȻ������λ�õ�������ת����,���û��������ת�ľ������ǰ����з�����ת,
			//����ǻ��������������ƶ���������ǰ���ķ���ת��
			/*if (zanglev!=0)
			{
				model.preRotate(-zanglev,0,0,1);
			}
			else if (udanglev!=0)
			{
				//if (UDcir>0)
					model.preRotate(udanglev,ycir,-xcir, 0);//���ٺ���
				//else
				//	model.preRotate(udanglev,-numbersign(ycir),numbersign(xcir), 0);//����ǰ��
			}
			else
				model.preRotate(xyanglev,ycir,-xcir, 0);*/
			
			
			udanglev = 0;
			xyanglev = 0;xcir = 0;ycir = 0;//�����Ŷ���ʱ��ת
		}
		catch(Exception e)
		{System.out.println("updateballModel error:"+e);
		}
	}
	
	//�������Ƿ����ƶ�
	public boolean isballMove()
	{
		if((velocity[0] == 0 && velocity[1] == 0) || inPocket)
			return false;
		return true;
	}
	
	public int numbersign(float b)
	{
		if (b == 0.0f)
			return 0;
		return (int)(Tools.myabs(b)/(float)b);
	}
	
}








