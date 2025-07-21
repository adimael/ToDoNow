package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.Properties;

public class DatabaseConfigScreen extends JDialog {
    private LoginScreen.RoundedTextField dbHostField;
    private LoginScreen.RoundedTextField dbPortField;
    private LoginScreen.RoundedTextField dbNameField;
    private LoginScreen.RoundedTextField dbUserField;
    private LoginScreen.RoundedPasswordField dbPassField;
    private JLabel dbStatusLabel;
    private boolean connected = false;

    public DatabaseConfigScreen(Frame parent) {
        super(parent, "Configuração do Banco de Dados", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(440, 480);
        setLocationRelativeTo(parent);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(245, 245, 255));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(147, 69, 205), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JLabel title = new JLabel("Configuração do Banco de Dados");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(147, 69, 205));
        title.setBounds(30, 20, 350, 30);
        panel.add(title);

        // Carregar dados salvos, se existirem
        String lastHost = "";
        String lastPort = "";
        String lastDb = "";
        String lastUser = "";
        String lastPass = "";
        try {
            java.util.Properties props = new java.util.Properties();
            java.io.File configFile = new java.io.File("config.properties");
            if (configFile.exists()) {
                props.load(new java.io.FileInputStream(configFile));
                String url = props.getProperty("db.url", "");
                if (url.startsWith("jdbc:mysql://")) {
                    String rest = url.substring("jdbc:mysql://".length());
                    int idxPort = rest.indexOf(":");
                    int idxSlash = rest.indexOf("/");
                    if (idxPort > 0 && idxSlash > idxPort) {
                        lastHost = rest.substring(0, idxPort);
                        lastPort = rest.substring(idxPort + 1, idxSlash);
                        lastDb = rest.substring(idxSlash + 1);
                    } else if (idxSlash > 0) {
                        lastHost = rest.substring(0, idxSlash);
                        lastDb = rest.substring(idxSlash + 1);
                    }
                }
                lastUser = props.getProperty("db.user", "");
                lastPass = props.getProperty("db.pass", "");
            }
        } catch (Exception ignore) {}

        int inputWidth = 320;
        int inputHeight = 38;
        int inputX = 50;
        int y = 70;
        int gap = 18;

        dbHostField = new LoginScreen.RoundedTextField(20);
        dbHostField.setText(lastHost);
        dbHostField.setPlaceholder("Host (ex: localhost)");
        dbHostField.setBounds(inputX, y, inputWidth, inputHeight);
        dbHostField.setToolTipText("Host do banco de dados, ex: localhost");
        panel.add(dbHostField);
        y += inputHeight + gap;

        dbPortField = new LoginScreen.RoundedTextField(20);
        dbPortField.setText(lastPort);
        dbPortField.setPlaceholder("Porta (ex: 3306)");
        dbPortField.setBounds(inputX, y, inputWidth, inputHeight);
        dbPortField.setToolTipText("Porta do MySQL, ex: 3306");
        panel.add(dbPortField);
        y += inputHeight + gap;

        dbNameField = new LoginScreen.RoundedTextField(20);
        dbNameField.setText(lastDb);
        dbNameField.setPlaceholder("Banco (ex: todonow)");
        dbNameField.setBounds(inputX, y, inputWidth, inputHeight);
        dbNameField.setToolTipText("Nome do banco de dados, ex: todonow");
        panel.add(dbNameField);
        y += inputHeight + gap;

        dbUserField = new LoginScreen.RoundedTextField(20);
        dbUserField.setText(lastUser);
        dbUserField.setPlaceholder("Usuário (ex: root)");
        dbUserField.setBounds(inputX, y, inputWidth, inputHeight);
        dbUserField.setToolTipText("Usuário do MySQL, ex: root");
        panel.add(dbUserField);
        y += inputHeight + gap;

        dbPassField = new LoginScreen.RoundedPasswordField(20);
        dbPassField.setText(lastPass);
        dbPassField.setPlaceholder("Senha");
        dbPassField.setBounds(inputX, y, inputWidth, inputHeight);
        dbPassField.setToolTipText("Senha do MySQL");
        panel.add(dbPassField);
        y += inputHeight + gap;

        dbStatusLabel = new JLabel("Status do BD: Não conectado");
        dbStatusLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        dbStatusLabel.setForeground(Color.RED);
        dbStatusLabel.setBounds(inputX, y, inputWidth, 30);
        panel.add(dbStatusLabel);
        y += 40;

        JButton saveBtn = new JButton("Salvar e Testar Conexão");
        saveBtn.setBackground(new Color(88, 205, 69));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        saveBtn.setFocusPainted(false);
        saveBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveBtn.setBounds(inputX, y, inputWidth, 40);
        saveBtn.addActionListener((ActionEvent e) -> {
            String dbHost = dbHostField.getText();
            String dbPort = dbPortField.getText();
            String dbName = dbNameField.getText();
            String dbUser = dbUserField.getText();
            String dbPass = new String(dbPassField.getPassword());
            try {
                Properties props = new Properties();
                props.setProperty("db.url", "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName);
                props.setProperty("db.user", dbUser);
                props.setProperty("db.pass", dbPass);
                props.store(new FileOutputStream("config.properties"), null);
            } catch (Exception ex) {
                dbStatusLabel.setText("Status: Erro ao salvar configuração");
                dbStatusLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(this, "Erro ao salvar configuração do banco: " + ex.getMessage());
                return;
            }
            // Testar conexão só ao clicar no botão
            try {
                util.ConnectionFactory.getConnection().close();
                dbStatusLabel.setText("Status do BD: Conectado");
                dbStatusLabel.setForeground(new Color(0, 153, 0));
                connected = true;
                JOptionPane.showMessageDialog(this, "Conexão bem-sucedida!");
                dispose();
            } catch (Exception ex) {
                dbStatusLabel.setText("Status do BD: Erro de conexão");
                dbStatusLabel.setForeground(Color.RED);
                connected = false;
                JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco: " + ex.getMessage());
            }
        });
        panel.add(saveBtn);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    private void estilizarInput(JTextField field, String placeholder) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 240), 1, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        field.setBackground(new Color(245, 245, 255));
        field.setForeground(new Color(40, 40, 40));
        field.setToolTipText(placeholder);
    }

    public boolean isConnected() {
        return connected;
    }
} 