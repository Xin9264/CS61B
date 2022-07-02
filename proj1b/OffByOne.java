public class OffByOne implements CharacterComparator{
    @Override
    public boolean equalChars(char x, char y) {
        int eq = x - y;
        return eq == 0;
    }
}
