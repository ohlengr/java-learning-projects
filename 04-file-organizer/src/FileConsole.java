import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileConsole {
    Scanner scanner;
    FileOrganizer fileOrganizer;
    public FileConsole(Scanner scanner, FileOrganizer fileOrganizer){
        this.scanner = scanner;
        this.fileOrganizer = fileOrganizer;
    }

    public void menu(){
        System.out.println("FILE ORGANIZER");
        System.out.println("===============");
        System.out.println("1. Check Folder Exist or Not");
        System.out.println("2. List Files");
        System.out.println("3. Create Category Directories");
        System.out.println("4. Organize Files");
        System.out.println("5. Exit");
    }

    public int readOption(){
        return scanner.nextInt();
    }

    public void performAction(int option){
        switch (option) {
            case 1:
                handleFolderPath();
            break;
            case 2:
                handleListFiles();
                break;
            case 3:
                handleCreateCategoryDirectory();
                break;
            case 4:
                handleOrganizeFiles();
                break;
            case 5:
                System.out.println("Exit!");
                break;
            default:
                System.out.println("Invalid option!");
            break;
        }
    }

    public void handleFolderPath(){
        scanner.nextLine();
        System.out.println("Enter Folder Path:");
        String folderPath = scanner.nextLine();
        DirectoryStatus directoryStatus = fileOrganizer.isValidDirectory(folderPath);
        switch (directoryStatus) {
            case NOT_FOUND -> System.out.println("✗ Path does not exist");
            case VALID_DIRECTORY -> System.out.println("✓ Valid directory");
            case FILE_EXISTS -> System.out.println("✓ File directory");
        }
    }

    public void handleListFiles(){
        scanner.nextLine();
        System.out.println("Enter folder path: ");
        String folderPath = scanner.nextLine();
        DirectoryStatus directoryStatus = fileOrganizer.isValidDirectory(folderPath);
        if(directoryStatus == DirectoryStatus.VALID_DIRECTORY){
            List<String> fileList = fileOrganizer.listFolderFiles(Paths.get(folderPath));
            for(String file : fileList) {
                System.out.println(file);
            }
        }else{
            System.out.println("✗ Path is a file or it does not exist!");
        }
    }

    public void handleCreateCategoryDirectory(){
        scanner.nextLine();
        System.out.println("Enter folder path: ");
        String folderPath = scanner.nextLine();
        Path folder = Paths.get(folderPath);
        fileOrganizer.createCategoryDirectories(folder);
    }

    public void handleOrganizeFiles(){
        scanner.nextLine();
        System.out.println("Enter folder path: ");
        String folderPath = scanner.nextLine();
        Path folder = Paths.get(folderPath);
        Map<FileCategory, Integer> fileCategoryCount = fileOrganizer.organizeFiles(folder);
        System.out.println("Moved:");
        int totalMoved = 0;
        for(Map.Entry<FileCategory, Integer> entry : fileCategoryCount.entrySet()) {
            FileCategory category = entry.getKey();
            int count = entry.getValue();
            System.out.println(category + ": " + count);
            totalMoved+=count;
        }
        System.out.println("Total file moved: " + totalMoved);
    }
}
