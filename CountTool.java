import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class CountTool{
	
	public static int CountJava(List<File> javafiles) {
		return javafiles.size();
	}
	public static int CountUniqueJava(List<File> javafiles, Set<File> Uniquejavafiles) throws IOException {
		
		Uniquejavafiles.addAll(javafiles);
		
		if (javafiles.size()==0||javafiles.size()==1) {
			return Uniquejavafiles.size();
		}
		else {
			for (File s1 : javafiles) {
				if (!Uniquejavafiles.contains(s1)) {
					continue;
				}
	            //System.out.println(s1);
		        for (File s2 : javafiles) {
		        	if (s1==s2) {
		        		continue;
		        	}
		        	else if (sameContent(s1.toPath(),s2.toPath())) {
		        		Uniquejavafiles.remove(s2);
		        	}
		        }
	        }

		}
		return Uniquejavafiles.size();
	}
	public static void BlankLines() {
		
	}
	public static void CommentLines() {
		
	}
	public static void CodeLines() {
		
	}
	private static boolean sameContent(Path file1, Path file2) throws IOException {
	    return Files.mismatch(file1, file2) == -1;
	}
	
    public static void searchDirectory(final File file, List<File> javafiles,final String pattern) {
        try {
        	for (final File f : file.listFiles()) {

        		if (f.isDirectory()) {
        			searchDirectory(f, javafiles,pattern);
        		}

        		if (f.isFile()) {
        			if (f.getName().matches(pattern)) {
        				javafiles.add(f);
        			}
        		}

        	}
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
    }

    public static void checkFile(final File file,List<File> javafiles) {
    	System.out.println(file.getName().toString()); 
    	javafiles.add(file);
    	
    }
	
	public static void main(String args[]) {
		System.out.println("Your first argument is: "+args[0]);  
		File file = new File(args[0]);

		boolean file_exists = file.exists();      // Check if the file exists
		boolean isDirectory = file.isDirectory(); // Check if it's a directory
		boolean isFile =      file.isFile();      // Check if it's a regular file
		
		System.out.println(file_exists+""+isDirectory+""+isFile);
		
		List<File> javafiles = new ArrayList<>();
		Set<File> Uniquejavafiles = new TreeSet<>(); 
		
		if (isDirectory) {
	        searchDirectory(file, javafiles, ".*\\.java");
	        
//	        for (String s : javafiles) {
//	            System.out.println(s);
//	        }

		}
		else if(isFile) {
			checkFile(file,javafiles);
		}
		
        
        int JavaCount = CountJava(javafiles);
        int CountUniqueJava=0;
        try {
			CountUniqueJava = CountUniqueJava(javafiles,Uniquejavafiles);
			//System.out.println(CountUniqueJava);
			  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
        System.out.println(JavaCount+"-"+CountUniqueJava);
	}
}