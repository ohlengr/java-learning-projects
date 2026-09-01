# Project 04 — File Organizer

A console-based File Organizer built with Java to practice **Java NIO, file system operations, path handling, file categorization, and exception handling**.

The application takes a folder as input and organizes files into categorized directories based on their file extensions.

## 🎯 Project Goal

The goal of this project is to gain practical experience working with the Java file system APIs while gradually building a useful CLI application.

Example:

```text
Downloads/
├── photo.jpg
├── resume.pdf
├── song.mp3
├── movie.mp4
├── notes.txt
└── program.java
```

After organization:

```text
Downloads/
├── Images/
│   └── photo.jpg
├── Documents/
│   ├── resume.pdf
│   └── notes.txt
├── Audio/
│   └── song.mp3
├── Videos/
│   └── movie.mp4
└── Code/
    └── program.java
```

## 📚 Concepts Practiced

* `Path`
* `Paths`
* `Files`
* Java NIO (`java.nio.file`)
* File and directory validation
* Directory traversal
* `DirectoryStream`
* `Files.list()`
* `Files.walk()`
* File extensions
* `enum`
* File categorization
* Directory creation
* Moving files
* `Files.move()`
* Exception handling
* Handling duplicate filenames
* Safe file operations
* CLI input with `Scanner`
* Basic layered/project structure

## 🚧 Development Phases

### Phase 1 — Read a Folder

* Accept folder path from the user
* Convert input into a `Path`
* Check whether the path exists
* Check whether it is a directory
* Validate invalid paths

### Phase 2 — List Files

* Read files from the directory
* Distinguish files from directories
* Display filenames
* Practice `DirectoryStream` and `Files.list()`

### Phase 3 — Categorize Files

Create categories based on file extensions.

Example:

```text
Images     → jpg, jpeg, png, gif
Documents  → pdf, txt, doc, docx
Audio      → mp3, wav
Videos     → mp4, mkv, avi
Code       → java, js, py, go
Archives   → zip, rar, 7z
```

### Phase 4 — Create Category Directories

Automatically create directories such as:

```text
Images/
Documents/
Audio/
Videos/
Code/
Archives/
```

### Phase 5 — Move Files

Move each file into its appropriate category directory.

The application should safely handle:

* Existing destination files
* Duplicate filenames
* Unsupported file types
* File system errors

### Phase 6 — Recursive File Organization

Extend the application to work with nested directories using:

```text
Files.walk()
```

Consider how to prevent the organizer from accidentally processing the directories it creates itself.

### Phase 7 — Safety & Error Handling

Add robust handling for:

* Invalid paths
* Missing directories
* Permission errors
* Duplicate files
* Unsupported extensions
* Files that cannot be moved

Introduce a **dry-run / safe mode** that shows what would happen without actually moving files.

### Phase 8 — Refactoring

Refactor the application into clean Java classes and responsibilities.

Possible structure:

```text
FileOrganizer
├── FileScanner
├── FileCategorizer
├── FileMover
└── FileCategory
```

The final structure will be decided during development rather than creating unnecessary abstractions upfront.

## 🛠️ Technologies

* Java
* Java NIO
* `java.util.Scanner`
* Git
* GitHub

## ▶️ Running the Project

Clone the repository:

```bash
git clone <repository-url>
```

Navigate into the project:

```bash
cd 04-file-organizer
```

Compile and run the application using your preferred Java development environment.

## 🧪 Example Usage

```text
===== File Organizer =====

Enter folder path:
C:\Users\User\Downloads

Folder exists: true
Is directory: true
```

Later versions will provide functionality such as:

```text
===== File Organizer =====

Enter folder path:
C:\Users\User\Downloads

Found 24 files.

Organizing files...

photo.jpg      → Images/
resume.pdf     → Documents/
song.mp3       → Audio/
movie.mp4      → Videos/
program.java   → Code/

Organization completed successfully.
```

## 🎓 Learning Objective

This project is primarily a **learning project** focused on understanding Java's file-system APIs and applying them in a practical application.

The implementation will be developed incrementally, with each phase introducing new Java concepts.

## 📌 Project Status

🚧 **In Progress**

Current phase:

```text
Phase 1 — Read a Folder
```

## 🔮 Future Improvements

Potential improvements after completing the core project:

* Configuration file for custom categories
* Custom extension mappings
* Interactive organization preview
* Undo/revert functionality
* Logging
* Command-line arguments
* Recursive organization options
* Improved duplicate-file strategies
* Unit tests
* Packaging as a standalone CLI application
