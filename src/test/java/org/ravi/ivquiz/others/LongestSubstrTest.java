package org.ravi.ivquiz.others;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

// This long input expected 17 chars. I got 15 only.
public class LongestSubstrTest {
    private final String longInput = "ofjplintgipshpufvxcmzwgcmaqzimksovoswoambntntwpnuvnwvcbwolubjzsmfnnmficrmdfoprkugrlijqlavpuzyejlkqncgdtfvfleoyoidzwgazibrbfvcmxecthlqsiaoklmxrfetovejrdvvhhulxeehilulevgbdcrvtevyfevmkstodluwhvjvmcwlttkrcvsbekbzmcislifqydydjdjwxufdnvdbqrcpicbdbfnjqniypzwuggocepqpngaqtiyxpxucyxrbtmvaexsokkutlnevadnocrnfjhvagslcsrhznxrnkowizbyebelpkmscbkylacbxkmqyluueihzdlgmiimmoajyhlboylnpgpitblfvcdwnoyolbfvawtxfulwxkhtxdcxppydripqyvsnhtbagezfngxssfmfgjgudneeqrcnjcwfgswplsbmnmqofcbvtbofdzvpisbaprldtdxsqisbfiylfuiapqqconwjojidllqnzaxihedoivenjhfzuzkgrwfkqlzzfrhlplkpomzkjipwbbbhdupciuegvxhcnzvrsbfpmsrjmipvbaiiiejhfjztzsamfpfnmafcqqysceygxuztjtewrifguqvrquevgvqxycxfielransjhnelsvcwspeahttossbesuvrzzwuoprvenkwbincrbfkmkfhscgcbfvslcaafxjjzhrxusnpstbzjwxesktuiceolsgulawulxjjxwceztgifqqyddcmcuhhcyvmractjauggeeizkqwwsfmlyeafgujkxbcedqerikoxrqrbcgiridiymnhacvftqhwvdogqfrslabrcwnriduqdvllarugupgifklbgpxzuahyofagebmfluwvsxyzpyvldvdfxeolaijjsnwbwogyravlqxrqzwuddlaehsosrivvckzlxcnolputtdwqddaaosezptatfjfvfnhuvyaheuukw";

    int uniqueChars(String s) {
        if ((s == null) || (s.length() == 0)) {
            return 0;
        }

        int max = 0;
        int counter = 0;
        Set<Character> set = new HashSet<>();
        String str = "";

        for (char ch : s.toCharArray()) {
            if (!set.contains(ch)) {
                counter++;
                set.add(ch);
                str += ch;
            } else {
                System.out.printf("existing-char=%c, counter=%d, max=%d, str=[%s].%d%n", ch, counter, max, str, str.length());
                if (counter >= max) {
                    max = counter;
                }
                set.clear();
                counter = 1;
                set.add(ch);
                str = "" + ch;
            }
        }

        return max;
    }

    @Test
    public void longInputString() {
        int c = uniqueChars(longInput);
        System.out.printf("longest-string=%d \n", c);
    }
}
