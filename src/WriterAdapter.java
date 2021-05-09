import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterAdapter implements TxtFileWriterPort {

    private BufferedWriter bufferedWriter = null;

    @Override
    public void writeTxtFile(StringBuffer content) {
        try {
            File file = new File("database.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try{
                if(bufferedWriter !=null)
                    bufferedWriter.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}