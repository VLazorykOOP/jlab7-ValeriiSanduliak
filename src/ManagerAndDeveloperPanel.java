import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerAndDeveloperPanel extends JPanel implements ActionListener {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    JButton dev1Button, dev2Button, man1Button, man2Button;
    Thread dev1, dev2, man1, man2;
    private Developer[] developers;
    private Manager[] managers;

    public ManagerAndDeveloperPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        developers = new Developer[2];
        developers[0] = new Developer(new Point(100, 100), 2, 10);
        developers[1] = new Developer(new Point(200, 300), 1, 10);

        managers = new Manager[2];
        managers[0] = new Manager(new Point(400, 200), 50, 10);
        managers[1] = new Manager(new Point(200, 200), 35, 10);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.GRAY);

        dev1Button = new JButton("Start developer1");
        dev2Button = new JButton("Start developer2");
        dev1Button.addActionListener(this);
        dev2Button.addActionListener(this);
        buttonPanel.add(dev1Button);
        buttonPanel.add(dev2Button);

        man1Button = new JButton("Start manager1");
        man2Button = new JButton("Start manager2");
        man1Button.addActionListener(this);
        man2Button.addActionListener(this);
        buttonPanel.add(man1Button);
        buttonPanel.add(man2Button);

        frame.add(this, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);

        Timer timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dev1Button) {
            if (dev1 == null) {
                dev1 = new Thread(developers[0]);
                dev1.start();
            }
        } else if (e.getSource() == dev2Button) {
            if (dev2 == null) {
                dev2 = new Thread(developers[1]);
                dev2.start();
            }
        } else if (e.getSource() == man1Button) {
            if (man1 == null) {
                man1 = new Thread(managers[0]);
                man1.start();
            }
        } else if (e.getSource() == man2Button) {
            if (man2 == null) {
                man2 = new Thread(managers[1]);
                man2.start();
            }
        } else {
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Developer developer : developers) {
            g.setColor(developer.getColor());
            g.fillOval((int) developer.getPosition().getX(), (int) developer.getPosition().getY(), developer.getSize(), developer.getSize());
        }

        for (Manager manager : managers) {
            g.setColor(manager.getColor());
            g.fillOval((int) manager.getPosition().getX(), (int) manager.getPosition().getY(), manager.getSize(), manager.getSize());
        }
    }

    public static void main(String[] args) {
        new ManagerAndDeveloperPanel();
    }
}
