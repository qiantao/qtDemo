package ocr;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2020/11/26 13:54
 * @version: V1.0
 */
public class WordResult {
    /**
     * 位置
     */
    private Location location;

    /**
     * 解析单词
     */
    private String words;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
}
