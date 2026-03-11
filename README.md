# Proyecto 1 

## Board Game Cafe Management System

Este repositorio contiene el desarrollo del Proyecto 1 del curso, correspondiente a un sistema de gestión para un Board Game Cafe. La primera entrega se centra en el análisis del sistema y la construcción del modelo de dominio.

---

##  Estructura del Proyecto

El repositorio está organizado por entregas, cada una conteniendo la documentación técnica y los diagramas correspondientes:

```text
Proyecto 1/
├── 📁 Entrega 1/
│   ├── 🖼️ Modelo de Dominio.png       # Diagrama de clases (Modelo de dominio)
│   └── 📄 Documento de Analisis.pdf   # Requerimientos funcionales y restricciones
│
├── 📁 Entrega 2/
│   ├── 🖼️ Modelo de Dominio.png       # Diagrama de clases actualizado
│   ├── 📄 Documento de Analisis.pdf   # Documento técnico completo
│   ├── ⚙️ Modelo de Dominio.uml       # Archivo fuente del diagrama (PlantUML/StarUML)
│   └── 📂 recursos/                   # Otros activos del análisis
│
└── 📘 README.md                       # Guía principal del proyecto
---

##  Estado Actual

Actualmente, el proyecto se encuentra en la **Entrega 1**, que corresponde al análisis del sistema. En esta etapa se ha desarrollado:

-  **Modelo de dominio (diagrama de clases conceptual)**  
  Incluye las entidades principales del sistema, sus atributos y relaciones, a partir del caso de estudio.

-  **Documento de análisis**  
  Contiene:
  - Requerimientos funcionales por cada clase.
  - Restricciones del proyecto

---

## Modelo de Dominio

El modelo de dominio incluye entidades como:

- `Usuario` (con herencia: `Administrador`, `Empleado`, `Cliente`)
- `Juego` (con herencia: `JuegoCartas`, `JuegoTablero`, `JuegoAccion`)
- `Mesa`, `Prestamo`, `Venta`, `Platillo`, `Turno`, entre otras.

Las relaciones incluyen asociaciones, herencias, composiciones y agregaciones necesarias para reflejar la lógica del negocio.

---

##  Equipo

- Ángela Valentina Bustos Giraldo
- [Nombre del estudiante 2]
- [Nombre del estudiante 3]

---

## 📚 Curso

Programación Orientada a Objetos  
Universidad de los Andes  
Profesor: Iván Salazar
