// BackwardChaining.java
import java.util.*;

public class BackwardChaining {
    static class Rule { List<String> ifs; String then;
        Rule(List<String> a,String c){ifs=a; then=c;}
    }
    List<Rule> rules=new ArrayList<>();
    Set<String> facts=new HashSet<>();

    void addFact(String f){ facts.add(f); }
    void addRule(List<String>a,String c){ rules.add(new Rule(a,c)); }

    boolean prove(String goal){
        if(facts.contains(goal)) return true;
        for(Rule r:rules){
            if(r.then.equals(goal)){
                boolean all=true;
                for(String s:r.ifs){ if(!prove(s)) all=false; }
                if(all){ facts.add(goal); return true; }
            }
        }
        return false;
    }

    public static void main(String[] args){
        BackwardChaining bc=new BackwardChaining();
        bc.addFact("fever"); bc.addFact("cough");
        bc.addRule(Arrays.asList("fever","cough"),"flu");
        bc.addRule(Arrays.asList("flu"),"rest");
        System.out.println("Can we prove flu? "+bc.prove("flu"));
        System.out.println("Can we prove rest? "+bc.prove("rest"));
        System.out.println("Can we prove measles? "+bc.prove("measles"));
    }
}
