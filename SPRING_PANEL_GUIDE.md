# SpringPanel Usage Guide
Guía oficial de uso para SpringPanel (v1 y v2).

---

## Propósito
### SpringPanel simplifica el uso de SpringLayout en Swing:
- Reduce boilerplate
- Hace el layout legible
- Evita errores silenciosos
- Permite alineación correcta de texto

---

## Versiones
### SpringPanel (v1 o Legacy)
- Requiere `.apply()`
- Builder con estado

### SpringPanel2 (v2 o normal) (RECOMENDADO)
- No requiere `.apply()`
- Builder sin estado
- API más limpia

---

## Uso básico (v2)
    SpringPanel panel = new SpringPanel();

    panel.with(label)
        .north(10)
        .west(10);

    panel.with(field)
        .baseline(label, 0)
        .west(label, 10);

---

## Posicionamiento
### Relativo al contenedor:
    .north(10)
    .west(10)
    .east(10)
    .south(10)

### Relativo a otro componente:
    .west(label, 10)
    .north(label, 5)

---

## Alineación de texto
### Incorrecto:
    panel.with(field).north(10);
Esto alinea bordes, no texto.
### Correcto:
    panel.with(field)
        .baseline(label, 0)
        .west(label, 10);
Esto alinea el contenido visual.

---

## Formularios (patrón estándar)
    panel.with(label)
        .north(10)
        .west(10);

    panel.with(field)
        .baseline(label, 0)
        .west(label, 10);

---

## Tamaño
    .width(200)
    .height(40)

    .width(100, 2f)
    .height(20, 1.5f)

---

## Posición absoluta (usar con cuidado)
    .x(100)
    .y(50)

---

## Reglas importantes
- Padding debe ser >= 0
- No mezclar layouts
- Usar baseline para texto
- Preferir posicionamiento relativo

---

## Qué evitar
- SpringLayout directo
- setLayout(null)
- GridBagLayout mezclado
- offsets iguales esperando alineación visual

---

## Ejemplo completo
    SpringPanel panel = new SpringPanel();

    JLabel label = new JLabel("Nombre:");
    JTextField field = new JTextField();

    panel.with(label)
        .north(10)
        .west(10);

    panel.with(field)
        .baseline(label, 0)
        .west(label, 10)
        .width(200);
