package com.mtrilogic.demos.springpaneldemo;

import com.mtrilogic.desktop.abstracts.SpringPanel;

import javax.swing.*;
import java.awt.*;

public class BaselineDemo extends SpringPanel {

    public BaselineDemo() {
        JPanel northPanel = createNorthPanel();
        JPanel baselinePanel = createBaselinePanel();
        // Position north panel at top, full width
        with(northPanel)
                .top(0)
                .fillHorizontally(0)
                .height(130);
        // Position baseline panel below north panel, remaining space
        with(baselinePanel)
                .below(northPanel, 0)
                .fillHorizontally(0)
                .bottom(0);
    }

    static void main() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("SpringPanel2: NORTH vs BASELINE");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);

            BaselineDemo demo = new BaselineDemo();
            frame.add(demo, BorderLayout.CENTER);

            frame.setLocationRelativeTo(null); // Center on screen
            frame.setVisible(true);
        });
    }

    private static JPanel createNorthPanel() {
        SpringPanel panel = new SpringPanel();
        panel.setBorder(BorderFactory.createTitledBorder("NORTH (visually misaligned)"));

        JLabel label = new JLabel("Name:");
        JTextField field = new JTextField("Example text");

        // Position label at top-left
        panel.with(label)
                .top(10)
                .left(10);

        // Position field using NORTH alignment (misaligned with label text)
        panel.with(field)
                .top(10)   // Aligns to top edge, not baseline
                .rightOf(label, 10)
                .width(200)
                .height(40);

        return panel;
    }

    private static JPanel createBaselinePanel() {
        SpringPanel panel = new SpringPanel();
        panel.setBorder(BorderFactory.createTitledBorder("BASELINE (correct alignment)"));

        JLabel label = new JLabel("Name:");
        JTextField field = new JTextField("Example text");

        // Position label at top-left
        panel.with(label)
                .top(10)
                .left(10);

        // Position field using BASELINE alignment (aligned with label text)
        panel.with(field)
                .baseline(label) // Aligns text baselines
                .rightOf(label, 10)
                .width(200)
                .height(40);

        return panel;
    }
}
