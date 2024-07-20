/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan_barang;

import java.awt.Desktop;
import java.net.URL;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Agung Yuda
 */
public class fpenjualan extends javax.swing.JFrame {

    public long total;
    public long bayar;
    public long kembali;
    public Statement st;
    Connection cn = koneksi.getKoneksi();

    private DefaultTableModel model;

    /**
     * Creates new form fpenjualan
     */
    public fpenjualan() {
        initComponents();
        model = new DefaultTableModel();
        tabelPenjualan.setModel(model);
        model.addColumn("ID");
        model.addColumn("kode barang");
        model.addColumn("nama barang");
        model.addColumn("harga satuan");
        model.addColumn("jumlah beli");
        model.addColumn("harga");
        loadData();
        nofaktur();
        tampilpilih();
    }

    public void FilterHuruf(KeyEvent a) {
        if (Character.isDigit(a.getKeyChar())) {
            a.consume();
            JOptionPane.showMessageDialog(null, "masukan huruf saja!", "peringatan",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void FilterAngka(KeyEvent a) {
        if (Character.isAlphabetic(a.getKeyChar())) {
            a.consume();
            JOptionPane.showMessageDialog(null, "masukan angka saja!", "peringatan",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public final void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM tb_hitung_jual";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                Object[] o = new Object[6];
                o[0] = r.getString("id_hitung");
                o[1] = r.getString("kd_barang");
                o[2] = r.getString("nama_barang");
                o[3] = r.getString("harga_satuan");
                o[4] = r.getString("harga");
                o[5] = r.getString("jumlah_jual");
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
    }

    private void tampilpilih() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT nama_barang FROM tb_barang WHERE jumlah_barang !='0'";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                pilihBarang.addItem(r.getString("nama_barang"));
            }
            r.last();
            int jumlahdata = r.getRow();
            r.first();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void nofaktur() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM tb_penjualan ORDER by no_faktur desc";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String nofak = r.getString("no_faktur").substring(1);
                String AN = "" + (Integer.parseInt(nofak) + 1);
                String Nol = "";
                if (AN.length() == 1) {
                    Nol = "000";
                } else if (AN.length() == 2) {
                    Nol = "00";
                } else if (AN.length() == 3) {
                    Nol = "0";
                } else if (AN.length() == 4) {
                    Nol = "";
                }
                noFaktur.setText("F" + Nol + AN);
            } else {
                noFaktur.setText("F0001");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private String getUniqueFilePath(String directoryPath, String baseFileName) {
        File file = new File(directoryPath, baseFileName);
        if (!file.exists()) {
            return file.getAbsolutePath();
        }

        String nameWithoutExtension = baseFileName.substring(0, baseFileName.lastIndexOf('.'));
        String extension = baseFileName.substring(baseFileName.lastIndexOf('.'));
        int count = 1;

        while (file.exists()) {
            String newFileName = nameWithoutExtension + "(" + count + ")" + extension;
            file = new File(directoryPath, newFileName);
            count++;
        }

        return file.getAbsolutePath();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_hitung = new javax.swing.JButton();
        noFaktur = new javax.swing.JTextField();
        jumlahJual = new javax.swing.JTextField();
        hargaSatuan = new javax.swing.JTextField();
        kdBarang = new javax.swing.JTextField();
        pilihBarang = new javax.swing.JComboBox<>();
        txt_total = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelPenjualan = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btn_total = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_bayar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_kembalian = new javax.swing.JTextField();
        btn_selesaiTransaksi = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btn_cetakInvoice = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(239, 231, 188));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 28)); // NOI18N
        jLabel1.setText("Penjualan");

        btn_back.setText("Back");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 616, Short.MAX_VALUE)
                .addComponent(btn_back)
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_back)
                    .addComponent(jLabel1))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 163, 132));

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama Barang");

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("No Faktur");

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kode Barang");

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Harga Satuan");

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Jumlah Jual");

        btn_hitung.setText("Hitung");
        btn_hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hitungActionPerformed(evt);
            }
        });

        noFaktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noFakturActionPerformed(evt);
            }
        });

        jumlahJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahJualActionPerformed(evt);
            }
        });

        hargaSatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaSatuanActionPerformed(evt);
            }
        });

        kdBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kdBarangActionPerformed(evt);
            }
        });

        pilihBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Barang", " " }));
        pilihBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihBarangActionPerformed(evt);
            }
        });

        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });

        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        tabelPenjualan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelPenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPenjualanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelPenjualan);

        jPanel3.setBackground(new java.awt.Color(116, 189, 203));

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));

        btn_total.setText("Total");
        btn_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_totalActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Bayar");

        txt_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bayarActionPerformed(evt);
            }
        });
        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Kembalian");

        txt_kembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kembalianActionPerformed(evt);
            }
        });

        btn_selesaiTransaksi.setText("Selesai Transaksi");
        btn_selesaiTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selesaiTransaksiActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Rp.");

        btn_cetakInvoice.setText("Cetak Invoice");
        btn_cetakInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakInvoiceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel10)
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cetakInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_selesaiTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGap(31, 31, 31)
                                            .addComponent(btn_total, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel9))
                                    .addGap(0, 29, Short.MAX_VALUE))))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_total)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_selesaiTransaksi)
                .addGap(18, 18, 18)
                .addComponent(btn_cetakInvoice)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(kdBarang)
                                    .addComponent(hargaSatuan)
                                    .addComponent(jumlahJual)
                                    .addComponent(noFaktur)
                                    .addComponent(pilihBarang, 0, 172, Short.MAX_VALUE)
                                    .addComponent(txt_total))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_hitung))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(noFaktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pilihBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 111, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(kdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(hargaSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jumlahJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_hitung))
                        .addGap(22, 22, 22)
                        .addComponent(btn_tambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        // TODO add your handling code here:
        fmenu fb = new fmenu();
        fb.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_hitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hitungActionPerformed
        // TODO add your handling code here:
        if (noFaktur.getText().equals("") || kdBarang.getText().equals("")
                || pilihBarang.getSelectedItem().equals("Pilih Barang") || hargaSatuan.getText().equals("") || jumlahJual.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "Pratama Shop",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            String a = jumlahJual.getText();
            int aa = Integer.parseInt(a);
            String b = hargaSatuan.getText();
            int bb = Integer.parseInt(b);
            if (aa > bb) {
                JOptionPane.showMessageDialog(null, "Jumlah melebihi stok", "Pratama Shop",
                        JOptionPane.INFORMATION_MESSAGE);
                jumlahJual.setText("");
            } else {
                if (jumlahJual.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "ISI JUMLAH BELI !");
                } else {
                    int jumlah, harga, total;
                    jumlah = Integer.parseInt(jumlahJual.getText());
                    harga = Integer.parseInt(hargaSatuan.getText());
                    total = jumlah * harga;
                    txt_total.setText(Integer.toString(total));
                    JOptionPane.showMessageDialog(null, "Total Harga: " + total);
                }
            }
        }
    }//GEN-LAST:event_btn_hitungActionPerformed

    private void noFakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noFakturActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noFakturActionPerformed

    private void jumlahJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahJualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahJualActionPerformed

    private void hargaSatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaSatuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaSatuanActionPerformed

    private void kdBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kdBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdBarangActionPerformed

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void txt_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bayarActionPerformed

    private void txt_kembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kembalianActionPerformed

    private void pilihBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihBarangActionPerformed
        // TODO add your handling code here:
        if (pilihBarang.getSelectedItem().equals("pilih barang")) {
            kdBarang.setText("");
            hargaSatuan.setText("");
        } else {
            try {
                Connection c = koneksi.getKoneksi();
                Statement s = c.createStatement();

                String sql = "SELECT kd_barang, jumlah_barang FROM tb_barang WHERE nama_barang ='"
                        + pilihBarang.getSelectedItem() + "'";
                ResultSet r = s.executeQuery(sql);
                while (r.next()) {
                    kdBarang.setText(r.getString("kd_barang"));
                    jumlahJual.setText(r.getString("jumlah_barang"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            try {
                Connection c = koneksi.getKoneksi();
                Statement s = c.createStatement();
                String sql = "SELECT harga_jual FROM tb_barang WHERE nama_barang ='"
                        + pilihBarang.getSelectedItem() + "'";
                ResultSet r = s.executeQuery(sql);
                while (r.next()) {
                    hargaSatuan.setText(r.getString("harga_jual"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }//GEN-LAST:event_pilihBarangActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        if (noFaktur.getText().equals("") || kdBarang.getText().equals("")
                || pilihBarang.getSelectedItem().equals("Pilih Barang") || hargaSatuan.getText().equals("")
                || jumlahJual.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "Pratama Shop", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String kdbarang = kdBarang.getText();
            String pilihbarang = (String) pilihBarang.getSelectedItem();
            String hsatuan = hargaSatuan.getText();
            String tjumlah = jumlahJual.getText();
            String totall = txt_total.getText();

            System.out.println("kdbarang: " + kdbarang);
            System.out.println("pilihbarang: " + pilihbarang);
            System.out.println("hsatuan: " + hsatuan);
            System.out.println("tjumlah: " + tjumlah);
            System.out.println("totall: " + totall);

            try {
                Connection c = koneksi.getKoneksi();
                String sql = "INSERT INTO tb_hitung_jual VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement p = c.prepareStatement(sql);

                p.setString(1, null);
                p.setString(2, kdbarang);
                p.setString(3, pilihbarang);
                p.setString(4, hsatuan);
                p.setString(5, totall);
                p.setString(6, tjumlah);
                p.executeUpdate();
                p.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Terjadi Error: " + e.getMessage(), "Pratama Shop", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally {
                nofaktur();
                kdBarang.setText("");
                pilihBarang.setSelectedItem("Pilih Barang");
                hargaSatuan.setText("");
                jumlahJual.setText("");
                txt_total.setText("");
                loadData();
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Pratama Shop", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }//GEN-LAST:event_btn_tambahActionPerformed

    private void tabelPenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPenjualanMouseClicked
        // TODO add your handling code here:
        int jawaban;
        if ((jawaban = JOptionPane.showConfirmDialog(null, "Yakin batal?", "Konfirmasi", JOptionPane.YES_NO_OPTION)) == 0) {
            try {

                int i = tabelPenjualan.getSelectedRow();
                if (i == -1) {
                    return;
                }
                String id = (String) model.getValueAt(i, 0);

                st = cn.createStatement();
                st.executeUpdate("delete from tb_hitung_jual where id_hitung = '" + id + "'");
                nofaktur();
                loadData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_tabelPenjualanMouseClicked

    private void btn_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_totalActionPerformed
        // TODO add your handling code here:
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT SUM(`harga`) AS total FROM tb_hitung_jual";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                jLabel7.setText(r.getString("" + "total"));

            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }

    }//GEN-LAST:event_btn_totalActionPerformed

    private void btn_selesaiTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selesaiTransaksiActionPerformed
        // TODO add your handling code here:
        if (txt_kembalian.getText().equals("") || txt_bayar.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LENGKAPI Nominal Bayar!", "Pratama Shop", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int ab = Integer.parseInt(txt_bayar.getText());
            if (ab < 0) {
                JOptionPane.showMessageDialog(null, "Uang anda kurang", "Pratama Shop", JOptionPane.INFORMATION_MESSAGE);
                txt_kembalian.setText("");
                txt_bayar.setText("");
            } else {
                try {
                    Connection c = koneksi.getKoneksi();
                    Statement s = c.createStatement();
                    String sql = "SELECT * FROM tb_hitung_jual";
                    ResultSet r = s.executeQuery(sql);
                    while (r.next()) {
                        long millis = System.currentTimeMillis();
                        java.sql.Date date = new java.sql.Date(millis);
                        String tgl = date.toString();
                        String sqla = "INSERT INTO tb_penjualan (no_faktur, kd_barang, nama_barang, hsatuan, jumlah_jual, harga, bayar, kembalian, tgl_penjualan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement p = c.prepareStatement(sqla);
                        p.setString(1, noFaktur.getText());
                        p.setString(2, r.getString("kd_barang"));
                        p.setString(3, r.getString("nama_barang"));
                        p.setString(4, r.getString("harga_satuan"));
                        p.setString(5, r.getString("jumlah_jual"));
                        p.setString(6, r.getString("harga"));
                        p.setString(7, txt_bayar.getText());
                        p.setString(8, txt_kembalian.getText());
                        p.setString(9, tgl);

                        p.executeUpdate();
                        p.close();
                    }
                    r.close();
                    s.close();
                } catch (SQLException e) {
                    System.out.println("Terjadi Error: " + e.getMessage());
                } finally {
                    try {
                        String sqla = "TRUNCATE tb_hitung_jual";
                        Connection conn = koneksi.getKoneksi();
                        PreparedStatement pst = conn.prepareStatement(sqla);
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "TRANSAKSI SELESAI", "Pratama Shop", JOptionPane.INFORMATION_MESSAGE);
                        loadData();
                        jTextField1.setText(noFaktur.getText());
                        txt_kembalian.setText("");
                        txt_bayar.setText("");
                        jLabel7.setText("");
                        nofaktur();
                        btn_tambah.setEnabled(true);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                }
            }
        }
    }//GEN-LAST:event_btn_selesaiTransaksiActionPerformed

    private void btn_cetakInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetakInvoiceActionPerformed
        // TODO add your handling code here:
        try {
            String noFakturCetak = jTextField1.getText();

            if (noFakturCetak.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nomor Faktur kosong!", "Pratama Shop", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String directoryPath = "C:/Users/adiyo/OneDrive/Documents";
            String filePath = directoryPath + "/Invoice_PratamaShop_" + noFakturCetak + ".pdf";

            filePath = getUniqueFilePath(directoryPath, "Invoice_PratamaShop_" + noFakturCetak + ".pdf");

            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Paragraph title = new Paragraph("INVOICE - Pratama Shop", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph noFakturParagraph = new Paragraph("No. Faktur: " + noFakturCetak, FontFactory.getFont(FontFactory.HELVETICA, 12));
            noFakturParagraph.setSpacingBefore(20);
            document.add(noFakturParagraph);

            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            Paragraph dateParagraph = new Paragraph("Tanggal: " + date.toString(), FontFactory.getFont(FontFactory.HELVETICA, 12));
            document.add(dateParagraph);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(20f);
            table.setSpacingAfter(20f);

            String[] headers = {"Nama Barang", "Jumlah Jual", "Harga Barang", "Total"};
            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(headerCell);
            }

            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM tb_penjualan WHERE no_faktur = '" + noFakturCetak + "'";
            ResultSet r = s.executeQuery(sql);

            String totalBelanja = "";
            String bayar = "";
            String kembalian = "";

            while (r.next()) {
                table.addCell(new PdfPCell(new Phrase(r.getString("nama_barang"), FontFactory.getFont(FontFactory.HELVETICA, 12))));
                table.addCell(new PdfPCell(new Phrase(r.getString("jumlah_jual"), FontFactory.getFont(FontFactory.HELVETICA, 12))));
                table.addCell(new PdfPCell(new Phrase(r.getString("hsatuan"), FontFactory.getFont(FontFactory.HELVETICA, 12))));
                table.addCell(new PdfPCell(new Phrase(r.getString("harga"), FontFactory.getFont(FontFactory.HELVETICA, 12))));
                totalBelanja = r.getString("harga");
                bayar = r.getString("bayar");
                kembalian = r.getString("kembalian");
            }

            document.add(table);

            Paragraph totalBelanjaParagraph = new Paragraph("Total Belanja: Rp. " + totalBelanja, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
            totalBelanjaParagraph.setAlignment(Element.ALIGN_RIGHT);
            totalBelanjaParagraph.setSpacingBefore(10f);
            document.add(totalBelanjaParagraph);

            Paragraph bayarParagraph = new Paragraph("Uang Tunai: Rp. " + bayar, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
            bayarParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(bayarParagraph);

            Paragraph kembalianParagraph = new Paragraph("Kembalian: Rp. " + kembalian, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
            kembalianParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(kembalianParagraph);
            document.close();

            JOptionPane.showMessageDialog(null, "Invoice berhasil dicetak!", "Pratama Shop", JOptionPane.INFORMATION_MESSAGE);
            
            jTextField1.setText("");

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(filePath));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage(), "Pratama Shop", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_cetakInvoiceActionPerformed

    private void txt_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyReleased
        // TODO add your handling code here:
        bayar = Integer.parseInt(String.valueOf(txt_bayar.getText()));
        total = Integer.parseInt(String.valueOf(jLabel7.getText()));
        kembali = bayar - total;

        txt_kembalian.setText(Long.toString(kembali));
    }//GEN-LAST:event_txt_bayarKeyReleased

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(fpenjualan.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fpenjualan.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fpenjualan.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fpenjualan.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fpenjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_cetakInvoice;
    private javax.swing.JButton btn_hitung;
    private javax.swing.JButton btn_selesaiTransaksi;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_total;
    private javax.swing.JTextField hargaSatuan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jumlahJual;
    private javax.swing.JTextField kdBarang;
    private javax.swing.JTextField noFaktur;
    private javax.swing.JComboBox<String> pilihBarang;
    private javax.swing.JTable tabelPenjualan;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_kembalian;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
