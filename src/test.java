import components.sequence.Sequence;
import components.sequence.Sequence1L;

public class test {
    public static void main(String[] args) {
        Sequence<String> seq = new Sequence1L<>();

        seq.add(0, "hi");
        seq.add(0, "hi again");
        seq.add(seq.length(), "hi again again");
        seq.add(1, "negative one");
        seq.add(seq.length(), "new one");

        System.out.println(seq.entry(0).toString());
        System.out.println(seq.entry(1).toString());
        System.out.println(seq.entry(seq.length() - 1).toString());

        String[] arr = new String[]{"1,2,3,4,55653,132412"};
        System.out.println(arr.toString());
    }
}
