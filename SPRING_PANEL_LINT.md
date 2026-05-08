# SpringPanel Lint Rules
Reglas estrictas para evitar errores en layouts con SpringPanel.

---

## Reglas prohibidas

NO usar SpringLayout directamente:

    new SpringLayout();

---

NO olvidar `.apply()` en SpringPanel (V1 o Legacy):

    panel.with(button).north(10); // incorrecto

---

NO usar `top()` para alinear texto:

    panel.with(field).top(10);

---

NO mezclar layouts:

    panel.setLayout(new GridBagLayout());
    panel.setLayout(null);

---

NO posicionar componentes relacionados de forma independiente:

    label → top(10)
    field → top(10)

---

## Reglas correctas

Usar SpringPanel (V2):

    SpringPanel panel = new SpringPanel();

---

Usar baseline para texto:

    .baseline(label)

---

Usar posicionamiento relativo:

    .rightOf(label, 10)

---

Mantener código legible:

    panel.with(field)
        .baseline(label)
        .rightOf(label, 10);

---

## Errores comunes
- Mismo offset NO implica alineación visual
- Diferentes tamaños rompen alineación
- Uso excesivo de x/y

---

## Anti-pattern
    panel.with(label)
        .top(10)
        .left(10);

    panel.with(field)
        .top(10)
        .left(120);

---

## Correcto
    panel.with(field)
        .baseline(label)
        .rightOf(label, 10);

---

## Reglas de decisión
1. Si es Swing → usar SpringPanel (V2)
2. Si hay texto → usar baseline
3. Si están relacionados → usar constraints relativos
4. Si el layout se ve mal → reescribir

---

## Objetivo
- UI limpia
- alineación correcta
- evitar bugs visuales
