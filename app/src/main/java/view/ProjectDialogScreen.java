/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import controller.ProjectController;
import javax.swing.JOptionPane;
import model.Project;

/**
 *
 * @author adimael
 */
public class ProjectDialogScreen extends javax.swing.JDialog {
    
    ProjectController controller;
    /**
     * Creates new form ProjectDialogScreen
     */
    public ProjectDialogScreen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        controller = new ProjectController();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelToolBar = new javax.swing.JPanel();
        jLabelToolBarTitle = new javax.swing.JLabel();
        jButtonToolBarSave = new javax.swing.JButton();
        jPanelProject = new javax.swing.JPanel();
        jTextFieldName = new javax.swing.JTextField();
        jScrollPaneDescription = new javax.swing.JScrollPane();
        jTextAreaDescription = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelToolBar.setBackground(new java.awt.Color(4, 12, 58));

        jLabelToolBarTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelToolBarTitle.setForeground(new java.awt.Color(10, 158, 254));
        jLabelToolBarTitle.setText("Projeto");

        jButtonToolBarSave.setBackground(new java.awt.Color(88, 205, 69));
        jButtonToolBarSave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonToolBarSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pencil (2).png"))); // NOI18N
        jButtonToolBarSave.setText("Cadastrar");
        jButtonToolBarSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonToolBarSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonToolBarSaveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelToolBarLayout = new javax.swing.GroupLayout(jPanelToolBar);
        jPanelToolBar.setLayout(jPanelToolBarLayout);
        jPanelToolBarLayout.setHorizontalGroup(
            jPanelToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelToolBarLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabelToolBarTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 303, Short.MAX_VALUE)
                .addComponent(jButtonToolBarSave)
                .addGap(21, 21, 21))
        );
        jPanelToolBarLayout.setVerticalGroup(
            jPanelToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelToolBarLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanelToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonToolBarSave)
                    .addComponent(jLabelToolBarTitle))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanelProject.setBackground(java.awt.Color.white);

        jTextFieldName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextFieldName.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nome do projeto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jScrollPaneDescription.setBorder(null);

        jTextAreaDescription.setColumns(20);
        jTextAreaDescription.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextAreaDescription.setRows(5);
        jTextAreaDescription.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descrição", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jScrollPaneDescription.setViewportView(jTextAreaDescription);

        javax.swing.GroupLayout jPanelProjectLayout = new javax.swing.GroupLayout(jPanelProject);
        jPanelProject.setLayout(jPanelProjectLayout);
        jPanelProjectLayout.setHorizontalGroup(
            jPanelProjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProjectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldName)
                    .addComponent(jScrollPaneDescription))
                .addContainerGap())
        );
        jPanelProjectLayout.setVerticalGroup(
            jPanelProjectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProjectLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelToolBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelProject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelProject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonToolBarSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonToolBarSaveMouseClicked
        // TODO add your handling code here:
        try {
            if(!jTextFieldName.getText().equals("")){
                Project project = new Project();
                project.setName(jTextFieldName.getText());
                project.setDescription(jTextAreaDescription.getText());
                controller.save(project);
                JOptionPane.showMessageDialog(rootPane, "Projeto salvo com sucesso!");
                this.dispose();
            } else{
                JOptionPane.showMessageDialog(rootPane, "Projeto não foi salvo, pois é preciso o preencimento do nome do projeto!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_jButtonToolBarSaveMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Java swing".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProjectDialogScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjectDialogScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjectDialogScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjectDialogScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ProjectDialogScreen dialog = new ProjectDialogScreen(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonToolBarSave;
    private javax.swing.JLabel jLabelToolBarTitle;
    private javax.swing.JPanel jPanelProject;
    private javax.swing.JPanel jPanelToolBar;
    private javax.swing.JScrollPane jScrollPaneDescription;
    private javax.swing.JTextArea jTextAreaDescription;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
}
