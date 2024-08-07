package ec.edu.uce.FabricaMusical.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Component
public class CrearNuevo extends JFrame {

    @Autowired
    private ApplicationContext context;

    private JPanel mainPanel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JButton jButtonCrear;
    private JButton jButtonRegr;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JPanel titleBar;
    private JButton closeButton;
    private Point initialClick;

    public CrearNuevo() {
        initComponents();
    }

    private void initComponents() {
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jButtonCrear = new JButton();
        jButtonRegr = new JButton();
        jTextField1 = new JTextField();
        jTextField2 = new JTextField();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1400, 800));
        setUndecorated(true);

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

        titleBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });

        titleBar.addMouseMotionListener(new java.awt.event.MouseAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
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
                    backgroundImage = ImageIO.read(getClass().getResource("/images/fondogold.jpg"));
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
        gbc.fill = GridBagConstraints.HORIZONTAL;

        jLabel1.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 36));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("INGRESE:");
        jLabel1.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(jLabel1, gbc);

        jLabel2.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 18));
        jLabel2.setText("NOMBRE:");
        jLabel2.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(jLabel2, gbc);

        jTextField1.setPreferredSize(new Dimension(300, 40));
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(jTextField1, gbc);

        jLabel3.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 18));
        jLabel3.setText("CONTRASEÑA:");
        jLabel3.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(jLabel3, gbc);

        jTextField2.setPreferredSize(new Dimension(300, 40));
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(jTextField2, gbc);

        Border buttonBorder = BorderFactory.createLineBorder(new Color(246,195,67), 2);

        jButtonRegr.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 14));
        jButtonRegr.setText("Regresar");
        jButtonRegr.setPreferredSize(new Dimension(150, 60));
        jButtonRegr.setBackground(Color.DARK_GRAY);
        jButtonRegr.setForeground(Color.WHITE);
        jButtonRegr.setBorder(buttonBorder);
        jButtonRegr.setFocusPainted(false);
        jButtonRegr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = context.getBean(Login.class);
                login.setVisible(true);
                dispose();
            }
        });

        jButtonCrear.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 14));
        jButtonCrear.setText("Crear");
        jButtonCrear.setPreferredSize(new Dimension(150, 60));
        jButtonCrear.setBackground(Color.DARK_GRAY);
        jButtonCrear.setForeground(Color.WHITE);
        jButtonCrear.setBorder(buttonBorder);
        jButtonCrear.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(jButtonCrear, gbc);


        gbc.gridy = 4;
        mainPanel.add(jButtonRegr, gbc);

        add(titleBar, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.BLACK);
    }
}
