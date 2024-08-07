package ec.edu.uce.FabricaMusical.view;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.uce.FabricaMusical.models.Product;
import ec.edu.uce.FabricaMusical.models.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Component
public class ClienteScreen extends JFrame {

    @Autowired
    private ProductService productService;
    @Autowired
    private ApplicationContext context;

    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JButton jButtonRea;
    private JButton jButtonBorrar;
    private JButton jButtonRegresar;
    private JComboBox<String> jComboBox1;
    private JPanel titleBar;
    private JButton closeButton;
    private Point initialClick;
    private BufferedImage logoImage;
    private JLabel listLabel;
    private List<Product> products;
    private List<Product> selectedProducts;

    @Autowired
    public ClienteScreen(ProductService productService) {
        this.productService = productService;
        selectedProducts = new ArrayList<>();
        initComponents();
        loadProducts();
    }

    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nombre", "Material", "Precio"}
        ));
        jScrollPane1.setViewportView(jTable1);

        jButtonRea = new JButton("Realizar el pedido");
        jButtonBorrar = new JButton("Borrar pedido");
        jButtonRegresar = new JButton("Regresar");
        jComboBox1 = new JComboBox<>();
        listLabel = new JLabel("Mi pedido:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1400, 800));
        setResizable(false);
        setUndecorated(true);

        Font customFont = new Font("Times New Roman", Font.BOLD, 18);
        Font labelFont = new Font("Times New Roman", Font.PLAIN, 16);
        jButtonRea.setFont(customFont);
        jButtonBorrar.setFont(customFont);
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

        styleButton(jButtonRea);
        styleButton(jButtonBorrar);
        styleButton(jButtonRegresar);

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
                    int logoWidth = 130;
                    int logoHeight = 130;
                    Image scaledLogo = logoImage.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
                    g2d.drawImage(scaledLogo, 20, 620, this);
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                }
            }
        };
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        jTable1.setOpaque(false);
        jTable1.setFillsViewportHeight(true);
        jTable1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        jTable1.setForeground(Color.CYAN);
        jTable1.setBackground(new Color(20, 20, 20));
        jTable1.setGridColor(new Color(0, 128, 255));

        JTableHeader tableHeader = jTable1.getTableHeader();
        tableHeader.setBackground(new Color(0, 0, 50));
        tableHeader.setForeground(Color.CYAN);
        tableHeader.setFont(new Font("Times New Roman", Font.BOLD, 18));
        tableHeader.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));

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

        jTable1.setRowHeight(30);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(listLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 6;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(jScrollPane1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        JLabel selectLabel = new JLabel("Lista de productos:");
        selectLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        selectLabel.setForeground(Color.WHITE);
        mainPanel.add(selectLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(jComboBox1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(jButtonRea, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        mainPanel.add(jButtonBorrar, gbc);

        gbc.gridx = 2;
        gbc.gridy = 7;
        mainPanel.add(jButtonRegresar, gbc);

        add(titleBar, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.BLACK);

        jComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                String selectedProductName = (String) comboBox.getSelectedItem();
                if (selectedProductName != null) {
                    Product selectedProduct = findProductByName(selectedProductName);
                    if (selectedProduct != null) {
                        addProductToTable(selectedProduct);
                    }
                }
            }
        });

        jButtonRea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarPedido();
            }
        });

        jButtonBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearOrder();
            }
        });
    }

    private void styleButton(JButton button) {
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.CYAN);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 0, 50));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.BLACK);
            }
        });
    }

    private void loadProducts() {
        products = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getResourceAsStream("/productConfig.json")) {
            if (inputStream != null) {
                JsonNode rootNode = mapper.readTree(inputStream);
                JsonNode productsNode = rootNode.get("products");
                if (productsNode.isArray()) {
                    for (JsonNode productNode : productsNode) {
                        Product product = mapper.treeToValue(productNode, Product.class);
                        products.add(product);
                        jComboBox1.addItem(product.getName());
                    }
                }
            } else {
                System.err.println("File not found: productConfig.json");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Product findProductByName(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    private void addProductToTable(Product product) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{product.getId(), product.getName(), product.getMaterial(), product.getPrice()});
        selectedProducts.add(product);
    }

    private void realizarPedido() {
        JOptionPane.showMessageDialog(this, "Su pedido ha sido realizado con éxito", "Pedido Realizado", JOptionPane.INFORMATION_MESSAGE);

        List<Product> selectedProducts = getSelectedProducts();

        AdminScreen adminScreen = context.getBean(AdminScreen.class);
        adminScreen.updateProductTable(selectedProducts);

        adminScreen.setVisible(true);
        this.dispose();
    }

    private void clearOrder() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        selectedProducts.clear();
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }
}