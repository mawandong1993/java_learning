package cn.tedu.shoot;
import java.util.Random;
/**子弹*/
public class Bullet extends FlyingObject{
	private int speed=3;
	public Bullet(int x,int y){
		image = ShootGame.bullet;
		width = image.getWidth();
		height = image.getHeight();
		this.x=x;   //根据英雄机坐标的到坐标
		this.y=y;
	}
	public Bullet(){
	}
	public void step(){
		y-=speed;  //y-  向下走
	}
	/**重写，检查是否越界*/
	public boolean outOfBounds(){
		return this.y<=-this.height;
	}
}
