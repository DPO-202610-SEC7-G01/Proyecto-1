# Proyecto 1 

## Board Game Cafe Management System

Este repositorio contiene el desarrollo del Proyecto 1 del curso, correspondiente a un sistema de gestión para un Board Game Cafe. La primera entrega se centra en el análisis del sistema y la construcción del modelo de dominio.

---

## Estructura del Proyecto

Proyecto 1/
│
├── Entrega 1/
│ ├── Modelo de Dominio.png # Imagen del diagrama de clases (modelo de dominio)
│ ├── Documento de Analisis.pdf # Documento de análisis con RF, restricciones, etc.
│
├── Entrega 2/
│ ├── Modelo de Dominio.png # Imagen del diagrama de clases (modelo de dominio)
│ ├── Documento de Analisis.pdf # Documento de análisis con RF, restricciones, etc.
│ ├── Modelo de Dominio.uml # Archivo fuente del diagrama (si aplica)
│ └── (otros recursos del análisis)
│
└── README.md # Este archivo
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
