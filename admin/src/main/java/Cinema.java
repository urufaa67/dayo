

public class Cinema {
	int titleId;
	String title;
	String imgpath;
	String moviepath;
	String date;
	int screenId;
	String time1;
	String time2;
	String time3;
	String time4;
	String time5;
	String time6;
	String description;
	//コンストラクター
	Cinema(int titleId, String title, String imgpath, String moviepath, String date, int screenId, String time1, String time2, String time3, String time4, String time5, String time6, String description) {
		this.titleId = titleId;
		this.title = title;
		this.imgpath = imgpath;
		this.moviepath = moviepath;
		this.date = date;
		this.screenId = screenId;
		this.time1 = time1;
		this.time2 = time2;
		this.time3 = time3;
		this.time4 = time4;
		this.time5 = time5;
		this.time6 = time6;
		this.description = description;
	}

	public String toJson() {
		return "{"
				+ "\"titleId\":" + titleId + ","
				+ "\"title\":\"" + title + "\","
				+ "\"imgpath\":\"" + imgpath + "\","
				+ "\"moviepath\":\"" + moviepath + "\","
				+ "\"date\":\"" + date + "\","
				+ "\"screenId\":\"" + screenId + "\","
				+ "\"time1\":\"" + time1 + "\","
				+ "\"time2\":\"" + time2 + "\","
				+ "\"time3\":\"" + time3 + "\","
				+ "\"time4\":\"" + time4 + "\","
				+ "\"time5\":\"" + time5 + "\","
				+ "\"time6\":\"" + time6 + "\","
				+ "\"description\":\"" + description + "\""
				+ "}";
	}
}