package cn.tedu.shoot;
/**英雄机*/
import java.awt.image.BufferedImage;
import java.util.Random;
public class Hero extends FlyingObject{
	private int doubleFire;     //火力值
	private int life;              //生命值 
	private BufferedImage[] images={};  //图片数组
	private int index;                           //协助图片切换
	public Hero(){
		image = ShootGame.hero0;
		width = image.getWidth();
		height = image.getHeight();
		x=150;     //英雄机初始位置
		y=400;
		doubleFire=0;
		life=3;
		images=new BufferedImage[]{ShootGame.hero0,ShootGame.hero1};
		index=0;
	}
	public void step(){
		index++;
		int a=index/10;
		int b=a%images.length;
		image=images[b];
	}
	// 发射子弹
	public Bullet[] shoot(){
		int xstep=this.width/4;      // 1/4 英雄机
		int ystep=20;                   //固定y值
		if(doubleFire>0){
			Bullet[] bs=new Bullet[2];  //双倍火力
			bs[0]=new Bullet(this.x+1*xstep,this.y-ystep);
			bs[1]=new Bullet(this.x+3*xstep,this.y-ystep);
			doubleFire-=2;
			return bs;
		}else{
			Bullet[] bs=new Bullet[1];  //单倍火力
			bs[0]=new Bullet(this.x+2*xstep,this.y-ystep);
			return bs;
		}
	}
	/**英雄机随着鼠标动*/
	public void moveTo(int x,int y){
		this.x=x-this.width/2;  // 英雄机x=鼠标x-英雄机宽度一半
		this.y=y-this.height/2;
	}
	/**重写，检查是否越界*/
	public boolean outOfBounds(){
		return false; //永不越界
	}
	
	public void addLife(){
		life++;
	}
	
	public int getLife(){
		return life;
	}
	/**减命*/
	public void subtractlife(){
		life--;
	}
	
	public void clearDoubleFire(){
		doubleFire=0;
	}
	public void addDoubleFire(){
		doubleFire+=40;
	}
	
	public boolean hit(FlyingObject other){
		int x1=other.x-this.width/2;
		int x2=other.x+other.width+this.width/2;
		int y1=other.y-this.height/2;
		int y2=other.y+other.height+this.height/2;
		int x=this.x+this.width/2;
		int y=this.y+this.height/2;
		return x>=x1&&x<=x2
				&&
				y>=y1&&y<=y2;
	}
}
