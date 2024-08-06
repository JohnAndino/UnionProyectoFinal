package ec.edu.uce.FabricaMusical.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

@Component
public class Login extends JFrame {

    @Autowired
    private ApplicationContext context;

    private String userType;

    private JButton jButtonRegis;
    private JButton jButtonIng;
    private JButton jButtonRegr;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel4;
    private JPanel mainPanel;
    private JPasswordField jPasswordField1;
    private JTextField jTextField1;
    private JPanel titleBar;
    private JButton closeButton;
    private Point initialClick;

    public Login() {
        initComponents();
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    private void initComponents() {
        jButtonRegis = new JButton();
        jButtonIng = new JButton();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jTextField1 = new JTextField();
        jLabel4 = new JLabel();
        jPasswordField1 = new JPasswordField();
        jButtonRegr = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1400, 800));
        setUndecorated(true);

        // Create a custom title bar
        titleBar = new JPanel();
        titleBar.setBackground(Color.BLACK);
        titleBar.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Login");
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

        // Title label
        jLabel1.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 36));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("INGRESE EN SU CUENTA O REGÍSTRESE:");
        jLabel1.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(jLabel1, gbc);

        // User label and text field
        jLabel4.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 18));
        jLabel4.setText("NOMBRE:");
        jLabel4.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(jLabel4, gbc);

        jTextField1.setPreferredSize(new Dimension(300, 40));
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(jTextField1, gbc);

        // Password label and password field
        jLabel2.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 18));
        jLabel2.setText("CONTRASEÑA:");
        jLabel2.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(jLabel2, gbc);

        jPasswordField1.setPreferredSize(new Dimension(300, 40));
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(jPasswordField1, gbc);

        // Buttons
        Border buttonBorder = BorderFactory.createLineBorder(new Color(246,195,67), 2);

        jButtonRegis.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 14));
        jButtonRegis.setText("Registrarse");
        jButtonRegis.setPreferredSize(new Dimension(150, 60));
        jButtonRegis.setBackground(Color.DARK_GRAY);
        jButtonRegis.setForeground(Color.WHITE);
        jButtonRegis.setBorder(buttonBorder);
        jButtonRegis.setFocusPainted(false);
        jButtonRegis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearNuevo nuevo = context.getBean(CrearNuevo.class);
                nuevo.setVisible(true);
                dispose();
            }
        });

        jButtonIng.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 14));
        jButtonIng.setText("Ingresar");
        jButtonIng.setPreferredSize(new Dimension(150, 60));
        jButtonIng.setBackground(Color.DARK_GRAY);
        jButtonIng.setForeground(Color.WHITE);
        jButtonIng.setBorder(buttonBorder);
        jButtonIng.setFocusPainted(false);
        jButtonIng.addActionListener(evt -> {
            String username = jTextField1.getText();
            String password = new String(jPasswordField1.getPassword());

            if (authenticate(username, password)) {
                if ("admin".equals(userType)) {
                    AdminScreen adminScreen = context.getBean(AdminScreen.class);
                    adminScreen.setSize(getSize());
                    adminScreen.setLocationRelativeTo(null);
                    adminScreen.setVisible(true);
                } else if ("client".equals(userType)) {
                    ClienteScreen clienteScreen = context.getBean(ClienteScreen.class);
                    clienteScreen.setSize(getSize());
                    clienteScreen.setLocationRelativeTo(null);
                    clienteScreen.setVisible(true);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas.");
            }
        });

        jButtonRegr.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 14));
        jButtonRegr.setText("Regresar");
        jButtonRegr.setPreferredSize(new Dimension(150, 60));
        jButtonRegr.setBackground(Color.DARK_GRAY);
        jButtonRegr.setForeground(Color.WHITE);
        jButtonRegr.setBorder(buttonBorder);
        jButtonRegr.setFocusPainted(false);
        jButtonRegr.addActionListener(evt -> {
            MenuPrincipal principal = context.getBean(MenuPrincipal.class);
            principal.setSize(getSize());
            principal.setLocationRelativeTo(null);
            principal.setVisible(true);
            dispose();
        });

        // Add buttons to the main panel
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        mainPanel.add(jButtonRegis, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(jButtonIng, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        mainPanel.add(jButtonRegr, gbc);

        // Add the title bar and main panel to the frame
        add(titleBar, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private boolean authenticate(String username, String password) {
        // La autenticación real no está implementada; siempre devuelve verdadero.
        return true;
    }
}
