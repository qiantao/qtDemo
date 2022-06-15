package test;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoTest {

    public static void main(String[] args) throws Exception {
//        Map<String,Integer> map = new HashMap<>(8);
//        for (int i = 0; i < 10; i++) {
//            map.put(i+"",i);
//        }
        User u = new User();
        u.setName("fsad");
        User u1 = new User();
        u1.setName(u.getName());

        u.setName("ffff");
        System.out.println(u1.getName());


//        System.out.println(map.toString());
    }
    /**
     * 计算单剂量进价
     * 单剂量进价=参考进价/单剂量数量
     * @param referencePurchasePrice 参考进价
     * @param singleDoseQty 单剂量数量
     * @return
     */
    public static Double calculateSingleDosePurchasePrice(Double referencePurchasePrice,Integer singleDoseQty){
        try {
            if(referencePurchasePrice == null || referencePurchasePrice == 0){
                //进价为空按照0去计算
                return 0D;
            }
            if ( singleDoseQty == null || singleDoseQty == 0) {
                return 0D;
            }

            BigDecimal referencePurchasePriceBD = new BigDecimal(referencePurchasePrice.toString());
            referencePurchasePriceBD = referencePurchasePriceBD.setScale(7,BigDecimal.ROUND_HALF_UP);//数据库保存6位小数
            BigDecimal singleDoseQtyBD = new BigDecimal(singleDoseQty.toString());
            singleDoseQtyBD = singleDoseQtyBD.setScale(7,BigDecimal.ROUND_HALF_UP);//数据库保存6位小数
            return referencePurchasePriceBD
                    .divide(singleDoseQtyBD,6,BigDecimal.ROUND_HALF_UP)
                    .doubleValue();
        }catch (Exception e){
//            log.error("计算单剂量进价错误，入参 referencePurchasePrice ={}，singleDoseQty={},",referencePurchasePrice,singleDoseQty);
//            log.error("计算单剂量进价错误",e);
        }
        return null;
    }

    /**
     * 计算最新毛利率
     * 最新毛利率=（最新售价-最新进价）/(最新售价），如果最新零售价为0，则最新毛利率也为0
     * @param purchasePrice 最新进价
     * @param recentPrice 最新售价
     * @return
     */
    public static Double calculateRecentRate(Double purchasePrice,Double recentPrice){
        try {
            if(purchasePrice == null || purchasePrice == -999){
                //进价为空按照0去计算
                purchasePrice = 0D;
            }
            if ( recentPrice == null || recentPrice == 0 || recentPrice == -999) {
                return null;
            }

            BigDecimal purchasePriceBD = new BigDecimal(purchasePrice.toString());
            purchasePriceBD = purchasePriceBD.setScale(4,BigDecimal.ROUND_HALF_UP);//数据库保存6位小数
            BigDecimal recentPriceBD = new BigDecimal(recentPrice.toString());
            recentPriceBD = recentPriceBD.setScale(4,BigDecimal.ROUND_HALF_UP);//数据库保存6位小数
            return recentPriceBD.subtract(purchasePriceBD)
                    .divide(recentPriceBD,4,BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal("100"))
                    .doubleValue();
        }catch (Exception e){
            throw e;
        }

    }
    public static void buildUrl(String version,String date){
        String url = "http://gitlab.pt.hydee.cn/h3-business/h3-{model}/blob/master/h3-{model}-center/script/sql/{date}/update_v{version}.sql";
        List<String> modelList = new ArrayList<>();
        modelList.add("ware");
        modelList.add("pay");
        modelList.add("promotion");
        modelList.add("customer");
        modelList.add("trade");
        modelList.add("supplier");

        for (String model :modelList) {
            System.out.println(url.replaceAll("\\{model\\}",model).replaceAll("\\{date\\}",date).replaceAll("\\{version\\}",version) );
            System.out.println();
        }
    }

    private static Timestamp getTodayEnd(){
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return Timestamp.valueOf(todayEnd);
    }
    private static Timestamp getTodayBegin(){
        LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        return Timestamp.valueOf(todayStart);
    }
    private static String longToByte(String cs ,long a){
        if(a/2>1){
            if(a%2==0) {
                cs = 0 + cs;
            }else{
                cs = 1 + cs;
            }
            cs = longToByte(cs,a/2);

        }else{
            if(a%2==0) {
                cs = 0 + cs;
            }else{
                cs = 1 + cs;
            }
            cs = a/2 + cs + "B";
//            十进制 D
//            二进制 B
//            十六进制 H

        }
        return cs;
    }

}
