package projet.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ItemList { //TODO use it more on arrays

	public static void add(Object[] list, Object o) throws FullArrayException{
		for (int i=0; i<list.length; i++)
			if (list[i]==null) {
				list[i] = o;
				return;
			}
		throw new FullArrayException();
	}

	public static int getSize(Object[] list) {
		int size = 0;
		for (int i=0; i<list.length; i++) {
			if (list[i]!=null)
				size++;
			else break;
		}
		return size;
	}

	public static boolean isEmpty(Object[] list) {
		for (int i=0; i<list.length; i++) {
			if (list[i]!=null)
				return false;
		}
		return true;
	}

	public static int findIndex(Object[] list, Object o) {
		for (int i=0; i<list.length; i++)
			if (o == list[i])
				return i;
		return -1;
	}

	public static void remove(Object[] list, Object o) throws EmptyArrayException {
		int index = findIndex(list,o);
		remove(list,index);
	}
	public static void remove(Object[] list, int index) throws EmptyArrayException  {
		try {
			if (isEmpty(list)) throw new EmptyArrayException();

			if (index != -1) {
				list[index] = null;
				for (int i = index; i < list.length - 1; i++) {
					list[i] = list[i + 1];
					list[i + 1] = null;
//					Method method = list.getClass().getMethod("finalize");
//					method.invoke(list[index]);
				}
			}
		} catch (EmptyArrayException /*|  NoSuchMethodException | IllegalAccessException | InvocationTargetException*/ e) {

		}
	}

	public static void addAll(Object[] list, Object... o) {
		for (int i=0; i<o.length; i++) {
			try {
				add(list,o[i]);
			} catch (FullArrayException e) {
				e.printStackTrace();
			}
		}
	}

	public static class EmptyArrayException extends Exception {
		public EmptyArrayException() {
			super();
		}
	}

	private static class FullArrayException extends Exception {
		public FullArrayException() {
			super();
		}
	}
}
