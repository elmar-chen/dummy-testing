package elmar.sanderlings;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CompositeIterator implements ResultSet {

	private List<CompositeIterator> components;

	public CompositeIterator(List<CompositeIterator> components) {
		this.components = components;
	}

//	@Override
//	public boolean hasNext() {
//		for (CompositeIterator compositeIterator : components) {
//			if(compositeIterator.hasNext())return true;
//		}
//		return false;
//	}
//
//	@Override
//	public TestProperty next() {
//		int i=components.size()-1;
//		for(;i>=0;i--) {
//			CompositeIterator iter = components.get(i);
//			if(iter.hasNext()) {
//				iter.next();
//				break;
//			}
//			else {
//				iter.reset();
//			}
//		}
//		return getCurrentItem();
//	}
//	
//
//	private TestProperty getCurrentItem() {
//		return null;
//	}
//
//	public abstract void reset();

	public static void main(String[] args) {
		List<List<Integer>> listOfList = new ArrayList<>();
		listOfList.add(Arrays.asList(11, 12, 13));
		listOfList.add(Arrays.asList(21, 22, 23));
		listOfList.add(Arrays.asList(31, 32, 33));

		int[] indecies = new int[listOfList.size()];
		while (true) {
			
			int i=indecies.length-1;
			for(;i>=0;i--) {
				if(indecies[i]<listOfList.get(i).size()-1) {
					indecies[i]++;
					break;
				}else {
					indecies[i]=0;
				}
			}
			if(i==0 && indecies[0]==0) {
				break;
			}
			for(int j=0;j<indecies.length;j++) {
				System.out.print(listOfList.get(j).get(indecies[j])+"\t");
			}
			System.out.println();
		}

	}
}
