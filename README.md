# 🚓 En-mode-J Compiler

![Java](https://img.shields.io/badge/Java-17+-ED8B00?logo=java&logoColor=white)
![ANTLR4](https://img.shields.io/badge/ANTLR-4.9.3-red)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?logo=apachemaven&logoColor=white)
![MicroPython](https://img.shields.io/badge/Target-MicroPython-2B5B84?logo=python&logoColor=white)
![University](https://img.shields.io/badge/University-UNamur-blue)

> **Academic compiler project** — A full compiler for **En-mode-J**, an emoji-based domain-specific language that compiles to **MicroPython** for CuteBot robots.

---

## 🌟 What is En-mode-J ?

En-mode-J is a Domain-Specific Language where **every keyword is an emoji**. Instead of writing `if`, `while`, or `for`, you use:

| Emoji | Meaning |
|-------|---------|
| 🤔 | `if` |
| 🤨 | `else` |
| ♾️ | `while` |
| 🔁 | `for` |
| 🏠 | `main function` |
| 🌀 | `void` |
| ↩️ | `return` |
| 📦 | `import` |
| ✋ | `arrest thief` |
| 🚨 | `toggle lights` |
| 📻 | `toggle sound` |
| ⬆️ ⬇️ ➡️ ⬅️ | `move robot` |

---

##  Project Overview

This compiler translates **En-mode-J source code** (`.moj` files) into **MicroPython** (`.py` files) that run on **CuteBot robots** — small autonomous vehicles built on the micro:bit microcontroller.

### Compilation pipeline

```
Source code (.moj)
      ↓
Lexical Analysis     ← ANTLR4 Lexer (EMJLexer.g4)
      ↓
Syntax Analysis      ← ANTLR4 Parser (EMJParser.g4)
      ↓
Abstract Syntax Tree
      ↓
Semantic Analysis    ← Visitor Pattern + Symbol Table
      ↓
Code Generation      ← EMJPrinter → MicroPython output
      ↓
output.py (MicroPython)
```

---

## 💻 Example

### Input — En-mode-J source code

```
📦 "demo.map"

🌀 🏠 () {

    🔢 [🚗] = 0 ;

    🔁(3) {
        ➡️(1) ;
        [🚗] = [🚗] + 1 ;
    }

    ✋() ;

    ↩️ 🌀 ;
}
```

### Output — Generated MicroPython

```python
import demo.map

def main():
    var_1 = 0

    for i in range(3):
        ## RIGHT ############
        cuteBot.move_time(cuteBot.Direction.RIGHT, 50, 0.2)
        basic.pause(200)
        cuteBot.move_time(cuteBot.Direction.FORWARD, 50, 1)
        var_1 = var_1 + 1

    ## ARREST ############
    ...

main()
```

---

##  Game Map

The robot navigates a grid map defined in a `.map` file using emojis:

| Emoji | Element |
|-------|---------|
| 🚓 | Police car (robot start position) |
| 🛣️ | Road (walkable) |
| 🦹 | Thief (target to arrest) |
| 🌋 | Volcano (obstacle) |
| 🏘️ | Houses (obstacle) |
| 🚧 | Construction (obstacle) |
| 🌊 | Water (obstacle) |
| 🚜 | Tractor (obstacle) |

---

## 🏗️ Architecture

```
src/main/
├── antlr4/
│   ├── EMJLexer.g4          ← Lexical grammar (tokens & emojis)
│   └── EMJParser.g4         ← Syntactic grammar (rules)
│
├── java/be/unamur/info/b314/compiler/
│   ├── main/
│   │   └── Main.java        ← Entry point, CLI argument parsing
│   │
│   ├── emj/
│   │   ├── EMJVisitor.java      ← Semantic analysis + code generation
│   │   ├── EMJPrinter.java      ← MicroPython code generator
│   │   ├── EMJMapPrinter.java   ← Map file parser
│   │   ├── EMJError.java        ← Error types
│   │   └── EMJErrorLogger.java  ← Error logging
│   │
│   ├── table_symbole/
│   │   ├── SymbolTable.java     ← Symbol table implementation
│   │   └── Variable.java        ← Variable representation
│   │
│   └── exception/
│       ├── EMJErrorException.java
│       ├── ParsingException.java
│       └── SymbolNotFoundException.java
```

---

## ⚙️ Data Types

| Emoji | Type |
|-------|------|
| 🔢 | Integer |
| 🔣 | Character |
| 🔡 | String |
| 🔟 | Boolean |
| 👥 | 2-tuple |

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven 3.x
- IntelliJ IDEA (recommended) with ANTLR v4 plugin

### Build

```bash
mvn package -DskipTests
```

### Run

```bash
java -jar target/en-mode-j-1.0.0-jar-with-dependencies.jar -i your_program.moj -o output.py
```

### Arguments

| Argument | Description |
|----------|-------------|
| `-i <file>` | Input `.moj` source file |
| `-o <file>` | Output `.py` MicroPython file |
| `-h` | Display help message |

### Visualize the Parse Tree (IntelliJ)

1. Install the **ANTLR v4** plugin in IntelliJ
2. Open `EMJParser.g4`
3. Right-click on the `programme` rule → **Test Rule**
4. Paste your `.moj` code
5. The AST appears on the right panel

---

##  Compiler Output

If the program is **valid**:
```
OK
```
And `output.py` is generated.

If the program is **invalid**:
```
Error: Variable 'x' not found
KO
```

---

##  Semantic Checks

The compiler verifies:

- No duplicate variable names within a function
- No use of undeclared variables
- Type compatibility in assignments
- Correct function call argument types
- Return type matches function declaration
- No division by zero
- Reserved names (`import`, `main`) not used as identifiers
- 2-tuple access only on tuple-typed variables
- loops and conditions 
---

## Target Platform

The generated MicroPython runs on **CuteBot robots** — wheeled robots based on the **BBC micro:bit** microcontroller, capable of:

- Moving forward, backward, left, right
- Detecting obstacles with ultrasonic sensors
- Toggling lights (LED gyrophare)
- Playing sounds (siren)

---

## Team

Project developed as part of **INFOB314 — Syntaxe et Sémantique des Langages**  
**Université de Namur** — Academic year 2023-2024  


---

*Java · ANTLR4 · Maven · MicroPython · micro:bit*