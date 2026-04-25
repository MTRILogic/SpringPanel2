package com.mtrilogic.desktop.abstracts;

import javax.swing.*;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class SpringPanel extends JPanel {

    public SpringPanel() {
        setLayout(new SpringLayout());
    }

    public ConstraintsBuilder with(JComponent component) {
        if (component == null) {
            throw new IllegalArgumentException("Component cannot be null");
        }

        if (component.getParent() != null && component.getParent() != this) {
            throw new IllegalStateException("Component already belongs to another container.");
        }

        if (component.getParent() == null) {
            add(component);
        }

        return new ConstraintsBuilder(component);
    }

    public SpringLayout.Constraints getConstraints(JComponent component) {
        return ((SpringLayout) getLayout()).getConstraints(component);
    }

    // =========================
    // BUILDER
    // =========================
    public class ConstraintsBuilder {
        private final JComponent component;
        private final SpringLayout layout;
        private final SpringLayout.Constraints constraints;

        private ConstraintsBuilder(JComponent component) {
            this.component = component;
            this.layout = (SpringLayout) getLayout();
            this.constraints = layout.getConstraints(component);
        }

        private void validatePad(int pad, String edge) {
            if (pad < 0) {
                throw new IllegalArgumentException(edge + " must be >= 0");
            }
        }

        private void requireNonNullComponent(JComponent other) {
            if (other == null) {
                throw new IllegalArgumentException("Reference component cannot be null");
            }
        }

        // =========================
        // POSICIÓN
        // =========================

        public ConstraintsBuilder north(int pad) {
            validatePad(pad, "NORTH");
            layout.putConstraint(SpringLayout.NORTH, component, pad, SpringLayout.NORTH, SpringPanel.this);
            return this;
        }

        public ConstraintsBuilder north(JComponent other, int pad) {
            requireNonNullComponent(other);
            validatePad(pad, "NORTH");
            layout.putConstraint(SpringLayout.NORTH, component, pad, SpringLayout.SOUTH, other);
            return this;
        }

        public ConstraintsBuilder south(int pad) {
            validatePad(pad, "SOUTH");
            layout.putConstraint(SpringLayout.SOUTH, component, -pad, SpringLayout.SOUTH, SpringPanel.this);
            return this;
        }

        public ConstraintsBuilder south(JComponent other, int pad) {
            requireNonNullComponent(other);
            validatePad(pad, "SOUTH");
            layout.putConstraint(SpringLayout.SOUTH, component, -pad, SpringLayout.NORTH, other);
            return this;
        }

        public ConstraintsBuilder west(int pad) {
            validatePad(pad, "WEST");
            layout.putConstraint(SpringLayout.WEST, component, pad, SpringLayout.WEST, SpringPanel.this);
            return this;
        }

        public ConstraintsBuilder west(JComponent other, int pad) {
            requireNonNullComponent(other);
            validatePad(pad, "WEST");
            layout.putConstraint(SpringLayout.WEST, component, pad, SpringLayout.EAST, other);
            return this;
        }

        public ConstraintsBuilder east(int pad) {
            validatePad(pad, "EAST");
            layout.putConstraint(SpringLayout.EAST, component, -pad, SpringLayout.EAST, SpringPanel.this);
            return this;
        }

        public ConstraintsBuilder east(JComponent other, int pad) {
            requireNonNullComponent(other);
            validatePad(pad, "EAST");
            layout.putConstraint(SpringLayout.EAST, component, -pad, SpringLayout.WEST, other);
            return this;
        }

        public ConstraintsBuilder baseline(JComponent other, int pad) {
            requireNonNullComponent(other);
            validatePad(pad, "BASELINE");
            layout.putConstraint(SpringLayout.BASELINE, component, pad, SpringLayout.BASELINE, other);
            return this;
        }

        public ConstraintsBuilder baseline(int pad) {
            layout.putConstraint(SpringLayout.BASELINE, component, pad, SpringLayout.BASELINE, SpringPanel.this);
            return this;
        }

        public ConstraintsBuilder horizontalCenter(int pad) {
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, component, pad, SpringLayout.HORIZONTAL_CENTER, SpringPanel.this);
            return this;
        }

        public ConstraintsBuilder verticalCenter(int pad) {
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, component, pad, SpringLayout.VERTICAL_CENTER, SpringPanel.this);
            return this;
        }

        // =========================
        // TAMAÑO
        // =========================

        public ConstraintsBuilder width(int value) {
            constraints.setWidth(Spring.constant(value));
            return this;
        }

        public ConstraintsBuilder width(int value, float factor) {
            constraints.setWidth(Spring.scale(Spring.constant(value), factor));
            return this;
        }

        public ConstraintsBuilder height(int value) {
            constraints.setHeight(Spring.constant(value));
            return this;
        }

        public ConstraintsBuilder height(int value, float factor) {
            constraints.setHeight(Spring.scale(Spring.constant(value), factor));
            return this;
        }

        // =========================
        // POSICIÓN ABSOLUTA
        // =========================

        public ConstraintsBuilder x(int value) {
            constraints.setX(Spring.constant(value));
            return this;
        }

        public ConstraintsBuilder x(int value, float factor) {
            constraints.setX(Spring.scale(Spring.constant(value), factor));
            return this;
        }

        public ConstraintsBuilder y(int value) {
            constraints.setY(Spring.constant(value));
            return this;
        }

        public ConstraintsBuilder y(int value, float factor) {
            constraints.setY(Spring.scale(Spring.constant(value), factor));
            return this;
        }
    }
}
