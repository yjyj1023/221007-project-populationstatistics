import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    public void readByLine(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(
                new FileReader(filename)
        );

        String str;

        while ((str = reader.readLine()) != null) {
            System.out.println(str);
        }
        reader.close();

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

    public PopulationMove pars(String data){
        String[] dataArr = data.split(",");
        //전입 행정구역시도코드
        int fromSido =  Integer.parseInt(dataArr[0]);

        //전출 행정구역시도코드
        int toSido = Integer.parseInt(dataArr[6]);

        return new PopulationMove(fromSido,toSido);
    }

    public static void main(String[] args) throws IOException {
        String fileArr = "C:\\Users\\lyj19\\git\\221007\\2021_인구관련연간자료_20220927_66125.csv";

        PopulationStatistics populationStatistics = new PopulationStatistics();
        populationStatistics.readByLine(fileArr);
    }
}
