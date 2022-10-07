public class PopulationMove {
    private int fromSido;
    private int toSido;

    //constructor추가
    public PopulationMove(int toSido, int fromSido) {
        this.toSido = toSido;
        this.fromSido = fromSido;
    }

    //생성자만있으면 private으로 선언된 변수들을 외부에서 사용못하기 때문에 getter추가
    public int getToSido() {
        return toSido;
    }

    public int getFromSido() {
        return fromSido;
    }
}
