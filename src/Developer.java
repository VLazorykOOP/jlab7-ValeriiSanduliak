import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class Developer implements Runnable {
    private static final int DEVELOPER_SIZE = 10;
    private static final Color DEVELOPER_COLOR = Color.RED;
    private int max_direction_change_time = 2;
    private Point position;
    private int direction = 0;
    private int speed;

    public Developer(Point position, int max_direction_change_time, int speed) {
        this.position = position;
        this.max_direction_change_time = max_direction_change_time;
        this.speed = speed;
    }

    @Override
    public void run() {
        Random random = new Random();
        long lastDirectionChangeTime = System.currentTimeMillis();
        while (true) {
            double x = position.getX() + speed * Math.cos(Math.toRadians(direction));
            double y = position.getY() + speed * Math.sin(Math.toRadians(direction));
            if (x < 0 || x > ManagerAndDeveloperPanel.WIDTH - DEVELOPER_SIZE) {
                direction = (180 - direction) % 360;
                x = position.getX() + speed * Math.cos(Math.toRadians(direction));
            }
            if (y < 0 || y > ManagerAndDeveloperPanel.HEIGHT - DEVELOPER_SIZE) {
                direction = (-direction) % 360;
                y = position.getY() + speed * Math.sin(Math.toRadians(direction));
            }
            position.setLocation(x, y);
            if (System.currentTimeMillis() - lastDirectionChangeTime >= max_direction_change_time * 1000) {
                direction = random.nextInt(360);
                lastDirectionChangeTime = System.currentTimeMillis();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Point getPosition() {
        return position;
    }

    public Color getColor() {
        return DEVELOPER_COLOR;
    }

    public int getSize() {
        return DEVELOPER_SIZE;
    }

}
