package ocr;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:
 * @Description: 匹配规则识别前缀 匹配规则默认全加上 XX:  【XX】
 * @author: QianTao
 * @date: 2020/12/21 16:38
 * @version: V1.0
 */
public class OcrPrefixConst {
    /**
     * 通用名
     */
    public static List<String> prefixTYMList = new ArrayList<>();

    static{
        prefixTYMList.add("通用名称");
        prefixTYMList.add("通用名");
        prefixTYMList.add("品名");
    }
    /**
     * 商品名
     */
    public static List<String> prefixSPMList = new ArrayList<>();

    static{
        prefixSPMList.add("商品名");
        prefixSPMList.add("商品名称");
    }

    /**
     * 生产企业
     */
    public static List<String> prefixSCQYList = new ArrayList<>();

    static{
        prefixSCQYList.add("企业名称");
        prefixSCQYList.add("生产厂名称");
        prefixSCQYList.add("分包装厂");
        prefixSCQYList.add("分包装企业");
        prefixSCQYList.add("制药商");
        prefixSCQYList.add("生产企业");
        prefixSCQYList.add("分包装厂名称");
        prefixSCQYList.add("分装企业");
        prefixSCQYList.add("受托方");
        prefixSCQYList.add("受托企业");
        prefixSCQYList.add("受托生产企业");
        prefixSCQYList.add("药品上市许可持有人");
        prefixSCQYList.add("注册人/生产企业");
        prefixSCQYList.add("企业生产单位");
    }

    /**
     * 委托生产企业
     */
    public static List<String> prefixWTSCQYList = new ArrayList<>();

    static{
        prefixWTSCQYList.add("委托方");
        prefixWTSCQYList.add("委托生产企业");
        prefixWTSCQYList.add("委托方企业名称");
        prefixWTSCQYList.add("委托企业");
        prefixWTSCQYList.add("委托生产单位");
    }

    /**
     * 产地
     */
    public static List<String> prefixCDList = new ArrayList<>();

    static{
        prefixCDList.add("产地");
    }
    /**
     * 批准文号
     */
    public static List<String> prefixPZWHList = new ArrayList<>();

    static{
        prefixPZWHList.add("批准文号");
        prefixPZWHList.add("备案号");
        prefixPZWHList.add("进口药品注册证号");
        prefixPZWHList.add("医药产品注册证号");
        prefixPZWHList.add("医疗器械注册证号");
        prefixPZWHList.add("产品备案号");
        prefixPZWHList.add("产品备案号/技术要求编号");
        prefixPZWHList.add("注册证编号/技术要求编号");
        prefixPZWHList.add("卫生许可证");
        prefixPZWHList.add("医疗器械注册证编号");
        prefixPZWHList.add("产品注册证号");
        prefixPZWHList.add("卫生许可证号");
    }
    /**
     * 生产许可证号
     *
     */
    public static List<String> prefixSCXKZHList = new ArrayList<>();

    static{
        prefixSCXKZHList.add("生产许可证编号");
        prefixSCXKZHList.add("生产许可证");
        prefixSCXKZHList.add("食品生产许可证号");
        prefixSCXKZHList.add("生产许可证号");
    }
    /**
     * 注册证号
     */
    public static List<String> prefixZCZHList = new ArrayList<>();

    static{
        prefixZCZHList.add("进口药品注册证号");
        prefixZCZHList.add("注册证号");
        prefixZCZHList.add("医药注册证号");
    }
    /**
     * 功能疗效
     */
    public static List<String> prefixGNLXList = new ArrayList<>();

    static{
        prefixGNLXList.add("适应症");
        prefixGNLXList.add("功能主治");
        prefixGNLXList.add("适应人群");
        prefixGNLXList.add("保健功能");
    }
    /**
     * 储存条件
     */
    public static List<String> prefixCCTJList = new ArrayList<>();

    static{
        prefixCCTJList.add("");
    }

    /**
     * 有效期至
     */
    public static List<String> prefixYXQList = new ArrayList<>();

    static{
        prefixYXQList.add("有效期至");
    }

    /**
     * 生产日期
     */
    public static List<String> prefixSCRQList = new ArrayList<>();

    static{
        prefixSCRQList.add("");
    }

    /**
     * 保质期 = 生产日期-有效期至
     */
    public static List<String> prefixBZQList = new ArrayList<>();

    static{
        prefixBZQList.add("有效期");
        prefixBZQList.add("保质期");
        prefixBZQList.add("使用年限");
        prefixBZQList.add("储存期");
    }



}
