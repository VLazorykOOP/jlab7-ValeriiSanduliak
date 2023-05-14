import java.awt.*;

public class Manager implements Runnable {
    private static final int MANAGER_SIZE = 10;
    private static final Color MANAGER_COLOR = Color.BLUE;
    private int manager_radius = 10;
    private Point position;
    private double speed;


    public Manager(Point position, int radius, double speed) {
        this.position = position;
        this.manager_radius = radius;
        this.speed = speed;
    }

    @Override
    public void run() {
        double x_0 = position.getX();
        double y_0 = position.getY();
        double time = 0;
        while (true) {
            double x_c = x_0 + manager_radius * Math.cos(speed * time);
            double y_c = y_0 + manager_radius * Math.sin(speed * time);
            position.setLocation(x_c, y_c);
            time += 0.01;
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
