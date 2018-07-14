package cn.tedu.shoot;
import java.util.Random;
public class Airplane extends FlyingObject implements Enemy {
	private int speed = 3; //移动的速度
	/**构造方法*/
	public Airplane(){
		image = ShootGame.ariplane;
		width = image.getWidth();
		height = image.getHeight();
		Random rand = new Random();
		x=rand.nextInt(ShootGame.WIDTH-this.width);
		y=-this.height;
	}
	public int getScore(){  //重写得分，一个敌机5分
		return 5;
	}
	// 重写step（），飞行物走一步
	public void step(){
		y+=speed;  //y+  向下走
	}
	/**重写，检查是否越界*/
	public boolean outOfBounds(){
		return this.y>=ShootGame.HEIGHT;
	}
}
