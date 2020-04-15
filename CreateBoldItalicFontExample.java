/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lokaverkefnid;

/*
 Create Bold and Italic Font Example 
 This java example shows how to create bold and italic font using 
 Java AWT Font class.
*/
 
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
 
 
 public class CreateBoldItalicFontExample extends Applet{
     String s;
 
    public void paint(Graphics g,String s ) {
        Font myFont = new Font("courier", Font.BOLD | Font.ITALIC ,13);
        g.setColor(Color.red);
 }
}