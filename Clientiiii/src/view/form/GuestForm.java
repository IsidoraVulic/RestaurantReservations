/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.form;

import controller.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author RYZEN
 */
public class GuestForm extends GenericForm {

    private AllGuestsForm parentForm;
    private String guestID;
    private String firstname;
    private String lastname;
    private String contact;
    private String email;
    private String status; //add or edit        

    public GuestForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblGuestID = new javax.swing.JLabel();
        lblFirstname = new javax.swing.JLabel();
        lblLastname = new javax.swing.JLabel();
        lblContact = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtGuestID = new javax.swing.JTextField();
        txtFirstname = new javax.swing.JTextField();
        txtLastname = new javax.swing.JTextField();
        txtContact = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Guest");

        lblGuestID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblGuestID.setText("GuestID:");

        lblFirstname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFirstname.setText("First name:");

        lblLastname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblLastname.setText("Last name:");

        lblContact.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblContact.setText("Contact:");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmail.setText("Email:");

        txtFirstname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFirstnameActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblGuestID)
                        .addGap(42, 42, 42)
                        .addComponent(txtGuestID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblFirstname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblLastname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lblContact, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtLastname)
                                    .addComponent(txtContact)
                                    .addComponent(txtFirstname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                    .addComponent(txtEmail)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBack)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                                .addComponent(btnSave)))
                        .addGap(36, 36, 36))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGuestID)
                    .addComponent(txtGuestID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstname)
                    .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLastname)
                    .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContact)
                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnBack))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFirstnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFirstnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFirstnameActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (correctData()) {
            if (status.equals("add")) {
                try {
                    addNewGuest();
                } catch (Exception ex) {
                    Logger.getLogger(GuestForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (status.equals("edit")) {
                try {
                    saveGuest();
                } catch (Exception ex) {
                    Logger.getLogger(GuestForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    throw new Exception("Neispravan status ekranske forme!");
                } catch (Exception ex) {
                    Logger.getLogger(GuestForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Neispravno uneti podaci.");
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        dispose();
        parentForm.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFirstname;
    private javax.swing.JLabel lblGuestID;
    private javax.swing.JLabel lblLastname;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtGuestID;
    private javax.swing.JTextField txtLastname;
    // End of variables declaration//GEN-END:variables

    @Override
    HashMap<String, String> createObject() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    void setFormName() {
        if (status.equals("add")) {
            this.setTitle("Kreiranje novog člana");
        } else if (status.equals("edit")) {
            this.setTitle("Izmena člana");
        }
    }

    @Override
    void setTableModels() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    void populateTable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    void setTools() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public AllGuestsForm getParentForm() {
        return parentForm;
    }

    public void setParentForm(AllGuestsForm parentForm) {
        this.parentForm = parentForm;
    }

    public String getGuestID() {
        return guestID;
    }

    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public void setBtnBack(JButton btnBack) {
        this.btnBack = btnBack;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(JButton btnSave) {
        this.btnSave = btnSave;
    }

    public JLabel getLblContact() {
        return lblContact;
    }

    public void setLblContact(JLabel lblContact) {
        this.lblContact = lblContact;
    }

    public JLabel getLblEmail() {
        return lblEmail;
    }

    public void setLblEmail(JLabel lblEmail) {
        this.lblEmail = lblEmail;
    }

    public JLabel getLblFirstname() {
        return lblFirstname;
    }

    public void setLblFirstname(JLabel lblFirstname) {
        this.lblFirstname = lblFirstname;
    }

    public JLabel getLblGuestID() {
        return lblGuestID;
    }

    public void setLblGuestID(JLabel lblGuestID) {
        this.lblGuestID = lblGuestID;
    }

    public JLabel getLblLastname() {
        return lblLastname;
    }

    public void setLblLastname(JLabel lblLastname) {
        this.lblLastname = lblLastname;
    }

    public JTextField getTxtContact() {
        return txtContact;
    }

    public void setTxtContact(JTextField txtContact) {
        this.txtContact = txtContact;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(JTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public JTextField getTxtFirstname() {
        return txtFirstname;
    }

    public void setTxtFirstname(JTextField txtFirstname) {
        this.txtFirstname = txtFirstname;
    }

    public JTextField getTxtGuestID() {
        return txtGuestID;
    }

    public void setTxtGuestID(JTextField txtGuestID) {
        this.txtGuestID = txtGuestID;
    }

    public JTextField getTxtLastname() {
        return txtLastname;
    }

    public void setTxtLastname(JTextField txtLastname) {
        this.txtLastname = txtLastname;
    }

    private boolean correctData() {
        if (emailCorrect() && contactCorrect() && firstnameCorrect() && lastnameCorrect()) {
            return true;
        }
        return false;
    }

    private void addNewGuest() throws Exception {
        boolean success = Controller.getInstace().saveGuest(getFormData());
        if (success) {
            closeForm();
            JOptionPane.showMessageDialog(parentForm, "Sistem je zapamtio novog člana");
        } else {
            JOptionPane.showMessageDialog(parentForm, "Sistem ne može da zapamti novog člana");
        }
    }

    public void createGuest() throws Exception {
        this.guestID = Controller.getInstace().createObject("guest").get("id");
        txtGuestID.setText(guestID);
    }

    private void saveGuest() throws Exception {
        boolean success = Controller.getInstace().saveGuest(getFormData());
        if (success) {
            closeForm();
            JOptionPane.showMessageDialog(parentForm, "Sistem je zapamtio člana.");
            parentForm.getGuestTableModel().setList(new ArrayList<>());
        } else {
            closeForm();
            JOptionPane.showMessageDialog(parentForm, "Sistem ne može zapamti člana.");
        }
    }

    private void closeForm() {
        parentForm.setVisible(true);
        dispose();
    }

    private HashMap<String, String> getFormData() {
        HashMap<String, String> data = new HashMap<>();
        data.put("id", txtGuestID.getText());
        data.put("firstname", txtFirstname.getText());
        data.put("lastname", txtLastname.getText());
        data.put("contact", txtContact.getText());
        data.put("email", txtEmail.getText());
        return data;
    }

    private boolean emailCorrect() {
        String email = txtEmail.getText();
        if (email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
            return true;
        }
        return false;
    }

    private boolean contactCorrect() {
        String contact = txtContact.getText();
        if (contact.matches("[0-9]+") && contact.length() > 2) {
            return true;
        }
        return false;
    }

    private boolean firstnameCorrect() {
        String firstname = txtFirstname.getText();
        if (firstname.matches("[a-zA-Zšđčćž]+") && firstname.length() > 1) {
            return true;
        }
        return false;
    }

    private boolean lastnameCorrect() {
        String lastname = txtLastname.getText();
        if (lastname.matches("[a-zA-Zšđčćž]+") && lastname.length() > 1) {
            return true;
        }
        return false;
    }
    
    public void populateForm(){
         if (guestID != null) {
            txtGuestID.setText(guestID);
        }
        if (email != null) {
            txtEmail.setText(email);
        }
        if (firstname != null) {
            txtFirstname.setText(firstname);
        }
        if (lastname != null) {
            txtLastname.setText(lastname);
        }
        if (contact != null) {
            txtContact.setText(contact);
        }
    }
    
     private void disableID() {
        txtGuestID.setEditable(false);
    }
}
