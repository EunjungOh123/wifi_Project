package Service;

import DB.WifiDB;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class WifiOpenAPI {

    final static String key = "764f755a4f7a78633536485378776f";
    final static String type = "json";
    final static String service = "TbPublicWifiInfo";

    public int WifiCall () {
        int dataCount = 0;
        ArrayList<Wifi> list = new ArrayList<>();
        WifiDB wifiDB = new WifiDB();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) jsonParser.parse(makeURL(0));
            JSONObject listCount = (JSONObject) jsonObject.get("TbPublicWifiInfo");
            dataCount = Integer.parseInt(String.valueOf(listCount.get("list_total_count")));

            for(int i = 0; i<=dataCount/1000; i++) {
                jsonObject = (JSONObject) jsonParser.parse(makeURL(i));
                JSONObject TbPublicWifiInfo = (JSONObject) jsonObject.get("TbPublicWifiInfo");
                JSONArray row = (JSONArray) TbPublicWifiInfo.get("row");
                for(int j = 0; j< row.size(); j++) {
                    JSONObject obj = (JSONObject) row.get(j);
                    Wifi wifi = new Wifi();
                    wifi.set관리번호((String) obj.get("X_SWIFI_MGR_NO"));
                    wifi.set자치구((String) obj.get("X_SWIFI_WRDOFC"));
                    wifi.set와이파이명((String) obj.get("X_SWIFI_MAIN_NM"));
                    wifi.set도로명주소((String) obj.get("X_SWIFI_ADRES1"));
                    wifi.set상세주소((String) obj.get("X_SWIFI_ADRES2"));
                    wifi.set설치위치 ((String) obj.get("X_SWIFI_INSTL_FLOOR"));
                    wifi.set설치유형((String) obj.get("X_SWIFI_INSTL_TY"));
                    wifi.set설치기관((String) obj.get("X_SWIFI_INSTL_MBY"));
                    wifi.set서비스구분((String) obj.get("X_SWIFI_SVC_SE"));
                    wifi.set망종류((String) obj.get("X_SWIFI_CMCWR"));
                    wifi.set설치년도((String) obj.get("X_SWIFI_CNSTC_YEAR"));
                    wifi.set실내외구분((String) obj.get("X_SWIFI_INOUT_DOOR"));
                    wifi.set접속환경((String) obj.get("X_SWIFI_REMARS3"));
                    wifi.setX좌표(Double.valueOf(String.valueOf(obj.get("LAT"))));
                    wifi.setY좌표(Double.valueOf(String.valueOf(obj.get("LNT"))));
                    wifi.set작업일자((String) obj.get("WORK_DTTM"));
                    list.add(wifi);
                }
            }
            wifiDB.wifiInfoInsert(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataCount;
    }

    public static String makeURL (int i) {
        String result = "";
        int startIdx = 1000*i+1;
        int endIdx = 1000*(i+1);

        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/").append(key).append("/").append(type).append("/").append(service).append("/");
        urlBuilder.append(startIdx).append("/").append(endIdx).append("/");
        try {
            URL url = new URL(urlBuilder.toString()); // URL 객체 생성
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream()));
            result = bf.readLine();
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

