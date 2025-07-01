package com.example;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class PadelRentalForm extends JFrame {
    private JTable padelTable;
    private DefaultTableModel tableModel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTextField tfNama, tfNoHP, tfTanggal, tfJamMulai, tfJamSelesai;
    private JComboBox<String> cbLapangan;
   
   public PadelRentalForm() {
    List<Padel> padels = new ArrayList<>();
       setTitle("Form Sewa Lapangan Padel");
        setSize(1080, 720);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
       setLayout(new BorderLayout());

       JPanel formPanel = new JPanel();
       formPanel.add(new JLabel("Nama "));
       tfNama = new JTextField(6);
       formPanel.add(tfNama);
       
       formPanel.add(new JLabel("NoHP "));
       tfNoHP = new JTextField(8);
       formPanel.add(tfNoHP);

       formPanel.add(new JLabel("Tanggal "));
       tfTanggal = new JTextField(8);
       formPanel.add(tfTanggal);

       formPanel.add(new JLabel("Jam Mulai "));
       tfJamMulai = new JTextField(5);
       formPanel.add(tfJamMulai);
       
       formPanel.add(new JLabel("Jam Selesai "));
       tfJamSelesai = new JTextField(5);
       formPanel.add(tfJamMulai);

       formPanel.add(new JLabel("Kategori:"));
       cbLapangan = new JComboBox<>(new String[]{ "1", "2", "3", "4"});
       formPanel.add(cbLapangan);
       
        addButton = new JButton("Add");
        formPanel.add(addButton);
        
        editButton = new JButton("Edit");
        formPanel.add(editButton);
        
        deleteButton = new JButton("Delete");
        formPanel.add(deleteButton);

        
        loadPadelData(padels);
        tableModel = new DefaultTableModel(new String[]{"id", "Nama", "no. HP", "tanggal", "Jam Mulai", "Jam Selesai", "Lapangan"}, 0);
        padelTable = new JTable(tableModel);
        add(new JScrollPane(padelTable), BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);

       
        padelTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = padelTable.getSelectedRow();
                if (selectedRow != -1) {
                    String selectedNama = tableModel.getValueAt(selectedRow, 0).toString();
                    String selectedNoHP = tableModel.getValueAt(selectedRow, 1).toString();
                    String selectedTanggal = tableModel.getValueAt(selectedRow, 2).toString();
                    String selectedJamMulai = tableModel.getValueAt(selectedRow, 3).toString();
                    String selectedJamSelesai = tableModel.getValueAt(selectedRow, 4).toString();
                    String selectedLapangan = tableModel.getValueAt(selectedRow, 5).toString();
                
                    tfNama.setText(selectedNama);
                    tfNoHP.setText(selectedNoHP);
                    tfTanggal.setText(selectedTanggal);
                    tfJamMulai.setText(selectedJamMulai);
                    tfJamSelesai.setText(selectedJamSelesai);
                    cbLapangan.setSelectedItem(selectedLapangan);
                }
            }
      });
    
       addButton.addActionListener(e -> {
        try {
            String nama = tfNama.getText();
            String nohp = tfNoHP.getText();
            String tanggal = tfTanggal.getText();
            String jammulai = tfJamMulai.getText();
            String jamselesai = tfJamSelesai.getText();
            String lapangan = cbLapangan.getSelectedItem().toString();
            
            
            int id = padels.size() + 1;
            Padel padel = new Padel(id, nama, nohp, tanggal, jammulai, jamselesai, lapangan);
            padels.add(padel);

            tableModel.addRow(new Object[]{padel.getNama(), padel.getNoHP(), padel.getTanggal(), padel.getJamMulai(),padel.getJamSelesai(), padel.getLapangan(),});

            tfNama.setText("");
            tfNoHP.setText("");
            tfTanggal.setText("");
            tfJamMulai.setText("");
            tfJamSelesai.setText("");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Inputan Salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });
   

    editButton.addActionListener(e -> {
        int selectedRow = padelTable.getSelectedRow();
        if (selectedRow != -1) {
            try {
               
                String nama = tfNama.getText();
                String nohp = tfNoHP.getText();
                String tanggal = tfTanggal.getText();
                String jammulai = tfJamMulai.getText();
                String jamselesai = tfJamSelesai.getText();
                String lapangan = cbLapangan.getSelectedItem().toString();
                

                tableModel.setValueAt(nama, selectedRow, 0);
                tableModel.setValueAt(nohp, selectedRow, 1);
                tableModel.setValueAt(tanggal, selectedRow, 2);
                tableModel.setValueAt(jammulai, selectedRow, 3);
                tableModel.setValueAt(jamselesai, selectedRow, 4);
                tableModel.setValueAt(lapangan, selectedRow, 5);

                tfNama.setText("");
                tfNoHP.setText("");
                tfTanggal.setText("");
                tfJamMulai.setText("");
                tfJamSelesai.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Masukkan harga dalam angka!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih produk yang ingin diedit!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }});

    deleteButton.addActionListener(e -> {
        int selectedRow = padelTable.getSelectedRow();
        if (selectedRow != -1) {
            padels.remove(selectedRow);
            tableModel.removeRow(selectedRow);
            tfNama.setText("");
            tfNoHP.setText("");
            tfTanggal.setText("");
            tfJamMulai.setText("");
            tfJamSelesai.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Pilih produk yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    });
};

     private void loadPadelData(List<Padel> padelList) {
        for (Padel padel : padelList) {
            tableModel.addRow(new Object[]{
               padel.getNama(), padel.getNoHP(), padel.getTanggal(), padel.getJamMulai(),padel.getJamSelesai(), padel.getLapangan()
            });
        }
    }
   public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PadelRentalForm().setVisible(true));
   }
}