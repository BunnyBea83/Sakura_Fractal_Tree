# Sakura Fractal Tree GUI

A Java project developed in IntelliJ IDEA.

Course: Computer Programming CSC143   
Instructor: Bill Barry   
Author: Bea Sauve  
Date: 05/2025

---

An interactive recursive fractal tree generator...

![Sakura Fractal Tree](resources/saurka_Fractal_Tree.png)

---

## Project Structure

## Project Structure

```
Project04_SakuraVersion/
├── .idea/                          # IntelliJ IDEA project configuration (IDE settings, not source code)
├── out/                            # Compiled Java bytecode output (auto-generated, not committed to Git)
├── resources/                      # Static assets used at runtime (images, audio, config files, etc.)
├── src/                            # Java source code (.java files)
├── .gitignore                      # Git ignore rules for IDE files, build output, and OS artifacts
└── Project04_SakuraVersion.iml     # IntelliJ module definition file
```

---

## Prerequisites

- **Java JDK** (version compatible with your IntelliJ setup)
- **IntelliJ IDEA** (Community or Ultimate edition)

---

## Getting Started

### Opening the Project

1. Clone or download this repository.
2. Open **IntelliJ IDEA**.
3. Select **File → Open** and navigate to the project root folder.
4. IntelliJ will detect the `.iml` file and configure the project automatically.

### Building the Project

- Use **Build → Build Project** (or `Ctrl+F9` / `Cmd+F9`) in IntelliJ.
- Compiled `.class` files will be placed in the `out/` directory.

### Running the Project

- Locate the main class in the `src/` directory.
- Right-click the file and select **Run**, or use the Run toolbar at the top of the IDE.

---
 
## Using the GUI
 
When the program launches, a window displays a fractal tree on a sakura-themed background, and music plays. A control panel on the left side lets you customize the tree in real time.
 
### Sliders
 
| Slider | Range | What it does |
|---|---|---|
| **Recursion Depth** | 4 – 20 | Controls how many times the tree branches. Higher values create denser, more detailed trees. |
| **Child to Parent Ratio** | 40 – 80 | Sets how long each child branch is relative to its parent (as a percentage). Higher = longer branches. |
| **Left Child Angle** | 0 – 90 | The angle (in degrees) at which the left branch splits from its parent. |
| **Right Child Angle** | 0 – 90 | The angle (in degrees) at which the right branch splits from its parent. |
| **Trunk Length** | 100 – 400 | Sets the height of the main trunk in pixels. |
| **Trunk Width** | 10 – 50 | Sets the thickness of the trunk. Branch width scales down with each recursion level. |
 
### Buttons
 
| Button | What it does |
|---|---|
| **Trunk Color** | Opens a color picker to change the color of the trunk and branches. |
| **Leaf Color** | Opens a color picker to change the color of the leaf/blossom tips at the ends of branches. |
| **Randomize** | Randomly sets all sliders and colors at once, generating a unique tree instantly. |
 
### Tips
- Start by hitting **Randomize** to explore the range of possible trees.
- Increasing **Recursion Depth** significantly (above 15) may slow down rendering, as the number of branches grows exponentially.
- Combining a high **Child to Parent Ratio** with a low **Recursion Depth** produces wide, sparse trees. The inverse creates tight, dense canopies.
- Asymmetric left/right angles produce naturally leaning or windswept-looking trees.
 
---

## File & Folder Details

| File / Folder | Purpose |
|---|---|
| `.idea/` | IDE configuration — workspace settings, run configs, code style. Not part of the program. |
| `out/` | Compiler output directory. Generated automatically by IntelliJ; excluded from version control. |
| `resources/` | Runtime assets such as images, audio, or data files used by the program. |
| `src/` | All Java source code for the project lives here. |
| `.gitignore` | Prevents IDE files, build artifacts, and OS-specific files from being tracked by Git. |
| `Project04_SakuraVersion.iml` | IntelliJ module file defining source roots and JDK settings for this project. |

