package org.ourunix.android.jpct.ball;

class Tools
{
	
	public static int returnEvenNumber(int temp){
		if((temp)%2==1){
			return temp+1;
		}
		return temp;
	}
//	public static void main(String args[]) {
		//-sin�����Ƕȶ��ձ�,һ��һ�ȵļ�  0��90�ȵ�
		/*
		 	for (int i = 0;i<=90;i++)
			{
				System.out.println(i+" : "+Math.sin(Math.toRadians(i)));
			}
		*/
//		for (int i = 0;i<=90;i++)
//		{
//			System.out.println(i+" : "+Math.cos(Math.toRadians(i)));
//		}
//	}
	static float sinangleTable [] = 
	{
		0.01745240643728351f,
		0.03489949670250097f,
		0.05233595624294383f,
		0.0697564737441253f,
		0.08715574274765817f,
		0.10452846326765346f,
		0.12186934340514748f,
		0.13917310096006544f,
		0.15643446504023087f,
		0.17364817766693033f,
		0.1908089953765448f,
		0.20791169081775931f,
		0.22495105434386498f,
		0.24192189559966773f,
		0.25881904510252074f,
		0.27563735581699916f,
		0.2923717047227367f,
		0.3090169943749474f,
		0.3255681544571567f,
		0.3420201433256687f,
		0.35836794954530027f,
		0.374606593415912f,
		0.3907311284892737f,
		0.40673664307580015f,
		0.42261826174069944f,
		0.4383711467890774f,
		0.45399049973954675f,
		0.4694715627858908f,
		0.48480962024633706f,
		0.49999999999999994f,
		0.5150380749100542f,
		0.5299192642332049f,
		0.544639035015027f,
		0.5591929034707468f,
		0.573576436351046f,
		0.5877852522924731f,
		0.6018150231520483f,
		0.6156614753256583f,
		0.6293203910498375f,
		0.6427876096865393f,
		0.6560590289905073f,
		0.6691306063588582f,
		0.6819983600624985f,
		0.6946583704589973f,
		0.7071067811865475f,
		0.7193398003386511f,
		0.7313537016191705f,
		0.7431448254773942f,
		0.7547095802227719f,
		0.766044443118978f,
		0.7771459614569708f,
		0.7880107536067219f,
		0.7986355100472928f,
		0.8090169943749475f,
		0.8191520442889918f,
		0.8290375725550417f,
		0.8386705679454239f,
		0.8480480961564261f,
		0.8571673007021122f,
		0.8660254037844386f,
		0.8746197071393957f,
		0.8829475928589269f,
		0.8910065241883678f,
		0.898794046299167f,
		0.9063077870366499f,
		0.9135454576426009f,
		0.9205048534524404f,
		0.9271838545667873f,
		0.9335804264972017f,
		0.9396926207859083f,
		0.9455185755993167f,
		0.9510565162951535f,
		0.9563047559630354f,
		0.9612616959383189f,
		0.9659258262890683f,
		0.9702957262759965f,
		0.9743700647852352f,
		0.9781476007338057f,
		0.981627183447664f,
		0.984807753012208f,
		0.9876883405951378f,
		0.9902680687415704f,
		0.992546151641322f,
		0.9945218953682733f,
		0.9961946980917455f,
		0.9975640502598242f,
		0.9986295347545738f,
		0.9993908270190958f,
		0.9998476951563913f,
		1.0f,
	};
	static float cosangleTable []={
		1.0f,
		0.9998476951563913f,
		0.9993908270190958f,
		0.9986295347545738f,
		0.9975640502598242f,
		0.9961946980917455f,
		0.9945218953682733f,
		0.992546151641322f,
		0.9902680687415704f,
		0.9876883405951378f,
		0.984807753012208f,
		0.981627183447664f,
		0.9781476007338057f,
		0.9743700647852352f,
		0.9702957262759965f,
		0.9659258262890683f,
		0.9612616959383189f,
		0.9563047559630355f,
		0.9510565162951535f,
		0.9455185755993168f,
		0.9396926207859084f,
		0.9335804264972017f,
		0.9271838545667874f,
		0.9205048534524404f,
		0.9135454576426009f,
		0.9063077870366499f,
		0.898794046299167f,
		0.8910065241883679f,
		0.882947592858927f,
		0.8746197071393957f,
		0.8660254037844387f,
		0.8571673007021123f,
		0.848048096156426f,
		0.838670567945424f,
		0.8290375725550417f,
		0.8191520442889918f,
		0.8090169943749475f,
		0.7986355100472928f,
		0.7880107536067219f,
		0.7771459614569709f,
		0.766044443118978f,
		0.754709580222772f,
		0.7431448254773942f,
		0.7313537016191705f,
		0.7193398003386512f,
		0.7071067811865476f,
		0.6946583704589974f,
		0.6819983600624985f,
		0.6691306063588582f,
		0.6560590289905074f,
		0.6427876096865394f,
		0.6293203910498375f,
		0.6156614753256583f,
		0.6018150231520483f,
		0.5877852522924731f,
		0.573576436351046f,
		0.5591929034707468f,
		0.5446390350150272f,
		0.5299192642332049f,
		0.5150380749100544f,
		0.5000000000000001f,
		0.4848096202463371f,
		0.46947156278589086f,
		0.45399049973954686f,
		0.43837114678907746f,
		0.42261826174069944f,
		0.4067366430758004f,
		0.3907311284892737f,
		0.3746065934159122f,
		0.35836794954530016f,
		0.3420201433256688f,
		0.32556815445715676f,
		0.30901699437494745f,
		0.29237170472273677f,
		0.27563735581699916f,
		0.25881904510252074f,
		0.24192189559966767f,
		0.22495105434386514f,
		0.20791169081775923f,
		0.19080899537654492f,
		0.17364817766693041f,
		0.15643446504023092f,
		0.13917310096006547f,
		0.12186934340514749f,
		0.10452846326765346f,
		0.08715574274765836f,
		0.06975647374412523f,
		0.052335956242943966f,
		0.03489949670250108f,
		0.0174524064372836f,
	};
	//����λС��
	public static float myround(float f)
	{		
		return ((float)((int)(f*100)))/100f;
	}
	public static float myabs(float f)//ʹ���Լ���abs�Ż��㷨
	{
		if (f<0f) f=-f;
		return f;
	}	
	public static float myMin(float n1,float n2)
	{
		return   n1<n2?n1:n2;  
	}	
	public static float myMax(float n1,float n2)
	{
		return   n1>n2?n1:n2; 
	}
	
	//���ݴ�������X,Yֵȷ�����ش˷���ĽǶ�.
	public static int getXYdir(float x,float y)
	{
		float ll = (float)Math.sqrt(x*x+y*y);
		
		if (ll==0) return 0;//��������ֵΪ0ʱ����0
		
		float sina = Tools.myabs(y/ll);
		
		int m,low,high,find;		
		m=0;low=0;high=Tools.sinangleTable.length-1;find=0;	
		//System.out.println("lllllll is"+Tools.sinangleTable.length);
		while((find==0)&&(low<=high))//���ֲ��ҷ����
		{		
			m=(low+high)/2;
			if (sina>=Tools.sinangleTable[m])
			{
				if (m == Tools.sinangleTable.length-1)
					break;
				else 
					 if (sina<Tools.sinangleTable[m+1])
						 break;						
			}
			
			if (sina<Tools.sinangleTable[m])
			{
				high = m-1;
			}
			else
			{
				low = m+1;
			}
		}
		m++;
		
		if (y>0)//0 1��
		{
			if (x<0)
			{
				m=180-m;
			}
		}
		else if (y<0)//2 3��
		{			
			if (x<0)
			{
				m+=180;
			}
			else if (x>0)
			{
				m=360-m;
			}
			else
				m+=180;
		}
		
		//System.out.println("direction is"+m);		
		if (m>=360)
			m-=360;
		else if (m<0)
			m+=360;
		
		return m;
	}	
	
	/*
	 bool   online(LINESEG   l,POINT   p)   
	 {   
	 	return(   (multiply(l.e,p,l.s)==0)   &&(   (   (p.x-l.s.x)*(p.x-l.e.x)<=0   )&&(   (p.y-l.s.y)*(p.y-l.e.y)<=0   )   )   );   
	 } 
	 */
	
	//�������߶��Ƿ��ཻ...��ȷ��!!!
	public static boolean checklinecut(float l1[],float l2[])
	{
		//�����߶��Ƿ��ཻX0X1 AND X1X2		
		float x1,y1,x2,y2,x3,y3,x4,y4;
		x1=l1[0];y1=l1[1];x2=l1[2];y2=l1[3];
		x3=l2[0];y3=l2[1];x4=l2[2];y4=l2[3];
		//x0=-17.065434f; y0=17.769289f;  x1=-16.874624f; y1=18.750916f;
		
		/*if (Map.isdebug && l2[0]==Map.tableline[3][0] && l2[1]==Map.tableline[3][1] && l2[2]==Map.tableline[3][2] && l2[3]==Map.tableline[3][3])
		{
			System.out.println("x0:"+x0+" y0:"+y0+"  x1:"+x1+" y1:"+y1);
			System.out.println("x2:"+x2+" y2:"+y2+"  x3:"+x3+" y3:"+y3);
		}*/
		
		
		//����1�����ж�..
		return ((myMax(x1,x2)>=myMin(x3,x4))&&
		        (myMax(x3,x4)>=myMin(x1,x2))&&
		        (myMax(y1,y2)>=myMin(y3,y4))&&
		        (myMax(y3,y4)>=myMin(y1,y2))&&
		        (multiply(x3,y3,x2,y2,x1,y1)*multiply(x2,y2,x4,y4,x1,y1)>=0)&&
		        (multiply(x1,y1,x4,y4,x3,y3)*multiply(x4,y4,x2,y2,x3,y3)>=0));	
		
		
		//����2
		//  ��d=0����ֱ��AB��CDƽ�л��غϣ�   
		//  ��d!=0����ֱ��AB��CD�н��㣬�ҽ���(x0,y0)Ϊ��   
		/*float d = (y2-y1)*(x4-x3)-(y4-y3)*(x2-x1);
		float lx0=0,ly0=0;
		if (d==0) return false;//ƽ�л��غ�
		else if (d!=0)
		{
			//�󽻵�
			lx0   =   ((x2-x1)*(x4-x3)*(y3-y1)+(y2-y1)*(x4-x3)*x1-(y4-y3)*(x2-x1)*x3)/d; 
	        ly0   =   ((y2-y1)*(y4-y3)*(x3-x1)+(x2-x1)*(y4-y3)*y1-(x4-x3)*(y2-y1)*y3)/(-d);
		}
		
		//�����������жϽ����Ƿ����߶��ϣ����ж����µ�ʽ�ӣ�   
		//ֻ��������ĸ�ʽ�Ӷ������ſ��ж�(x0,y0)���߶�AB��CD�Ľ��㣬�������߶��޽���
		return ((lx0-x1)*(lx0-x2)<=0 && 
				(lx0-x3)*(lx0-x4)<=0 &&
				(ly0-y1)*(ly0-y2)<=0 &&
				(ly0-y3)*(ly0-y4)<=0);*/

	}
	/*
	 ����(P1-P0)*(P2-P0)�Ĳ����
	 �����Ϊ������<P0,P1>��<P0,P2>��˳ʱ�뷽��
	 ��Ϊ0��<P0,P1><P0,P2>���ߣ�
	 ��Ϊ����<P0,P1>��<P0,P2>������ʱ�뷽��;
	 ���Ը����������ȷ�������߶��ڽ��㴦��ת��,
	 ����ȷ��p0p1��p1p2��p1������ת������ת��ֻҪ��
	 (p2-p0)*(p1-p0)����<0����ת��>0����ת��=0����
	*/
	public static float multiply(float p1x,float p1y,float p2x,float p2y,float p0x,float p0y)
	{
		return((p1x-p0x)*(p2y-p0y)-(p2x-p0x)*(p1y-p0y));	
	}
	
	//�ж��������
	public static float twoPointlength(float a[],float b[])
	{
		float xx = (b[0])-(a[0]);
		float yy = (b[1])-(a[1]);
		return (float)Math.sqrt(xx*xx+yy*yy);
	}
	
	// a��b���߶ε������˵㣬 c�Ǽ���  
	// ����㵽�߶ε���С����.
	// ��Ҫ������жϣ������߶��ϵ�ͶӰ���Ƿ�λ���߶��ڲ����ǲſ��Բ��õ㵽ֱ�ߵĹ�ʽ������Ͳ���
	//--����OK,��������߶ε�ӳ�䷶Χ�ڵĻ�,��᷵�ص㵽�߶ε���̾���,������򷵻ؽ������!~~
	public static float[] distancePointLine(float l[],float c[]) 
	{			 
		float re[]=new float[2];
		float a[] = {l[0],l[1]};
		float b[] = {l[2],l[3]};
		float abx,aby,acx,acy;
		abx = b[0]-a[0];aby = b[1]-a[1];
		acx = c[0]-a[0];acy = c[1]-a[1];
		float f = abx * acx + aby * acy;
	 	if (f<0) 
	 	{
	 		re[0] = -1;
	 		re[1] = twoPointlength(a, c);//���ж����Ƿ����߶��ⲿ��a����� return -9999;//
	 		return re;
	 	}
	 	float d = abx * abx + aby * aby;
	 	if ( f>d) 
	 	{
	 		re[0] = 1;
	 		re[1] = twoPointlength(a, c);//�ж��Ƿ����߶��ⲿ��b���ұ�return 9999;//
	 		return re;
	 	}
	 	f = f/d; 
	 	float D[] = new float[2];
	 	D[0] = a[0] + f *abx; // c��ab�߶��ϵ�ͶӰ��
	 	D[1] = a[1] + f *aby; // c��ab�߶��ϵ�ͶӰ��
	 	
	 	re[0] = 0;
	 	re[1] = twoPointlength(c, D);//�����ڵ㵽�߶εľ���
	 	return re;
	 	
	}

	
	//�����ٶ�������ײ�߶κ���ٶ������ķ���,Ȼ�󷵻�
	public static float[] computerF(Ball3D balls,float L[])
	{
		float of[] = new float[2];		
		
		float length,s,t,s1x,s1y,s2x,s2y,p0x,p0y,p1x,p1y,p2x,p2y,p3x,p3y,xi,yi,npx,npy,Nx,Ny,Fx,Fy;
		
		//first move ball
		//balls[index].varsF[INDEX_X]+=balls[index].varsF[INDEX_XV];
		//balls[index].varsF[INDEX_Y]+=balls[index].varsF[INDEX_YV];

		// now project velocity vector forward and test for intersection with all lines of polygon shape

		// build up vector in direction of trajectory
		p0x=balls.position[0]-balls.velocity[0];//���λ��
		p0y=balls.position[1]-balls.velocity[1];

	//#if 1 // this is the velocity vector used as segment 1
		p1x=balls.position[0];//�յ�λ��
		p1y=balls.position[1];
		s1x=p1x-p0x;
		s1y=p1y-p0y;

		// normalize and scale to 1.25*radius
		length = (float)Math.sqrt(s1x*s1x+s1y*s1y);
		s1x = 1.25f*Ball3D.RADIUS*s1x/length;
		s1y = 1.25f*Ball3D.RADIUS*s1y/length; 
		p1x = p0x + s1x;
		p1y = p0y + s1y;
	//#endif

		// for each line try and intersect
		//for (int line=0; line < shape.num_verts; line++)
		//{
			// now build up vector based on line
			p2x=L[0];
			p2y=L[1];	
			p3x=L[2];
			p3y=L[3];
	
			s2x=p3x-p2x;
			s2y=p3y-p2y;
	
		//#if 0 // this is the perp vector used as segment 1
			// normalize s2x, s2y to create a perpendicular collision vector from the ball center
			length = (float)Math.sqrt(s2x*s2x+s2y*s2y);
			s1x = Ball3D.RADIUS*s2y/length;
			s1y = -Ball3D.RADIUS*s2x/length; 
			p1x = p0x+s1x;
			p1y = p0y+s1y;
		//#endif
	
			// compute s and t, the parameters
			s = (-s1y*(p0x-p2x) + s1x*(p0y-p2y))/(-s2x*s1y + s1x*s2y);
			t =  (s2x*(p0y-p2y) - s2y*(p0x-p2x))/(-s2x*s1y + s1x*s2y);
	
			// test for valid range (0..1)
			//if (s >= 0 && s <=1 && t >= 0 && t <=1)//---�ߵ���ײ��ⲻ��������
			//{
				// find collision point based on s
				xi = p0x+s*s1x;
				yi = p0y+s*s1y;
		
				// now we know point of intersection, reflect ball at current location
		
				// N = (-I . N')*N'
				// F = 2*N + I
				npx = -s2y;
				npy = s2x;
		
				// normalize p
				length = (float)Math.sqrt(npx*npx+npy*npy);
				npx/=length;
				npy/=length;
		
				// compute N = (-I . N')*N'
				Nx = -(balls.velocity[0]*npx + balls.velocity[1]*npy)*npx;
				Ny = -(balls.velocity[0]*npx + balls.velocity[1]*npy)*npy;
		
				// compute F = 2*N + I
				Fx = 2*Nx + balls.velocity[0];
				Fy = 2*Ny + balls.velocity[1];
		
				// update velocity with results
				of[0] = Fx;
				of[1] = Fy;
				//System.out.println("dfsdfas");
				//balls.velocity[0] = Fx;
				//balls.velocity[1] = Fy;
		
				//balls.position[0]+=balls.velocity[0];
				//balls.position[1]+=balls.velocity[1];
			//}
		//}
		return of;
	}
	
	/* 
	 *  �жϴ���������������ٶ����Ƚ��Ըõ�ΪԲ��Բ��ײ����Ե��ٶ�
	 *  ע:(������������Բ�Ѿ������е�.)
	 *
	 *	������
	 *  ��ײ���ÿ��ֻ�Ƚϴ����ĸ��ٵ������ƶ����뱻����֮�����ײ,
	 *  ��Ϊ�Ǹ�����,�����ٶȱȱ������,���Դ˴�ת�������λ�������Ѿ����������λ�ü������ٶȷ���
	 *  ���Ƚϴ�ʱ�������Ѓ�����ײ,�еĻ��ͽ�����
	 *
	 *	����ת�������ת��֮�����ײ,��תֵ�Ǽ��������ݵ�,���Բ���������תֵ.
	 *	����ԭ������ת�����תֵ.
	 *
	 *	�Թ���bug�����:  1.4,0.7   0.5,0.9  ����1.4,0.9   0.5,0.7
	 *  ���Ǹպ�XΪT��,YΪN��,�ٶȴ�С��û��,������ͻ��ڸ��겻����������
	 */
	public static float[] twocirPasspower(float ap[],float av[],float bp[],float bv[])
	{		
		
		//--����2��-------
		//����������ת����ʽ
		//nabx,nabyΪ��������   ����a���ĵ�b���ĵ�ʸ��	
		//��ʱ�����Ѿ����õ���ӽ��������е�λ�õ���,���Կ�ʼ��ʽ����		
		float nabx = (bp[0])-(ap[0]);	//
		float naby = (bp[1])-(ap[1]);	//
		
		//lengthΪ���������ߵľ���
		float length = (float)Math.sqrt(nabx*nabx+naby*naby);	//������		
		
		if (length==0)//�����ϲ�������,���������غϵ����
		{
			//ʹ��length��Ϊ0,�±ߵĳ���������ȷ����
			//֮ǰ�Ѿ�������ٶȷ���һС����
			ap[0]-=av[0];
			ap[1]-=av[1];
			
			nabx = (bp[0])-(ap[0]);	//
			naby = (bp[1])-(ap[1]);	//			
			//lengthΪ���������ߵľ���
			length = (float)Math.sqrt(nabx*nabx+naby*naby);	//������		
			
			System.out.println("ballcollision error!!!! balllength = 0!! change position");	
			//float r[]={0,0,0,0};
			//return r;			
		}
		
		//length = 2.501f;//����ʱΪ2.5f
		
		//��׼��(��λ)ʸ�����꣬�������ײ��(n��)�ĵ�λʸ��   	P749~750
		nabx /= length;	//��׼���ǽ����������ֵ������,���Ǳ�׼��ʸ��ֵ
		naby /= length;		
		
		//t��,�����ײ�ߵ����ߵ�ʸ��,��ֱ��n���.
		float tabx = -naby;//--�������ʸ��
		float taby = nabx;
		
		//a��n,t���ٶ�ת�� ,��������ٶ�ʸ����n���t����ٶȷ�ʸ��,��ֻ�Ǳ���,����ʵ��ֵ,Ҫת�س�XY��
		//û��������������,����ײǰ��t���ʸ������,��n���ʸ���ᷢ���仯
		float vain = av[0]*nabx+av[1]*naby;//a.xvi*nabx+a.yvi*naby;
		float vait = av[0]*tabx+av[1]*taby;//a.xvi*tabx+a.yvi*taby;	
		
		//��ʾΪ��������ײ
		/*float vIgnore = Ball.stopvi;//-----�Լ�����			
		if (vain<0)//--
		{
			if (Tools.myabs(vain)>vIgnore) 
			{
				System.out.println("ignore  ");
				a.position[0] -= a.velocity[0]/_collDefinition;
				a.position[1] -= a.velocity[1]/_collDefinition;
				b.position[0] -= b.velocity[0]/_collDefinition;
				b.position[1] -= b.velocity[1]/_collDefinition;
				nabx = b.position[0]-a.position[0];
				naby = b.position[1]-a.position[1];
				//lengthΪ����ľ���
				length = (float)Math.sqrt(nabx*nabx+naby*naby);
				//���滯ʸ�����꣬n��
				nabx /= length;
				naby /= length;
				//t��
				tabx = -naby;
				taby = nabx;
				//a���n,t���ٶ�ת��
				vain = a.velocity[0]*nabx+a.velocity[1]*naby;
				vait = a.velocity[0]*tabx+a.velocity[1]*taby;
			} 
			else 
			{
			   vain = 0;
			}
		}*/
		 
		//trace(vain);
		//b���n,t���ٶ�ת��
		float vbin = bv[0]*nabx+bv[1]*naby;
		float vbit = bv[0]*tabx+bv[1]*taby;
		/*if (Tools.myabs(vbin)<vIgnore) 
		{
			vbin = 0;
		}*/
		
		//���������������m
		float m = 1f;//Ball.WEIGHT;
		
		//a����ײ���n,t���ٶ�
		float e = 0.96f;	//-----�Լ�����,��ȫ������ײ
		float vafn = ((e+1)*m*vbin+vain*(1*m-e*m))/(m+m);//Ĭ�����m����Ϊ1ʱ���ٶȹ�ʽ
		float vaft = vait;
		//b����ײ���n,t�ٶ�
		float vbfn = ((e+1)*m*vain-vbin*(1*m-e*m))/(m+m);
		float vbft = vbit;

			
		//ת����XY����ϵ���ٶ�ֵ
		float vafx,vafy,vbfx,vbfy;		
		//a���x,y���ٶ�ת��
		vafx = nabx*vafn+tabx*vaft;
		vafy = naby*vafn+taby*vaft;
		/*if (Tools.myabs(vafx)<vIgnore) {
			vafx = 0;
		}
		if (Tools.myabs(vafy)<vIgnore) {
			vafy = 0;
		}*/

		//b���n,t���ٶ�ת��
		vbfx = nabx*vbfn+tabx*vbft;
		vbfy = naby*vbfn+taby*vbft;
		/*if (Tools.myabs(vbfx)<vIgnore) {
			vbfx = 0;
		}
		if (Tools.myabs(vbfy)<vIgnore) {
			vbfy = 0;
		}*/
		
		av[0] = vafx;
		av[1] = vafy;
		bv[0] = vbfx;
		bv[1] = vbfy;
		float re[] = {av[0],av[1],bv[0],bv[1]};
		return re;		
	}	
	
}
