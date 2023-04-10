import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class MemoryReadWriter {
    public static void main(String[] args) {
        String sample = "This is sample string";
        List arrayList = new ArrayList();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            arrayList.add(sample);
        }

        arrayList.stream().collect(Collectors.toList());

        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end-start) + " ms");
    }
}
