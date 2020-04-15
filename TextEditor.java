
package lokaverkefnid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static lokaverkefnid.JavaSoundRecorder.RECORD_TIME;
import lokaverkefnid.extraframe;

import net.miginfocom.swing.*;

/**
 * @author safaj
 */
public class TextEditor extends JFrame {
    

    // Text component 
        static javax.swing.JTextArea t ; 
        static String s ;
        static String filetoread;
        static MouseEvent em ;
        static String selectedtext;
        static Graphics g;
        static String filePath= null;
        static BufferedImage img = null;
        static JLabel lb =new JLabel();
        static JTable table = new JTable();
        extraframe f2 = new extraframe() ;
        static DefaultTableModel model = new DefaultTableModel();
    
    //Frame 
    static javax.swing.JFrame frame ;
    
    TextEditor (String filetoread){
        

    
    // Create a menubar 
        JMenuBar mb = new JMenuBar();
        
    //create a menu for the menubar 
        JMenu m = new JMenu("File");
        JMenu m1 = new JMenu("Home");
        JMenu m2 = new JMenu("Insert");
        
        
    //create the components of the menu FILE 
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi3 = new JMenuItem("Open ..");
        JMenuItem mi4 = new JMenuItem("Save");
        JMenuItem mi5 = new JMenuItem("Save as ..");
        JMenuItem mi6 = new JMenuItem("Close");
        
    //create the components of the menu HOME  
        JMenuItem mi11 = new JMenuItem("Copy");
        JMenuItem mi31 = new JMenuItem("Cut");
        JMenuItem mi41 = new JMenuItem("Paste");
        
    //create the components of the menu INSERT 
        JMenuItem mi12 = new JMenuItem("Image");
        JMenuItem mi32 = new JMenuItem("Table");
        JMenuItem mi42 = new JMenuItem("Link");
        JMenuItem mi52 = new JMenuItem("Record a voice");
        
    // add items to menu FILE 
        m.add(mi1); 
        m.add(mi3); 
        m.add(mi4);
        m.add(mi5);
        m.add(mi6);
        
        
    // add items to menu HOME 
        m1.add(mi11); 
        m1.add(mi31); 
        m1.add(mi41);
        
    // add items to menu INSERT 
        m2.add(mi12); 
        m2.add(mi32); 
        m2.add(mi42);
        m2.add(mi52);
        
        mb.add(m);
        mb.add(m1);
        mb.add(m2);
        
        
    
    
        
    //add actionlistener to menu 
    // if an item is chosen : new or open r save or save as 
        ActionListener l = new ActionListener()
        { public void actionPerformed( ActionEvent e )
        {
          String s = e.getActionCommand();
          if (s.equals("New")) { 
           Newtext();
            }
          if (s.equals("Open ..")) { 
           open();
          }
          if (s.equals("Save as ..")) { 
           saveas();
            }
        if (s.equals("Save")) { 
           save();   
            }
        if (s.equals("Close")) { 
           System.exit(0);  
            }
        if (s.equals("Image")) { 
           image();   
            }
        if (s.equals("Table")) { 
           //búa til auka frame sem poppar út til að bæta við gögn í töflunni , við finnum það í klassanum extraframe
           table();   
            }
        if (s.equals("Link")) { 
            
            mouseReleased(em);
           link();   
            }
        if (s.equals("Record a voice")) { 
           record();   
            }
      
	}
        };
        
        m.addActionListener(l);
        
        
       
    // Add action listener to items 
        mi1.addActionListener(l); 
        mi3.addActionListener(l); 
        mi4.addActionListener(l);
        mi5.addActionListener(l);
        mi11.addActionListener(l); 
        mi31.addActionListener(l); 
        mi41.addActionListener(l);
        mi12.addActionListener(l); 
        mi32.addActionListener(l); 
        mi42.addActionListener(l);
        mi52.addActionListener(l);
        
    // add menu bar to frame 
        frame.setJMenuBar(mb); 
        
    // read the file from the argument args[0]  
        File fi = new File("C:\\Users\\safaj\\OneDrive\\Bureau\\"+filetoread);
        try { 
    // String 
        String s1 = "", sl = ""; 
    // File reader 
        FileReader fr = new FileReader(fi); 
        s = fi.getName();
        frame.setTitle(s);
    // Buffered reader 
        BufferedReader br = new BufferedReader(fr); 
    // Initilize sl 
        sl = br.readLine(); 
    // Take the input from the file 
        while ((s1 = br.readLine()) != null) { 
            sl = sl + "\n" + s1; 
        }
    // Set the text 
        t.setText(sl); 
    } 
    catch (Exception evt) { 
     
        JOptionPane.getDefaultLocale();
    
    }

    //add text to frame 
        frame.add(t); 
        
 
    // set frame visible 
        frame.setSize(500, 500); 
        frame.setVisible(true);
    }   
    //newtext method 
    private static void Newtext(){
        t.setText(""); 
        frame.remove(lb);
        s = "New File";
        frame.setTitle(s);
    }
    //open method 
    private static void open(){
        // Create an object of JFileChooser class 
                JFileChooser j = new JFileChooser("f:");
           // Invoke the showsOpenDialog function to show the save dialog 
                int r = j.showOpenDialog(null); 
            // If selecting a file 
                if (r == JFileChooser.APPROVE_OPTION) { 
            // Set the label to the path of the selected directory 
                File fi = new File(j.getSelectedFile().getAbsolutePath());
            try { 
            // String 
                String s1 = "", sl = ""; 
            // File reader 
                FileReader fr = new FileReader(fi); 
                s = fi.getName();
                frame.setTitle(s);
            // Buffered reader 
                BufferedReader br = new BufferedReader(fr); 
            // Initilize sl 
                sl = br.readLine(); 
            // Take the input from the file 
                while ((s1 = br.readLine()) != null) { 
                        sl = sl + "\n" + s1; 
                    }
                // Set the text 
                    t.setText(sl); 
                } 
            catch (Exception evt) { 
                    JOptionPane.showMessageDialog(frame, evt.getMessage()); 
                }
                    }
        
    }
    //save as method 
    private static void saveas(){
        // Create an object of JFileChooser class
                JFileChooser j = new JFileChooser("f:");
            // Invoke the showsSaveDialog function to show the save dialog 
                int r = j.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) { 
  
            // Set the label to the path of the selected directory 
                File fi = new File(j.getSelectedFile().getAbsolutePath()); 
               
  
            try { 
            // Create a file writer 
                FileWriter wr = new FileWriter(fi, false); 
            // Create buffered writer to write 
                BufferedWriter w = new BufferedWriter(wr); 
            // Write 
                w.write(t.getText()); 
                w.flush(); 
                w.close(); 
                } 
            catch (Exception evt) { 
                JOptionPane.showMessageDialog(frame, evt.getMessage()); 
            }    
            }
            // If the user cancelled the operation 
            else
                JOptionPane.showMessageDialog(frame, "operation cancelled ");
        
    }
    private static void save(){
         // get the file into this path 
                File fi = new File("C:\\Users\\safaj\\OneDrive\\Documents\\NewFile.txt"); 
           
            try { 
            // Create a file writer 
                FileWriter wr = new FileWriter(fi, false); 
            // Create buffered writer to write 
                BufferedWriter w = new BufferedWriter(wr); 
            // Write 
                w.write(t.getText()); 
                w.flush(); 
                w.close(); 
                } 
            
            catch (Exception evt) { 
               
                JOptionPane.showMessageDialog(frame, evt.getMessage()); 
            } 
    }
    private static void record(){
        final JavaSoundRecorder recorder = new JavaSoundRecorder();
        // creates a new thread that waits for a specified
        // of time before stopping
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(RECORD_TIME);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                recorder.finish();
            }
        });
 
        stopper.start();
 
        // start recording
        recorder.start();
    }
    
    //get the selected text 
    public void mouseReleased(MouseEvent e) {
        if (t.getSelectedText() != null) { // See if they selected something 
                  selectedtext = t.getSelectedText();
        // Do work with String s
        }
        }
    private static void link(){
        final CreateBoldItalicFontExample bolditalictext = new CreateBoldItalicFontExample();
            try {
                
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(selectedtext));
                bolditalictext.paint(g,selectedtext);
            } catch (IOException ex) {
                Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("well done");
        
    }
    private static void image(){
        final JFileChooser fc = new JFileChooser();
        int returnval = fc.showOpenDialog(fc);
        if (returnval == JFileChooser.APPROVE_OPTION) {
            filePath = fc.getSelectedFile().getAbsolutePath(); //retriving file path from FILECHOOSER 
        }
        else {
            System.out.print("operation cancelled by user ");
            
        }
        try {
                img =ImageIO.read(new File (filePath));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            lb.setIcon(new ImageIcon(img));
        
        lb.setPreferredSize(new Dimension(5, 5));
        lb.setIcon(new ImageIcon(img));
        //t.add(lb);
        frame.getContentPane().add(lb,BorderLayout.CENTER);
        frame.pack();
        
        
    }
    private void table(){
        
        //set external frame f2 visible 
        f2.setVisible(true);
      
        
        // create a table model and set a Column Identifiers to this model 
        Object[] columns = {"Id","First Name","Last Name","Age"};
        
        model.setColumnIdentifiers(columns);
        
        // set the model to the table
        table.setModel(model);
        
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        
        // create JTextFields
        JTextField textId = new JTextField();
        JTextField textFname = new JTextField();
        JTextField textLname = new JTextField();
        JTextField textAge = new JTextField();
        
   
        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 400, 100);
        frame.setLayout(null);
        frame.add(pane);
        
  
        // get selected row data From table to textfields 
        table.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            // i = the index of the selected row
            int i = table.getSelectedRow();
            
            textId.setText(model.getValueAt(i, 0).toString());
            textFname.setText(model.getValueAt(i, 1).toString());
            textLname.setText(model.getValueAt(i, 2).toString());
            textAge.setText(model.getValueAt(i, 3).toString());
        }
        });
        
   
    }
   
    public static void main(String[] args) {
        
       
        frame = new javax.swing.JFrame("My Editing Book");
        frame.setLayout(new MigLayout());
      
        t = new javax.swing.JTextArea();
        if(args.length > 0) {
            try {
            TextEditor te = new TextEditor(args[0]) ; 
            } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        }
        else {
             try {
              t.setText("");
              TextEditor te = new TextEditor(t.getText()) ; 
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
          
        }        

    }
    
}
