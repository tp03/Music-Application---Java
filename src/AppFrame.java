import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class AppFrame extends JFrame{
    private Color backgroundColor;
    private Color textColor;
    private ImageIcon imageIcon;
    private JLabel titleLabel;
    private int frameWidth;
    private int labelWidth;
    AppFrame(){
        this.imageIcon = new ImageIcon("assets/logo1.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        this.imageIcon = new ImageIcon(newImage);
        this.frameWidth = 1000;
        this.labelWidth = 250;
        

        this.titleLabel = new JLabel(imageIcon);
        this.backgroundColor = new Color(230, 138, 0);
        this.textColor = new Color(0,0,0);

        Border border = BorderFactory.createLineBorder(Color.green,3);

        titleLabel.setBorder(border);
        titleLabel.setText("SpotiSing");
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.TOP);
        titleLabel.setForeground(textColor);
        titleLabel.setFont(new Font("Monospaced",Font.PLAIN, 20));
        titleLabel.setIconTextGap(10);
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(this.frameWidth/2-this.labelWidth/2, 0, labelWidth, labelWidth);


        this.setTitle("SpotiSing");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(frameWidth,frameWidth);
        this.setLayout(null);
        this.add(titleLabel);
        this.getContentPane().setBackground(backgroundColor);
    }
}
