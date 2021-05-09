public abstract class Output {

    private static TxtFileWriterPort fileWriterPort = new WriterAdapter();
    private static StringBuffer buffer = new StringBuffer();

    public static void write(String content){
        System.out.println(content);
        buffer.append(content + System.lineSeparator());
    }

    public static void generateFile(){
        fileWriterPort.writeTxtFile(buffer);
    }

}
