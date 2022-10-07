import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PopulationStatistics {
    //기존에 FileReader로 작성했던 코드 메소드로 분리하기
    public void readByChar(String filename)throws IOException {
        FileReader fileReader = new FileReader(filename);
        //아직 파일을 읽지 않음

        String fileContents = "";
        while(fileContents.length()< 1000000){
            char c = (char)fileReader.read();
            fileContents += c;
        }
        System.out.println(fileContents);
    }

    //한줄씩 읽는 코드 BufferedReader로 작성
    public List<PopulationMove> readByLine(String filename) throws IOException {
        List<PopulationMove> pml = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new FileReader(filename)
        );
        String str;

        while ((str = reader.readLine()) != null) {
            //System.out.println(str);
            PopulationMove pm = parse(str);
            pml.add(pm);
        }
        reader.close();
        return pml;
    }

    //Files.newBufferedReader 요즘 스타일임
    public void readByLine2(String filename) throws IOException{
        try(BufferedReader br = Files.newBufferedReader(
                Paths.get(filename), StandardCharsets.UTF_8)){

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PopulationMove parse(String data){
        String[] dataArr = data.split(",");
        //전입 행정구역시도코드
        int toSido =  Integer.parseInt(dataArr[0]);

        //전출 행정구역시도코드
        int fromSido = Integer.parseInt(dataArr[6]);

        return new PopulationMove(toSido,fromSido);
    }

    public void createAFile(String filename){
        File file = new File(filename);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        String fileArr = "C:\\Users\\lyj19\\git\\221007\\2021_인구관련연간자료_20220927_66125.csv";

        PopulationStatistics populationStatistics = new PopulationStatistics();
//        List<PopulationMove> pml = populationStatistics.readByLine(fileArr);
//
//
//        for(PopulationMove pm : pml){
//            System.out.printf("전입: %s, 전출: %s\n", pm.getToSido(), pm.getFromSido() );
//        }

        populationStatistics.createAFile("./from_to.txt");


    }
}
