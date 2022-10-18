package org.bekker;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;


public class ColorChooser extends JPanel
        implements ChangeListener {

    protected JColorChooser tcc, bcc;
    protected JLabel banner;

    private static int font_size = 48;

    public ColorChooser() {
        super(new BorderLayout());

        //Set up the banner at the top of the window
        banner = new JLabel("Welcome to Color Chooser",
                JLabel.CENTER);
        banner.setForeground(new Color(99, 0,99));
        banner.setBackground(Color.BLACK);
        banner.setOpaque(true);
        banner.setFont(new Font("SansSerif", Font.BOLD, 48));
        banner.setPreferredSize(new Dimension(100, 120));
        banner.addMouseListener(new FontChangeListener());

        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.add(banner, BorderLayout.CENTER);
        bannerPanel.setBorder(BorderFactory.createTitledBorder(
                "В режиме выбора цвета \"Swatches\" перемещайтесь по палитре с помощью стрелок клавиатуры " +
                        "и применяйте изменения клавишей Пробел. Приятного использования :)"));

        //Set up color chooser for setting text color
        tcc = new JColorChooser(banner.getForeground());
        tcc.setOpaque(true);
        tcc.getSelectionModel().addChangeListener(this);
        tcc.setBorder(BorderFactory.createTitledBorder(
                "Выберите цвет текста"));

        bcc = new JColorChooser(banner.getBackground());
        bcc.setOpaque(true);
        bcc.getSelectionModel().addChangeListener(this);
        bcc.setBorder(BorderFactory.createTitledBorder("Выберите цвет фона"));

        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
        center.add(tcc);
        center.add(bcc);
        center.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
                "by Lex Bekker", TitledBorder.TRAILING, TitledBorder.BOTTOM));

        center.setBackground(Color.LIGHT_GRAY);

        add(bannerPanel, BorderLayout.PAGE_START);
        add(center, BorderLayout.CENTER);

    }

    private static void increaseFontSize() {
        if (font_size < 80) font_size += 2;
    }

    private static void decreaseFontSize() {
        if (font_size > 20) font_size -= 2;
    }

    private class FontChangeListener extends MouseAdapter {
        @Override public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                decreaseFontSize();
            }
            if (e.getButton() == MouseEvent.BUTTON1) {
                increaseFontSize();
            }
            banner.setFont(new Font("SansSerif", Font.BOLD, font_size));
        }
    }

    public void stateChanged(ChangeEvent e) {
        Color newColor = tcc.getColor();
        banner.setForeground(newColor);
        Color bg_color = bcc.getColor();
        banner.setBackground(bg_color);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("ColorChooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ColorChooser();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        frame.setResizable(false);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(ColorChooser::createAndShowGUI);
    }
}
