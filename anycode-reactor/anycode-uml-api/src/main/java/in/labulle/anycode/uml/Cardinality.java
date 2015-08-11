package in.labulle.anycode.uml;

/**
 * UML Attribute's cardinality.
 *
 * @author Jose Carreno
 */
public enum Cardinality {
    /**
     * Zero to one.
     */
    ZERO_TO_ONE("0","1", true),
    /**
     * One to one.
     */
    ONE_TO_ONE("1","1", false),
    /**
     * Zero to many.
     */
    ZERO_TO_MANY("0","*", true),

    /**
     * One to many.
     */
    ONE_TO_MANY("1","*", false),

    /**
     * Many to many
     */
    MANY_TO_MANY("*", "*", false),

    /**
     * Any to many
     */
    ANY_TO_MANY("*", "*", true);


    private String lower;

    private String upper;

    private boolean optional;

    private static final String MANY = "*";

    private Cardinality(final String lower, final String upper, final boolean optional) {
        this.lower = lower;
        this.upper = upper;
        this.optional = optional;
    }

    /**
     * Checks whether cardinality's maximum is one.
     *
     * @return true if cardinality is "zero to one" or "one to one".
     */
    public boolean isSingle() {
        return this.equals(ZERO_TO_ONE) || this.equals(ONE_TO_ONE);
    }

    /**
     * Checks whether cardinality's minimum is zero
     *
     * @return true if cardinality is "zero to one" or "zero to many".
     */
    public boolean isOptional() {
        return this.optional;
    }

    public static Cardinality fromRange(String lowerRange, String upperRange) {
        assertValidRange(lowerRange, upperRange);
        String l = "1";
        String u = "1";
        if (!isEmpty(lowerRange)) {
            l = lowerRange;
        }
        if (!isEmpty(upperRange)) {
            u = upperRange;
        }
        boolean optional = false;
        try {
            if(Integer.parseInt(l) > 1) {
                l = MANY;
                optional = false;
            }
        } catch(Exception e) {
            optional = true;
        }
        try {
            if(Integer.parseInt(u) > 1) {
                u = MANY;
            }
        } catch(Exception e) {

        }



        for (Cardinality c : Cardinality.values()) {
            String card = Cardinality.getRangeAsString(l,u);
            if (c.getAsString().equals(card)) {
                if(MANY_TO_MANY.equals(c) && optional) {
                    return ANY_TO_MANY;
                }
                if(ANY_TO_MANY.equals(c) && !optional) {
                    return MANY_TO_MANY;
                }
                return c;
            }
        }

        return Cardinality.MANY_TO_MANY;
    }

    private static String getRangeAsString(String lower, String upper) {
        return lower + ".." + upper;
    }

    public String getAsString() {
        return Cardinality.getRangeAsString(this.lower, this.upper);
    }

    public Integer getLowerValue() {
        try {
            return Integer.parseInt(this.lower);
        } catch(Exception e) {
            return null;
        }
    }

    public Integer getUpperValue() {
        try {
            return Integer.parseInt(this.lower);
        } catch(Exception e) {
            return null;
        }
    }


    private static void assertValidRange(final String lower, final String upper) {
        assertValid(lower);
        assertValid(upper);
    }

    private static void assertValid(final String token) {
        if(!isEmpty(token)) {
            if(!isMany(token)) {
                try {
                    Integer i = Integer.parseInt(token);
                    if (i < 0) {
                        throw new RuntimeException("Invalid cardinality value");
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Invalid cardinality value :" + token);
                }
            }
        }
    }


    private static boolean isEmpty(final String token) {
        return token == null || "".equals(token);
    }

    private static boolean isMany(final String token) {
        return MANY.equals(token);
    }

    private static boolean isNumber(final String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch(Exception e) {
            return false;
        }
    }


}
