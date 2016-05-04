package data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import base.BaseDataLayer;
import okhttp3.ResponseBody;
import pojo.PmInfo;
import pojo.WeatherInfo;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import service.NetManager;
import service.NetResponse;
import store.WeatherStore;
import utils.DataUtils;

/**
 * Created by phoenix on 2016/4/8.
 */
public class DataLayer extends BaseDataLayer {

    public DataLayer(NetManager manager, WeatherStore store) {
        super(manager,store);
    }

    @NonNull
    public Observable<WeatherInfo> getWehther(){
    mNetManager.getWeather()
            .filter(new Func1<NetResponse<ResponseBody>, Boolean>() {
                @Override
                public Boolean call(NetResponse<ResponseBody> responseBodyNetResponse) {

                    return responseBodyNetResponse.isOnNext();
                }
            })
            .map(new Func1<NetResponse<ResponseBody>, WeatherInfo>() {
                @Override
                public WeatherInfo call(NetResponse<ResponseBody> responseBodyNetResponse) {
                    String s = "";
                    try {
                        s = responseBodyNetResponse.getValue().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return DataLayer.this.ConvertData(s);
                }
            })
            .filter(new Func1<WeatherInfo, Boolean>() {
                @Override
                public Boolean call(WeatherInfo info) {
                    if(info.result==null&&info.reason!=null){
                            WeatherInfo weatherInfo = new Gson().fromJson("{\n" +
                                    "    \"reason\": \"��ѯ�ɹ�\",\n" +
                                    "    \"result\": {\n" +
                                    "        \"data\": {\n" +
                                    "            \"realtime\": {\n" +
                                    "                \"city_code\": \"101210701\",\n" +
                                    "                \"city_name\": \"����\",     /*����*/\n" +
                                    "                \"date\": \"2014-10-15\",  /*����*/\n" +
                                    "                \"time\": \"09:00:00\",     /*����ʱ��*/\n" +
                                    "                \"week\": 3,\n" +
                                    "                \"moon\": \"����إ��\",\n" +
                                    "                \"dataUptime\": 1413337811,\n" +
                                    "                \"weather\": {    /*��ǰʵ������*/\n" +
                                    "                    \"temperature\": \"19\",     /*�¶�*/\n" +
                                    "                    \"humidity\": \"54\",     /*ʪ��*/\n" +
                                    "                    \"info\": \"��\",\n" +
                                    "                    \"img\": \"18\" /*18����������������Ӧ��ͼƬ��ID��ÿ��������ͼƬ��Ҫ���Լ���ƣ��������Ķ�\n" +
                                    " https://www.juhe.cn/docs/api/id/39/aid/117*/\n" +
                                    "                },\n" +
                                    "                \"wind\": {\n" +
                                    "                    \"direct\": \"����\",\n" +
                                    "                    \"power\": \"1��\",\n" +
                                    "                    \"offset\": null,\n" +
                                    "                    \"windspeed\": null\n" +
                                    "                }\n" +
                                    "            },\n" +
                                    "            \"life\": {     /*����ָ��*/\n" +
                                    "                \"date\": \"2014-10-15\",\n" +
                                    "                \"info\": {\n" +
                                    "                    \"chuanyi\": [     /*����ָ��*/\n" +
                                    "                        \"������\",\n" +
                                    "                        \"�����ű����׻�ţ������ȷ�װ���������������żп�������ë�µȡ���ҹ�²�ϴ�ע���ʵ������·���\"\n" +
                                    "                    ],\n" +
                                    "                    \"ganmao\": [    /*��ðָ��*/\n" +
                                    "                        \"���׷�\",\n" +
                                    "                        \"��ҹ�²�ϴ󣬽��׷�����ð�����ʵ������·������ʽ�����������ע�������\"\n" +
                                    "                    ],\n" +
                                    "                    \"kongtiao\": [   /*�յ�ָ��*/\n" +
                                    "                        \"���ٿ���\",\n" +
                                    "                        \"�����е������ʣ�һ�㲻��Ҫ�����յ���\"\n" +
                                    "                    ],\n" +
                                    "                    \"wuran\": [     /*��Ⱦָ��*/\n" +
                                    "                        \"��\",\n" +
                                    "                        \"�������������ڿ�����Ⱦ��ϡ�͡���ɢ����������������������\"\n" +
                                    "                    ],\n" +
                                    "                    \"xiche\": [     /*ϴ��ָ��*/\n" +
                                    "                        \"������\",\n" +
                                    "                        \"������ϴ����δ��һ�����꣬������С����ϴһ�µ����������ܱ���һ�졣\"\n" +
                                    "                    ],\n" +
                                    "                    \"yundong\": [     /*�˶�ָ��*/\n" +
                                    "                        \"������\",\n" +
                                    "                        \"�����Ϻã��������ϴ��Ƽ������������˶������ڻ����˶���ע����硣\"\n" +
                                    "                    ],\n" +
                                    "                    \"ziwaixian\": [   /*������*/\n" +
                                    "                        \"�е�\",\n" +
                                    "                        \"���е�ǿ�������߷������������ʱ����Ϳ��SPF����15��PA+�ķ�ɹ����Ʒ����ñ�ӡ�̫������\"\n" +
                                    "                    ]\n" +
                                    "                }\n" +
                                    "            },\n" +
                                    "            \"weather\": [   /*δ����������Ԥ��*/\n" +
                                    "                {\n" +
                                    "                    \"date\": \"2014-10-15\",\n" +
                                    "                    \"info\": {\n" +
                                    "                        \"day\": [     /*��������*/\n" +
                                    "                            \"0\",     /*����ID*/\n" +
                                    "                            \"��\",     /*����*/\n" +
                                    "                            \"24\",     /*����*/\n" +
                                    "                            \"������\",     /*����*/\n" +
                                    "                            \"3-4 ��\"      /*����*/\n" +
                                    "                        ],\n" +
                                    "                        \"night\": [    /*ҹ������*/\n" +
                                    "                            \"0\",\n" +
                                    "                            \"��\",\n" +
                                    "                            \"13\",\n" +
                                    "                            \"������\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ]\n" +
                                    "                    },\n" +
                                    "                    \"week\": \"��\",\n" +
                                    "                    \"nongli\": \"����إ��\"\n" +
                                    "                },\n" +
                                    "                {\n" +
                                    "                    \"date\": \"2014-10-16\",\n" +
                                    "                    \"info\": {\n" +
                                    "                        \"dawn\": [\n" +
                                    "                            \"0\",\n" +
                                    "                            \"��\",\n" +
                                    "                            \"13\",\n" +
                                    "                            \"������\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ],\n" +
                                    "                        \"day\": [\n" +
                                    "                            \"0\",\n" +
                                    "                            \"��\",\n" +
                                    "                            \"25\",\n" +
                                    "                            \"������\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ],\n" +
                                    "                        \"night\": [\n" +
                                    "                            \"1\",\n" +
                                    "                            \"����\",\n" +
                                    "                            \"15\",\n" +
                                    "                            \"������\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ]\n" +
                                    "                    },\n" +
                                    "                    \"week\": \"��\",\n" +
                                    "                    \"nongli\": \"����إ��\"\n" +
                                    "                },\n" +
                                    "                {\n" +
                                    "                    \"date\": \"2014-10-17\",\n" +
                                    "                    \"info\": {\n" +
                                    "                        \"dawn\": [\n" +
                                    "                            \"1\",\n" +
                                    "                            \"����\",\n" +
                                    "                            \"15\",\n" +
                                    "                            \"������\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ],\n" +
                                    "                        \"day\": [\n" +
                                    "                            \"1\",\n" +
                                    "                            \"����\",\n" +
                                    "                            \"26\",\n" +
                                    "                            \"������\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ],\n" +
                                    "                        \"night\": [\n" +
                                    "                            \"1\",\n" +
                                    "                            \"����\",\n" +
                                    "                            \"16\",\n" +
                                    "                            \"������\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ]\n" +
                                    "                    },\n" +
                                    "                    \"week\": \"��\",\n" +
                                    "                    \"nongli\": \"����إ��\"\n" +
                                    "                },\n" +
                                    "                {\n" +
                                    "                    \"date\": \"2014-10-18\",\n" +
                                    "                    \"info\": {\n" +
                                    "                        \"dawn\": [\n" +
                                    "                            \"1\",\n" +
                                    "                            \"����\",\n" +
                                    "                            \"16\",\n" +
                                    "                            \"������\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ],\n" +
                                    "                        \"day\": [\n" +
                                    "                            \"1\",\n" +
                                    "                            \"����\",\n" +
                                    "                            \"26\",\n" +
                                    "                            \"����\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ],\n" +
                                    "                        \"night\": [\n" +
                                    "                            \"1\",\n" +
                                    "                            \"����\",\n" +
                                    "                            \"18\",\n" +
                                    "                            \"����\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ]\n" +
                                    "                    },\n" +
                                    "                    \"week\": \"��\",\n" +
                                    "                    \"nongli\": \"����إ��\"\n" +
                                    "                },\n" +
                                    "                {\n" +
                                    "                    \"date\": \"2014-10-19\",\n" +
                                    "                    \"info\": {\n" +
                                    "                        \"dawn\": [\n" +
                                    "                            \"1\",\n" +
                                    "                            \"����\",\n" +
                                    "                            \"18\",\n" +
                                    "                            \"����\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ],\n" +
                                    "                        \"day\": [\n" +
                                    "                            \"1\",\n" +
                                    "                            \"����\",\n" +
                                    "                            \"27\",\n" +
                                    "                            \"����\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ],\n" +
                                    "                        \"night\": [\n" +
                                    "                            \"1\",\n" +
                                    "                            \"����\",\n" +
                                    "                            \"19\",\n" +
                                    "                            \"���Ϸ�\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ]\n" +
                                    "                    },\n" +
                                    "                    \"week\": \"��\",\n" +
                                    "                    \"nongli\": \"����إ��\"\n" +
                                    "                },\n" +
                                    "                {\n" +
                                    "                    \"date\": \"2014-10-20\",\n" +
                                    "                    \"info\": {\n" +
                                    "                        \"dawn\": [\n" +
                                    "                            \"1\",\n" +
                                    "                            \"����\",\n" +
                                    "                            \"19\",\n" +
                                    "                            \"���Ϸ�\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ],\n" +
                                    "                        \"day\": [\n" +
                                    "                            \"1\",\n" +
                                    "                            \"����\",\n" +
                                    "                            \"27\",\n" +
                                    "                            \"���Ϸ�\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ],\n" +
                                    "                        \"night\": [\n" +
                                    "                            \"2\",\n" +
                                    "                            \"��\",\n" +
                                    "                            \"18\",\n" +
                                    "                            \"�Ϸ�\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ]\n" +
                                    "                    },\n" +
                                    "                    \"week\": \"һ\",\n" +
                                    "                    \"nongli\": \"����إ��\"\n" +
                                    "                },\n" +
                                    "                {\n" +
                                    "                    \"date\": \"2014-10-21\",\n" +
                                    "                    \"info\": {\n" +
                                    "                        \"dawn\": [\n" +
                                    "                            \"2\",\n" +
                                    "                            \"��\",\n" +
                                    "                            \"18\",\n" +
                                    "                            \"�Ϸ�\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ],\n" +
                                    "                        \"day\": [\n" +
                                    "                            \"1\",\n" +
                                    "                            \"����\",\n" +
                                    "                            \"26\",\n" +
                                    "                            \"������\",\n" +
                                    "                            \"3-4 ��\"\n" +
                                    "                        ],\n" +
                                    "                        \"night\": [\n" +
                                    "                            \"2\",\n" +
                                    "                            \"��\",\n" +
                                    "                            \"17\",\n" +
                                    "                            \"\",\n" +
                                    "                            \"΢��\"\n" +
                                    "                        ]\n" +
                                    "                    },\n" +
                                    "                    \"week\": \"��\",\n" +
                                    "                    \"nongli\": \"����إ��\"\n" +
                                    "                }\n" +
                                    "            ],\n" +
                                    "            \"pm25\": {    /*PM2.5*/\n" +
                                    "                \"key\": \"Wenzhou\",\n" +
                                    "                \"show_desc\": 0,\n" +
                                    "                \"pm25\": {\n" +
                                    "                    \"curPm\": \"97\",\n" +
                                    "                    \"pm25\": \"72\",\n" +
                                    "                    \"pm10\": \"97\",\n" +
                                    "                    \"level\": 2,\n" +
                                    "                    \"quality\": \"��\",\n" +
                                    "                    \"des\": \"���Խ��ܵģ�����������ĳ����Ⱦ���ر����е������⣬�Թ��ڽ���û��Σ����\"\n" +
                                    "                },\n" +
                                    "                \"dateTime\": \"2014��10��15��09ʱ\",\n" +
                                    "                \"cityName\": \"����\"\n" +
                                    "            },\n" +
                                    "            \"date\": null,\n" +
                                    "            \"isForeign\": 0\n" +
                                    "        }\n" +
                                    "    },\n" +
                                    "    \"error_code\": 0\n" +
                                    "}", WeatherInfo.class);

                                store.put(weatherInfo);
                            return false;
                    }
                    return true;
                }
            })
            .subscribe(new Action1<WeatherInfo>() {
                @Override
                public void call(WeatherInfo item) {
                    store.put(item);
                }
            });
        return  store.query();
    }

  public interface GetWeather{
      @NonNull
      Observable<WeatherInfo> call();
  }
    /**
     * json 2 Object
     */
    private WeatherInfo ConvertData(String responseBody) {
        WeatherInfo weatherInfo =null;
        try {
            weatherInfo = new Gson().fromJson(responseBody, WeatherInfo.class);
            JSONObject jsonObject = new JSONObject(responseBody);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONObject data = result.getJSONObject("data");
            JSONObject pm25 = data.getJSONObject("pm25");
            JSONObject pm251 = pm25.getJSONObject("pm25");
            weatherInfo.result.data.pm25.pm = new PmInfo();
            weatherInfo.result.data.pm25.pm.pm25_=pm251.getString("pm25");
            weatherInfo.result.data.pm25.pm.curPm=pm251.getString("curPm");
            weatherInfo.result.data.pm25.pm.pm10=pm251.getString("pm10");
            weatherInfo.result.data.pm25.pm.level=pm251.getLong("level");
            weatherInfo.result.data.pm25.pm.quality=pm251.getString("quality");
            weatherInfo.result.data.pm25.pm.des=pm251.getString("des");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weatherInfo;
    }

}
