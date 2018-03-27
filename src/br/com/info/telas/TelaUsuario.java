/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.info.telas;

/**
 *
 * @author User
 */
import java.sql.*;
import br.com.info.DAL.ModuloConexao;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {
//Preparando a Conexao
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaUsuario
     */
    //Método para consultar Usuários
    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
// Depois de fazer a Lógica do Método consultar, colocar a ação no botão
    private void consultar() {
        String sql = "select * from tbusuarios where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUsuNome.setText(rs.getString(2));
                txtUsuTelefone.setText(rs.getString(3));
                txtUsuLogin.setText(rs.getString(4));
                txtUsuSenha.setText(rs.getString(5));
                cboUsuPerfil.setSelectedItem(rs.getString(6));
                txtUsuEmail.setText(rs.getString(7));

            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
                //As linhas abaixo "Limpa os campos".
                txtUsuId.setText(null);
                txtUsuNome.setText(null);
                txtUsuTelefone.setText(null);
                txtUsuLogin.setText(null);
                txtUsuSenha.setText(null);
                txtUsuEmail.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Método para Adicionar Usários
    private void adicionar() {
        String sql = "Insert into tbusuarios(iduser,usuario,telefone,login,senha,perfil,email) values(?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            pst.setString(2, txtUsuNome.getText());
            pst.setString(3, txtUsuTelefone.getText());
            pst.setString(4, txtUsuLogin.getText());
            pst.setString(5, txtUsuSenha.getText());
            pst.setString(6, cboUsuPerfil.getSelectedItem().toString());
            pst.setString(7, txtUsuEmail.getText());

            //Validação dos campos Obrigatorios
            if (((txtUsuId.getText().isEmpty() || txtUsuNome.getText().isEmpty()
                    || txtUsuEmail.getText().isEmpty()))) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios (*) ");
            } else {

                //A linha abaixo atualiza a tabela com os dados do Formulário.
                //A linha abaixo serve para confirmar a inserção dos dados na TABELA. Criando uma Variável
                int adicionado = pst.executeUpdate();
                System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuTelefone.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                    txtUsuEmail.setText(null);
                    cboUsuPerfil.setSelectedItem(null);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//Criando o método para alterar dados do usuário.
    private void alterar() {
        String sql = "update tbusuarios set usuario=?,telefone=?,login=?,senha=?,perfil=?,email=? where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuTelefone.getText());
            pst.setString(3, txtUsuLogin.getText());
            pst.setString(4, txtUsuSenha.getText());
            pst.setString(5, cboUsuPerfil.getSelectedItem().toString());
            pst.setString(6, txtUsuEmail.getText());
            pst.setString(7, txtUsuId.getText());

            if (((txtUsuId.getText().isEmpty() || txtUsuNome.getText().isEmpty()
                    || txtUsuEmail.getText().isEmpty()))) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios (*) ");
            } else {
                //A estrutura abaixo é usada para confirmar a alteração dos dados na tabela.
                int adicionado = pst.executeUpdate();
                // System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usário alterados com sucesso");
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuTelefone.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                    txtUsuEmail.setText(null);
                    cboUsuPerfil.setSelectedItem(null);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }
//Criando o método para Remover os dados
    private void remover() {
        //A estrutura abaixo confirma a remoção do usuario.
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbusuarios where iduser=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuId.getText());
                int apagado = pst.executeUpdate();
                //A linha abaixo: Estrutura de decisão que determina a remoção do usuário.
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuãrio removido com sucesso");
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuTelefone.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                    txtUsuEmail.setText(null);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);

            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuTelefone = new javax.swing.JTextField();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuEmail = new javax.swing.JTextField();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        txtUsuSenha = new javax.swing.JTextField();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuRead = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        btnUsuDelete = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setPreferredSize(new java.awt.Dimension(620, 465));

        jLabel1.setText("* Id");

        jLabel2.setText("* Nome");

        jLabel3.setText(" Telefone");

        jLabel4.setText("* Login");

        jLabel5.setText("* Senha");

        jLabel6.setText("* Perfil");

        jLabel7.setText("* Email");

        txtUsuEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuEmailActionPerformed(evt);
            }
        });

        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "user", "admin" }));
        cboUsuPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUsuPerfilActionPerformed(evt);
            }
        });

        btnUsuCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/info/icones/create.png"))); // NOI18N
        btnUsuCreate.setToolTipText("Adicionar");
        btnUsuCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
            }
        });

        btnUsuRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/info/icones/read.png"))); // NOI18N
        btnUsuRead.setToolTipText("Buscar");
        btnUsuRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuRead.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuReadActionPerformed(evt);
            }
        });

        btnUsuUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/info/icones/update.png"))); // NOI18N
        btnUsuUpdate.setToolTipText("Alterar");
        btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });

        btnUsuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/info/icones/delete.png"))); // NOI18N
        btnUsuDelete.setToolTipText("Remover");
        btnUsuDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeleteActionPerformed(evt);
            }
        });

        jLabel8.setText("* Campos obrigatorios");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/info/icones/usuario.png"))); // NOI18N
        jLabel9.setToolTipText("Usuário");
        jLabel9.setPreferredSize(new java.awt.Dimension(40, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtUsuTelefone)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(24, 24, 24)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtUsuEmail)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel8))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel6)
                                                .addGap(18, 18, 18)
                                                .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(27, 27, 27)
                                        .addComponent(txtUsuNome)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnUsuRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnUsuCreate, btnUsuDelete, btnUsuRead, btnUsuUpdate});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUsuTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtUsuEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnUsuCreate, btnUsuDelete, btnUsuRead, btnUsuUpdate});

        setBounds(0, 0, 620, 457);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuEmailActionPerformed

    private void btnUsuReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuReadActionPerformed
        // Chamando o método CONSULTAR.
        consultar();
    }//GEN-LAST:event_btnUsuReadActionPerformed

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
        // Chamando o método ADICIONAR.
        adicionar();
    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        // Chamando o método ALTERAR.   
        alterar();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
        // Chamando o método REMOVER.
        remover();
    }//GEN-LAST:event_btnUsuDeleteActionPerformed

    private void cboUsuPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUsuPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUsuPerfilActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuRead;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtUsuEmail;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    private javax.swing.JTextField txtUsuTelefone;
    // End of variables declaration//GEN-END:variables
}
