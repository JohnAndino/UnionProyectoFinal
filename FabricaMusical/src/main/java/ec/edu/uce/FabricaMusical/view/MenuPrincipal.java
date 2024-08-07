package ec.edu.uce.FabricaMusical.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Component
public class MenuPrincipal extends JFrame {

    @Autowired
    private ApplicationContext context;

    private JButton jButtonAdmin;
    private JButton jButtonUser;
    private JPanel titleBar;
    private JButton closeButton;
    private Point initialClick;
    private JPanel mainPanel;

    public MenuPrincipal() {
        initComponents();
    }

    private void initComponents() {
        jButtonAdmin = new JButton();
        jButtonUser = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1400, 800));
        setUndecorated(true);
        setTitle("");

        // Create a custom title bar
        titleBar = new JPanel();
        titleBar.setBackground(Color.BLACK);
        titleBar.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("");
        titleLabel.setForeground(Color.WHITE);
        titleBar.add(titleLabel, BorderLayout.WEST);

        closeButton = new JButton("X");
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(Color.BLACK);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> System.exit(0));
        titleBar.add(closeButton, BorderLayout.EAST);

        titleBar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });

        titleBar.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int thisX = getLocation().x;
                int thisY = getLocation().y;

                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                setLocation(X, Y);
            }
        });

        mainPanel = new JPanel(new GridBagLayout()) {
            private BufferedImage backgroundImage;
            private BufferedImage logoImage;

            {
                try {
                    backgroundImage = ImageIO.read(getClass().getResource("/images/fondo.jpg"));
                    logoImage = ImageIO.read(getClass().getResource("/images/logom.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    int width = getWidth();
                    int height = getHeight();
                    Image scaledImage = backgroundImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    g.drawImage(scaledImage, 0, 0, this);
                }
                if (logoImage != null) {
                    int logoWidth = 130;
                    int logoHeight = 130;
                    Image scaledLogo = logoImage.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
                    g2d.drawImage(scaledLogo, 20, getHeight() - logoHeight - 20, this);
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                }
            }
        };
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel greetingLabel = new JLabel("Saludos, seleccione por favor:");
        greetingLabel.setForeground(Color.WHITE);
        greetingLabel.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(greetingLabel, gbc);

        Dimension buttonSize = new Dimension(200, 200);
        jButtonAdmin.setPreferredSize(buttonSize);
        jButtonUser.setPreferredSize(buttonSize);

        styleButton(jButtonAdmin, "/images/admin.png");
        styleButton(jButtonUser, "/images/user.jpg");

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(jButtonAdmin, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(jButtonUser, gbc);

        jButtonAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Login login = context.getBean(Login.class);
                login.setUserType("admin");
                login.setSize(getSize());
                login.setLocationRelativeTo(null);
                login.setVisible(true);
                dispose();
            }
        });

        jButtonUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Login login = context.getBean(Login.class);
                login.setUserType("client");
                login.setSize(getSize());
                login.setLocationRelativeTo(null);
                login.setVisible(true);
                dispose();
            }
        });

        add(titleBar, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.BLACK);
    }

    private void styleButton(JButton button, String imagePath) {
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        button.setFocusPainted(false);
        try {
            Image img = ImageIO.read(getClass().getResource(imagePath));
            Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
