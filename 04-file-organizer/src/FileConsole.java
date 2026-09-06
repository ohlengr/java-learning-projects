import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
        System.out.println("9. Exit");
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
            default:
                System.out.println("Invalid option!");
            break;
        }
    }

    public void handleFolderPath(){
        scanner.nextLine();
        System.out.println("Enter Folder Path:");
        String folderPath = scanner.nextLine();
        int status = fileOrganizer.isValidDirectory(folderPath);
        if(status==1){
            System.out.println("Path exist!");
            System.out.println("Path is directory!");
        }else if(status==2){
            System.out.println("Path exist!");
        }else {
            System.out.println("Path not exist!");
        }
    }

    public void handleListFiles(){
        scanner.nextLine();
        System.out.println("Enter folder path: ");
        String folderPath = scanner.nextLine();
        int status = fileOrganizer.isValidDirectory(folderPath);
        if(status == 1){
            List<String> fileList = fileOrganizer.listFolderFiles(Paths.get(folderPath));
            for(String file : fileList) {
                System.out.println(file);
            }
        }else{
            System.out.println("Invalid folder path!");
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
        fileOrganizer.organizeFiles(folder);
    }
}
