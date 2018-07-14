package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Clock extends JFrame implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    int x, y, x0, y0, r, h, olds_x, olds_y, oldm_x, oldm_y, oldh_x, oldh_y, ss, mm, hh, old_m, old_h, ang;
    final double RAD = Math.PI / 180;

    //        BufferedImage image;
    public Clock() {
        super("Java时钟");
        setDefaultCloseOperation(3);
        setSize(400, 300);
        setBackground(Color.gray);
        setFont(new Font("宋体", Font.PLAIN, 16));
//                try {
//                        image = ImageIO.read(Clock.class.getResource("pig.png"));
//                } catch (IOException e1) {
//                        e1.printStackTrace();
//                        System.out.println("加载图片失败");
//                }
//                setIconImage(image);      设置程序图标，打开之后左上角的小图标     
        setLocation(300, 150);
        setResizable(false);
        setVisible(true);
        int delay = 1000;
        //创建一个监听事件
        ActionListener drawClock = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        };
        //创建一个时间计算器
        new Timer(delay, drawClock).start();
    }

    //实现ActionListener接口必须实现的方法
    public void paint(Graphics g) {
        //画背景图片
//                g.drawImage(image, 0, 0, null);   用于画背景图片，没有上传图片就注释掉
        //因为擦除指针的方法是让原有指针变为背景颜色，所以暂时不能设置背景图片
        Graphics2D g2D = (Graphics2D) g;
        Insets insets = getInsets();
        int L = insets.left / 2, T = insets.top / 2;
        h = getSize().height;
        g.setColor(Color.white);
        //画圆
        g2D.setStroke(new BasicStroke(4.0f));
        g.drawOval(L + 40, T + 40, h - 80, h - 80);
        r = h / 2 - 40;
        x0 = 40 + r - 5 + L;
        y0 = 40 + r - 5 - T;
        ang = 60;
        //绘制时钟上的12个汉字
        for (int i = 1; i <= 12; i++) {
            x = (int) ((r + 15) * Math.cos(RAD * ang) + x0);
            y = (int) ((r + 15) * Math.sin(RAD * ang) + y0);
            g.drawString("" + i, x, h - y);
            ang -= 30;
        }
        //获得现在时间
        Calendar now = new GregorianCalendar();
        int nowh = now.get(Calendar.HOUR_OF_DAY);
        int nowm = now.get(Calendar.MINUTE);
        int nows = now.get(Calendar.SECOND);
        int nowd = now.get(Calendar.DAY_OF_WEEK);
        char[] dd = {'日', '一', '二', '三', '四', '五', '六'};
        String st = null;
        if (nowh < 10) {
            st = "0" + nowh;
        } else {
            st = "" + nowh;
        }
        if (nowm < 10) {
            st += ":0" + nowm;
        } else {
            st += ":" + nowm;
        }
        if (nows < 10) {
            st += ":0" + nows;
        } else {
            st += ":" + nows;
        }
        st += "星期" + dd[nowd - 1];
        //在窗体上显示时间
        g.setColor(Color.pink);
        g.fillRect(L, T, 120, 30);
        g.setColor(Color.blue);
        g.drawString(st, L + 2, T + 26);
        //签名
        g.setColor(Color.WHITE);
        g.fillRect(L + 357, T + 265, 50, 20);
        g.setColor(Color.BLACK);
        g.drawString("©贾", L + 362, T + 280);
        //计算时间与度数的关系
        ss = 90 - nows * 6;
        mm = 90 - nowm * 6;
        hh = 90 - nowh * 30 - nowm / 2;
        x0 = r + 40 + L;
        y0 = r + 40 + T;
        g2D.setStroke(new BasicStroke(1.2f));
        //擦除秒针
        if (olds_x > 0) {
            g.setColor(getBackground());
            g.drawLine(x0, y0, olds_x, h - olds_y);
        } else {
            old_m = mm;
            old_h = hh;
        }
        //绘制秒针
        x = (int) (r * 0.9 * Math.cos(RAD * ss)) + x0;
        y = (int) (r * 0.9 * Math.sin(RAD * ss)) + y0 - 2 * T;
        g.setColor(Color.orange);
        g.drawLine(x0, y0, x, h - y);
        olds_x = x;
        olds_y = y;
        g2D.setStroke(new BasicStroke(2.2f));
        //擦除分针
        if (old_m != mm) {
            g.setColor(getBackground());
            g.drawLine(x0, y0, oldm_x, h - oldm_y);
        }
        //绘制分针
        x = (int) (r * 0.7 * Math.cos(RAD * mm)) + x0;
        y = (int) (r * 0.7 * Math.sin(RAD * mm)) + y0 - 2 * T;
        g.setColor(Color.green);
        g.drawLine(x0, y0, x, h - y);
        oldm_x = x;
        oldm_y = y;
        old_m = mm;
        g2D.setStroke(new BasicStroke(3.4f));
        //擦除时针
        if (old_h != hh) {
            g.setColor(getBackground());
            g.drawLine(x0, y0, oldh_x, h - oldh_y);
        }
        //绘制时针
        x = (int) (r * 0.5 * Math.cos(RAD * hh)) + x0;
        y = (int) (r * 0.5 * Math.sin(RAD * hh)) + y0 - 2 * T;
        g.setColor(Color.red);
        g.drawLine(x0, y0, x, h - y);
        oldh_x = x;
        oldh_y = y;
        old_h = hh;
    }

    public static void main(String[] args) {
        Clock c = new Clock();
    }

    public void actionPerformed(ActionEvent e) {

    }
}
