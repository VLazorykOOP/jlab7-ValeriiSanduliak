import java.awt.*;

public class Manager implements Runnable {
    private static final int MANAGER_SIZE = 10;
    private int manager_radius = 10;
    private static final Color MANAGER_COLOR = Color.BLUE;
    private Point position;
    private double angle = 0;
    private double speed;

    public Manager(Point position, int radius, double speed) {
        this.position = position;
        this.manager_radius = radius;
        this.speed = speed;
    }

    @Override
    public void run() {
        while (true) {
            double x = position.getX() + manager_radius * Math.cos(Math.toRadians(angle));
            double y = position.getY() + manager_radius * Math.sin(Math.toRadians(angle));
            angle = (angle + speed) % 360;
            position.setLocation(x, y);
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
        return MANAGER_COLOR;
    }

    public int getSize() {
        return MANAGER_SIZE;
    }
}
