package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.UserController;
import java.io.*;
import java.util.UUID;

public class LoginScreen extends JDialog {
    private boolean loginSuccessful = false;
    private JTextField userField;
    private JPasswordField passField;
    private String usuarioLogado = null;
    private JTextField dbHostField;
    private JTextField dbPortField;
    private JTextField dbNameField;
    private JTextField dbUserField;
    private JPasswordField dbPassField;
    private JLabel dbStatusLabel;
    private JButton dbConfigBtn;

    public LoginScreen(Frame parent) {
        super(parent, "ToDoNow - Login", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(900, 560);
        setLocationRelativeTo(parent);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        // Painel esquerdo com gradiente e título estilizado
        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(106, 130, 251), getWidth(), getHeight(), new Color(147, 69, 205));
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        leftPanel.setPreferredSize(new Dimension(350, 480));
        leftPanel.setLayout(new GridBagLayout());
        JLabel title = new JLabel("ToDoNow");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 48));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JLabel subtitle = new JLabel("Gestão de tarefas");
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        JPanel logoPanel = new JPanel();
        logoPanel.setOpaque(false);
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        logoPanel.add(title);
        logoPanel.add(subtitle);

        // Usar BoxLayout vertical no leftPanel
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Painel superior para status e botão
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        dbStatusLabel = new JLabel("Status do BD: Não conectado");
        dbStatusLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        dbStatusLabel.setForeground(Color.RED);
        dbStatusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dbStatusLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        topPanel.add(dbStatusLabel);

        dbConfigBtn = new JButton("Configurar Banco de Dados");
        dbConfigBtn.setBackground(new Color(88, 105, 205));
        dbConfigBtn.setForeground(Color.WHITE);
        dbConfigBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        dbConfigBtn.setFocusPainted(false);
        dbConfigBtn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(147, 69, 205), 2, true),
            BorderFactory.createEmptyBorder(10, 30, 10, 30)));
        dbConfigBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dbConfigBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        dbConfigBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dbConfigBtn.setBackground(new Color(147, 69, 205));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dbConfigBtn.setBackground(new Color(88, 105, 205));
            }
        });
        topPanel.add(dbConfigBtn);

        leftPanel.add(topPanel);
        leftPanel.add(Box.createVerticalGlue());
        // Centralizar logoPanel (ToDoNow e Gestão de tarefas) no leftPanel
        leftPanel.remove(logoPanel); // caso já tenha sido adicionado
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(logoPanel);
        leftPanel.add(Box.createVerticalGlue());
        logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Painel direito (formulário) com sombra
        JPanel rightPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0,0,0,30));
                g2.fillRoundRect(15, 15, getWidth()-30, getHeight()-30, 30, 30);
            }
        };
        rightPanel.setOpaque(false);
        rightPanel.setLayout(null);
        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBounds(30, 20, 480, 480);
        formPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        formPanel.setOpaque(true);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230,230,230), 1),
            BorderFactory.createEmptyBorder(0,0,0,0)));

        JLabel welcome = new JLabel("Tenha o controle das suas tarefas!");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 22));
        welcome.setForeground(new Color(147, 69, 205));
        welcome.setBounds(40, 20, 400, 30);
        formPanel.add(welcome);

        JLabel bemvindo = new JLabel("BEM-VINDO (A) ao sistema de gestão");
        bemvindo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        bemvindo.setBounds(40, 55, 350, 20);
        formPanel.add(bemvindo);

        JLabel loginTitle = new JLabel("Faça Login");
        loginTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        loginTitle.setBounds(40, 80, 200, 30);
        formPanel.add(loginTitle);

        // Remover ícones dos inputs
        // JLabel userIcon = ...
        // JLabel passIcon = ...
        // ... não adicionar userIcon e passIcon ao formPanel ...
        // Substituir os campos por componentes customizados
        userField = new RoundedTextField(20);
        ((RoundedTextField)userField).setPlaceholder("Digite seu usuário");
        userField.setBounds(80, 125, 320, 38);
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        userField.setForeground(new Color(40, 40, 40));
        userField.setCaretColor(new Color(147, 69, 205));
        formPanel.add(userField);

        // Input senha com ícone
        passField = new RoundedPasswordField(20);
        ((RoundedPasswordField)passField).setPlaceholder("Digite sua senha");
        passField.setBounds(80, 180, 320, 38);
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passField.setForeground(new Color(40, 40, 40));
        passField.setCaretColor(new Color(147, 69, 205));
        formPanel.add(passField);

        JCheckBox keepConnected = new JCheckBox("Mantenha me conectado");
        keepConnected.setBackground(Color.WHITE);
        keepConnected.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        keepConnected.setBounds(80, 230, 200, 20);
        keepConnected.setFocusPainted(false);
        formPanel.add(keepConnected);

        JButton loginBtn = new JButton("Fazer login");
        loginBtn.setBackground(new Color(88, 205, 69));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginBtn.setBounds(80, 340, 150, 40);
        loginBtn.setFocusPainted(false);
        loginBtn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(88, 205, 69), 2, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Efeito hover
        loginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginBtn.setBackground(new Color(70, 180, 60));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginBtn.setBackground(new Color(88, 205, 69));
            }
        });
        formPanel.add(loginBtn);

        // Remover botão 'Esqueci minha senha!'
        // JButton forgotBtn = new JButton("Esqueci minha senha!");
        // forgotBtn.setBackground(Color.WHITE);
        // forgotBtn.setForeground(new Color(88, 105, 205));
        // forgotBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        // forgotBtn.setBorderPainted(false);
        // forgotBtn.setBounds(240, 340, 160, 40);
        // forgotBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // forgotBtn.addMouseListener(new java.awt.event.MouseAdapter() {
        //     public void mouseEntered(java.awt.event.MouseEvent evt) {
        //         forgotBtn.setForeground(new Color(147, 69, 205));
        //     }
        //     public void mouseExited(java.awt.event.MouseEvent evt) {
        //         forgotBtn.setForeground(new Color(88, 105, 205));
        //     }
        // });
        // formPanel.add(forgotBtn);

        JButton registerBtn = new JButton("Cadastrar");
        registerBtn.setBackground(Color.WHITE);
        registerBtn.setForeground(new Color(88, 105, 205));
        registerBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        registerBtn.setBorderPainted(false);
        registerBtn.setBounds(80, 390, 320, 40);
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Efeito hover
        registerBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerBtn.setForeground(new Color(147, 69, 205));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerBtn.setForeground(new Color(88, 105, 205));
            }
        });
        formPanel.add(registerBtn);

        // Mover botão 'Esqueci minha senha!' um pouco para cima
        // forgotBtn.setBounds(240, 340, 160, 40);

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterScreen registerScreen = new RegisterScreen((Frame) getParent());
                registerScreen.setVisible(true);
                if (registerScreen.isRegisterSuccessful()) {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Cadastro realizado com sucesso! Faça login.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JLabel rodape = new JLabel("©2023 Desenvolvido por Adimael.");
        rodape.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        rodape.setForeground(new Color(120, 120, 120));
        rodape.setBounds(80, 440, 350, 20);
        formPanel.add(rodape);

        // Botão e status do banco no topo do painel azul
        // dbConfigBtn = new JButton("Configurar Banco de Dados");
        // dbConfigBtn.setBackground(new Color(88, 105, 205));
        // dbConfigBtn.setForeground(Color.WHITE);
        // dbConfigBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        // dbConfigBtn.setFocusPainted(false);
        // dbConfigBtn.setBorder(BorderFactory.createCompoundBorder(
        //     BorderFactory.createLineBorder(new Color(147, 69, 205), 2, true),
        //     BorderFactory.createEmptyBorder(10, 30, 10, 30)));
        // dbConfigBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // // Status do banco no topo do painel azul
        // dbStatusLabel = new JLabel("Status do BD: Não conectado");
        // dbStatusLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        // dbStatusLabel.setForeground(Color.RED);
        // dbStatusLabel.setBounds(30, 20, 260, 30);
        // leftPanel.add(dbStatusLabel);

        // // Botão logo abaixo do status
        // dbConfigBtn.setBounds(30, 55, 260, 40);
        // leftPanel.add(dbConfigBtn);

        // Os textos centrais (title, subtitle) continuam centralizados no painel
        // Checar status do banco ao abrir
        boolean dbOk = false;
        try {
            util.ConnectionFactory.getConnection().close();
            dbOk = true;
        } catch (Exception ex) {
            dbOk = false;
        }
        if (dbOk) {
            dbStatusLabel.setText("Status do BD: Conectado");
            dbStatusLabel.setForeground(new Color(0, 220, 80));
            dbConfigBtn.setEnabled(false);
            dbConfigBtn.setBackground(new Color(180, 180, 180));
            dbConfigBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } else {
            dbStatusLabel.setText("Status do BD: Não conectado");
            dbStatusLabel.setForeground(Color.RED);
            dbConfigBtn.setEnabled(true);
        }
        dbConfigBtn.addActionListener(e -> {
            DatabaseConfigScreen configScreen = new DatabaseConfigScreen((Frame) getParent());
            configScreen.setVisible(true);
            // Após fechar, atualizar status
            boolean dbOk2 = false;
            try {
                util.ConnectionFactory.getConnection().close();
                dbOk2 = true;
            } catch (Exception ex2) {
                dbOk2 = false;
            }
            if (dbOk2) {
                dbStatusLabel.setText("Status do BD: Conectado");
                dbStatusLabel.setForeground(new Color(0, 220, 80));
                dbConfigBtn.setEnabled(false);
                dbConfigBtn.setBackground(new Color(180, 180, 180));
                dbConfigBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } else {
                dbStatusLabel.setText("Status do BD: Não conectado");
                dbStatusLabel.setForeground(Color.RED);
                dbConfigBtn.setEnabled(true);
                dbConfigBtn.setBackground(new Color(88, 105, 205));
                dbConfigBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Testar conexão ANTES de tentar login
                boolean dbOk = false;
                try {
                    util.ConnectionFactory.getConnection().close();
                    dbOk = true;
                } catch (Exception ex) {
                    dbStatusLabel.setText("Status: Erro de conexão");
                    dbStatusLabel.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(LoginScreen.this, "Erro ao conectar ao banco: " + ex.getMessage());
                    return;
                }
                dbStatusLabel.setText("Status: Conectado");
                dbStatusLabel.setForeground(new Color(0, 153, 0));
                String user = userField.getText();
                String pass = new String(passField.getPassword());
                UserController userController = new UserController();
                System.out.println("Tentando login para usuário: " + user);
                if (userController.autenticar(user, pass)) {
                    System.out.println("Login bem-sucedido!");
                    loginSuccessful = true;
                    usuarioLogado = user;
                    if (keepConnected.isSelected()) {
                        try {
                            String token = userController.gerarSalvarTokenAutologin(user);
                            try (FileWriter fw = new FileWriter("user_autologin.txt")) {
                                fw.write(token);
                            }
                        } catch (Exception ex) {
                            System.out.println("Erro ao salvar autologin: " + ex.getMessage());
                        }
                    } else {
                        new File("user_autologin.txt").delete();
                        userController.limparTokenAutologin(user);
                    }
                    dispose();
                    // ABRIR A TELA PRINCIPAL APÓS LOGIN
                    java.awt.EventQueue.invokeLater(() -> {
                        try {
                            System.out.println("Abrindo MainScreen...");
                            new MainScreen().setVisible(true);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Erro ao abrir tela principal: " + ex.getMessage());
                        }
                    });
                } else {
                    System.out.println("Login falhou!");
                    JOptionPane.showMessageDialog(LoginScreen.this, "Usuário ou senha inválidos!", "Erro de login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        rightPanel.add(formPanel);

        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    // Componentes customizados para inputs modernos
    static class RoundedTextField extends JTextField {
        private String placeholder = "";
        public RoundedTextField(int columns) { super(columns); setOpaque(false); setBorder(BorderFactory.createEmptyBorder()); }
        public void setPlaceholder(String text) { this.placeholder = text; }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(245, 245, 255));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 28, 28);
            g2.setColor(new Color(220, 220, 240));
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 28, 28);
            if (isFocusOwner()) {
                g2.setColor(new Color(147, 69, 205));
                g2.setStroke(new BasicStroke(2.5f));
                g2.drawRoundRect(1, 1, getWidth()-3, getHeight()-3, 26, 26);
            }
            // Placeholder
            if (getText().isEmpty() && placeholder != null) {
                g2.setFont(getFont().deriveFont(Font.ITALIC));
                g2.setColor(new Color(150, 150, 180));
                int x = 48; // espaço do ícone + padding
                int y = getHeight() / 2 + getFont().getSize() / 2 - 3;
                g2.drawString(placeholder, x, y);
            }
            g2.dispose();
            super.paintComponent(g);
        }
        @Override
        public Insets getInsets() { return new Insets(10, 38, 10, 10); }
    }
    static class RoundedPasswordField extends JPasswordField {
        private String placeholder = "";
        public RoundedPasswordField(int columns) { super(columns); setOpaque(false); setBorder(BorderFactory.createEmptyBorder()); }
        public void setPlaceholder(String text) { this.placeholder = text; }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(245, 245, 255));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 28, 28);
            g2.setColor(new Color(220, 220, 240));
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 28, 28);
            if (isFocusOwner()) {
                g2.setColor(new Color(147, 69, 205));
                g2.setStroke(new BasicStroke(2.5f));
                g2.drawRoundRect(1, 1, getWidth()-3, getHeight()-3, 26, 26);
            }
            // Placeholder
            if (getPassword().length == 0 && placeholder != null) {
                g2.setFont(getFont().deriveFont(Font.ITALIC));
                g2.setColor(new Color(150, 150, 180));
                int x = 48; // espaço do ícone + padding
                int y = getHeight() / 2 + getFont().getSize() / 2 - 3;
                g2.drawString(placeholder, x, y);
            }
            g2.dispose();
            super.paintComponent(g);
        }
        @Override
        public Insets getInsets() { return new Insets(10, 38, 10, 10); }
    }

    public boolean showLoginDialog() {
        // Autologin seguro por token
        try (BufferedReader br = new BufferedReader(new FileReader("user_autologin.txt"))) {
            String token = br.readLine();
            if (token != null && !token.isEmpty()) {
                UserController userController = new UserController();
                String usuario = userController.buscarUsuarioPorToken(token);
                if (usuario != null) {
                    loginSuccessful = true;
                    usuarioLogado = usuario;
                    return true;
                }
            }
        } catch (IOException ex) {
            // Ignorar se não existir
        }
        setVisible(true);
        return loginSuccessful;
    }
} 