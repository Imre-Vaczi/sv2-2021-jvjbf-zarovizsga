package videos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class VideoPlatform {

    private List<Channel> channels = new ArrayList<>();

    public List<Channel> getChannels() {
        return new ArrayList<>(channels);
    }

    public void readDataFromFile(Path path) {
        try (BufferedReader bf = Files.newBufferedReader(path)) {
            String line;
            populateChannels(bf);

        } catch (IOException ioException) {
            throw new IllegalArgumentException("Cannot open file for read!", ioException);
        }
    }

    private void populateChannels(BufferedReader bf) throws IOException {
        String line;
        while ((line = bf.readLine()) != null) {
            if (!line.split(";")[0].equals("channel")) {
                String name = line.split(";")[0];
                int subNumber = Integer.valueOf(line.split(";")[1]);
                int vidNumber = Integer.valueOf(line.split(";")[2]);
                channels.add(new Channel(name, subNumber, vidNumber));
            }
        }
    }

    public int calculateSumOfVideos() {
        return channels.stream()
                .mapToInt(c -> c.getNumberOfVideos())
                .sum();
    }
}
