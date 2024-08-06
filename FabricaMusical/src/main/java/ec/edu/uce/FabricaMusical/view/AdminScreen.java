package ec.edu.uce.FabricaMusical.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

@org.springframework.stereotype.Component
public class AdminScreen extends JFrame {

    @Autowired
    private ApplicationContext context;

    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JButton jButtonFabricar; //SI
    private JButton jButtonRechazar; //SI
    private JButton jButtonRegresar; //SI
    private JPanel titleBar;
    private JButton closeButton;
    private Point initialClick;
    private BufferedImage logoImage;
    private JLabel listLabel;

    public AdminScreen() {
        initComponents();
    }

    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable(new Object[][]{
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},


        }, new String[]{
                "Producto", "Descripción", "Title 3", "Title 4"
        });
        jScrollPane1.setViewportView(jTable1);

        jButtonFabricar = new JButton("Fabricar");
        jButtonRechazar = new JButton("Rechazar");
        jButtonRegresar = new JButton("Regresar");
        listLabel = new JLabel("Lista de productos a fabricar:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1400, 800));
        setResizable(false);
        setUndecorated(true); // Eliminar barra de título

        // Set custom font
        Font customFont = new Font("Times New Roman", Font.BOLD, 18);
        Font labelFont = new Font("Times New Roman", Font.PLAIN, 16);

        jButtonFabricar.setFont(customFont);
        jButtonRechazar.setFont(customFont);
        jButtonRegresar.setFont(customFont);
        jButtonRegresar.addActionListener(evt -> {
            Login login = context.getBean(Login.class);
            login.setSize(getSize());
            login.setLocationRelativeTo(null);
            login.setVisible(true);
            dispose();
        });
        listLabel.setFont(customFont);
        listLabel.setForeground(Color.WHITE);

        // Style buttons
        styleButton(jButtonFabricar);
        styleButton(jButtonRechazar);
        styleButton(jButtonRegresar);

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

        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            private BufferedImage backgroundImage;
            private BufferedImage logoImage;

            {
                try {
                    backgroundImage = ImageIO.read(getClass().getResource("/images/fondo1.jpg"));
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
                    int logoWidth = 130;  // Desired width of the logo
                    int logoHeight = 130; // Desired height of the logo
                    Image scaledLogo = logoImage.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f)); // 80% transparency
                    g2d.drawImage(scaledLogo, 20, 620, this);
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // Reset to full opacity
                }
            }
        };
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Set up table appearance
        jTable1.setOpaque(false); // Make sure table background is transparent
        jTable1.setFillsViewportHeight(true);
        jTable1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        jTable1.setForeground(Color.CYAN); // Text color for table
        jTable1.setBackground(new Color(20, 20, 20)); // Dark background color for table
        jTable1.setGridColor(new Color(0, 128, 255)); // Grid color for table

        // Style the table header
        JTableHeader tableHeader = jTable1.getTableHeader();
        tableHeader.setBackground(new Color(0, 0, 50)); // Dark background for header
        tableHeader.setForeground(Color.CYAN); // Light text color for header
        tableHeader.setFont(new Font("Times New Roman", Font.BOLD, 18));
        tableHeader.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));

        // Set up custom renderer for alternating row colors
        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    cell.setBackground(new Color(0, 50, 100));
                    cell.setForeground(Color.WHITE);
                } else {
                    cell.setBackground(row % 2 == 0 ? new Color(30, 30, 30) : new Color(40, 40, 40));
                    cell.setForeground(Color.CYAN);
                }
                return cell;
            }
        });

        // Reduce the row height
        jTable1.setRowHeight(30);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add the list label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(listLabel, gbc);

        // Add table
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        mainPanel.add(jScrollPane1, gbc);

        // Add buttons
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(jButtonFabricar, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        mainPanel.add(jButtonRechazar, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        mainPanel.add(jButtonRegresar, gbc);

        // Add the title bar and main panel to the frame
        add(titleBar, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null); // Center the frame

        // Set the JFrame background color to black
        getContentPane().setBackground(Color.BLACK);
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

}
