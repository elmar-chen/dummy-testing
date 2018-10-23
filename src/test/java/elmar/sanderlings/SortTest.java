package elmar.sanderlings;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.List;
import java.util.zip.CRC32;

import org.junit.Test;
import org.junit.runner.RunWith;

import elmar.sanderlings.DummyRunner;

@RunWith(DummyRunner.class)
public class SortTest {

	@Test
	public void testSort() {
		CRC32 crc32 =  new CRC32();
		crc32.update(new byte[] {0x49, 0x48, 0x44, 0x52, 0x00, 0x00, 0x02, (byte) 0xea, 0x00, 0x00, 0x00, (byte) 0xcf, 0x08, 0x02, 0x00, 0x00, 0x00});
		System.out.printf("%x",crc32.getValue());
		List<Integer> list = Arrays.asList(1,4,5,2);
		Sort.sort(list);
		assertArrayEquals(new Integer[] {1,2,4,5}, list.toArray());
	}

}
