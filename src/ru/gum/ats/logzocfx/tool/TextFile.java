package ru.gum.ats.logzocfx.tool;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by chupiraov on 30.05.2016.
 */
public class TextFile extends ArrayList<String>{
    //read the whole(vsego) file as a single string
    public static String read(String fileName){
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
            try{
                String s;
                while ((s=br.readLine()) != null){
                    sb.append(s);
                    sb.append("\n");
                }
            }finally {
                br.close();
            }
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }
        return sb.toString();
    }//end m.read()
    //File record one method call
    public static String write(String filename, String text){
        try{
            PrintWriter pw = new PrintWriter(new File(filename).getAbsoluteFile());
            try{
                pw.print(text);

            }finally {
                pw.close();
            }
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }
        return filename;
    }//end m.write()

    //a constructor to read the file, by regular expression
    public TextFile(String fileName, String splitter){
        super(Arrays.asList(read(fileName).split(splitter)));
        //regular expression split() often leaves (ostavljaet)
        // a blank line (pustuyu stroku) in the first position
        if(get(0).equals("")) remove(0);
    }

    //a constructor for the ordinary read line by line file
    public TextFile(String fileName){
        this(fileName, "\n");
    }

    //
    public void write(String fileName){
        try{
            PrintWriter pw = new PrintWriter( new File(fileName).getAbsoluteFile());
            try{
                for (String item: this)
                    pw.println(item);
            }finally {
                pw.close();
            }
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }
}
