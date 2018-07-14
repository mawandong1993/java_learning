package cn.tedu.shoot;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Arrays;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
/**主程序*/
public class ShootGame extends JPanel {
	public static final int WIDTH =400; //窗口宽
	public static final int HEIGHT=654; //窗口高 
	
	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage pause;
	public static BufferedImage gameover;
	public static BufferedImage ariplane;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	
	static{ //初始化静态图片
		try{
			background = ImageIO.read(ShootGame.class.getResource("background.png"));
			start= ImageIO.read(ShootGame.class.getResource("start.png"));
			pause = ImageIO.read(ShootGame.class.getResource("pause.png"));
			gameover = ImageIO.read(ShootGame.class.getResource("gameover.png"));
			ariplane = ImageIO.read(ShootGame.class.getResource("airplane.png"));
			bee = ImageIO.read(ShootGame.class.getResource("bee.png"));
			bullet = ImageIO.read(ShootGame.class.getResource("bullet.png"));
			hero0 = ImageIO.read(ShootGame.class.getResource("hero0.png"));
			hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private Hero hero=new Hero();    //英雄机对象
	private FlyingObject[] flyings={};   //敌机数组
	private Bullet[] bullets ={};            //子弹数组
	
	public static final int START=0;
	public static final int RUNNING=1;
	public static final int PAUSE=2;
	public static final int GAME_OVER=3;
	private int state=START;
	
	
//	ShootGame(){
//		flyings = new FlyingObject[2];
//		flyings[0]=new Airplane();
//		flyings[1]=new Bee();
//		bullets=new Bullet[1];
//		bullets[0]=new Bullet();
//	}
	//创建敌人对象，敌机或者小蜜蜂
	public FlyingObject nextOne(){
		Random rand =new Random();
		int type=rand.nextInt(20);   //0-19
		if(type<4){    //0-3
			return new Bee();
		}else{            //4-19
			return new Airplane();
		}
	}
	
	int flyEnteredIndex=0;    //敌人入场计数
	//敌人入场
	public void enterAction(){  //10毫秒走一次
		flyEnteredIndex++;         //每10毫秒增1
		if(flyEnteredIndex%40==0){ //400毫秒执行一次
			FlyingObject obj = nextOne();  //调用创建敌人方法
			flyings=Arrays.copyOf(flyings, flyings.length+1);  //扩容
			flyings[flyings.length-1]=obj;                     //放到最后一个
		}
	}
	
	//飞行物（敌机、小蜜蜂、英雄机），走步,10毫秒一次
	public void stepAction(){
		hero.step();
		for(int i=0;i<flyings.length;i++){
			flyings[i].step();
		}
		for(int i=0;i<bullets.length;i++){
			bullets[i].step();
		}
	}
	/**子弹入场，英雄机发射子弹*/
	int shootIndex=0;
	public void shootAction(){
		shootIndex++;   //10毫秒加1
		if(shootIndex%30==0){  //300毫秒发射一次
			Bullet[] bs=hero.shoot(); //获取子弹数组
			bullets=Arrays.copyOf(bullets,bullets.length+bs.length);  //扩容
			System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length);   // 数组的追加
		}
	}
	//删除越界飞行物
	public void outOfBoundsAction(){  // 10毫秒
		/**删除越界敌机(敌机，小蜜蜂)*/
		int index=0;
		FlyingObject[] flyingLives=new FlyingObject[flyings.length];
		for(int i=0;i<flyings.length;i++){
			FlyingObject f=flyings[i];
			if(!f.outOfBounds()){
				flyingLives[index]=f;  //将不越界对象放到活着的数组中
				index++;    //不越界数组下标价+1
			}
		}
		flyings=Arrays.copyOf(flyingLives, index);
		/**删除越界子弹*/
		 index=0;
		Bullet[] bulletLives=new Bullet[bullets.length];
		for(int i=0;i<bullets.length;i++){
			Bullet b=bullets[i];
			if(!b.outOfBounds()){
				bulletLives[index]=b;  //将不越界对象放到活着的数组中
				index++;    //不越界数组下标价+1
			}
		}
		bullets= Arrays.copyOf(bulletLives, index);
	}
	/**所有子弹与敌人碰撞，10毫秒检查一次*/
	public void bangAction(){ 
		for(int i=0;i<bullets.length;i++){
			Bullet b=bullets[i];
			bang(b);
		}
	}
	/**一个子弹与所有敌人的碰撞*/
	int score=0;// 玩家得分
	public void bang(Bullet b){
		int index=-1;
		for(int i=0;i<flyings.length;i++){
			FlyingObject f=flyings[i];
			if(f.shootBy(b)){
				
//				zidan(b);
				
				index=i;
				break;
			}
		}
		if(index!=-1){
			FlyingObject one = flyings[index];
			if(one instanceof Enemy){
				Enemy e=(Enemy) one;
				score+=e.getScore();
			}
			if(one instanceof Award){
				Award a=(Award)one;
				int type=a.getType();
				switch(type){
				case Award.DOUBLE_FIRE:
					hero.addDoubleFire();
					break;
				case Award.LIFE:
				    hero.addLife();
				    break;
				}
			}
			/**删除敌人对象*/
			FlyingObject t=flyings[index];
			flyings[index]=flyings[flyings.length-1];
			flyings[flyings.length-1]=t;
			flyings=Arrays.copyOf(flyings, flyings.length-1);
		}
	}
	/**删除子弹*/
	public void zidan(Bullet b){
		Bullet t=b;
		b=bullets[flyings.length-1];
     	bullets[bullets.length-1]=t;
		bullets=Arrays.copyOf(bullets, bullets.length-1);
	}
	
	/**英雄机被击中*/
	public void hitAction(){
		for(int i=0;i<flyings.length;i++){
		FlyingObject f=flyings[i];
		if(hero.hit(f)){
			hero.subtractlife();
			hero.clearDoubleFire();
			FlyingObject t=flyings[i];
			flyings[i]=flyings[flyings.length-1];
			flyings[flyings.length-1]=t;
			flyings=Arrays.copyOf(flyings, flyings.length-1);
		}
		}
	}
	
	public void checkGameOverAction(){
		if(hero.getLife()<=0){ //游戏结束
			state=GAME_OVER;
		}
	}
	/**程序启动执行*/
	public void action(){
		MouseAdapter l=new MouseAdapter(){
			// 处理鼠标移动事件
		     public void mouseMoved(MouseEvent e){
		    	 if(state==RUNNING){
		    	 int x=e.getX();  //获取鼠标x，y
		    	 int y=e.getY();
		    	 hero.moveTo(x, y);
		    	 }
		     }
		     /**鼠标点击事件*/
		     public void mouseClicked(MouseEvent e){
		    	 switch(state){
		    	 case  START:
		    		 state=RUNNING;
		    		 break;
		    	 case GAME_OVER:
		    		 score=0;
		    		 hero=new Hero();
		    		 flyings=new FlyingObject[0];
		    		 bullets=new Bullet[0];
		    		 state=START;
		    		 break;
		    	 }
		     }
		     public void mouseExited(MouseEvent e){
		    	 if(state==RUNNING){
		    		 state=PAUSE;
		    	 }
		     }
		     public void mouseEntered(MouseEvent e){
		    	 if(state==PAUSE){
		    		 state=RUNNING;
		    	 }
		     }
		};
		this.addMouseListener(l); // 处理鼠标
		this.addMouseMotionListener(l); // 处理鼠标
		
		Timer timer =new Timer();
		int intervel=10; //时间间隔，以毫秒为单位
		timer.schedule(new TimerTask(){
			public void run(){   // 每10毫秒走一次
				if(state==RUNNING){
				enterAction();  // 敌人入场
				stepAction(); //飞行物（敌机、小蜜蜂、英雄机），走步
				shootAction(); //子弹入场
				outOfBoundsAction();//处理越界对象
				bangAction(); //子弹与敌人碰撞
				hitAction();  // 英雄机被击中、
				checkGameOverAction();
			    }
				repaint();   //重画(调用paint)
			}
		},intervel,intervel);  //定时
		
	}
	/**重写paint() g画笔*/
	public void paint(Graphics g){
		g.drawImage(background,0,0,null);
		paintHero(g);
		paintFlyingObject(g);
		paintBullets(g);
		paintScoreAndLife(g);
		paintState(g);
	}
	/**画英雄机对象*/
	public void paintHero(Graphics g){
		g.drawImage(hero.image,hero.x,hero.y,null);
	}
	/**画敌人（敌机、小蜜蜂）对象*/
	public void paintFlyingObject(Graphics g){
		for(int i=0;i<flyings.length;i++){  //遍历敌人数组，获得对象（敌机，小蜜蜂）
			FlyingObject f = flyings[i];
			g.drawImage(f.image,f.x,f.y,null);
		}
	}
	/**画子弹*/
	public void paintBullets(Graphics g){
		g.setColor(new Color(0xFF0000));
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,24));
		for(int i=0;i<bullets.length;i++){
			Bullet b = bullets[i];
			g.drawImage(b.image,b.x,b.y,null);
		}
	}
	/**画分 画命*/
	public void paintScoreAndLife(Graphics g){
		g.drawString("SCORE:"+score, 10, 25);
		g.drawString("LIFE:"+hero.getLife(), 10, 45);
	}
	/**画状态*/
	public void paintState(Graphics g){
		switch(state){
		case START:
			g.drawImage(start, 0, 0, null);
			break;
		case PAUSE:
			g.drawImage(pause, 0, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameover, 0, 0, null);
			break;
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("fly-mwd");              //创建窗口
		ShootGame game = new ShootGame();      //创建面板
		frame.add(game);                            //将面板添加到窗口里
		frame.setSize(WIDTH,HEIGHT);     //窗口大小
		frame.setAlwaysOnTop(true);        //设置一直在最上面
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置默认关闭
		frame.setLocationRelativeTo(null);     //设置窗口居中
		frame.setVisible(true);               //1、设置窗口可见   2、尽快调用paint()方法
		game.action();      // 启动程序执行
		
		
	}

}
