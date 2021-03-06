/*
 * CS321 Group Project
 * By: Ryan Manecke, Sarah Pearce, Collin Mitchell
 * Matching Madness Game
 */
package controller;

import filehandlers.CSVRead;
import filehandlers.CSVWrite;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import models.PlayField;
import models.Tile;
import javax.swing.JPanel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import models.User;

/**
 * This is the main controller for the game.
 * This class creates the JFrame and all necessary JPanels.
 * It provides the portal for user input to the game and draws 
 * the game to the screen.
 * @author Ryan Manecke, Sarah Pearce, Collin Mitchell
 */
public class MatchingMain extends javax.swing.JFrame
{
    private PlayField pf;
    private JPanel glassPane;
    private ArrayList<User> currentUsers;
    private CSVRead rFile;
    private CSVWrite wFile;
    
    /**
     * This is the default constructor for MatchingMain.
     * It creates the PlayField object and initializes a mouse press listener.
     */
    public MatchingMain() {
        initComponents();
        
        wFile = new CSVWrite();
        
        //Create a play field model
        pf = new PlayField();
        
        //Create user list
        currentUsers = new ArrayList();
        
        //Read file and add users to list
        rFile = new CSVRead();
        if (rFile.readFile() != null)
        {
            currentUsers = rFile.readFile();
        }
        
        //Add user names to dropdown list
        for (User user : currentUsers)
        {
            DefaultTableModel model = (DefaultTableModel) scoreTable.getModel();
            profileDropdown.addItem(user.getName());
            model.addRow(new Object[]{user.getName(), user.getScore()});
        }
        
        //Sort the high scores by setting a sorter on the table
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(scoreTable.getModel());
        scoreTable.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
               
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
            //Override the mousePressed event
            @Override
            public void mousePressed(MouseEvent click)
            {
                super.mousePressed(click);
                //If the play field is busy don't allow new clicks
                if (pf.checkIfBusy())
                {
                    click.consume();
                    return;
                }
                //Loop through and find where the user clicked on the playfield
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
        nameLabel = new javax.swing.JLabel();
        profileDropdown = new javax.swing.JComboBox<>();
        addProfileButton = new javax.swing.JButton();
        addProfiletext = new javax.swing.JLabel();
        scoreText = new javax.swing.JLabel();
        score = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        scoreTable = new javax.swing.JTable();
        scoreLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        testPanel = new javax.swing.JPanel();
        gv = new views.GameView();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Matching Madness");
        setBackground(new java.awt.Color(0, 0, 0));
        setMaximumSize(new java.awt.Dimension(1000, 760));
        setMinimumSize(new java.awt.Dimension(1000, 760));
        setPreferredSize(new java.awt.Dimension(1000, 710));
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 710));

        menuPanel.setBackground(new java.awt.Color(0, 0, 0));
        menuPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menuPanel.setMaximumSize(new java.awt.Dimension(160, 720));
        menuPanel.setMinimumSize(new java.awt.Dimension(160, 720));
        menuPanel.setPreferredSize(new java.awt.Dimension(160, 720));

        startButton.setBackground(new java.awt.Color(0, 255, 0));
        startButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        startButton.setForeground(new java.awt.Color(153, 0, 0));
        startButton.setText("Start Game");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        timeText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        timeText.setForeground(new java.awt.Color(0, 255, 0));
        timeText.setText("Time:");

        timer.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        timer.setForeground(new java.awt.Color(0, 255, 0));
        timer.setText("60");

        typeDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ellipses", "Cosmic Destruction", "Fruit Blender", "Dungeon Crawler", "Animal Farm" }));

        typeComboLabel.setForeground(new java.awt.Color(255, 255, 255));
        typeComboLabel.setText("Choose game mode:");

        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameLabel.setText("Choose a previous profile:");

        profileDropdown.setToolTipText("");
        profileDropdown.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                profileDropdownItemStateChanged(evt);
            }
        });

        addProfileButton.setBackground(new java.awt.Color(0, 255, 0));
        addProfileButton.setForeground(new java.awt.Color(153, 0, 0));
        addProfileButton.setText("Add New Profile");
        addProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProfileButtonActionPerformed(evt);
            }
        });

        addProfiletext.setForeground(new java.awt.Color(255, 255, 255));
        addProfiletext.setText("Or Create a new profile:");

        scoreText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        scoreText.setForeground(new java.awt.Color(0, 255, 0));
        scoreText.setText("Score:");

        score.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        score.setForeground(new java.awt.Color(0, 255, 0));
        score.setText("0");

        scoreTable.setBackground(new java.awt.Color(0, 0, 0));
        scoreTable.setForeground(new java.awt.Color(255, 255, 255));
        scoreTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Score"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scoreTable.setRowSelectionAllowed(false);
        scoreTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(scoreTable);
        if (scoreTable.getColumnModel().getColumnCount() > 0) {
            scoreTable.getColumnModel().getColumn(0).setResizable(false);
            scoreTable.getColumnModel().getColumn(1).setResizable(false);
        }

        scoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        scoreLabel.setText("All Time High Scores:");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/small1.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/small2.png"))); // NOI18N

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(profileDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(typeDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(typeComboLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addComponent(scoreText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(score))
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addComponent(timeText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(timer))
                    .addComponent(addProfileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addProfiletext)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scoreLabel)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeText)
                    .addComponent(timer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(scoreText)
                    .addComponent(score))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(typeComboLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(typeDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profileDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(addProfiletext)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addProfileButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scoreLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        timeText.getAccessibleContext().setAccessibleDescription("");

        testPanel.setBackground(new java.awt.Color(0, 0, 0));
        testPanel.setMaximumSize(new java.awt.Dimension(720, 720));
        testPanel.setMinimumSize(new java.awt.Dimension(720, 720));
        testPanel.setPreferredSize(new java.awt.Dimension(720, 720));

        gv.setMaximumSize(new java.awt.Dimension(720, 720));
        gv.setMinimumSize(new java.awt.Dimension(720, 720));
        gv.setPreferredSize(new java.awt.Dimension(720, 720));

        javax.swing.GroupLayout gvLayout = new javax.swing.GroupLayout(gv);
        gv.setLayout(gvLayout);
        gvLayout.setHorizontalGroup(
            gvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 718, Short.MAX_VALUE)
        );
        gvLayout.setVerticalGroup(
            gvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout testPanelLayout = new javax.swing.GroupLayout(testPanel);
        testPanel.setLayout(testPanelLayout);
        testPanelLayout.setHorizontalGroup(
            testPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        testPanelLayout.setVerticalGroup(
            testPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gv, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(testPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(menuPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 709, Short.MAX_VALUE)
                    .addComponent(testPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 709, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This method starts up the game and runs the game timers.
     * @param evt An event that is triggered when the new game button is pressed.
     */
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        //Create a playfield and add it to the panel
        pf.clearPlayfield();
        pf.buildTiles();
        //Draw tiles to the view based on user selection in the dropdown box
        gv.setTileType(typeDropdown.getSelectedIndex());
        
        //Current user index for arraylist
        int currentUser = profileDropdown.getSelectedIndex();

        //Set user settings if there are users
        if (!currentUsers.isEmpty())
        {
            this.currentUsers.get(currentUser).setPref(typeDropdown.getSelectedIndex());
        }
        
        //Disable the buttons and dropdowns so they can't be pressed again during play
        startButton.setEnabled(false);
        typeDropdown.setEnabled(false);
        profileDropdown.setEnabled(false);
        addProfileButton.setEnabled(false);
        
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
                    //Tell the GameView to add a tile to the screen with these settings
                    gv.addTile(temp2);
                }
            }
        }
                
        //Schedule a repaint at set time intervals for rendering changes made by the controller/playfield
        //Draw new tileset every refresh
        ScheduledExecutorService gameSchedule = Executors.newScheduledThreadPool(1);
        //paintTimer = new Timer();
        Runnable gamePainter = new Runnable() {
        //paintTimer.scheduleAtFixedRate(new TimerTask() {
            int t = 2400;
            
            @Override
            public void run() {
                //Change the time shown on the screen for countdown
                timer.setText(String.valueOf(t/40));
                gv.clearGame();
                score.setText(String.valueOf(pf.getScore()));
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
                gv.repaint();
                if (t < 0)
                {
                    //paintTimer.cancel();
                    //Update user data and save file
                    if (!currentUsers.isEmpty())
                    {
                        currentUsers.get(currentUser).setPref(typeDropdown.getSelectedIndex());
                        if (currentUsers.get(currentUser).getScore() < pf.getScore())
                        {
                            currentUsers.get(currentUser).setScore(pf.getScore());
                        }
                        wFile.writeFile(currentUsers);
                    }
                    //Reset everything on the game
                    DefaultTableModel model = (DefaultTableModel) scoreTable.getModel();
                    model.setRowCount(0);
                    for (User user : currentUsers)
                    {
                        model.addRow(new Object[]{user.getName(), user.getScore()});
                    }
                    pf.clearPlayfield();
                    pf.resetScore();
                    gv.clearGame();
                    gv.resetBg();
                    startButton.setEnabled(true);
                    typeDropdown.setEnabled(true);
                    profileDropdown.setEnabled(true);
                    addProfileButton.setEnabled(true);
                    gv.resetBg();
                    repaint();
                    gameSchedule.shutdownNow();
                }
            }
        };
        //, 0, 20)
        gameSchedule.scheduleWithFixedDelay(gamePainter, 0, 25, TimeUnit.MILLISECONDS);
        this.validate();
    }//GEN-LAST:event_startButtonActionPerformed

    /**
     * This method allows the user to create a new profile name.
     * @param evt An event that is triggered when the add profile button is pressed.
     */
    private void addProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProfileButtonActionPerformed
        //Open a popup to get profile name
        String name = JOptionPane.showInputDialog("What is your name?");
        //Remove any special characters from name if player added them
        String  altName = name.replaceAll("[^\\w\\s]","");
//        if (name != null && name.contains(","))
//        {
//            JOptionPane.showMessageDialog(null, "Name contains a comma, enter another name.");
//            name = JOptionPane.showInputDialog("What is your name?");
//        }
        if ((altName != null) && (!"".equals(altName)))
        {
            //Create a tmp user
            User tmp = new User();
            //Set the user default data
            tmp.setName(altName);
            tmp.setScore(0);
            tmp.setPref(typeDropdown.getSelectedIndex());
            //Add user to the combobox and user arraylist and select new profile
            int tmpidx = currentUsers.size();
            currentUsers.add(tmpidx, tmp);
            profileDropdown.addItem(altName);
            profileDropdown.setSelectedItem(altName);
            wFile.writeFile(currentUsers);
        }
    }//GEN-LAST:event_addProfileButtonActionPerformed

    /**
     * This method changes the tile type selection based on user preferences that were loaded.
     * @param evt An event that is triggered when the drop down is changed.
     */
    private void profileDropdownItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_profileDropdownItemStateChanged
        int tmpidx = profileDropdown.getSelectedIndex();
        if (!currentUsers.isEmpty())
        {
            typeDropdown.setSelectedIndex(currentUsers.get(tmpidx).getPref());
        }
    }//GEN-LAST:event_profileDropdownItemStateChanged

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
    private javax.swing.JButton addProfileButton;
    private javax.swing.JLabel addProfiletext;
    private views.GameView gv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JComboBox<String> profileDropdown;
    private javax.swing.JLabel score;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JTable scoreTable;
    private javax.swing.JLabel scoreText;
    private javax.swing.JButton startButton;
    private javax.swing.JPanel testPanel;
    private javax.swing.JLabel timeText;
    private javax.swing.JLabel timer;
    private javax.swing.JLabel typeComboLabel;
    private javax.swing.JComboBox<String> typeDropdown;
    // End of variables declaration//GEN-END:variables
}
