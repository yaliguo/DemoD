package pojo;import com.litesuits.orm.db.annotation.Table;import java.util.List;public class WeatherInfo{	public String reason;	public Result result;	public long error_code;	public class Result{		public Data data;	}	public class Data{		public Realtime realtime;		public long isForeign;		public Life life;		public Pm25 pm25;		public List<Weather> weather;	}	public class Pm25{		public PmInfo pm ;		public String key;		public long show_desc;		public String cityName;		public String dateTime;	}	public class Life{		public String date;		public Info info;		class Info{			public List<String> day;			public List<String> night;			public List<String> dawn;		}	}	public class Realtime{		public String city_name;		public long week;		public String city_code;		public String date;		public String moon;		public Wind wind;		public String time;		public Weather weather;		public long dataUptime;		public class Weather{			public String temperature;			public String img;			public String humidity;			public String info;			public class Info{				public List<String> wuran;				public List<String> xiche;				public List<String> ganmao;				public List<String> kongtiao;				public List<String> yundong;				public List<String> chuanyi;				public List<String> ziwaixian;			}		}	}	public class Weather{		public String week;		public Info info;		public String nongli;		public String date;		public class Info{			public List<String> day;			public List<String> night;		}	}	public class Wind{		public Object windspeed;		public String power;		public String direct;		public Object offset;	}}