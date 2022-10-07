public class PopulationMove {
    private int fromSido;
    private int toSido;

    //constructor추가
    public PopulationMove(int fromSido, int toSido) {
        this.fromSido = fromSido;
        this.toSido = toSido;
    }

    //생성자만있으면 private으로 선언된 변수들을 사용못하기 때문에 getter추가
    public int getFromSido() {
        return fromSido;
    }

    public int getToSido() {
        return toSido;
    }
}
