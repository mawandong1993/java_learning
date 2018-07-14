package cn.tedu.shoot;
import java.util.Random;
public class Bee extends FlyingObject implements Award{
	private int xspeed = 1;
	private int yspeed = 2;
	private int awardType;    //奖励类型(0或1)
	public Bee(){
		image = ShootGame.bee;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();
		x=rand.nextInt(ShootGame.WIDTH-this.width);
		y=-this.height;
		awardType=rand.nextInt(2);
	}
	public int getType(){  //重写奖励
		return awardType;
	}
	public void step(){
		y+=yspeed;
		x+=xspeed;
		/**控制蜜蜂左右来回碰撞*/
		if(x>=ShootGame.WIDTH-this.width){  //碰到右边框
			xspeed=-1;
		}
		if(x<=0){           //碰到左边框
			xspeed=1;
		}
	}
	/**重写，检查是否越界*/
	public boolean outOfBounds(){
		return this.y>=ShootGame.HEIGHT;
	}
}
