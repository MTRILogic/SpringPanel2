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

NO usar `north()` para alinear texto:

    panel.with(field).north(10);

---

NO mezclar layouts:

    panel.setLayout(new GridBagLayout());
    panel.setLayout(null);

---

NO posicionar componentes relacionados de forma independiente:

    label → north(10)
    field → north(10)

---

## Reglas correctas

Usar SpringPanel (V2):

    SpringPanel panel = new SpringPanel();

---

Usar baseline para texto:

    .baseline(label, 0)

---

Usar posicionamiento relativo:

    .west(label, 10)

---

Mantener código legible:

    panel.with(field)
        .baseline(label, 0)
        .west(label, 10);

---

## Errores comunes
- Mismo offset NO implica alineación visual
- Diferentes tamaños rompen alineación
- Uso excesivo de x/y

---

## Anti-pattern
    panel.with(label)
        .north(10)
        .west(10);

    panel.with(field)
        .north(10)
        .west(120);

---

## Correcto
    panel.with(field)
        .baseline(label, 0)
        .west(label, 10);

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
