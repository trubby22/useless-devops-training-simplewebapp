package ic.doc;

public class QueryProcessor {

    public String process(String query) {
        StringBuilder results = new StringBuilder();
        if (query.toLowerCase().contains("shakespeare")) {
            results.append("William Shakespeare (26 April 1564 - 23 April 1616) was an\n" +
                           "English poet, playwright, and actor, widely regarded as the greatest\n" +
                           "writer in the English language and the world's pre-eminent dramatist. \n");
            results.append(System.lineSeparator());
        }

        if (query.toLowerCase().contains("asimov")) {
            results.append("Isaac Asimov (2 January 1920 - 6 April 1992) was an\n" +
                           "American writer and professor of Biochemistry, famous for\n" +
                           "his works of hard science fiction and popular science. \n");
            results.append(System.lineSeparator());
        }

        if (query.toLowerCase().contains("newton")) {
          results.append("Sir Isaac Newton PRS (25 December 1642 - 20 March 1727) \nwas an English mathematician, physicist, astronomer, alchemist, theologian, and author \n(described in his time as a \"natural philosopher\") \nwidely recognised as one of the greatest mathematicians and physicists of all time \nand among the most influential scientists.\n");
          results.append(System.lineSeparator());
        }
        return results.toString();
    }
}
