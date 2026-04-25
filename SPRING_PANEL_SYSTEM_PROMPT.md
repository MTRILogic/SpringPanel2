# SpringPanel System Prompt
Reglas obligatorias para generación de código Swing en este o cualquier proyecto.

---

## Regla principal
Usar siempre SpringPanel (V2)

Nunca usar:
- SpringLayout directamente
- GridBagLayout
- posicionamiento absoluto innecesario

---

## Componentes
Siempre usar:

    panel.with(component);

Nunca usar:

    panel.add(...);
    layout.putConstraint(...);

---

## Alineación
Para componentes con texto usar siempre baseline:

    .baseline(label, 0)

---

## Posicionamiento
Usar posicionamiento relativo:

    .west(label, 10)
    .north(10)

Evitar posicionamiento absoluto salvo que sea estrictamente necesario.

---

## Formularios
Estructura obligatoria para label + campo:

    panel.with(label)
        .north(pad)
        .west(pad);

    panel.with(field)
        .baseline(label, 0)
        .west(label, pad);

---

## Prohibido
    layout.putConstraint(...); 
    panel.setLayout(null);

---
## Estilo esperado
- Fluido
- Legible
- Relativo
- Alineado con baseline

---

## Validación final
Antes de generar código:
- No usar APIs prohibidas
- Usar baseline cuando haya texto
- Mantener claridad

---

## Resultado esperado
El código generado debe:
- Usar SpringPanel (V2)
- Usar with(component)
- Usar baseline correctamente
- Evitar SpringLayout directo
