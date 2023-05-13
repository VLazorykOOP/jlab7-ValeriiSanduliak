import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenagerAndDeveloperPanel extends JPanel implements ActionListener {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Developer[] developers;
    private Manager[] managers;

    public MenagerAndDeveloperPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        developers = new Developer[2];
        developers[0] = new Developer(new Point(100, 100), 2,10);
        developers[1] = new Developer(new Point(200, 200), 1,10);

        managers = new Manager[2];
        managers[0] = new Manager(new Point(400, 200), 15, 10);
        managers[1] = new Manager(new Point(200, 200), 15, 10);

        for (Developer developer : developers) {
            new Thread(developer).start();
        }
        for (Manager manager : managers) {
            new Thread(manager).start();
        }

        Timer timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Developer developer : developers) {
            g.setColor(developer.getColor());
            g.fillOval((int) developer.getPosition().getX(), (int) developer.getPosition().getY(),
                    developer.getSize(), developer.getSize());
        }

        for (Manager manager : managers) {
            g.setColor(manager.getColor());
            g.fillOval((int) manager.getPosition().getX(), (int) manager.getPosition().getY(),
                    manager.getSize(), manager.getSize());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        MenagerAndDeveloperPanel panel = new MenagerAndDeveloperPanel();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
