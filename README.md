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
    o
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation("com.github.MTRILogic:SpringPanel2:Tag")
    o
    implementation 'com.github.MTRILogic:SpringPanel2:Tag'
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
    <version>Tag</version>
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

### Positioning Constraints
- `north(padding)` - Distance from top edge
- `south(padding)` - Distance from bottom edge
- `east(padding)` - Distance from right edge
- `west(padding)` - Distance from left edge
- `horizontalCenter(offset)` - Center horizontally
- `verticalCenter(offset)` - Center vertically
- `baseline(component, offset)` - Align text baselines
- `x(offset)` - Absolute X position
- `y(offset)` - Absolute Y position

### Sizing Constraints
- `width(size)` - Fixed width
- `width(size, factor)` - Scaled width
- `height(size)` - Fixed height
- `height(size, factor)` - Scaled height

### Relative Positioning
All constraints accept an optional reference component:

```java
public class RelativePositioningExample extends SpringPanel {
    public RelativePositioningExample() {
        JButton button1 = new JButton("First");
        JButton button2 = new JButton("Second");

        with(button1)
            .north(10)
            .west(10);

        with(button2)
            .north(button1, 10)  // 10px below button1
            .west(button1, 5);   // 5px right of button1
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
            .north(10)
            .west(10)
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
            .north(0)
            .west(0)
            .east(0)
            .height(60);
            
        // Content area
        JPanel content = new JPanel();
        JPanel sidebar = new JPanel();
        
        with(content)
            .north(header, 0)
            .west(0)
            .east(sidebar, 0)
            .south(0);
            
        // Sidebar
        with(sidebar)
            .north(header, 0)
            .width(200)
            .east(0)
            .south(0);
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
            .north(10)
            .west(10);

        with(field)
            .baseline(label, 0)  // Align text baselines
            .west(label, 10)
            .width(200);
    }
}
```

## 🛠️ Implementation

SpringPanel extends JPanel and internally manages:

1. **Component Tracking**: Stores the current component being configured
2. **Constraint Collection**: Validate and apply restrictions as they are added

## 📋 Features

- ✅ Fluent API design
- ✅ Method chaining support
- ✅ All SpringLayout constraints
- ✅ Relative and absolute positioning
- ✅ Baseline alignment
- ✅ Size scaling factors
- ✅ Built-in validation
- ✅ Thread-safe constraint application
- ✅ Zero external dependencies

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.
