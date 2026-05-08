package com.mtrilogic.demos.springpaneldemo;

import com.mtrilogic.desktop.abstracts.SpringPanel;

import javax.swing.*;
import java.awt.*;

public class SpringPanelDemo extends SpringPanel {

    public SpringPanelDemo() {
        // Create avatar panel (absolute positioned)
        JPanel avatar = new JPanel();
        avatar.setBackground(Color.WHITE);
        with(avatar)
                .x(10)
                .y(10)
                .width(80)
                .height(80);

        // Create header panel (top, full width)
        JPanel header = new JPanel();
        header.setBackground(Color.RED);
        with(header)
                .top(0)
                .left(0)
                .right(0)
                .height(100);

        // Declare remaining panels before positioning
        JPanel content = new JPanel();
        content.setBackground(Color.YELLOW);
        JPanel footer = new JPanel();
        JPanel sidebar = new JPanel();
        
        // Position content panel (main area)
        with(content)
                .below(header, 0)
                .left(0)
                .leftOf(sidebar, 0)
                .above(footer, 0);

        // Position sidebar (right side)
        sidebar.setBackground(Color.GREEN);
        with(sidebar)
                .below(header, 0)
                .width(150)
                .right(0)
                .above(footer, 0)
     ;

        // Position footer (bottom, full width)
        footer.setBackground(Color.BLUE);
        with(footer)
                .bottom(0)
                .left(0)
                .right(0)
                .height(50);
    }

    static void main() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null); // Center on screen
            frame.getContentPane().add(new SpringPanelDemo());
            frame.setVisible(true);
        });
    }
}
