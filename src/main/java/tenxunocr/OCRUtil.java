package tenxunocr;

import ocr.Base64Util;
import ocr.FileUtil;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2020/11/25 15:22
 * @version: V1.0
 */
public class OCRUtil {

    public static String base64Img(String filePath) throws Exception{
        byte[] imgData = FileUtil.readFileByBytes(filePath);
        String imgStr = Base64Util.encode(imgData);
//        imgStr = "data:image/jpeg;base64,"+imgStr;
//        String imgParam = URLEncoder.encode(imgStr, "UTF-8");
        return imgStr;
    }
}
