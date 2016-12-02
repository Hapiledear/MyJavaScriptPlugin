package citySelect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectCityHelper {
	
	public static String[] filterWord = {"省","市","县","区"};
	
	public static Map<String,String> zoneCodeMap = new HashMap<String,String>(){{
		put("北京","11");
		put("天津","12");
		put("河北","13");
		put("山西","14");
		put("内蒙古","15");
		put("辽宁","21");
		put("吉林","22");
		put("黑龙江","23");
		put("上海","31");
		put("江苏","32");
		
		put("张家口","1307");
		put("逐鹿","1414");
		put("赤城","1415");
		put("承德","1308");
		
	}};
	
	
	public static String getZoneCode(String name){
		String zone = name;
		for(String str : filterWord){
			zone = name.replaceAll(str, "");
		}
		
		return zoneCodeMap.get(zone) == null ? "0" : zoneCodeMap.get(zone);
	}
	/**
	 * 0 银行名;1=省名;2=市名
	 * @param name
	 * @return
	 */
	public static int getZoneType(String name){
		String zoneCode = getZoneCode(name);
		switch (zoneCode.length()) {
		case 2:
			return 1;
		case 4:
			return 2;
		default :
			return 0;
		}
	}
}


/*
 * 改进方法，使用正则表达式
 * */
/*SELECT BL.LBNK_NO,BL.LBNK_NM,BL.BNK_CD,BL.LBNK_PROV,BL.LBNK_CITY,BN.BNK_NM, CITY.CITY_NM,CITY.PROV_NM
FROM T_BAP_CDE_BNK_LINK BL
LEFT JOIN T_BAP_CDE_BNK BN ON BL.BNK_CD = BN.BNK_CD
LEFT JOIN T_BAP_CDE_BNK_CITY CITY ON BL.LBNK_CITY = CITY.CITY_CD
WHERE (BL.LBNK_NO LIKE CONCAT(CONCAT('%',#{_parameter,jdbcType=VARCHAR}),'%') OR BL.LBNK_NM LIKE CONCAT(CONCAT('%',#{_parameter,jdbcType=VARCHAR}),'%') OR CITY.CITY_NM LIKE CONCAT(CONCAT('%',#{_parameter,jdbcType=VARCHAR}),'%') OR CITY.PROV_NM LIKE CONCAT(CONCAT('%',#{_parameter,jdbcType=VARCHAR}),'%'))
and bl.CDE_FLG = 1 and bn.CDE_FLG = '1' and bl.CDE_FLG = '1' order by BL.LBNK_NO desc*/
