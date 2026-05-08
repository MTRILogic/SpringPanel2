# SpringPanel (V2)
[![](https://jitpack.io/v/MTRILogic/SpringPanel2.svg)](https://jitpack.io/#MTRILogic/SpringPanel2)

A fluent API wrapper for Java Swing's SpringLayout that simplifies complex UI layouts with intuitive method chaining.

## 🚀 Why SpringPanel?

SpringLayout is powerful but verbose and error-prone. SpringPanel provides:

- **Fluent API**: Chain methods for readable, concise layout code
- **Type Safety**: Compile-time checking of constraints
- **Less Boilerplate**: No more manual constraint management
- **Intuitive Syntax**: `with(component).constraint(value)` pattern
- **Error Prevention**: Built-in validation prevents common mistakes

## 📦 Installation

Add JitPack to your `build.gradle`:

```gradle
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.MTRILogic:SpringPanel2:1.1.0")
}
```

Or with Groovy DSL:

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.MTRILogic:SpringPanel2:1.1.0'
}
```

Or Maven:

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.MTRILogic</groupId>
    <artifactId>SpringPanel2</artifactId>
    <version>1.1.0</version>
</dependency>
```

## 💡 Basic Usage

The SpringPanel pattern follows three simple steps:

1. **WITH** - Select the component to position
2. **CONSTRAINTS** - Define positioning and sizing

```java
public class MyPanel extends SpringPanel {
    public MyPanel() {
        JButton button = new JButton("Click Me");
        
        // Position button at (10, 10) with size 100x30
        with(button)
            .x(10)
            .y(10)
            .width(100)
            .height(30);
    }
}
```

## 🔧 Constraint Methods

### Positioning Constraints (Relative to Panel)
- `top(padding)` - Distance from top edge
- `bottom(padding)` - Distance from bottom edge
- `left(padding)` - Distance from left edge
- `right(padding)` - Distance from right edge
- `centerHorizontally(offset)` - Center horizontally
- `centerVertically(offset)` - Center vertically
- `center(offset)` - Center both horizontally and vertically
- `x(offset)` - Absolute X position
- `y(offset)` - Absolute Y position

### Relative Positioning Constraints
- `below(component, padding)` - Place below another component
- `above(component, padding)` - Place above another component
- `rightOf(component, padding)` - Place to the right of another component
- `leftOf(component, padding)` - Place to the left of another component
- `baseline(component)` - Align text baselines
- `centerHorizontally(component, offset)` - Center horizontally relative to component
- `centerVertically(component, offset)` - Center vertically relative to component

### Fill Constraints
- `fill(padding)` - Fill all available space
- `fillHorizontally(padding)` - Fill horizontal space
- `fillVertically(padding)` - Fill vertical space

### Sizing Constraints
- `width(size)` - Fixed width
- `width(factor)` - Scaled width (factor of panel width)
- `height(size)` - Fixed height
- `height(factor)` - Scaled height (factor of panel height)

### Relative Positioning
All constraints accept an optional reference component:

```java
public class RelativePositioningExample extends SpringPanel {
    public RelativePositioningExample() {
        JButton button1 = new JButton("First");
        JButton button2 = new JButton("Second");

        with(button1)
            .top(10)
            .left(10);

        with(button2)
            .below(button1, 10)   // 10px below button1
            .rightOf(button1, 5); // 5px right of button1
    }
}
```

## 🆚 SpringPanel vs Raw SpringLayout

### Raw SpringLayout (Verbose & Error-Prone)
```java
public class RawSpringLayoutExample extends JPanel {
    public RawSpringLayoutExample() {
        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        JButton button = new JButton("Click Me");

        // Manual constraint setup
        layout.putConstraint(SpringLayout.NORTH, button, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, button, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, button, -10, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.SOUTH, button, -10, SpringLayout.SOUTH, this);

        SpringLayout.Constraints constraints = layout.getConstraints(button);
        constraints.setWidth(Spring.constant(100));
        constraints.setHeight(Spring.constant(30));

        add(button);
    }
}
```

### SpringPanel (Clean & Intuitive)
```java
public class SpringPanelExample extends SpringPanel {
    public SpringPanelExample() {
        JButton button = new JButton("Click Me");
        
        with(button)
            .top(10)
            .left(10)
            .width(100)
            .height(30);
    }
}
```

## 🎯 Advanced Examples

### Complex Layout
```java
public class ComplexLayout extends SpringPanel {
    public ComplexLayout() {
        // Header
        JPanel header = new JPanel();
        with(header)
            .top(0)
            .left(0)
            .right(0)
            .height(60);

        // Declare panels before positioning
        JPanel content = new JPanel();
        JPanel sidebar = new JPanel();

        // Content area
        with(content)
            .below(header, 0)
            .left(0)
            .leftOf(sidebar, 0)
            .bottom(0);

        // Sidebar
        with(sidebar)
            .below(header, 0)
            .width(200)
            .right(0)
            .bottom(0);
    }
}
```

### Baseline Alignment
```java
public class BaselineExample extends SpringPanel {
    public BaselineExample() {
        // Perfect text alignment between label and field
        JLabel label = new JLabel("Name:");
        JTextField field = new JTextField();

        with(label)
            .top(10)
            .left(10);

        with(field)
            .baseline(label)      // Align text baselines
            .rightOf(label, 10)
            .width(200);
    }
}
```

## 🛠️ Implementation

SpringPanel extends JPanel and internally manages:

1. **SpringLayout Setup**: Automatically sets SpringLayout as the layout manager
2. **Component Registration**: Adds components to the panel if not already added
3. **Constraint Application**: Validates and applies constraints via a fluent builder
4. **Null Safety**: Throws descriptive exceptions for null or invalid components

## 📋 Features

- ✅ Fluent API design
- ✅ Method chaining support
- ✅ All SpringLayout constraints
- ✅ Relative and absolute positioning
- ✅ Baseline alignment
- ✅ Size scaling factors
- ✅ Built-in validation
- ✅ Fill helpers for common layout patterns
- ✅ Zero external dependencies

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.
