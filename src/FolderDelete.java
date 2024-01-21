import java.io.*;

class FolderDelete
{
   public static boolean deleteDirectory(File dir)
   {
     if (dir.isDirectory())
     {
       File[] children = dir.listFiles();
     
       for (int i = 0; i < children.length; i++)
       {
         boolean success = deleteDirectory(children[i]);
         if (!success)
         {
            return false;
         }
       }
       System.exit(0);
     } // either file or an empty directory
     
     System.out.println("removing file or directory : " + dir.getName());
       return dir.delete();
   }
   /* * Incorrect way to delete a directory in Java */
   public static void deleteDirectory(String file)
   {
     File directory = new File(file);
     File[] children = directory.listFiles();
     for (File child : children)
     {
        System.out.println(child.getAbsolutePath());
     } // let's delete this directory // it will not work because directory has sub-directory // which has files inside it. // In order to delete a directory, // you need to first delete its files or contents.
     boolean result = directory.delete();
     if (result)
     {
        System.out.printf("Directory '%s' is successfully deleted", directory.getAbsolutePath());
     }
     else
     {
        System.out.printf("Failed to delete directory '%s' %n", directory.getAbsolutePath());
     }
     
   }
}

