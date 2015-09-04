package com.amplero.interview;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public interface WordScramble {
    /* Vowels and Vowel combinations. */
    public enum VowelCombination {
        A("A"), E("E"), I("I"), O("O"), U("U"), Y("Y"),
        AI("AI"), AY("AY"), EA("EA"), EE("EE"), EO("EO"), IO("IO"), OA("OA"), OO("OO"), OY("OY"), YA("YA"), YO("YO"), YU("YU");

        private final String vowelConsonant;
        private VowelCombination(final String vowelConsonant) {
            this.vowelConsonant = vowelConsonant;
        }

        public String getValue() {
            return vowelConsonant;
        }

        private static final Map<String, VowelCombination> lookup = new HashMap<String, VowelCombination>();
        static {
            for (VowelCombination vc : EnumSet.allOf(VowelCombination.class))
                lookup.put(vc.getValue(), vc);
        }
        public static VowelCombination get(final String val) {
            return lookup.get(val);
        }
    }

    /* Consonants and consonant combinations */
    public enum ConsonantCombination {
        B("B"), C("C"), D("D"), F("F"), G("G"), H("H"), J("J"), K("K"), L("L"), M("M"),
        N("N"), P("P"), Q("Q"), R("R"), S("S"), T("T"), V("V"), W("W"), X("X"), Z("Z"),

        BB("BB"), CC("CC"), DD("DD"), FF("FF"), GG("GG"), HH("HH"), JJ("JJ"), KK("KK"),
        LL("LL"), MM("MM"), NN("NN"), PP("PP"), QQ("QQ"), RR("RR"), SS("SS"), TT("TT"),
        VV("VV"), WW("WW"), XX("XX"), YY("YY"), ZZ("ZZ"),

        BL("BL"), BR("BR"), CH("CH"), CK("CK"), CL("CL"), CR("CR"), DR("DR"),
        FL("FL"), FR("FR"), GH("GH"), GL("GL"), GR("GR"), KL("KL"), KR("KL"), KW("KW"),
        PF("PF"), PL("PL"), PR("PR"), SC("SC"),  SH("SH"),
        SK("SK"), SL("SL"), SM("SM"), SN("SN"), SP("SP"), SQ("SQ"), ST("ST"), SW("SW"),
        TH("TH"), TR("TR"), TW("TW"), WH("WH"), WR("WR"),

        SCH("SCH"), SCR("SCR"),SHR("SHR"),THR("THR")

        ;

        private final String doubleConsonant;
        private ConsonantCombination(final String doubleConsonant) {
            this.doubleConsonant = doubleConsonant;
        }

        public String getValue() {
            return doubleConsonant;
        }

        private static final Map<String, ConsonantCombination> lookup = new HashMap<String, ConsonantCombination>();
        static {
            for (ConsonantCombination dc : EnumSet.allOf(ConsonantCombination.class))
                lookup.put(dc.getValue(), dc);
        }
        public static ConsonantCombination get(final String val) {
            return lookup.get(val);
        }
    }

    /**
     *  Score the difficulty of any particular scrambling of a word.
     */
    boolean score(String scrambledWord, String unscrambledWord);

}
