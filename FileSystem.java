import java.util.*;

public class FileSystem {
    class File{
        boolean isFile=false;
        String fileName;
        String content="";
        public Map<String,File> filenameVsFile=new HashMap<>();
        int noOfFiles=0;
        int noOfDirectories=0;

    }
    File root;
    public FileSystem(){
        root=new File();
    }
    // A makeDirectory Function
    public void mkDirs(String path) {

        File temp = root;
        String subpath[] = path.split("/");
        int pathlen = subpath.length;
        //Assuming path is starting with /
        for (int i = 1; i < pathlen; i++) {
            String midPath = subpath[i];
            if (!temp.filenameVsFile.containsKey(midPath)) {
                temp.fileName = midPath;
                temp.filenameVsFile.put(midPath, new File());
                temp.noOfDirectories+=1;
            }

            temp = temp.filenameVsFile.get(midPath);
        }
    }

    //A file listing function
    public List<String> listAllFiles(String path){

        File temp=root;
        String subpath[]=path.split("/");
        int pathlen=subpath.length;
        List<String> allFiles=new ArrayList<>();
        //Assuming path is starting with /
        if(!path.equals("/")) {
            for (int i=1;i<pathlen;i++){
                String midPath=subpath[i];
                temp=temp.filenameVsFile.get(midPath);
            }
            // if last pathName is itself a file
            if(temp.isFile){
                allFiles.add(temp.fileName);
                return allFiles;
            }
        }
        allFiles=new ArrayList<>(temp.filenameVsFile.keySet());
        Collections.sort(allFiles);
        System.out.println("Total directories "+temp.noOfDirectories);
        System.out.println("Total files "+ temp.noOfFiles);
        return allFiles;

    }

    public void addFileInDirectory(String path, String content){

        File temp=root;

        String subpath[]=path.split("/");
        int pathlen=subpath.length;
        System.out.println(subpath[0]);
        //Assuming path is starting with /
        for (int i=1;i<pathlen-1;i++) {
            String midPath = subpath[i];
            temp = temp.filenameVsFile.get(midPath);
        }

        String fileName=subpath[subpath.length-1];
        File newFile=new File();
        newFile.isFile=true;
        newFile.fileName=fileName;
        newFile.content=content;  //appending new Content;

        if(!temp.filenameVsFile.containsKey(fileName)){
            temp.filenameVsFile.put(fileName,newFile);
        }
        temp.noOfFiles+=1;
        return;
    }
    public void deleteFileInDirectory(String path){

        File temp=root;

        String subpath[]=path.split("/");
        int pathlen=subpath.length;

        //Assuming path is starting with /
        for (int i=1;i<pathlen-1;i++) {
            String midPath = subpath[i];
            temp = temp.filenameVsFile.get(midPath);
        }
        String removeFileName=subpath[subpath.length-1];
        temp.filenameVsFile.remove(removeFileName);
        temp.noOfFiles-=1;
        return;
    }

    public static void main(String[] args) {

        FileSystem system=new FileSystem();

        //adding new directory
        String path="/yash/16092023";
        system.mkDirs(path);


        //File going to be added
        String filePath1="/yash/16092023/salary.txt";
        String filePath2="/yash/16092023/employee.txt";
        system.addFileInDirectory(filePath1,"Your salary is 20 lpa");
        system.addFileInDirectory(filePath2,"Yash Akash Hrithik");

        System.out.println("Listing all files inside a directory:");
        system.listAllFiles("/yash/16092023").stream().forEach(e-> System.out.println(e));

        //Deleting file inside a directory
        String deleteFilePath="/yash/16092023/salary.txt";
        system.deleteFileInDirectory(deleteFilePath);

        System.out.println("Listing all files inside a directory after deletion salary.txt:");
        system.listAllFiles("/yash/16092023").stream().forEach(e-> System.out.println(e));

        //adding new directory
        path="/yash/17092023";
        system.mkDirs(path);
        filePath1="/yash/17092023/weather.txt";
        filePath2="/yash/17092023/cars.txt";
        system.addFileInDirectory(filePath1,"Today weather is 15 degree celsius");
        system.addFileInDirectory(filePath2,"Sonet Seltos i20");

        system.listAllFiles("/yash/17092023").stream().forEach(e-> System.out.println(e));

        //adding new directory
        path="/yash/17092023/data";
        system.mkDirs(path);
        system.listAllFiles("/yash/17092023").stream().forEach(e-> System.out.println(e));


    }

}
