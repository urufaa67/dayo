

import java.util.List;
import java.util.stream.Collectors;

public class EditData {
	List<Cinema> editer;

	EditData(List<Cinema> editerxxx) {
		this.editer = editerxxx;
	}

	public String toJson() {
		String editJson = this.editer.stream()
				.map(Cinema::toJson)
				.collect(Collectors.joining(","));

		return "{\"editer\":[" + editJson + "]}";
	}
}