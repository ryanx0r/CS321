/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import models.PlayField;
import models.Tile;
import javax.swing.JPanel;

/**
 *
 * @author Ryan
 */
public class MatchingMain extends javax.swing.JFrame
{
    private Timer gameTime;
    private Timer paintTimer;
    private PlayField pf;
    private JPanel glassPane;
    
    /**
     * Creates new form MatchingMain
     */
    public MatchingMain() {
        initComponents();
        
        pf = new PlayField();
        
        glassPane = new JPanel();
        glassPane.setOpaque(false);
        glassPane.setLayout(null);
        glassPane.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent click)
            {
                click.consume();
            }
        });
        
        //Add a mouse pressed listener to the playfield
        gv.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent click)
            {
                super.mousePressed(click);
                if (pf.checkIfBusy())
                {
                    click.consume();
                    return;
                }
                for (int i = 0; i < pf.getGridSize(); i++)
                {
                    for (int j = 0; j < pf.getGridSize(); j++)
                    {
                        //See if a tile was clicked on
                        if ((pf.tileStatus(i,j) != null) && (pf.tileStatus(i,j).contains(click.getX(),click.getY())))
                        {
                            //Check if a tile is currently selected
                            //"highlight" the tile if it gets clicked and "unhighlight" the previous tile
                            if (pf.isTileHighlighted() == true)
                            {
                                pf.setTileHighlighted(false);
                                pf.swapTiles(i,j);
                                break;
                            }
                            //Else no tile has yet been clicked on
                            else
                            {
                                pf.setTileHighlighted(true);
                                pf.tileStatus(i,j).highlight();
                                pf.setPrevTile(i,j);
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPanel = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        timeText = new javax.swing.JLabel();
        timer = new javax.swing.JLabel();
        typeDropdown = new javax.swing.JComboBox<>();
        typeComboLabel = new javax.swing.JLabel();
        testPanel = new javax.swing.JPanel();
        gv = new views.GameView();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Match Madness");
        setBackground(new java.awt.Color(0, 0, 0));
        setMaximumSize(new java.awt.Dimension(970, 825));
        setMinimumSize(new java.awt.Dimension(970, 825));
        setPreferredSize(new java.awt.Dimension(970, 825));
        setResizable(false);
        setSize(new java.awt.Dimension(970, 825));

        menuPanel.setBackground(new java.awt.Color(0, 0, 0));
        menuPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menuPanel.setMaximumSize(new java.awt.Dimension(160, 800));
        menuPanel.setMinimumSize(new java.awt.Dimension(160, 800));
        menuPanel.setPreferredSize(new java.awt.Dimension(160, 800));

        startButton.setText("New Game");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        timeText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        timeText.setForeground(new java.awt.Color(255, 0, 0));
        timeText.setText("Time:");

        timer.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        timer.setForeground(new java.awt.Color(255, 0, 0));
        timer.setText("60");

        typeDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ellipses", "Cosmic Destruction", "Fruit Blender" }));

        typeComboLabel.setForeground(new java.awt.Color(255, 255, 255));
        typeComboLabel.setText("Choose game tile type:");

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(typeDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, menuPanelLayout.createSequentialGroup()
                        .addComponent(timeText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(timer))
                    .addComponent(typeComboLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeText)
                    .addComponent(timer))
                .addGap(48, 48, 48)
                .addComponent(typeComboLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(typeDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(startButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        timeText.getAccessibleContext().setAccessibleDescription("");

        testPanel.setBackground(new java.awt.Color(0, 0, 0));
        testPanel.setMaximumSize(new java.awt.Dimension(800, 800));
        testPanel.setMinimumSize(new java.awt.Dimension(800, 800));

        gv.setMaximumSize(new java.awt.Dimension(800, 800));
        gv.setMinimumSize(new java.awt.Dimension(800, 800));

        javax.swing.GroupLayout gvLayout = new javax.swing.GroupLayout(gv);
        gv.setLayout(gvLayout);
        gvLayout.setHorizontalGroup(
            gvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 798, Short.MAX_VALUE)
        );
        gvLayout.setVerticalGroup(
            gvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 798, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout testPanelLayout = new javax.swing.GroupLayout(testPanel);
        testPanel.setLayout(testPanelLayout);
        testPanelLayout.setHorizontalGroup(
            testPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        testPanelLayout.setVerticalGroup(
            testPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testPanelLayout.createSequentialGroup()
                .addComponent(gv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(testPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(menuPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(testPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(145, 145, 145))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //New game button pressed
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        //Create a playfield and add it to the panel
        pf.buildTiles();
        //Draw tiles to the view based on user selection
        gv.setTileType(typeDropdown.getSelectedIndex());
        
        //Disable the button so it can't be pressed again during play
        startButton.setEnabled(false);
        typeDropdown.setEnabled(false);
        
        //Loop through the playfield and get tile data to send to the gameview
        for (int i = 0; i < pf.getGridSize(); i++)
        {
            for (int j = 0; j < pf.getGridSize(); j++)
            {
                Tile temp = pf.tileStatus(i, j);
                if (temp != null)
                {
                    //Copy values from the tile to a temporary array
                    int[] temp2 = new int[4];
                    temp2[0] = temp.getTileType();
                    temp2[1] = temp.getAlpha();
                    temp2[2] = temp.getX();
                    temp2[3] = temp.getY();
                    gv.addTile(temp2);
                }
            }
        }
        
        //Start timer for the new game
        gameTime = new Timer();
        gameTime.scheduleAtFixedRate(new TimerTask() {
            int i = 60;
            @Override
            public void run() {
                //Subtract from the time counter
                timer.setText(String.valueOf(i--));
                if (i< 0)
                {
                    gameTime.cancel();
                    pf.clearPlayfield();
                    gv.clearGame();
                    startButton.setEnabled(true);
                    typeDropdown.setEnabled(true);
                    repaint();
                }
            }
        }, 0, 1000);
        
        //Schedule a repaint at set time intervals for rendering changes made by the controller/playfield
        //Draw new tileset every refresh
        paintTimer = new Timer();
        paintTimer.scheduleAtFixedRate(new TimerTask() {
            int t = 3000;
            @Override
            public void run() {
                gv.clearGame();
                //Loop through the playfield and get tile data to send to the gameview
                for (int i = 0; i < pf.getGridSize(); i++)
                {
                    for (int j = 0; j < pf.getGridSize(); j++)
                    {
                        Tile temp = pf.tileStatus(i, j);
                        if (temp != null)
                        {
                            //Copy values from the tile to a temporary array
                            int[] temp2 = new int[4];
                            temp2[0] = temp.getTileType();
                            temp2[1] = temp.getAlpha();
                            temp2[2] = temp.getX();
                            temp2[3] = temp.getY();
                            gv.addTile(temp2);
                        }
                    }
                }
                t--;
                if (pf.checkIfBusy())
                {
                    glassPane.setVisible(true);
                }
                else
                {
                    glassPane.setVisible(false);
                }
                repaint();
                if (t < 0)
                {
                    paintTimer.cancel();
                    pf.clearPlayfield();
                    gv.clearGame();
                    gv.resetBg();
                    repaint();
                }
            }
        }, 0, 20);
        this.validate();
    }//GEN-LAST:event_startButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MatchingMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MatchingMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MatchingMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MatchingMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MatchingMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private views.GameView gv;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JButton startButton;
    private javax.swing.JPanel testPanel;
    private javax.swing.JLabel timeText;
    private javax.swing.JLabel timer;
    private javax.swing.JLabel typeComboLabel;
    private javax.swing.JComboBox<String> typeDropdown;
    // End of variables declaration//GEN-END:variables
}
