package com.mtrilogic.desktop.abstracts;

import javax.swing.*;

/**
 * A JPanel extension that provides a fluent API for working with SpringLayout.
 * This class simplifies the process of creating complex layouts using SpringLayout
 * by offering a builder pattern for setting component constraints.
 * 
 * <p>Example usage:
 * <pre>{@code
 * SpringPanel panel = new SpringPanel();
 * panel.with(button1)
 *      .top(10)
 *      .left(10)
 *      .width(100)
 *      .height(30);
 *
 * panel.with(button2)
 *      .below(button1, 5)
 *      .left(10)
 *      .width(100)
 *      .height(30);
 * }</pre>
 * 
 * @author MTRILogic
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class SpringPanel extends JPanel {

    /**
     * Creates a new SpringPanel with a SpringLayout as its layout manager.
     */
    public SpringPanel() {
        setLayout(new SpringLayout());
    }

    /**
     * Adds a component to this panel and returns a ConstraintsBuilder for setting
     * layout constraints using a fluent API.
     * 
     * @param component the JComponent to add to this panel
     * @return a ConstraintsBuilder instance for setting component constraints
     * @throws IllegalArgumentException if the component is null
     * @throws IllegalStateException if the component already belongs to another container
     */
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

    // =========================
    // BUILDER
    // =========================
    
    /**
     * A fluent builder class for setting SpringLayout constraints on components.
     * This class provides a chainable API for positioning and sizing components
     * within a SpringPanel using SpringLayout.
     * 
     * <p>The builder supports three types of operations:
     * <ul>
     *   <li>Position constraints relative to edges (north, south, east, west)</li>
     *   <li>Size constraints (width, height)</li>
     *   <li>Absolute position constraints (x, y)</li>
     * </ul>
     * 
     * <p>All methods return the builder instance to enable method chaining.
     */
    public class ConstraintsBuilder {
        private final JComponent component;
        private final SpringLayout layout;
        private final SpringLayout.Constraints constraints;

        /**
         * Creates a new ConstraintsBuilder for the specified component.
         * 
         * @param component the component to build constraints for
         */
        private ConstraintsBuilder(JComponent component) {
            this.component = component;
            layout = (SpringLayout) getLayout();
            constraints = layout.getConstraints(component);
        }

        // =========================
        // POSITION
        // =========================

        /**
         * Sets the north edge constraint relative to the panel's north edge.
         * 
         * @param pad the padding distance from the panel's north edge (must be >= 0)
         * @return this ConstraintsBuilder for method chaining
         * @throws IllegalArgumentException if pad is negative
         */
        public ConstraintsBuilder top(int pad) {
            validatePad(pad, "Top");
            layout.putConstraint(SpringLayout.NORTH, component, pad, SpringLayout.NORTH, SpringPanel.this);
            return this;
        }

        /**
         * Sets the north edge constraint relative to another component's south edge.
         * 
         * @param other the reference component
         * @param pad the padding distance from the reference component's south edge (must be >= 0)
         * @return this ConstraintsBuilder for method chaining
         * @throws IllegalArgumentException if pad is negative or other is null
         */
        public ConstraintsBuilder below(JComponent other, int pad) {
            requireNonNullComponent(other);
            validatePad(pad, "Below");
            layout.putConstraint(SpringLayout.NORTH, component, pad, SpringLayout.SOUTH, other);
            return this;
        }

        /**
         * Sets the south edge constraint relative to the panel's south edge.
         *
         *
         * @param pad the padding distance from the panel's south edge (must be >= 0)
         * @return this ConstraintsBuilder for method chaining
         * @throws IllegalArgumentException if pad is negative
         */
        public ConstraintsBuilder bottom(int pad) {
            validatePad(pad, "Bottom");
            layout.putConstraint(SpringLayout.SOUTH, component, -pad, SpringLayout.SOUTH, SpringPanel.this);
            return this;
        }

        /**
         * Sets the south edge constraint relative to another component's north edge.
         * 
         * @param other the reference component
         * @param pad the padding distance from the reference component's north edge (must be >= 0)
         * @return this ConstraintsBuilder for method chaining
         * @throws IllegalArgumentException if pad is negative or other is null
         */
        public ConstraintsBuilder above(JComponent other, int pad) {
            requireNonNullComponent(other);
            validatePad(pad, "Above");
            layout.putConstraint(SpringLayout.SOUTH, component, -pad, SpringLayout.NORTH, other);
            return this;
        }

        /**
         * Sets the west edge constraint relative to the panel's west edge.
         * 
         * @param pad the padding distance from the panel's west edge (must be >= 0)
         * @return this ConstraintsBuilder for method chaining
         * @throws IllegalArgumentException if pad is negative
         */
        public ConstraintsBuilder left(int pad) {
            validatePad(pad, "Left");
            layout.putConstraint(SpringLayout.WEST, component, pad, SpringLayout.WEST, SpringPanel.this);
            return this;
        }

        /**
         * Sets the west edge constraint relative to another component's east edge.
         * 
         * @param other the reference component
         * @param pad the padding distance from the reference component's east edge (must be >= 0)
         * @return this ConstraintsBuilder for method chaining
         * @throws IllegalArgumentException if pad is negative or other is null
         */
        public ConstraintsBuilder rightOf(JComponent other, int pad) {
            requireNonNullComponent(other);
            validatePad(pad, "Right of");
            layout.putConstraint(SpringLayout.WEST, component, pad, SpringLayout.EAST, other);
            return this;
        }

        /**
         * Sets the east edge constraint relative to the panel's east edge.
         * 
         * @param pad the padding distance from the panel's east edge (must be >= 0)
         * @return this ConstraintsBuilder for method chaining
         * @throws IllegalArgumentException if pad is negative
         */
        public ConstraintsBuilder right(int pad) {
            validatePad(pad, "Right");
            layout.putConstraint(SpringLayout.EAST, component, -pad, SpringLayout.EAST, SpringPanel.this);
            return this;
        }

        /**
         * Sets the east edge constraint relative to another component's west edge.
         * 
         * @param other the reference component
         * @param pad the padding distance from the reference component's west edge (must be >= 0)
         * @return this ConstraintsBuilder for method chaining
         * @throws IllegalArgumentException if pad is negative or other is null
         */
        public ConstraintsBuilder leftOf(JComponent other, int pad) {
            requireNonNullComponent(other);
            validatePad(pad, "Left of");
            layout.putConstraint(SpringLayout.EAST, component, -pad, SpringLayout.WEST, other);
            return this;
        }
        
        /**
         * Sets the component to fill the vertical space of the panel with optional padding.
         *
         * @param pad the padding from the panel's north and south edges (must be >= 0)
         * @return this ConstraintsBuilder for method chaining
         * @throws IllegalArgumentException if pad is negative
         */
        public ConstraintsBuilder fillVertically(int pad) {
            validatePad(pad, "Fill vertically");
            layout.putConstraint(SpringLayout.NORTH, component, pad, SpringLayout.NORTH, SpringPanel.this);
            layout.putConstraint(SpringLayout.SOUTH, component, -pad, SpringLayout.SOUTH, SpringPanel.this);
            return this;
        }

        /**
         * Sets the component to fill the horizontal space of the panel with optional padding.
         *
         * @param pad the padding from the panel's west and east edges (must be >= 0)
         * @return this ConstraintsBuilder for method chaining
         * @throws IllegalArgumentException if pad is negative
         */
        public ConstraintsBuilder fillHorizontally(int pad) {
            validatePad(pad, "Fill horizontally");
            layout.putConstraint(SpringLayout.WEST, component, pad, SpringLayout.WEST, SpringPanel.this);
            layout.putConstraint(SpringLayout.EAST, component, -pad, SpringLayout.EAST, SpringPanel.this);
            return this;
        }
        
        /**
         * Sets the component to fill all available space in the panel with optional padding.
         *
         * @param pad the padding from all edges of the panel (must be >= 0)
         * @return this ConstraintsBuilder for method chaining
         * @throws IllegalArgumentException if pad is negative
         */
        public ConstraintsBuilder fill(int pad) {
            validatePad(pad, "Fill all");
            layout.putConstraint(SpringLayout.NORTH, component, pad, SpringLayout.NORTH, SpringPanel.this);
            layout.putConstraint(SpringLayout.SOUTH, component, -pad, SpringLayout.SOUTH, SpringPanel.this);
            layout.putConstraint(SpringLayout.WEST, component, pad, SpringLayout.WEST, SpringPanel.this);
            layout.putConstraint(SpringLayout.EAST, component, -pad, SpringLayout.EAST, SpringPanel.this);
            return this;
        }

        /**
         * Sets the horizontal center constraint relative to the panel's horizontal center.
         * 
         * @param pad the offset distance from the panel's horizontal center
         * @return this ConstraintsBuilder for method chaining
         */
        public ConstraintsBuilder centerHorizontally(int pad) {
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, component, pad, SpringLayout.HORIZONTAL_CENTER, SpringPanel.this);
            return this;
        }

        /**
         * Sets the horizontal center constraint relative to another component's horizontal center.
         *
         * @param other the reference component
         * @param pad the offset distance from the reference component's horizontal center
         * @return this ConstraintsBuilder for method chaining
         * @throws IllegalArgumentException if other is null
         */
        public ConstraintsBuilder centerHorizontally(JComponent other, int pad) {
            requireNonNullComponent(other);
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, component, pad, SpringLayout.HORIZONTAL_CENTER, other);
            return this;
        }

        /**
         * Sets the vertical center constraint relative to the panel's vertical center.
         * 
         * @param pad the offset distance from the panel's vertical center
         * @return this ConstraintsBuilder for method chaining
         */
        public ConstraintsBuilder centerVertically(int pad) {
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, component, pad, SpringLayout.VERTICAL_CENTER, SpringPanel.this);
            return this;
        }

        /**
         * Sets the vertical center constraint relative to another component's vertical center.
         *
         * @param other the reference component
         * @param pad the offset distance from the reference component's vertical center
         * @return this ConstraintsBuilder for method chaining
         * @throws IllegalArgumentException if other is null
         */
        public ConstraintsBuilder centerVertically(JComponent other, int pad) {
            requireNonNullComponent(other);
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, component, pad, SpringLayout.VERTICAL_CENTER, other);
            return this;
        }

        /**
         * Centers the component both horizontally and vertically relative to the panel.
         *
         * @param pad the offset distance from the panel's center
         * @return this ConstraintsBuilder for method chaining
         */
        public ConstraintsBuilder center(int pad) {
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, component, pad, SpringLayout.HORIZONTAL_CENTER, SpringPanel.this);
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, component, pad, SpringLayout.VERTICAL_CENTER, SpringPanel.this);
            return this;
        }

        /**
         * Sets the baseline constraint relative to another component's baseline.
         *
         * @param other the reference component
         * @return this ConstraintsBuilder for method chaining
         * @throws IllegalArgumentException if other is null
         */
        public ConstraintsBuilder baseline(JComponent other) {
            requireNonNullComponent(other);
            layout.putConstraint(SpringLayout.BASELINE, component, 0, SpringLayout.BASELINE, other);
            return this;
        }

        // =========================
        // SIZE
        // =========================

        /**
         * Sets the width constraint to a fixed value.
         * 
         * @param value the fixed width value
         * @return this ConstraintsBuilder for method chaining
         */
        public ConstraintsBuilder width(int value) {
            constraints.setWidth(Spring.constant(value));
            return this;
        }

        /**
         * Sets the width constraint as a scaled factor of the panel's width.
         *
         * @param factor the scaling factor to apply to the panel's width
         * @return this ConstraintsBuilder for method chaining
         */
        public ConstraintsBuilder width(float factor) {
            constraints.setWidth(Spring.scale(getWidthSpring(), factor));
            return this;
        }

        /**
         * Sets the height constraint to a fixed value.
         * 
         * @param value the fixed height value
         * @return this ConstraintsBuilder for method chaining
         */
        public ConstraintsBuilder height(int value) {
            constraints.setHeight(Spring.constant(value));
            return this;
        }

        /**
         * Sets the height constraint as a scaled factor of the panel's height.
         *
         * @param factor the scaling factor to apply to the panel's height
         * @return this ConstraintsBuilder for method chaining
         */
        public ConstraintsBuilder height(float factor) {
            constraints.setHeight(Spring.scale(getHeightSpring(), factor));
            return this;
        }

        // =========================
        // ABSOLUTE POSITION
        // =========================

        /**
         * Sets the absolute x-coordinate constraint to a fixed value.
         * 
         * @param value the fixed x-coordinate value
         * @return this ConstraintsBuilder for method chaining
         */
        public ConstraintsBuilder x(int value) {
            constraints.setX(Spring.constant(value));
            return this;
        }

        /**
         * Sets the absolute x-coordinate constraint as a scaled factor of the panel's width.
         *
         * @param factor the scaling factor to apply to the panel's width
         * @return this ConstraintsBuilder for method chaining
         */
        public ConstraintsBuilder x(float factor) {
            constraints.setX(Spring.scale(getWidthSpring(), factor));
            return this;
        }

        /**
         * Sets the absolute y-coordinate constraint to a fixed value.
         * 
         * @param value the fixed y-coordinate value
         * @return this ConstraintsBuilder for method chaining
         */
        public ConstraintsBuilder y(int value) {
            constraints.setY(Spring.constant(value));
            return this;
        }

        /**
         * Sets the absolute y-coordinate constraint as a scaled factor of the panel's height.
         *
         * @param factor the scaling factor to apply to the panel's height
         * @return this ConstraintsBuilder for method chaining
         */
        public ConstraintsBuilder y(float factor) {
            constraints.setY(Spring.scale(getHeightSpring(), factor));
            return this;
        }

        // =========================
        // VALIDATE METHODS
        // =========================

        /**
         * Validates that the padding value is non-negative.
         *
         * @param pad the padding value to validate
         * @param edge the edge name for error messages
         * @throws IllegalArgumentException if pad is negative
         */
        private void validatePad(int pad, String edge) {
            if (pad < 0) {
                throw new IllegalArgumentException(edge + " must be >= 0");
            }
        }

        /**
         * Validates that the reference component is not null.
         *
         * @param other the reference component to validate
         * @throws IllegalArgumentException if the component is null
         */
        private void requireNonNullComponent(JComponent other) {
            if (other == null) {
                throw new IllegalArgumentException("Reference component cannot be null");
            }
        }

        /**
         * Returns a Spring representing the panel's width.
         *
         * @return a Spring for the panel's width
         */
        private Spring getWidthSpring() {
            return layout.getConstraint(SpringLayout.WIDTH, SpringPanel.this);
        }

        /**
         * Returns a Spring representing the panel's height.
         *
         * @return a Spring for the panel's height
         */
        private Spring getHeightSpring() {
            return layout.getConstraint(SpringLayout.HEIGHT, SpringPanel.this);
        }
    }
}
