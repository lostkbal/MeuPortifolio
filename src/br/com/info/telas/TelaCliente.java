/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.info.telas;

import java.sql.*;
import br.com.info.DAL.ModuloConexao;
import javax.swing.JOptionPane;
//Importando recurso rs2xml.jar
import net.proteanit.sql.DbUtils;

/**
 *
 * @author User
 */
public class TelaCliente extends javax.swing.JInternalFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaCliente() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    //Método para Adicionar Clientes
    private void adicionar() {
        String sql = "Insert into tbclientes(nomecli,endcli,fonecli,emailcli)values(?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliTel.getText());
            pst.setString(4, txtCliEmail.getText());
            //Validando o campo obrigatorio
            if (((txtCliNome.getText().isEmpty() || txtCliTel.getText().isEmpty()))) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios(*)");
            } else {
                //A linha abaixo atualiza a tabela com os dados do Formulário.
                //A linha abaixo serve para confirmar a inserção dos dados na TABELA.
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
                    
                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliTel.setText(null);
                    txtCliEmail.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Criando o método PESQUISAR com filtro 
    private void pesquisar_cliente() {
        String sql = "select * from tbclientes where nomecli like?";
        try {
            pst = conexao.prepareStatement(sql);
            //passando o contéudo da caixa de pesquisa para o ?.
            pst.setString(1, txtCliPesquisar.getText() + "%");
            rs = pst.executeQuery();
            //A linha abaixo usa a Biblioteca rs2xml.jar para o preechimento dos clientes.
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            
        }
    }

    //Método para setar os campos do formulário com o conteúdo da tabela
    public void setar_campos() {
        int setar = tblClientes.getSelectedRow();
        txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
        txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
        txtCliEndereco.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
        txtCliTel.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
        txtCliEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString());

        //A linha abaixo desabilita o botão ADICIONAR
        btnAdicionar.setEnabled(false);
    }

    //Método para alterar dados do CLIENTE
    private void alterar() {
        String sql = "update tbclientes set nomecli=?,endcli=?,fonecli=?,emailcli=? where idcli=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliTel.getText());
            pst.setString(4, txtCliEmail.getText());
            pst.setString(5, txtCliId.getText());
            btnAdicionar.setEnabled(true);
            
            if (((txtCliNome.getText().isEmpty() || txtCliTel.getText().isEmpty()))) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios (*) ");
            } else {
                //A estrutura abaixo é usada para confirmar a alteração dos dados na tabela.
                int adicionado = pst.executeUpdate();
                // System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usário alterados com sucesso");
                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliTel.setText(null);
                    txtCliEmail.setText(null);
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            
        }
        
    }

    //Criando o método Remover Clientes
    private void remover_cliente() {
          //A linha abaixo valida a remoção do cliente
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este cliente", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbclientes where idcli=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCliId.getText());
                int apagado = pst.executeUpdate();

                //A linha abaixo: Estrutura de decisão que determina a remoção do usuário.
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso");
                    txtCliId.setText(null);
                    txtCliNome.setText(null);
                    txtCliEndereco.setText(null);
                    txtCliTel.setText(null);
                    txtCliEmail.setText(null);
                    btnAdicionar.setEnabled(true);
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

        jLabel2 = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCliEndereco = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCliTel = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCliEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnAdicionar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        txtCliPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(620, 465));

        jLabel2.setText("* Nome");

        jLabel3.setText("Endereço");

        jLabel4.setText("* Telefone");

        txtCliTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliTelActionPerformed(evt);
            }
        });

        jLabel5.setText("Email");

        txtCliEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliEmailActionPerformed(evt);
            }
        });

        jLabel6.setText("* Campos Obrigatorios");

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/info/icones/create.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/info/icones/update.png"))); // NOI18N
        btnAlterar.setToolTipText("Editar");
        btnAlterar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/info/icones/delete.png"))); // NOI18N
        btnRemover.setToolTipText("Excluir");
        btnRemover.setPreferredSize(new java.awt.Dimension(80, 80));
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        txtCliPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliPesquisarActionPerformed(evt);
            }
        });
        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/info/icones/pesquisar.png"))); // NOI18N
        jLabel1.setToolTipText("Pesquisar");

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        jLabel7.setText("Id Cliente");
        jLabel7.setEnabled(false);

        txtCliId.setEnabled(false);
        txtCliId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel7))
                                    .addGap(40, 40, 40)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtCliNome)
                                        .addComponent(txtCliEndereco)
                                        .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(34, 34, 34)
                                    .addComponent(txtCliTel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(jLabel6))
                            .addComponent(jScrollPane1))))
                .addGap(69, 69, 69))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdicionar, btnAlterar, btnRemover});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCliTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdicionar, btnAlterar, btnRemover});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliTelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliTelActionPerformed

    private void txtCliEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliEmailActionPerformed

    private void txtCliPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliPesquisarActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // Chamando o método ADICIONAR clientes;
        adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // Chamando o metodor PESQUISAR_CLIENTES    
        pesquisar_cliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased
//EVENTO abaixo QUE SERA USADO PARA SETAR OS CAMPOS DA TABELA COM MOUSE
    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        //Chamando o método SETAR_CAMPOS   
        setar_campos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // Chamando o método ALTERAR
        alterar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void txtCliIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliIdActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // Chamando  o método REMOVER_CLIENTES
        remover_cliente();
    }//GEN-LAST:event_btnRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtCliTel;
    // End of variables declaration//GEN-END:variables

}
