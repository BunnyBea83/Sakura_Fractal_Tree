# Project04 — Sakura Version

A Java project developed in IntelliJ IDEA.

Course: Computer Programming CSC143   
Instructor: Bill Barry   
Author: Bea Sauve  
Date: 05/2025

---

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

## File & Folder Details

| File / Folder | Purpose |
|---|---|
| `.idea/` | IDE configuration — workspace settings, run configs, code style. Not part of the program. |
| `out/` | Compiler output directory. Generated automatically by IntelliJ; excluded from version control. |
| `resources/` | Runtime assets such as images, audio, or data files used by the program. |
| `src/` | All Java source code for the project lives here. |
| `.gitignore` | Prevents IDE files, build artifacts, and OS-specific files from being tracked by Git. |
| `Project04_SakuraVersion.iml` | IntelliJ module file defining source roots and JDK settings for this project. |

