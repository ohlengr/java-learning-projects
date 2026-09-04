import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
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
}
