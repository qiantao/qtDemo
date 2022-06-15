package ocr;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2020/11/26 13:53
 * @version: V1.0
 */
public class BaiDuResult {
    /**
     * 唯一请求id
     */
    private String log_id;
    /**
     * 解析结果数量
     */
    private String words_result_num;
    /**
     * 解析结果
     */
    private List<WordResult> words_result;

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public String getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(String words_result_num) {
        this.words_result_num = words_result_num;
    }

    public List<WordResult> getWords_result() {
        return words_result;
    }

    public void setWords_result(List<WordResult> words_result) {
        this.words_result = words_result;
    }
}
