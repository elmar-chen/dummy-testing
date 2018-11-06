package elmar.sanderlings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.List;
//
public abstract class CompositeIterator{// {
//
//	private List<CompositeIterator<T>> components;
//	
//	private T[] currentItems;
//	
//	public CompositeIterator(List<CompositeIterator<T>> components) {
//		this.components = components;
//		init();
//	}
//
//	@Override
//	public boolean hasNext() {
//		return currentItems!=null;
//	}
//
//	@Override
//	public List<T> next() {
//		prepareNext();
//		return currentItems;
//	}
//	
//	private void init() {
//		List<T> current = new ArrayList<T>();
//		for (CompositeIterator<T> compositeIterator : components) {
//			if(compositeIterator.hasNext()) {
//				current.addAll(compositeIterator.next());
//			} else {
//				current = null;
//				break;
//			}
//		}
//		this.currentItems = current;
//	}
//	private void prepareNext() {
//		int i=components.size()-1;
//		for(;i>=0;i--) {
//			CompositeIterator iter = components.get(i);
//			if(iter.hasNext()) {
//				currentItems. iter.next();
//				break;
//			}
//			else {
//				iter.reset();
//			}
//		}
//		return getCurrentItem();
//	}
////
////	private TestProperty getCurrentItem() {
////		return null;
////	}
////
////	public abstract void reset();
//
	public static void main(String[] args) {
		while(true) {
			for()
			
		}
	}
}
