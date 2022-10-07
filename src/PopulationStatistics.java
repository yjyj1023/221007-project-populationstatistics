import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        int fromSido = Integer.parseInt(dataArr[1]);

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

    //List<String>을 지정한 파일에 write
    public void write(List<String> strs, String filename){
        File file = new File(filename);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(String str:strs){
                writer.write(str);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String fromToString(PopulationMove populationMove){
        return populationMove.getToSido()+","+populationMove.getFromSido()+"\n";
    }

    public Map<String, Integer> getMoveCntMap(List<PopulationMove> pml){

        Map<String, Integer> moveCntMap = new HashMap<>();
        for (PopulationMove pm : pml){
            String key = pm.getFromSido() + "," + pm.getToSido();
            if(moveCntMap.get(key) == null){
                moveCntMap.put(key,1);
            }
            moveCntMap.put(key, moveCntMap.get(key)+1);
        }
        return moveCntMap;
    }

    public static void main(String[] args) throws IOException {
        String fileArr = "./from_to.txt";

        PopulationStatistics ps = new PopulationStatistics();
        List<PopulationMove> pml = ps.readByLine(fileArr);

        Map<String, Integer> map = ps.getMoveCntMap(pml);

        String targetFilename = "./each_sido_cnt.txt";
        ps.createAFile(targetFilename);
        List<String> cntResult = new ArrayList<>();

        for(String key:map.keySet()){
            String s = String.format("key:%s value:%d\n", key,map.get(key));
            cntResult.add(s);
            //System.out.printf("key:%s value:%d\n", key,map.get(key));
        }
        ps.write(cntResult, targetFilename);
//        for(PopulationMove pm : pml){
//            System.out.printf("전입: %s, 전출: %s\n", pm.getToSido(), pm.getFromSido() );
//
//        }

    }
}
