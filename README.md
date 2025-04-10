# Robot – Obstacle and Movement Game with Object-Oriented Programming (Java)

Academic project developed for the Object-Oriented Programming course.  
The goal is to build a grid-based robot game in Java, where robots move around a 4x4 matrix, avoid obstacles, and search for food.  
The project applies key OOP concepts such as exceptions, inheritance, polymorphism, and abstract classes.

---

## Objective

Control one or two robots in a 4x4 grid. The robots must navigate the space, avoid obstacles, and reach a food item placed by the user.

- The regular robot moves randomly after hitting obstacles.
- The intelligent robot adapts its movement to avoid repeating invalid actions.

---

## Features

- Robot representation with position and color
- Movement by text or numeric commands (method overloading)
- Food placement chosen by the user
- Custom exception handling via `MovimentoInvalidoException`
- Collision detection with obstacles:
  - Bomb: destroys or paralyzes the robot
  - Rock: blocks movement
- Grid display showing robots and obstacles
- `RoboInteligente` subclass that avoids repeating invalid moves
- Multiple game modes, each in a separate `Main` class:
  - Manual control of a single robot
  - Two robots moving randomly
  - Regular robot vs. intelligent robot (no obstacles)
  - Regular robot vs. intelligent robot (with obstacles)

---

## Topics Covered

- Object-Oriented Programming (OOP)
  - Classes and objects
  - Inheritance
  - Abstract classes
  - Polymorphism
  - Exception handling
  - Method overloading

---

## Technologies

- Java  
- Eclipse IDE

---

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/J0Vict0r/NPC1-TRABALHO-2-ROBO.git
