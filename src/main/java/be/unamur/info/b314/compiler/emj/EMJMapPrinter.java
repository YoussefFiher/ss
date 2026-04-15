package be.unamur.info.b314.compiler.emj;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EMJMapPrinter {

    private PrintWriter writer;
    private static final Map<String, String> emojiMap = new HashMap<>();

    static {
        emojiMap.put("🚓", "cutebot");
        emojiMap.put("🛣️", "road");
        emojiMap.put("🏘️", "house");
        emojiMap.put("🌊", "water");
        emojiMap.put("🚧", "construction");
        emojiMap.put("🚜", "tractor");
        emojiMap.put("🦹", "thief");
        emojiMap.put("➡️", "EST");
        emojiMap.put("⬅️", "OUEST");
        emojiMap.put("⬆️", "NORD");
        emojiMap.put("⬇️", "SUD");
    }

    public EMJMapPrinter(String fileName) throws FileNotFoundException {
        writer = new PrintWriter(fileName);
    }

    public void printMap(int rows, int cols, List<String> mapRows, String orientation) {
        writer.printf("dimensions = (%d, %d)%n", rows, cols);
        writer.printf("orientation = \"%s\"%n", emojiMap.getOrDefault(orientation, orientation));

        writer.println("map = [");
        for (String row : mapRows) {
            String[] cells = row.split(" ");
            writer.print("    [");
            for (int i = 0; i < cells.length; i++) {
                writer.printf("\"%s\"", emojiMap.getOrDefault(cells[i], cells[i]));
                if (i < cells.length - 1) {
                    writer.print(", ");
                }
            }
            writer.println("],");
        }
        writer.println("]");

        writer.flush();
        close();
    }

     public void close() {
        writer.close();
    }
}
