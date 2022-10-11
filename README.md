# 221007-project-populationstatistics
# <Java로 대용량 데이터 분석하기 프로젝트>
## 1. 학습 목표
>1. 대용량 데이터 500MB를 읽을 수 있다.
>2. 읽어온 데이터를 메모리에 올릴 수 있다.
>3. 읽어온 데이터로 데이터 분석을 할 수 있다.

## 2. 실습 목표
>- Mdis에서 받은 인구이동 데이터로 2021 서울에서 가장 많이 이사간 지역은 어디인지 알아내기

## 3. 구현
 ### 1. 분석할 데이터
 #### 1) 분석할 데이터? 2021_인구관련연간자료_20220927_66125.csv
 #### 2) 메타데이터? 데이터들의 정보를 담고있다. (인덱스, 각 value의 설명 등등)
	- ex) 인구관련연간자료의 메타데이터
```
    1	코드형	전입행정구역시도코드	1	2		
	2	코드형	전입행정구역시군구코드	3	3		
	3	코드형	전입행정구역읍면동코드	6	5		
	4	문자형	전입연도	11	4		
	5	문자형	전입월	15	2		
	6	문자형	전입일	17	2		
	7	코드형	전출행정구역시도코드	19	2		
	8	코드형	전출행정구역시군구코드	21	3		
	9	코드형	전출행정구역읍면동코드	24	5		
	10	코드형	전입사유코드	29	1		
	11	코드형	전입자1_세대주관계코드	30	1		
	12	숫자형	전입자1_만연령	31	3		
	13	코드형	전입자1_성별코드	34	1		
	14	코드형	전입자2_세대주관계코드	35	1		
	15	숫자형	전입자2_만연령	36	3		
	16	코드형	전입자2_성별코드	39	1		
	17	코드형	전입자3_세대주관계코드	40	1		
	18	숫자형	전입자3_만연령	41	3		
	19	코드형	전입자3_성별코드	44	1		
	20	코드형	전입자4_세대주관계코드	45	1		
	21	숫자형	전입자4_만연령	46	3		
	22	코드형	전입자4_성별코드	49	1		
	23	코드형	전입자5_세대주관계코드	50	1		
	24	숫자형	전입자5_만연령	51	3		
	25	코드형	전입자5_성별코드	54	1		
	26	코드형	전입자6_세대주관계코드	55	1		
	27	숫자형	전입자6_만연령	56	3		
	28	코드형	전입자6_성별코드	59	1		
	29	코드형	전입자7_세대주관계코드	60	1		
	30	숫자형	전입자7_만연령	61	3		
	31	코드형	전입자7_성별코드	64	1		
	32	코드형	전입자8_세대주관계코드	65	1		
	33	숫자형	전입자8_만연령	66	3		
	34	코드형	전입자8_성별코드	69	1		
	35	코드형	전입자9_세대주관계코드	70	1		
	36	숫자형	전입자9_만연령	71	3		
	37	코드형	전입자9_성별코드	74	1		
	38	코드형	전입자10_세대주관계코드	75	1		
	39	숫자형	전입자10_만연령	76	3		
	40	코드형	전입자10_성별코드	79	1		
	41	문자형	일련번호	80	6
```
#### 3) csv란?
	- Comma Separated Values   
 	- 쉼표로 구분된 값 파일이다.
    ![](https://velog.velcdn.com/images/lyj1023/post/9954a096-fcdf-4d8d-a4b3-e1c1f486415d/image.png)
	- 파일로 되어있는 데이터를 한줄씩 읽어서 메모리에 로드해 사용한다. (csv -> java object로)
### 2. 데이터 처리하는 여러가지 방법
#### 1) Map사용
- Map에 <"전입,전출",횟수> 이런 식으로 데이터를 처리할 수 있다.
#### 2) Set사용
- 중복을 허용하지 않는 Set을 사용해 지역을 카운트하는 방법으로 처리할 수도 있다.
#### 3) Array사용
- arr1, arr2를 만들어서 전입 전출을 저장한 다음에 카운트를 할 수도 있다.
- 위의 세가지 방법말고도 다양한 방법들이 있을 수 있다.

### 3. 코드 작성
#### 0) 주의 사항
- 설계하면서 코드 작성할 것!
  1) 논리적 설계 - 기능이 어떤것인지 써보는 것
  2) 물리적 설계 - java로 코딩하면 어떤 모양이 될 지 써보는 것
  3) 구현 - 설계해둔것으로 코딩
- 메소드 이름 짓기
  1) 단일 책임의 원칙 지키기: 메서드는 한 개의 기능만 작동할 수 있도록 작성해야 한다.
  2) 어떤 기능을 하는지 알 수 있도록 지어야 한다.(이름이 길어져도 ok, 어차피 컴파일 할 때 빠르게 읽을 수 있도록 알아서 변환한다.)
- 디버깅 하는 법
  1) 상단 플레이버튼 오른쪽 디버그 버튼을 눌러서 디버깅 모드로 실행한다.
    ![](https://velog.velcdn.com/images/lyj1023/post/7f6e378a-935e-4cfb-bad0-7e21bd24732f/image.png)
  2) 에러가 나는 부분에 어떤 값이 들어오는지 확인한다.
    ![](https://velog.velcdn.com/images/lyj1023/post/82bf122e-f314-4c79-8c49-870908310798/image.png)

#### 1) 파일 읽기
```java
public class PopulationMove {
    private int fromSido;
    private int toSido;

    //constructor추가
    public PopulationMove(int fromSido, int toSido) {
        this.fromSido = fromSido;
        this.toSido = toSido;
    }

    //생성자만있으면 private으로 선언된 변수들을 외부에서 사용못하기 때문에 getter추가
    public int getFromSido() {
        return fromSido;
    }

    public int getToSido() {
        return toSido;
    }
}
```
```java
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

    public static void main(String[] args) throws IOException {
        String fileArr = "C:\\Users\\lyj19\\git\\221007\\2021_인구관련연간자료_20220927_66125.csv";

        PopulationStatistics populationStatistics = new PopulationStatistics();
        populationStatistics.readByLine(fileArr);
    }
}
```
- `readByChar()` 메소드
  - FileReader를 사용해서 파일 불러오는 메소드
  - `char c = fileReader.read()`가 오류나는 이유?	
    - read()에 ctrl+좌클릭 해보면 int형으로 반환시키는것을 알수있다.
    - char로 형변환을 해주면 된다.
  - fileReader로 작성했을때 엄청 느릴줄알았는데 안느린이유?
    - 실행하는 부분만 조금 불러오기 때문이다.(선언을 했을 때 파일을 읽는게 아님)
  - 백만 글자 출력해보기
    - 엄청 느리다(모래를 한알씩 옮기는것과 같다.) -> 삽으로 옮기자(BufferedReader 쓰자)
    
>- 메소드가 점점 많아지면 클래스로 나눠야 한다.
  - 파일별로 클래스나 interface나눠서하기, 한파일에 interface, class등등 다넣으면 보긴편하지만 협업을 할땐 어디에 무슨 클래스가 있는지 찾을수가 없다.

- `readByLine()` 메소드
  - BufferedReader를 사용해서 파일 불러오는 메소드
  - `readLine`을 사용하면 한 줄씩 불러올 수 있다.
  - 데이터가 커서 불러오는데 몇 초걸리긴 하지만 fileReader보다는 빠르다.
 
- `readByLine2()` 메소드
  - `Files.newBufferedReader`를 사용해서 파일 불러오는 메소드
  - 요즘 많이 사용하는 스타일이다.
  - 그냥 BufferedReader랑 속도는 비슷하다.
#### 2) parse 구현
```java
public PopulationMove parse(String data){
    String[] dataArr = data.split(",");
    //전입 행정구역시도코드
    int toSido =  Integer.parseInt(dataArr[0]);

    //전출 행정구역시도코드
    int fromSido = Integer.parseInt(dataArr[6]);

    return new PopulationMove(toSido,fromSido);
}
```
- parsing이란?
  - 주어진 데이터를 내가 원하는 형태로 가공해서 사용하는 것이다.
- `parse()`메소드
  - 전입, 전출 코드를 parsing하는 메소드이다.
  - `split()`: String[] 타입으로 리턴하는 메소드, ()안의 구분자로 잘라서 배열에 넣어준다.
    - ex)
      ```java
      String data2 = "이연재%이연재2%이연재3";
      String splitted = data.split("%");
      //결과: [이연재,이연재2,이연재3]
      ```
#### 3) readByLine 수정
```java
public List<PopulationMove> readByLine(String filename) throws IOException {
    List<PopulationMove> pml = new ArrayList<>();
    BufferedReader reader = new BufferedReader(
            new FileReader(filename)
    );

    String str;

    while ((str = reader.readLine()) != null) {
        PopulationMove pm = parse(str);
        pml.add(pm);
    }
    reader.close();

    return pml;
}

```
```java
public static void main(String[] args) throws IOException {
    String fileArr = "C:\\Users\\lyj19\\git\\221007\\2021_인구관련연간자료_20220927_66125.csv";

    PopulationStatistics populationStatistics = new PopulationStatistics();

    List<PopulationMove> pml = populationStatistics.readByLine(fileArr);

    System.out.println(pml.size());
}
```
- readByLine()메소드 수정
  - while문 안에서 string을 populationMove로 parsing하여 pml에 add한다.
  - 루프가 끝나면(파일을 모두 읽어서 parsing을 끝냈으면) return한다.

>- 500mb를 매번 읽는 것은 부담이다.
  - 왜? 한번 파일을 메모리로 로드하는데 10초가량 걸리기 때문이다.
  - 따라서 필요한 부분만 파일로 저장해서 사용하면 좋다.(위의 코드에선 전입 전출만 뽑아서 저장)

#### 4) 파일 생성
```java
public void createAFile(String filename){
    File file = new File(filename);
    try {
        file.createNewFile();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
```
```java
public static void main(String[] args) throws IOException {
    String fileArr = "C:\\Users\\lyj19\\git\\221007\\2021_인구관련연간자료_20220927_66125.csv";

    PopulationStatistics populationStatistics = new PopulationStatistics();
    populationStatistics.createAFile("./from_to.txt");  
}
```
- `createAFile()`메소드
  - `createNewFile`를 이용해서 `from_to.txt`파일을 생성한다

#### 5) 파일 쓰기
```java
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
```
```java
public String fromToString(PopulationMove populationMove){
    return populationMove.getToSido()+","+populationMove.getFromSido()+"\n";
}
```
```java
public static void main(String[] args) throws IOException {
    String fileArr = "C:\\Users\\lyj19\\git\\221007\\2021_인구관련연간자료_20220927_66125.csv";

    PopulationStatistics populationStatistics = new PopulationStatistics();

    List<PopulationMove> pml = populationStatistics.readByLine(fileArr);

    List<String> strings = new ArrayList<>();
    for(PopulationMove pm : pml){
        String fromTo = populationStatistics.fromToString(pm);
        strings.add(fromTo);
    }
    System.out.println(strings.size());
    populationStatistics.write(strings,"./from_to.txt" );
}
```
- `write()`메소드
  - List를 지정한 파일에 write하는 메소드이다.
  - write를 이용해 fromToString으로 가공된 데이터를 `from_to.txt`에 저장한다.
- `fromToString()`메소드
  - 전입,전출 코드를 저장할 때 콤마(,)로 구분해서 가공해주는 메소드이다.
  
#### 6) parse 수정
```java
public PopulationMove parse(String data){
    String[] dataArr = data.split(",");
    //전입 행정구역시도코드
    int toSido =  Integer.parseInt(dataArr[0]);

    //전출 행정구역시도코드
    int fromSido = Integer.parseInt(dataArr[1]);

    return new PopulationMove(toSido,fromSido);
}
```
```java
public static void main(String[] args) throws IOException {
    String fileArr = "./from_to.txt";

    PopulationStatistics populationStatistics = new PopulationStatistics();
    List<PopulationMove> pml = populationStatistics.readByLine(fileArr);

    for(PopulationMove pm : pml){
        System.out.printf("전입: %s, 전출: %s\n", pm.getToSido(), pm.getFromSido());
}
```
- `parse()`메소드 수정
  - 전입, 전출 코드만 담겨있는 from_to.txt을 불러와서 사용하기 때문에 인덱스를 0과 1로 바꾼다.

#### 7) count후 저장
```java
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
```
```java
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
    }
    ps.write(cntResult, targetFilename);
}
```
- `getMoveCntMap()`메소드
  - HashMap을 생성해 <“전입,전출”, cnt>의 형식으로 moveCntMap에 저장하는 메소드
    1) `List<PopulationMove>`를 순환 하면서 "전입,전출" 형태의 key를 만든다.
    2) moveCntMap에서 해당 key에 해당하는 Object가 없으면 생성하고 1이라고 check한다.
    3) key로 꺼내서 +1한다.
    4) moveCntMap을 return한다.
- `getMoveCntMap()`메소드에서 return된 moveCntMap을 `each_sido_cnt.txt`에 저장한다.
  ![](https://velog.velcdn.com/images/lyj1023/post/7d970745-47ae-4f11-b1f0-537af307ed2c/image.png)

#### 8) 히트맵 그리기
```java
public Map<String, Integer> heatmapIdxMap(){
    Map<String, Integer> map = new HashMap<>();
    map.put("11", 0);
    map.put("26", 1);
    map.put("27", 2);
    map.put("28", 3);
    map.put("29", 4);
    map.put("30", 5);
    map.put("31", 6);
    map.put("36", 7);
    map.put("41", 8);
    map.put("42", 9);
    map.put("43", 10);
    map.put("44", 11);
    map.put("45", 12);
    map.put("46", 13);
    map.put("47", 14);
    map.put("48", 15);
    map.put("50", 16);

    return map;
}
```
```java
public static void main(String[] args) throws IOException {
    String fileArr = "./from_to.txt";

    PopulationStatistics ps = new PopulationStatistics();
    List<PopulationMove> pml = ps.readByLine(fileArr);

    Map<String, Integer> map = ps.getMoveCntMap(pml);

    String targetFilename = "./for_heatmap.txt";
    ps.createAFile(targetFilename);
    Map<String, Integer> heatMapIdxMap = ps.heatmapIdxMap();
    List<String> cntResult = new ArrayList<>();

    for(String key:map.keySet()){
        String[] fromto = key.split(",");
        //매핑 후 저장
        String s = String.format("[%s, %s, %d],\n", heatMapIdxMap.get(fromto[0]),heatMapIdxMap.get(fromto[1]),map.get(key));
        cntResult.add(s);
    }
    ps.write(cntResult, targetFilename);
}
```
- `heatmapIdxMap()`메소드
  - 각 시도코드를 map에 저장하는 메소드
- `for_heatmap.txt`에 [x의 index, y의 index, conut], 형식으로 write한다.
- 결과 
![](https://velog.velcdn.com/images/lyj1023/post/6db35f8d-c727-4f0d-8953-182991e5a196/image.png)

## 4. 추가로 생각해볼 것들
- 객체지향적으로 리펙토링 해보기

