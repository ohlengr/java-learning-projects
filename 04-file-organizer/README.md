# Project 04 — File Organizer

> **Status:** ✅ Completed

A Java CLI application that automatically organizes files into category folders using Java NIO.

This project was built to practice real-world file system operations, recursive directory traversal, Java Streams, enums, collections, and clean application architecture.

---

## Features

- Validate folder paths
- List files and directories
- Categorize files by extension
- Create category folders automatically
- Organize files recursively
- Handle duplicate filenames safely
- Skip files already in the correct category
- Display organization summary after completion

---

## Categories

The organizer currently supports:

| Category | Extensions |
|----------|------------|
| IMAGES | `.jpg`, `.jpeg`, `.png`, `.gif` |
| DOCUMENTS | `.pdf`, `.txt`, `.doc`, `.docx` |
| AUDIO | `.mp3`, `.wav` |
| VIDEOS | `.mp4`, `.mkv`, `.avi` |
| CODE | `.java`, `.py`, `.js`, `.go` |
| ARCHIVES | `.zip`, `.rar`, `.7z` |
| OTHERS | Unknown or unsupported extensions |

---

## Example

### Before

```text
Downloads/
├── photo.jpg
├── resume.pdf
├── song.mp3
├── Main.java
├── archive.zip
└── Work/
    └── report.pdf
```

### After

```text
Downloads/
├── IMAGES/
│   └── photo.jpg
├── DOCUMENTS/
│   ├── resume.pdf
│   └── report.pdf
├── AUDIO/
│   └── song.mp3
├── CODE/
│   └── Main.java
├── ARCHIVES/
│   └── archive.zip
└── Work/
```

Duplicate files are automatically renamed:

```text
photo.jpg
photo_1.jpg
photo_2.jpg
```

---

## Menu

```text
FILE ORGANIZER
==============

1. Check Folder
2. List Files
3. Create Category Directories
4. Organize Files
9. Exit
```

---

## Project Structure

```text
04-file-organizer/
├── src/
│   ├── Main.java
│   ├── FileConsole.java
│   ├── FileOrganizer.java
│   ├── FileCategory.java
│   └── DirectoryStatus.java
├── README.md
└── .gitignore
```

---

## Concepts Practiced

### Java Core

- Classes
- Methods
- Enums
- Collections
- Exception Handling

### Java Collections

- `ArrayList`
- `HashMap`
- `Map`

### Java NIO

- `Path`
- `Paths`
- `Files.exists()`
- `Files.isDirectory()`
- `DirectoryStream`
- `Files.walk()`
- `Files.createDirectories()`
- `Files.move()`
- `Path.resolve()`

### Streams

- `Stream<Path>`
- `filter()`
- Method references (`Files::isRegularFile`)

### Software Design

- Layered architecture
- Service layer
- CLI separation
- Recursive file processing
- Idempotent operations
- Duplicate file handling

---

## What I Learned

This project helped me understand how Java interacts with the operating system through the NIO API.

Key takeaways include:

- Working with `Path` instead of raw strings.
- Traversing directories recursively using `Files.walk()`.
- Organizing files safely without overwriting existing files.
- Using enums instead of magic numbers for clearer code.
- Separating user interaction from business logic.

---

## Next Project

➡️ **Project 05 — URL Checker**

The next project focuses on Java networking and HTTP using `HttpClient`, covering:

- HTTP requests
- Status codes
- Response time measurement
- Error handling
- Multiple URL checking
- Network programming fundamentals