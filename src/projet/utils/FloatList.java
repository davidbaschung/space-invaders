package projet.utils;

public class FloatList {

	public static float getMax(float[] list) {
		float max = 0;

		for (int i=0; i<list.length; i++) {
			if (list[i] > max) {
				max = list[i];
			}
		}
		return max;
	}

	public static float getMin(float[] list) {
		float min = Integer.MAX_VALUE;
		for (int i=0; i<list.length; i++) {
			if (list[i] < min) {
				min = list[i];
			}
		}
		return min;
	}
}
