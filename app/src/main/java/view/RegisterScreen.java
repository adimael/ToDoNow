package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.UserController;

public class RegisterScreen extends JDialog {
    private boolean registerSuccessful = false;
    private JTextField nameField;
    private JTextField userField;
    private JPasswordField passField;
    private JPasswordField confirmPassField;

    public RegisterScreen(Frame parent) {
        super(parent, "Cadastro de Usuário", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(500, 560);
        setLocationRelativeTo(parent);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230,230,230), 1),
            BorderFactory.createEmptyBorder(0,0,0,0)));
        panel.setBounds(0, 0, 500, 480);

        JLabel title = new JLabel("Cadastro de Usuário");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(147, 69, 205));
        title.setBounds(40, 20, 400, 30);
        panel.add(title);

        JLabel nameLabel = new JLabel("Nome");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        nameLabel.setBounds(40, 65, 100, 20);
        panel.add(nameLabel);
        nameField = new RoundedTextField(20);
        ((RoundedTextField)nameField).setPlaceholder("Digite seu nome");
        nameField.setBounds(40, 90, 380, 38);
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameField.setForeground(new Color(40, 40, 40));
        nameField.setCaretColor(new Color(147, 69, 205));
        panel.add(nameField);

        JLabel userLabel = new JLabel("Usuário");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        userLabel.setBounds(40, 135, 100, 20);
        panel.add(userLabel);
        userField = new RoundedTextField(20);
        ((RoundedTextField)userField).setPlaceholder("Digite seu usuário");
        userField.setBounds(40, 160, 380, 38);
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        userField.setForeground(new Color(40, 40, 40));
        userField.setCaretColor(new Color(147, 69, 205));
        panel.add(userField);

        JLabel passLabel = new JLabel("Senha");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        passLabel.setBounds(40, 205, 100, 20);
        panel.add(passLabel);
        passField = new RoundedPasswordField(20);
        ((RoundedPasswordField)passField).setPlaceholder("Digite sua senha");
        passField.setBounds(40, 230, 380, 38);
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passField.setForeground(new Color(40, 40, 40));
        passField.setCaretColor(new Color(147, 69, 205));
        panel.add(passField);

        JLabel confirmPassLabel = new JLabel("Confirmar Senha");
        confirmPassLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        confirmPassLabel.setBounds(40, 275, 150, 20);
        panel.add(confirmPassLabel);
        confirmPassField = new RoundedPasswordField(20);
        ((RoundedPasswordField)confirmPassField).setPlaceholder("Confirme sua senha");
        confirmPassField.setBounds(40, 300, 380, 38);
        confirmPassField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        confirmPassField.setForeground(new Color(40, 40, 40));
        confirmPassField.setCaretColor(new Color(147, 69, 205));
        panel.add(confirmPassField);

        JButton registerBtn = new JButton("Cadastrar");
        registerBtn.setBackground(new Color(88, 205, 69));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        registerBtn.setBounds(40, 410, 150, 40);
        registerBtn.setFocusPainted(false);
        registerBtn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(88, 205, 69), 2, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(registerBtn);

        JButton cancelBtn = new JButton("Cancelar");
        cancelBtn.setBackground(Color.WHITE);
        cancelBtn.setForeground(new Color(147, 69, 205));
        cancelBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        cancelBtn.setBounds(210, 410, 150, 40);
        cancelBtn.setFocusPainted(false);
        cancelBtn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(147, 69, 205), 2, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(cancelBtn);

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nameField.getText();
                String usuario = userField.getText();
                String senha = new String(passField.getPassword());
                String senha2 = new String(confirmPassField.getPassword());
                if (nome.isEmpty() || usuario.isEmpty() || senha.isEmpty() || senha2.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterScreen.this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!senha.equals(senha2)) {
                    JOptionPane.showMessageDialog(RegisterScreen.this, "As senhas não coincidem!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    UserController userController = new UserController();
                    userController.save(nome, usuario, senha);
                    registerSuccessful = true;
                    JOptionPane.showMessageDialog(RegisterScreen.this, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RegisterScreen.this, "Erro ao cadastrar usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        cancelBtn.addActionListener(e -> dispose());

        // Adicionar rodapé
        JLabel rodape = new JLabel("©2023 Desenvolvido por Adimael.");
        rodape.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        rodape.setForeground(new Color(120, 120, 120));
        rodape.setBounds(40, 470, 350, 20);
        panel.add(rodape);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    // Reutiliza os campos customizados da tela de login
    static class RoundedTextField extends LoginScreen.RoundedTextField {
        public RoundedTextField(int columns) { super(columns); }
    }
    static class RoundedPasswordField extends LoginScreen.RoundedPasswordField {
        public RoundedPasswordField(int columns) { super(columns); }
    }

    public boolean isRegisterSuccessful() { return registerSuccessful; }
} 