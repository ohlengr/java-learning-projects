import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.sql.Array;
import java.util.*;
import java.util.stream.Stream;

public class FileOrganizer {

    Map<String, FileCategory> fileCategoryMap = new HashMap<>();
    
    public FileOrganizer(){
        fileCategoryMap.put(".jpg", FileCategory.IMAGES);
        fileCategoryMap.put(".jpeg", FileCategory.IMAGES);
        fileCategoryMap.put(".png", FileCategory.IMAGES);
        fileCategoryMap.put(".gif", FileCategory.IMAGES);

        fileCategoryMap.put(".pdf", FileCategory.DOCUMENTS);
        fileCategoryMap.put(".txt", FileCategory.DOCUMENTS);
        fileCategoryMap.put(".doc", FileCategory.DOCUMENTS);
        fileCategoryMap.put(".docx", FileCategory.DOCUMENTS);

        fileCategoryMap.put(".mp3", FileCategory.AUDIO);
        fileCategoryMap.put(".wav", FileCategory.AUDIO);

        fileCategoryMap.put(".mp4", FileCategory.VIDEOS);
        fileCategoryMap.put(".mkv", FileCategory.VIDEOS);
        fileCategoryMap.put(".avi", FileCategory.VIDEOS);

        fileCategoryMap.put(".java", FileCategory.CODE);
        fileCategoryMap.put(".py", FileCategory.CODE);
        fileCategoryMap.put(".js", FileCategory.CODE);
        fileCategoryMap.put(".go", FileCategory.CODE);

        fileCategoryMap.put(".zip", FileCategory.ARCHIVES);
        fileCategoryMap.put(".rar", FileCategory.ARCHIVES);
        fileCategoryMap.put(".7z", FileCategory.ARCHIVES);
    }

    public int isValidDirectory(String folderPath){
        Path path = Paths.get(folderPath);
        if(Files.exists(path) && Files.isDirectory(path)){
            return 1;
        }else if(Files.exists(path)){
            return 2;
        }else {
            return 0;
        }
    }

    public List<String> listFolderFiles(Path path){
        List<String> list = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for(Path entry : stream) {
                boolean isDirectory = Files.isDirectory(entry);
                String prefix = ((isDirectory)?"[D] ":"[F] ");
                if(!isDirectory){
                    FileCategory fileCategory = getFileCategory(entry.getFileName().toString());
                    list.add(prefix + entry.getFileName() + " -> " + fileCategory);
                }else {
                    list.add(prefix + entry.getFileName());
                }
            }
            return list;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public FileCategory getFileCategory(String fileName){
        int dotIndex = fileName.lastIndexOf(".");
        String extension = (dotIndex>0)?fileName.substring(dotIndex):"";
        if(!extension.isEmpty() && fileCategoryMap.containsKey(extension.toLowerCase())){
            return fileCategoryMap.get(extension.toLowerCase());
        }else {
            return FileCategory.OTHERS;
        }
    }

    public void createCategoryDirectories(Path folderPath){
        try {
            FileCategory[] fileCategory = FileCategory.values();
            for (FileCategory category : fileCategory ){
                Path categoryDirectoryPath = folderPath.resolve(category.toString());
                Files.createDirectories(categoryDirectoryPath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void organizeFiles(Path folderPath){
        List<Path> list = findFilesRecursively(folderPath);
        for(Path path : list) {
            FileCategory fileCategory = getFileCategory(path.getFileName().toString());
            Path categoryPath = folderPath.resolve(fileCategory.toString());
            try {
                Files.createDirectories(categoryPath);
                Path destinationPath = categoryPath.resolve(path.getFileName().toString());
                Path uniqueDestinationPath = getUniqueDestination(destinationPath);
                if(!uniqueDestinationPath.getParent().equals(categoryPath)){
                    Files.move(path, uniqueDestinationPath);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Path> findFilesRecursively(Path folderPath){
        List<Path> list = new ArrayList<>();
        try (Stream<Path> stream = Files.walk(folderPath)){
            stream.filter(Files::isRegularFile).forEach(list::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Path getUniqueDestination(Path destinationPath){
        Path parent = destinationPath.getParent();
        String fileName = destinationPath.getFileName().toString();

        int dotIndex = fileName.lastIndexOf(".");

        String baseFile = fileName;
        String extension = "";

        if(dotIndex>0){
            baseFile = fileName.substring(0,dotIndex);
            extension = fileName.substring(dotIndex);
        }

        int count = 1;
        while (Files.exists(destinationPath)) {
            String newDestination = baseFile+"_"+count+extension;
            destinationPath = parent.resolve(newDestination);
            count++;
        }
        return destinationPath;
    }
}
